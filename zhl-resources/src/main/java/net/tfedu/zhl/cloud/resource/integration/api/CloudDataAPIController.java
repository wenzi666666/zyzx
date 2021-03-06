package net.tfedu.zhl.cloud.resource.integration.api;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.asset.entity.ZAsset;
import net.tfedu.zhl.cloud.resource.asset.service.ZAssetService;
import net.tfedu.zhl.cloud.resource.config.ResourceWebConfig;
import net.tfedu.zhl.cloud.resource.constant.ResourcePlatformWebConstant;
import net.tfedu.zhl.cloud.resource.downloadrescord.service.ResDownRecordService;
import net.tfedu.zhl.cloud.resource.integration.constant.JQueryEasyUIConstantt;
import net.tfedu.zhl.cloud.resource.integration.entity.CourseNode;
import net.tfedu.zhl.cloud.resource.integration.entity.ResultInfo;
import net.tfedu.zhl.cloud.resource.integration.util.BeanConvertUtil;
import net.tfedu.zhl.cloud.resource.integration.util.CloudHttpInterfaceUtil;
import net.tfedu.zhl.cloud.resource.integration.util.CloudIntegralParamCheckUtil;
import net.tfedu.zhl.cloud.resource.integration.util.ResourceFileConvertUtil;
import net.tfedu.zhl.cloud.resource.intergral.service.UserResourceIntergralService;
import net.tfedu.zhl.cloud.resource.navigation.service.TreeService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResPoolService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.impl.ResFormatServiceImpl;
import net.tfedu.zhl.cloud.resource.prepare.entity.ResourceSimpleInfo;
import net.tfedu.zhl.cloud.resource.prepare.service.JPrepareService;
import net.tfedu.zhl.cloud.resource.prepare.util.JPrepareConstant;
import net.tfedu.zhl.cloud.resource.resSearch.service.ResSearchService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DistrictRes;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResource;
import net.tfedu.zhl.cloud.resource.resourceList.service.DisResService;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.cloud.resource.user.teach.service.JTeachSyscourseService;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.Custom500Exception;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.sso.school.entity.JSchool;
import net.tfedu.zhl.sso.school.service.JSchoolService;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.entity.UserAreaInfo;
import net.tfedu.zhl.sso.user.service.JUserService;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 
 * 将 3.0中的接口cloudData_*.action 迁移到本类中
 * 
 * 
 * @author wangwr
 * @date 2017年8月3日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
public class CloudDataAPIController {

	/**
	 * tree 接口中类型区分：自建目录
	 */

	private static final int COURSE_NAVIGATION_TYPE = 0;
	/**
	 * tree 接口中类型区分：系统目录
	 */
	private static final int SYSCOURSE_NAVIGATION_TYPE = 1;

	/**
	 * tree 接口中类型区分：知识点目录
	 */
	private static final int KONW_NAVIGATION_TYPE = 2;

	/**
	 * 0 为自建课程树 1 系统资源 2 共享资源 3校本资源 4 区本资源
	 */
	private static final int TYPE_FROMFLAG_ASSET = 0;
	private static final int TYPE_FROMFLAG_SHARED_RESOURCE = 2;
	private static final int TYPE_FROMFLAG_SYS_RESOURCE = 1;
	private static final int TYPE_FROMFLAG_DISTRICT_RESOURCE = 4;
	private static final int TYPE_FROMFLAG_SCHOOL_RESOURCE = 3;

	//
	public static final String ALL = "all";
	
	/**
	 * 上传资源的默认(系统)类型为素材
	 */
	private static final Integer DEFAULT_ASSET_TYPE = 1;

	@Resource
	JTeachSyscourseService jTeachSyscourseService;

	@Resource
	ZAssetService zAssetService;

	@Resource
	JUserService userService;
	@Resource
	RegisterService registerService;

	@Resource
	ResDownRecordService resDownRecordService;

	@Resource
	SysResourceService sysResourceService;

	@Resource
	DisResService disResService;

	@Resource
	JPrepareService jPrepareService;

	@Resource
	CommonWebConfig commonWebConfig;

	@Resource
	ResourceWebConfig resourceWebConfig;

	@Resource
	ResPoolService resPoolService;

	@Resource
	ResTypeService resTypeService;

	@Resource
	UserResourceIntergralService userResourceIntergralService;

	@Resource
	JSchoolService schoolService;

	@Resource
	TreeService sysCourseTreeService;

	@Resource
	ResSearchService resSearchService;
	
	@Resource
	ResFormatServiceImpl resFormatService;

	/**
	 * 获取（地区下）学校的上传统计信息
	 * 
	 * @return
	 */
	@RequestMapping("cloudData_areaUpload.action")
	@ResponseBody
	public ResultInfo areaUpload(HttpServletRequest request) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		if (result == null) {
			String schoolIds;
			Map<String, Object> info = null;
			try {
				schoolIds = ControllerHelper.getParameter(request, "schoolIds");
				info = zAssetService.statisticsSchoolUpload(schoolIds.split(","));
				result = ResultInfo.success(info);
			} catch (Exception e) {
				return ResultInfo.error();
			}

		}
		return result;
	}

	/**
	 * 更新用户的学段 学科
	 */
	@RequestMapping("cloudData_updateTermSubject.action")
	@ResponseBody
	public ResultInfo updateTermSubject(HttpServletRequest request) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		if (result == null) {
			try {

				String userName = ControllerHelper.getParameter(request, "userName");
				String termId = ControllerHelper.getParameter(request, "termId");
				String subjectIds = ControllerHelper.getParameter(request, "subjectIds");

				userService.updateUserTermSubject(userName, termId,
						StringUtils.isEmpty(subjectIds) ? null : subjectIds.split(","));

				result = ResultInfo.success();

			} catch (Exception e) {
				return ResultInfo.error();
			}

		}
		return result;
	}

	/**
	 * 获取tree 目錄
	 * 
	 * @param request
	 * @param id
	 *            為null時，獲取全部目錄，不為null時，獲取字節點
	 * @param type
	 *            區分目錄的來源：0 为自建课程树 1 系统目录树 2 为知识点
	 * @return
	 */
	@RequestMapping("cloudData_tree.action")
	@ResponseBody
	public ResultInfo tree(HttpServletRequest request, String id, String type) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		if (result == null) {

			try {

				id = ControllerHelper.checkEmpty(id);

				int typeInt = StringUtils.isEmpty(type) ? COURSE_NAVIGATION_TYPE
						: (ResourcePlatformWebConstant.COURSE.equals(type)) ? COURSE_NAVIGATION_TYPE
								: Integer.parseInt(type.trim());

				switch (typeInt) {

				case COURSE_NAVIGATION_TYPE:
					// 自建目录 ,对接时的自建目录取云平台的，所以这里的代码意义不大

					result = ResultInfo.success(JQueryEasyUIConstantt.getDefaultCourseNode());
					break;
				case SYSCOURSE_NAVIGATION_TYPE:
					// 系统目录
					result = ResultInfo.success();

					break;
				case KONW_NAVIGATION_TYPE:
					// 知识点目录
					result = ResultInfo.success();
					break;
				}

			} catch (Exception e) {
				result = ResultInfo.error();
			}
		}
		return result;
	}

	/**
	 * 从云平台获取自建目录
	 * 
	 * @return
	 * @throws JSONArray
	 */
	@RequestMapping("cloudData_cloudTree.action")
	@ResponseBody
	public ResultInfo cloudTree(HttpServletResponse response, HttpServletRequest request) throws Exception {
		String userName = ControllerHelper.getParameter(request, "userName");
		String cloudPlatFormLocal = ControllerHelper.getParameter(request, "cloudPlatFormLocal");
		String id = ControllerHelper.getOptionalParameter(request, "id");

		String currentCloudForm = cloudPlatFormLocal;

		ResultInfo result = null;

		try {

			String url = currentCloudForm + "/coludRssourseAjax_sendTree.action?userName=" + userName + "&id=" + id;

			List<CourseNode> ls = JSONArray.parseArray(HttpClientUtils.doGET(url), CourseNode.class);

			result = ResultInfo.success(ls);

		} catch (Exception e) {
			result = ResultInfo.error();
		}

		return result;
	}

	public Long getUserId(HttpServletRequest request) throws Exception {
		String userName = ControllerHelper.getParameter(request, "userName");

		SRegister reg = registerService.getRegister(userName);

		boolean isExist = reg != null;

		if (!isExist) {
			String cloudPlatFormLocal = ControllerHelper.getParameter(request, "cloudPlatFormLocal");

			if (StringUtils.isNotEmpty(cloudPlatFormLocal) && cloudPlatFormLocal.indexOf(",") >= 0) {
				cloudPlatFormLocal = cloudPlatFormLocal.split(",")[0];
			}
			return CloudHttpInterfaceUtil.getUserIdFromCloud(userName, cloudPlatFormLocal);

		} else {
			return reg.getId();
		}

	}

	/**
	 * 云平台删除自建资源
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cloudData_delAsset.action")
	@ResponseBody
	public ResultInfo delAsset(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String resId = request.getParameter("resId");

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);
		try {

			if (result == null) {

				ZAsset obj = new ZAsset();
				obj.setId(Long.parseLong(resId));
				obj.setFlag(true);
				zAssetService.update(obj);

				result = ResultInfo.success();
			}

		} catch (Exception e) {
			result = ResultInfo.error();
		}

		return result;
	}

	/**
	 * 增加下载记录和下载次数 //fromflag 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
	 * 
	 * @return
	 */
	@RequestMapping("cloudData_downRecord.action")
	@ResponseBody
	public ResultInfo downRecord(HttpServletRequest request, HttpServletResponse response, Long resId) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		// fromflag 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)

		try {
			int fromFlag = ControllerHelper.getOptionalIntegerParameter(request, "fromflag");
			long userId = ControllerHelper.getOptionalLongParameter(request, "userId");

			int record_fromFlag = 2;// 下载资源的来源 0 系统资源 1 自建资源 2 共享资源 3校本资源,4区本资源
			switch (fromFlag) {
			case 1:// 1 系统资源
				record_fromFlag = 0;
				break;
			case 2: // 2共享资源
				record_fromFlag = 2;
				break;
			case 3:
				record_fromFlag = 4;
				break;
			case 4:
				record_fromFlag = 3;
				break;
			default:// 0 自建资源
				record_fromFlag = 1;
				break;
			}

			resDownRecordService.addResourceDownloadRecordAndUpdateDownloadNumber(userId, resId, record_fromFlag);

			result = ResultInfo.success();

		} catch (Exception e) {
			result = ResultInfo.error();
		}

		return result;

	}

	/**
	 * 获取单个资源的下载链接，并下载
	 * 
	 * @param request
	 * @param response
	 * @param resId
	 * @param fromflag
	 *            //fromflag 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
	 * @return
	 */
	@RequestMapping("cloudData_downUrl.action")
	public String downUrl(HttpServletRequest request, HttpServletResponse response, Long resId, Integer fromflag)
			throws CustomException {

		// fromflag 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
		try {

			Long userId = ControllerHelper.getOptionalLongParameter(request, "userId");

			fromflag = null == fromflag ? 0 : fromflag;

			String url_path = "";
			String cloudPlatFormLocal = request.getParameter("cloudPlatFormLocal");
			String currentCloudForm = request.getParameter("currentCloudForm");
			String resServiceLocal = commonWebConfig.getResServiceLocal();
			String resService = commonWebConfig.getResService();
			String currentResPath = resServiceLocal;
			if (cloudPlatFormLocal != null && currentCloudForm != null && !cloudPlatFormLocal.equals(currentCloudForm)
					&& !cloudPlatFormLocal.contains(currentCloudForm)) {
				currentResPath = resService;
			}

			int fromflags_4_prepare;

			switch (fromflag.intValue()) {// fromflag 0 自建资源 1 系统资源 2共享资源 (3 区本
											// 4 校本资源 暂不支持)
			case 1:
				fromflags_4_prepare = JPrepareConstant.fromFlag_sysRes;
				break;
			case 3:
				fromflags_4_prepare = JPrepareConstant.fromFlag_districtRes;
				break;

			case 4:
				fromflags_4_prepare = JPrepareConstant.fromFlag_schoolRes;
				break;

			case 2:
				fromflags_4_prepare = JPrepareConstant.fromFlag_sharedRes;
				break;
			default:
				fromflags_4_prepare = JPrepareConstant.fromFlag_localRes;
				break;
			}

			List<ResourceSimpleInfo> list = jPrepareService.getResourceSimpleInfoForDownload(
					new String[] { String.valueOf(resId) }, new String[] { String.valueOf(fromflags_4_prepare) },
					userId);
			// 将原始的path重置为可用的web链接

			if (list != null && list.size() > 0) {

				JPrepareConstant.resetResourceDownLoadURLWeb(list, resServiceLocal, currentResPath);
				url_path = list.get(0).getPath();
				response.sendRedirect(url_path);
			} else {
				throw new Custom500Exception(ResourcePlatformWebConstant.RESOURCE_NOT_EXIST);
			}
		} catch (Exception e) {
			throw new Custom500Exception(e.getMessage());
		}
		return null;
	}

	/**
	 * 获取资源（批量）的下载文件及其加载链接AJAX 参数 fromflags 资源的来源（新资源中心的方式） 0 自建资源 1 系统资源 2共享资源
	 * 3 区本 4 校本资源 ids 资源的主键
	 * 
	 * 举例： cloudData_downUrlForZip.action?ids=1,2,3&fromflags=0,1,1
	 * 
	 * 返回值为json格式，包含了 成功或失败信息的“message”和资源路径信息集合“data”，"data"中每个资源路径信息包含了：
	 * isdwj: 是否多文件 path: （在数据库中）文件的相对路径，当是多文件的 系统资源时 应该是对应的main文件的路径 downUrl :
	 * 上述path的下载路径 当是多文件的 系统资源时 应该是空 imgPath: 缩略 图的相对路径（可选） imgUrl: 缩略
	 * 图的下载路径（可选） convertPath: 转换文件的路径（可选） ，当是多文件的 系统资源时 应该是对应的zip文件的路径
	 * convertUrl : 转换文件的下载路径（可选） 当是多文件的 系统资源时 应该是对应的zip文件的下载路径
	 * 
	 * 
	 * 注意： 多文件main文件的路径为 fPath.substring(0,
	 * fPath.indexOf(resCode))+File.separator+resCode+File.separator+fname
	 * 
	 */
	@RequestMapping("cloudData_downUrlForZip.action")
	@ResponseBody
	public ResultInfo downUrlForZip(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		// 获取参数传递的browser中的云平台当前地址和 云平台内网地址
		String cloudPlatFormLocal = request.getParameter("cloudPlatFormLocal");
		String currentCloudForm = request.getParameter("currentCloudForm");
		String resServiceLocal = commonWebConfig.getResServiceLocal();
		String resService = commonWebConfig.getResService();
		String currentResPath = resServiceLocal;
		if (cloudPlatFormLocal != null && currentCloudForm != null && !cloudPlatFormLocal.equals(currentCloudForm)
				&& !cloudPlatFormLocal.contains(currentCloudForm)) {
			currentResPath = resService;
		}

		try {

			if (result == null) {
				String ids = ControllerHelper.getParameter(request, "ids");
				String fromflags = ControllerHelper.getParameter(request, "fromflags");
				String[] ids_array = ids.split(",");
				String[] fromflags_array = fromflags.split(",");

				if (ids_array.length == fromflags_array.length) {
					for (int i = 0; i < fromflags_array.length; i++) {
						int from_flag = Integer.parseInt(fromflags_array[i]);
						long resId = Long.parseLong(ids_array[i]);
						int isdwj = 0;
						int isnet = 0;

						String path = "";
						String downUrl = "";

						String imgPath = "";
						String imgUrl = "";
						String convertPath = "";
						String convertUrl = "";
						String md5Code = "";

						String title = "";

						HashMap<String, Object> resMap = new HashMap<String, Object>();

						switch (from_flag) {
						// 自建资源 共享资源 id一致
						case 0:
						case 2:
							ZAsset rs = (ZAsset) zAssetService.getByPrimaryKey(resId).getData();

							if (rs != null && rs.getId() > 0) {

								title = rs.getName();

								boolean isLocal = 1 == rs.getIslocal();
								String _path = rs.getAssetpath();
								if (isLocal) {// isLocal是否是本地资源 0为本地资源1为网络资源
									isnet = 1;
									path = _path;
								} else {
									// 获取原始文件
									path = _path;

									downUrl = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, _path);
									// 获取转码文件
									if (ResourceFileConvertUtil.isNeedConvert(_path)) {
										convertPath = ResourceFileConvertUtil.convertType(_path);
										String info = ZhlResourceCenterWrap.GetFileInfo(resServiceLocal, convertPath);
										if (info != null && info.length() > 0) {
											convertUrl = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, convertPath);
										}
									}
									// 获取缩略图
									imgPath = _path.substring(0, _path.lastIndexOf("."))
											+ ZhlResourceCenterWrap.THUMBNAILS_IMG_TYPE;
									String info = ZhlResourceCenterWrap.GetFileInfo(resServiceLocal, imgPath);
									if (info != null && info.length() > 0) {
										imgUrl = ZhlResourceCenterWrap.getWebThumbnail(resServiceLocal, imgPath);
									} else {
										imgPath = "";
									}

								}
							}
							break;
						case 1:

							SysResource sys = (SysResource) sysResourceService.getByPrimaryKey(resId).getData();
							if (null != sys && sys.getId() > 0) {
								title = sys.getTitle();
								// int sys_fromflag = sys.getFromFlag();
								isdwj = sys.getIsdwj() ? 1 : 0;
								// int diskOrder = 1;// rs.getInt("diskOrder");
								String resCode = sys.getRescode();
								String fname = sys.getFname();
								String fPath = sys.getFpath();
								String _path = fPath + File.separator + fname;
								String fileExt = ResourceFileConvertUtil.FILE_PATTERN_PDF;
								String flag = fname.substring(fname.indexOf("."), fname.length());

								// 获取系统资源的缩略图
								imgPath = _path.substring(0, _path.lastIndexOf("."))
										+ ZhlResourceCenterWrap.THUMBNAILS_IMG_TYPE;
								String info = ZhlResourceCenterWrap.GetFileInfo(resServiceLocal, imgPath);
								if (info != null && info.length() > 0) {
									imgUrl = ZhlResourceCenterWrap.getWebThumbnail(resServiceLocal, imgPath);
								} else {
									imgPath = "";
								}

								/**
								 * 多文件时 path: main文件的路径 downUrl : 多文件 应该是空
								 * imgPath: 缩略 图的相对路径（可选） imgUrl: 缩略 图的下载路径（可选）
								 * 
								 * 
								 */
								if (isdwj == 1) {
									// 离线使用情况下：main文件的路径

									path = (fPath.indexOf(resCode) > 0 ? (fPath.substring(0, fPath.indexOf(resCode)))
											: fPath) + File.separator + resCode + File.separator + fname;

									// convertPath: 多文件时 应该是对应的zip文件的路径
									convertPath = (fPath.indexOf(resCode) > 0
											? (fPath.substring(0, fPath.indexOf(resCode))) : fPath) + File.separator
											+ resCode + ResourceFileConvertUtil.ZIP_SUFFIX;

									// convertUrl : 多文件时 应该是对应的zip文件的下载路径
									convertUrl = ZhlResourceCenterWrap.getMutipleResourceZipURL(resServiceLocal,
											resCode);
								} else {

									// 格式转换的
									if (fileExt.indexOf(flag) != -1) {
										path = _path;
										downUrl = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, _path);
										convertPath = fPath + File.separator + fname.substring(0, fname.indexOf("."))
												+ ResourceFileConvertUtil.PDF_SUFFIX;
										if (!_path.equals(convertPath)) {
											convertUrl = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, convertPath);
										}
										// 格式加密的
									} else if (ResourceFileConvertUtil.SWF_SUFFIX.indexOf(flag) >= 0
											|| ResourceFileConvertUtil.MP4_SUFFIX.indexOf(flag) >= 0) {
										path = fPath + File.separator
												+ fname.replace(ResourceFileConvertUtil.SWF_SUFFIX,
														ResourceFileConvertUtil.TFSWF_SUFFIX)
														.replace(ResourceFileConvertUtil.MP4_SUFFIX,
																ResourceFileConvertUtil.TFMP4_SUFFIX);
										downUrl = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, path);
									} else {
										path = _path;
										downUrl = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, path);
									}
								}
							}

							break;
						case 3:
						case 4:

							DistrictRes dis = (DistrictRes) disResService.get(resId).getData();
							if (dis != null && dis.getId() > 0) {
								title = dis.getTitle();
								String _path = dis.getFullpath();
								if (dis.getIslocal() == 1) {

									isnet = 1;
									path = _path;

								} else {
									// 获取原始文件
									path = _path;
									downUrl = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, _path);
									// 获取转码文件
									if (ResourceFileConvertUtil.isNeedConvert(_path)) {
										convertPath = ResourceFileConvertUtil.convertType(_path);
										String info = ZhlResourceCenterWrap.GetFileInfo(resServiceLocal, convertPath);
										if (info != null && info.length() > 0) {
											convertUrl = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, convertPath);
										}
									}
									// 获取缩略图
									imgPath = _path.substring(0, _path.lastIndexOf("."))
											+ ZhlResourceCenterWrap.THUMBNAILS_IMG_TYPE;
									String info = ZhlResourceCenterWrap.GetFileInfo(resServiceLocal, imgPath);
									if (info != null && info.length() > 0) {
										imgUrl = ZhlResourceCenterWrap.getWebThumbnail(resServiceLocal, imgPath);
									} else {
										imgPath = "";
									}
								}

							}

							break;

						}

						// //统一获取下载文件的md5
						if (isnet == 0 && path.trim().length() > 0) {
							String fileInfo = ZhlResourceCenterWrap.GetFileInfo(resServiceLocal,
									isdwj == 1 ? convertPath : path);
							if (fileInfo != null && fileInfo.length() > 0) {
								@SuppressWarnings("rawtypes")
								Map m = JSONObject.parseObject(fileInfo, HashMap.class);
								md5Code = m.get("MD5").toString();
							}
						}

						if (path.trim().length() > 0) {
							resMap.put("id", resId);
							resMap.put("title", title);
							resMap.put("isnet", isnet);
							resMap.put("isdwj", isdwj);
							resMap.put("path", path);
							resMap.put("md5Code", md5Code);
							resMap.put("fromflag", from_flag);

							resMap.put("downUrl", downUrl.replace(resServiceLocal, currentResPath));
							resMap.put("imgPath", imgPath);
							resMap.put("imgUrl", imgUrl.replace(resServiceLocal, currentResPath));
							resMap.put("convertPath", convertPath);
							resMap.put("convertUrl", convertUrl.replace(resServiceLocal, currentResPath));
							list.add(resMap);
						}

					}
				}

			}

			result = ResultInfo.success(list);

		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
		return result;
	}

	/**
	 * 获取上传路径
	 * 
	 * @return
	 */
	@RequestMapping("cloudData_getUpUrl.action")
	@ResponseBody
	public ResultInfo getUpUrl(HttpServletRequest request, HttpServletResponse response) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		try {
			if (null == result) {
				long userId = getUserId(request);
				long schoolId = 0;

				JUser user = userService.getUserById(userId);
				schoolId = user.getSchoolid();

				String hostLocal = commonWebConfig.getHostLocalOne();
				String resServiceLocal = commonWebConfig.getResServiceLocal();
				String currentResPath = commonWebConfig.getCurrentResPath(request);

				// 上传资源相关
				ZhlResourceCenterWrap.getUserUploadPath(userId, schoolId);

				String uploadPath = ZhlResourceCenterWrap.getUserUploadPath(userId, schoolId);
				// 获取上传文件路径
				String uploadUrl = ZhlResourceCenterWrap.getUploadUrlConvert(uploadPath, resServiceLocal,
						currentResPath, hostLocal, userId);

				HashMap<String, Object> root = new HashMap<String, Object>();
				root.put("uploadUrl", uploadUrl);
				root.put("uploadPath", uploadPath);
				result = ResultInfo.success(root);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultInfo.error();
		}

		return result;

	}

	/**
	 * 获取全部资源库
	 * 
	 * @return
	 */
	@RequestMapping("cloudData_pool.action")
	@ResponseBody
	public ResultInfo pool(HttpServletRequest request, HttpServletResponse response) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		try {
			if (null == result) {
				List<ResPool> list = resPoolService.getAllPools();

				result = ResultInfo.success(BeanConvertUtil.toListMapLowerCase(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultInfo.error();
		}

		return result;

	}

	/**
	 * 获取单个资源的信息
	 * 
	 * @return
	 */
	@RequestMapping("cloudData_resInfo.action")
	@ResponseBody
	public ResultInfo resInfo(HttpServletRequest request, HttpServletResponse response) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		try {
			if (null == result) {

				String resServiceLocal = commonWebConfig.getResServiceLocal();
				String currentResPath = commonWebConfig.getCurrentResPath(request);

				long subjectId = 0;

				// fromflag 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
				Integer fromflag = ControllerHelper.getIntParameter(request, "fromflag");
				Long resId = ControllerHelper.getLongParameter(request, "resId");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String, Object> info = null;
				String resPlayUrl = "";// 最终的播放地址

				switch (fromflag) {// 1 系统资源

				case 1:

					SysResource sys = (SysResource) sysResourceService.get(resId).getData();

					if (sys != null && sys.getId() > 0) {
						info = new HashMap<String, Object>();

						ResType type = (ResType) resTypeService.get(sys.getMtype()).getData();

						String title = sys.getTitle();
						int _fromflag = sys.getFromflag();
						String createTime = format.format(sys.getCreatedt());
						String typeName = type != null ? type.getMtype() : "";
						String fName = sys.getFname();// rs.getString("fName");
						String fPath = sys.getFpath();// rs.getString("fName");
						String fileExt = sys.getFileext(); // rs.getString("fileExt");
						String filePath = "port_view.action?fromflag=1&resId=" + resId;
						boolean isdwj = sys.getIsdwj();
						String assetPath = fPath + File.separatorChar + fName;

						info.put("title", title);
						info.put("fromflag", _fromflag);
						info.put("createTime", createTime);
						info.put("typeName", typeName);
						info.put("fileExt", fileExt);
						info.put("filePath", filePath);
						info.put("isdwj", isdwj);
						info.put("isfinished", 0);

						int typeId = sys.getMtype();
						String flag = fName.substring(fName.indexOf("."), fName.length());
						if (fName.toLowerCase().indexOf(ResourceFileConvertUtil.SWF_SUFFIX) > 0
								|| fName.toLowerCase().indexOf(ResourceFileConvertUtil.MP4_SUFFIX) > 0) {
							fName = fName
									.replace(ResourceFileConvertUtil.SWF_SUFFIX, ResourceFileConvertUtil.TFSWF_SUFFIX)
									.replace(ResourceFileConvertUtil.MP4_SUFFIX, ResourceFileConvertUtil.TFMP4_SUFFIX);
						}
						// 电子教辅资源播放
						if (_fromflag == 2 && typeId == 40 && ".html".equals(flag)) {
							resPlayUrl = ZhlResourceCenterWrap.GetEBookPlayerURL(fPath, resServiceLocal, 1);
						} else {

							String pdf_suffix = ResourceFileConvertUtil.FILE_PATTERN_PDF;
							if (pdf_suffix.indexOf(fileExt) != -1) {
								assetPath = fPath + File.separator + fName.substring(0, fName.indexOf("."))
										+ ResourceFileConvertUtil.PDF_SUFFIX;
							} else {
								assetPath = fPath + File.separator + fName;
							}
							resPlayUrl = ZhlResourceCenterWrap.getWebPlayUrl(resServiceLocal, assetPath, isdwj, 1);
						}

						info.put("assetPath", assetPath);
						info.put("fName", fName);

					}

					break;

				case 0:
				case 2:// 自建或共享资源

					ZAsset asset = (ZAsset) zAssetService.get(resId).getData();

					if (asset != null && asset.getId() > 0) {

						ResType type = (ResType) resTypeService.get(asset.getUnifytypeid()).getData();

						info = new HashMap<String, Object>();
						String title = asset.getName();// rs.getString("name");
						String assetPath = asset.getAssetpath();// rs.getString("assetPath");
						String typeName = (null != type ? type.getMtype() : ""); // rs.getString("type_name");
						String createTime = format.format(asset.getCreatetime());
						boolean isdwj = asset.getIscourseware();
						String filePath = "port_view.action?fromflag=0&resId=" + resId;

						info.put("title", title);
						info.put("createTime", createTime);
						info.put("typeName", typeName);
						info.put("filePath", filePath);
						info.put("assetPath", assetPath);
						info.put("isdwj", isdwj);
						info.put("isfinished", asset.getIsfinished());

						boolean isLocalFile = (1 == asset.getIslocal());
						if (isLocalFile) {// isLocalFile 是否为本地资源 false 为是 ，true
											// 为不是
							resPlayUrl = assetPath;
						} else {
							resPlayUrl = ResourceFileConvertUtil.isNeedConvert(assetPath)
									? ResourceFileConvertUtil.convertType(assetPath) : assetPath;
							resPlayUrl = ZhlResourceCenterWrap.getWebPlayUrl(resServiceLocal, resPlayUrl);
						}

					}

					break;
				case 3:
				case 4:

					DistrictRes dRes = (DistrictRes) disResService.get(resId).getData();

					String filePath = "/port_view.action?fromflag=" + fromflag + "&resId=" + resId;

					if (null != dRes && dRes.getId() > 0) {

						info = new HashMap<String, Object>();

						ResType type = (ResType) resTypeService.get(dRes.getMtype()).getData();

						info.put("title", dRes.getTitle());
						info.put("createTime", dRes.getCreatedt());
						info.put("assetPath", dRes.getFullpath());
						info.put("typeName", null != type ? type.getMtype() : "");
						info.put("fileExt", dRes.getFileext());
						info.put("filePath", filePath);
						info.put("isdwj", dRes.getIsdwj());
						info.put("isfinished", 0);
						info.put("fName", dRes.getFname());

					}
					break;

				}
				if (subjectId == 0) {

					// subjectId = resImpl.getSubjectIdForResource(resId,
					// fromflag);
				}
				if (info != null) {

					resPlayUrl = resPlayUrl.replace(resServiceLocal, currentResPath);

					info.put("subjectId", subjectId);
					info.put("resPlayUrl", resPlayUrl);
				}

				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				list.add(info);

				result = ResultInfo.success(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultInfo.error();
		}

		return result;

	}

	/**
	 * 用于判断用户的对当前资源是否需要消耗积分，并且积分是否充足
	 * 
	 * integralExpendFlag 是否需要消耗积分，true 是 （提示积分的消费），false 否
	 * 可直接下载或引用，当为false时，以下两个返回值为undefined
	 * 
	 * integralEnoughFlag 积分是否充足，true 是 （提示积分的消费），false 否 提示积分不足
	 * 
	 * userUseableIntegral 用户可用积分
	 * 
	 * 
	 * 
	 * 
	 * 
	 * userId 当前用户 sharedAssetId 共享资源id sharedAssetIntegral 共享资源积分
	 */
	@RequestMapping("cloudData_isUserIntegralEnough.action")
	@ResponseBody
	public ResultInfo isUserIntegralEnough(HttpServletRequest request, HttpServletResponse response) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		try {
			if (null == result) {

				String sharedAssetIds = ControllerHelper.getParameter(request, "sharedAssetIds");
				String sharedUserIds = ControllerHelper.getParameter(request, "sharedUserIds");
				String sharedAssetIntegrals = ControllerHelper.getParameter(request, "sharedAssetIntegrals");

				Long userId = ControllerHelper.getLongParameter(request, "userId");

				ResultJSON json = userResourceIntergralService.isUserIntegralEnough(userId, sharedAssetIds.split(","),
						sharedAssetIntegrals.split(","), sharedUserIds.split(","));

				result = ResultInfo.success(json.getData());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultInfo.error();
		}

		return result;

	}

	/**
	 * 用户积分消费： 增减双方积分 增加双方积分增减的记录 返回成功失敗信息
	 * 
	 * userId 当前用户 sharedAssetId 共享资源id sharedAssetIntegral 共享资源积分 sharedUserId
	 * 共享资源创建人
	 */
	@RequestMapping("cloudData_userIntegralExpend.action")
	@ResponseBody
	public ResultInfo userIntegralExpend(HttpServletRequest request, HttpServletResponse response) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		try {
			if (null == result) {
				String sharedAssetIds = ControllerHelper.getParameter(request, "sharedAssetIds");
				String sharedUserIds = ControllerHelper.getParameter(request, "sharedUserIds");
				String sharedAssetIntegrals = ControllerHelper.getParameter(request, "sharedAssetIntegrals");

				Long userId = ControllerHelper.getLongParameter(request, "userId");

				Boolean flag = userResourceIntergralService.userIntegralExpend(userId, sharedUserIds.split(","),
						sharedAssetIds.split(","), sharedAssetIntegrals.split(","));

				result = ResultInfo.success(flag);

			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultInfo.error();
		}

		return result;

	}

	/**
	 * 获取地区学校列表
	 * 
	 * @return
	 */
	@RequestMapping("cloudData_schoolList.action")
	@ResponseBody
	public ResultInfo schoolList(HttpServletRequest request, HttpServletResponse response) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		try {
			if (null == result) {
				Long userId = ControllerHelper.getLongParameter(request, "userId");

				UserAreaInfo info = userService.getUserAreaInfo(userId);
				List<JSchool> list = schoolService.getSchoolsByDistrictId(info.getDistrictId());

				result = ResultInfo.success(BeanConvertUtil.toListMapLowerCase(list));

			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultInfo.error();
		}

		return result;

	}

	/**
	 * 获取(系统、自建、区本、校本、共享)资源类型
	 * 
	 * @param request
	 * @param type
	 *            0 为自建课程树 1 系统资源 2 共享资源 3校本资源 4 区本资源
	 * @param isCollect
	 *            自建资源页面区分0:所有1:自建2:收藏3我的共享
	 * @param fromFlag
	 *            六大库（0-6） 0 为全部
	 * @param id
	 *            需要查询的自建目录节点
	 * @param tfcode
	 *            系统目录的code
	 * @param searchFlag
	 *            共享时searchFlag查询方式 1按教材目录查询 2按知识点目录查询
	 * @param mType
	 *            系统资源类型
	 * @return
	 */
	@RequestMapping("cloudData_resType.action")
	@ResponseBody
	public ResultInfo resType(HttpServletRequest request, Integer type, Integer isCollect, Integer fromFlag, Long id,
			String tfcode, Integer searchFlag, Long mType) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		try {
			if (null == result) {
				Long userId = getUserId(request);

				type = null == type ? 0 : type;
				id = null == id ? 0 : id;
				long courseId = id;

				List<Long> courseIds = new ArrayList<Long>();
				List<Long> list = new ArrayList<Long>();

				switch (type) {
				// 0 为自建资源
				case 0:
					courseIds.clear();
					if (courseId > 0) {
						list.add(courseId);
					}

					getCourse(courseIds, list, userId);

					resultList = resSearchService.getAssetFileTypeAndFormat(userId, isCollect, courseIds);

					break;
				// 1 系统资源
				case 1:
					List<String> syscourseCodes = new ArrayList<String>();

					syscourseCodes.clear();
					if (courseId == 0) {
						syscourseCodes = getUserSysCourseList(userId);
					} else {
						syscourseCodes = getTfCode(courseId);
					}

					// 获取资源库id
					Integer poolId = fromFlag == null ? null : fromFlag.intValue();

					resultList = resSearchService.getSysResourceTypeAndFormat(mType,
							resourceWebConfig.getExceptPoolIds(), poolId, syscourseCodes,
							resourceWebConfig.getSys_from());
					break;

				// 2 共享资源
				case 2:

					String knowlageId = ControllerHelper.getOptionalParameter(request, "knowlageId");

					// 共享时searchFlag查询方式 1按教材目录查询 2按知识点目录查询
					if (2 == searchFlag) {
						tfcode = knowlageId;
					}
					if (StringUtils.isEmpty(tfcode) || ALL.equals(tfcode)) {
						tfcode = jTeachSyscourseService.queryTeachingSelectTfcode(userId);
					}

					resultList = resSearchService.getOtherSharedAssetTypeAndFormat(userId, searchFlag, tfcode, mType);
					break;
				// 3校本资源 4 区本资源
				case 3:
				case 4:
					syscourseCodes = new ArrayList<String>();

					syscourseCodes.clear();
					if (courseId == 0) {
						syscourseCodes = getUserSysCourseList(userId);
					} else {
						syscourseCodes = getTfCode(courseId);
					}

					resultList = resSearchService.getDistrictResourceTypeAndFormat(userId, type, syscourseCodes, mType);
					break;
				default:
					break;

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultInfo.error();
		}

		result = ResultInfo.success(resultList);

		return result;

	}

	/**
	 * 获取(系统、自建、区本、校本、共享)资源列表
	 * 
	 * @param request
	 * @param type
	 *            0 为自建课程树 1 系统资源 2 共享资源 3校本资源 4 区本资源
	 * @param isCollect
	 *            自建资源页面区分0:所有1:自建2:收藏3我的共享
	 * @param fromFlag
	 *            六大库（0-6） 0 为全部
	 * @param id
	 *            需要查询的自建目录节点
	 * @param tfcode
	 *            系统目录的code
	 * @param searchFlag
	 *            共享时searchFlag查询方式 1按教材目录查询 2按知识点目录查询
	 * @param mTypeId
	 *            系统资源类型
	 * @param resPattern
	 *            系统资源类型 all、空 为全部
	 * @param resType
	 *            文件类型要求 0 或 all 或空 为 全部
	 * @return
	 */
	@RequestMapping("cloudData_resList.action")
	@ResponseBody
	public ResultInfo resList(HttpServletRequest request, Integer type, Integer isCollect, Integer fromFlag, Long id,
			String tfcode, Integer searchFlag, Long mTypeId, String resPattern, String resType, Integer curPage,
			Integer perPage) {

		if (StringUtils.isEmpty(resPattern) || ALL.equals(resPattern.trim())) {
			resPattern = "";
		}
		if (StringUtils.isEmpty(resType) || ALL.equals(resType.trim())) {
			mTypeId = 0L;
		} else {
			mTypeId = Long.parseLong(resType);
		}

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		try {
			Integer orderBy = ControllerHelper.getOptionalIntegerParameter(request, "orderBy");

			if (null == result) {
				Long userId = getUserId(request);

				type = null == type ? 0 : type;
				id = null == id ? 0 : id;
				long courseId = id;

				List<Long> courseIds = new ArrayList<Long>();
				List<Long> list = new ArrayList<Long>();

				curPage = ControllerHelper.getPage(request);
				perPage = ControllerHelper.getPageSize(request, 15);

				switch (type) {
				// 0 为自建资源
				case 0:
					courseIds.clear();
					if (courseId > 0) {
						list.add(courseId);
					}

					getCourse(courseIds, list, userId);

					resultList = resSearchService.queryAssetList(userId, isCollect, courseIds, curPage, perPage,
							mTypeId, resPattern);

					// 收藏的系统资源，取其缩略图
					resetCollectedSysResourceImage(resultList, request);
					break;
				// 1 系统资源
				case 1:

					List<String> syscourseCodes = new ArrayList<String>();

					syscourseCodes.clear();
					if (courseId == 0) {
						syscourseCodes = getUserSysCourseList(userId);
					} else {
						syscourseCodes = getTfCode(courseId);
					}

					// 获取资源库id
					Integer poolId = fromFlag == null ? null : fromFlag.intValue();

					resultList = resSearchService.querySysResourceList(mTypeId, resourceWebConfig.getExceptPoolIds(),
							poolId, syscourseCodes, resourceWebConfig.getSys_from(), curPage, perPage, resPattern,
							orderBy);

					// 增加是否收藏的判断
					resetIsCollected(userId, resultList);

					// 增加缩略图
					ressetSysResourceImagePath(resultList, request);

					break;

				// 2 共享资源
				case 2:

					String knowlageId = ControllerHelper.getOptionalParameter(request, "knowlageId");

					// 共享时searchFlag查询方式 1按教材目录查询 2按知识点目录查询
					if (2 == searchFlag) {
						tfcode = knowlageId;
					}
					if (StringUtils.isEmpty(tfcode) || ALL.equals(tfcode)) {
						tfcode = jTeachSyscourseService.queryTeachingSelectTfcode(userId);
					}

					resultList = resSearchService.querySharedAssetList(userId, mTypeId, searchFlag, tfcode, curPage,
							perPage, resPattern, orderBy);

					resetSharedAssetImage(resultList, request);

					break;
				// 3校本资源 4 区本资源
				case 3:
				case 4:
					syscourseCodes = new ArrayList<String>();

					syscourseCodes.clear();
					if (courseId == 0) {
						syscourseCodes = getUserSysCourseList(userId);
					} else {
						syscourseCodes = getTfCode(courseId);
					}

					resultList = resSearchService.queryDistrictResource(userId, mTypeId, type, tfcode, curPage, perPage,
							resPattern, orderBy);

					resetDistrictResImage(resultList, request);

					break;
				default:
					break;

				}

				result = ResultInfo.success(resultList);

			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultInfo.error();
		}

		return result;

	}

	/**
	 * 获取学案资源信息 批量获取资源的信息
	 * 
	 * @param request
	 * @param fromflags
	 *            0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
	 * @param ids
	 * @return
	 */
	@RequestMapping("cloudData_xueResInfo.action")
	@ResponseBody
	public ResultInfo xueResInfo(HttpServletRequest request, String fromflags, String ids) {

		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);

		List<Map<String, Object>> dataList = null;
		try {
			if (null == result) {
				Long userId = getUserId(request);

				String resServiceLocal = commonWebConfig.getResServiceLocal();
				String currentResPath = commonWebConfig.getCurrentResPath(request);

				List<Long> assetIdList = new ArrayList<Long>();
				List<Long> sysResourceIdList = new ArrayList<Long>();
				List<Long> districtResIdList = new ArrayList<Long>();

				if (StringUtils.isNotEmpty(ids) && StringUtils.isNotEmpty(fromflags)) {

					String[] idArray = ids.split(",");
					String[] fromflagArray = fromflags.split(",");

					if (idArray.length != fromflagArray.length) {
						return ResultInfo.error();
					}

					for (int i = 0; i < fromflagArray.length; i++) {

						String string = fromflagArray[i];// 0 自建资源 1 系统资源 2共享资源
															// (3 区本 4 校本资源
															// 暂不支持)

						switch (Integer.parseInt(string)) {
						case TYPE_FROMFLAG_ASSET:
						case TYPE_FROMFLAG_SHARED_RESOURCE:
							assetIdList.add(Long.parseLong(idArray[i]));
							break;

						case TYPE_FROMFLAG_SYS_RESOURCE:
							sysResourceIdList.add(Long.parseLong(idArray[i]));
							break;

						case TYPE_FROMFLAG_SCHOOL_RESOURCE:
						case TYPE_FROMFLAG_DISTRICT_RESOURCE:
							districtResIdList.add(Long.parseLong(idArray[i]));
							break;
						}

					}

					Long[] assetIds = new Long[assetIdList.size()];
					Long[] sysResourceIds = new Long[sysResourceIdList.size()];
					Long[] districtResIds = new Long[districtResIdList.size()];

					assetIdList.toArray(assetIds);
					sysResourceIdList.toArray(sysResourceIds);
					districtResIdList.toArray(districtResIds);

					dataList = resSearchService.queryBatchResourceInfo(assetIds, sysResourceIds, districtResIds);

					for (int i = 0; i < dataList.size(); i++) {
						Map<String, Object> res = (Map<String, Object>) dataList.get(i);
						String resType = (String) res.get("restype");
						String assetPath = (String) res.get("assetpath");
						String isfinished = null == res.get("isfinished") ? "" : res.get("isfinished").toString();// 上传之后的后台任务是否完成
						String asseturl = "";

						if ("sys_res".equals(resType)) {// 系统资源
							if ((Boolean) res.get("isdwj")) {// 如果是多文件
								String rescode = (String) res.get("rescode");
								String fpath = (String) res.get("fpath");
								asseturl = ZhlResourceCenterWrap.getMutipleResourceZipPath(rescode);
							} else if (assetPath.toLowerCase().indexOf(".swf") > 0
									|| assetPath.toLowerCase().indexOf(".mp4") > 0) {
								assetPath = assetPath.replace(".swf", ".tfswf").replace(".mp4", ".tfmp4");
								asseturl = assetPath;
							} else {
								asseturl = assetPath;
							}

							asseturl = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, asseturl);
							if (!resServiceLocal.equals(currentResPath)) {
								asseturl = asseturl.replace(resServiceLocal, currentResPath);
							}
						} else {

							asseturl = assetPath;
							asseturl = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, asseturl);
							if (!!resServiceLocal.equals(currentResPath)) {
								asseturl = asseturl.replace(resServiceLocal, currentResPath);
							}
						}
						res.put("assetpath", assetPath);
						res.put("asseturl", asseturl);
						res.put("isfinished", isfinished);
					}
				}

				result = ResultInfo.success(dataList);

			}
		} catch (Exception e) {
			e.printStackTrace();
			result = ResultInfo.error();
		}

		return result;

	}
	
	
	
	
	

	/**
	 * 
	 * 遍历查询某个教师自建目录下的所有节点
	 * 
	 * @param courseIds
	 *            用于收集所有节点
	 * @param list
	 * @param userId
	 * @param dbId
	 * @throws Exception
	 */
	private void getCourse(List<Long> courseIds, List<Long> list, long userId) throws Exception {
		// List<Long> alist = new ArrayList();

	}

	/**
	 * 将指定系统目录的全部节点的Id查询并放到集合（syscourseCodes）中
	 * 
	 * @param syscourseCodes
	 * @param syscourseId
	 */
	private List<String> getTfCode(Long syscourseId) {

		String tfcode = sysCourseTreeService.getTfCodeById(syscourseId);

		List<String> syscourseCodes = sysCourseTreeService.querySysChildren(tfcode);

		if (null != syscourseCodes) {
			syscourseCodes.add(tfcode);
		}

		return syscourseCodes;
	}

	/**
	 * 获取用户的默认教材
	 * 
	 * @param syscourseCodes
	 * @param userId
	 * @return
	 * @throws CustomException
	 */
	private List<String> getUserSysCourseList(Long userId) throws CustomException {
		String tfcode = jTeachSyscourseService.queryTeachingSelectTfcode(userId);

		CourseNode node = sysCourseTreeService.getTreeNodeByCode(tfcode);

		if (null != node) {

			return getTfCode(Long.parseLong(node.getId()));

		}

		List<String> ls = new ArrayList<String>();
		ls.add(tfcode);
		return ls;
	}

	/**
	 * 更新用户是否已经收藏系统资源
	 * 
	 * @param userId
	 * @param resultList
	 */
	private void resetIsCollected(Long userId, List<Map<String, Object>> resultList) {
		if (null != resultList && resultList.size() > 0) {
			for (Iterator<Map<String, Object>> iterator = resultList.iterator(); iterator.hasNext();) {
				Map<String, Object> map = iterator.next();
				Long id = (Long) map.get("id");
				Boolean flag = resSearchService.ifSysResourceCollected(userId, id);
				map.put("collected", flag);
			}

		}
	}

	/**
	 * 设置系统资源缩略图
	 * 
	 * @param resultList
	 */
	private void ressetSysResourceImagePath(List<Map<String, Object>> resultList, HttpServletRequest request) {

		if (null != resultList && resultList.size() > 0) {

			Map<String, Object> temp = null;

			String assetpath = null;

			String fname = null;

			String imgPath = null;

			String resServiceLocal = commonWebConfig.getResServiceLocal();

			String currentResSerivce = commonWebConfig.getCurrentResPath(request);

			boolean isNeeded = !resServiceLocal.equals(currentResSerivce);

			for (Iterator<Map<String, Object>> iterator = resultList.iterator(); iterator.hasNext();) {

				temp = iterator.next();

				fname = (String) temp.get("fname");

				assetpath = (String) temp.get("fpath") + File.separator + fname;

				imgPath = assetpath.substring(0, assetpath.lastIndexOf("."))
						+ ZhlResourceCenterWrap.THUMBNAILS_IMG_TYPE;
				imgPath = ZhlResourceCenterWrap.getWebThumbnail(resServiceLocal, imgPath);

				if (isNeeded) {
					imgPath = imgPath.replace(resServiceLocal, currentResSerivce);
				}

				temp.put("imgpath", imgPath);

			}

		}

	}

	/**
	 * 收藏的系统资源，取其缩略图
	 * 
	 * @param resultList
	 * @param request
	 */
	private void resetCollectedSysResourceImage(List<Map<String, Object>> resultList, HttpServletRequest request) {
		if (null != resultList && resultList.size() > 0) {

			Map<String, Object> temp = null;

			String assetpath = null;

			String fname = null;

			String imgPath = null;

			String resServiceLocal = commonWebConfig.getResServiceLocal();

			String currentResSerivce = commonWebConfig.getCurrentResPath(request);

			boolean isNeeded = !resServiceLocal.equals(currentResSerivce);

			for (Iterator<Map<String, Object>> iterator = resultList.iterator(); iterator.hasNext();) {
				temp = iterator.next();

				imgPath = "";

				long rescourseFrom = temp.get("rescoursefrom") instanceof Integer
						? (new Integer((Integer) temp.get("rescoursefrom")).longValue())
						: (Long) temp.get("rescoursefrom");

				if (0 == rescourseFrom) {// 收藏系统资源

					fname = (String) temp.get("fname");

					assetpath = (String) temp.get("fpath") + File.separator + fname;

					imgPath = assetpath.substring(0, assetpath.lastIndexOf("."))
							+ ZhlResourceCenterWrap.THUMBNAILS_IMG_TYPE;
					imgPath = ZhlResourceCenterWrap.getWebThumbnail(resServiceLocal, imgPath);

					if (isNeeded) {
						imgPath = imgPath.replace(resServiceLocal, currentResSerivce);
					}
				}

				temp.put("imgpath", imgPath);
			}

		}
	}

	/**
	 * 区校资源，补充缩略图
	 * 
	 * @param resultList
	 * @param request
	 */
	private void resetDistrictResImage(List<Map<String, Object>> resultList, HttpServletRequest request) {
		resetDifferentResourceWithPropertyName(resultList, request, "fullpath");
	}

	/**
	 * 共享资源，补充缩略图
	 * 
	 * @param resultList
	 * @param request
	 */
	private void resetSharedAssetImage(List<Map<String, Object>> resultList, HttpServletRequest request) {
		resetDifferentResourceWithPropertyName(resultList, request, "assetpath");
	}

	/**
	 * 设置资源的缩略图
	 * 
	 * @param resultList
	 * @param request
	 * @param fullPathName
	 *            指定资源的相对全路径
	 */
	private void resetDifferentResourceWithPropertyName(List<Map<String, Object>> resultList,
			HttpServletRequest request, String fullPathName) {
		if (null != resultList && resultList.size() > 0) {

			Map<String, Object> temp = null;

			String assetpath = null;

			String imgPath = null;

			String resServiceLocal = commonWebConfig.getResServiceLocal();

			String currentResSerivce = commonWebConfig.getCurrentResPath(request);

			boolean isNeeded = !resServiceLocal.equals(currentResSerivce);

			for (Iterator<Map<String, Object>> iterator = resultList.iterator(); iterator.hasNext();) {
				temp = iterator.next();

				assetpath = (String) temp.get(fullPathName);

				imgPath = null;

				//非网络资源
				if (!ControllerHelper.isNetResource(assetpath)) {
					imgPath = assetpath.substring(0, assetpath.lastIndexOf("."))
							+ ZhlResourceCenterWrap.THUMBNAILS_IMG_TYPE;

					if (ZhlResourceCenterWrap.isFileExist(resServiceLocal, imgPath)) {

						imgPath = ZhlResourceCenterWrap.getWebThumbnail(resServiceLocal, imgPath);

						if (isNeeded) {
							imgPath = imgPath.replace(resServiceLocal, currentResSerivce);
						}
					}
				}

				temp.put("imgpath", imgPath);
			}

		}
	}
	
	
	
	/**
	 * 云平台备课夹上传资源    改为自动保存
	 * 学案中的资源上传的支持      改为自动保存  返回json数据
	 * 支持批量
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cloudData_uploadAndSaveNew.action")
	@ResponseBody
	public ResultInfo uploadAndSaveNew(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String cloudPlatFormLocal =  request.getParameter("cloudPlatFormLocal");//对接使用云平台的内网地址
		String currentCloudForm =  request.getParameter("currentCloudForm");//对接使用云平台的地址
		cloudPlatFormLocal = cloudPlatFormLocal==null?"":cloudPlatFormLocal.trim();
		currentCloudForm = currentCloudForm==null?"":currentCloudForm.trim();
		
		ResultInfo result = CloudIntegralParamCheckUtil.checkCloudParams(request);
		if (null != result) {
			return result;
		}
		
		
		
		//自动保存
		try {
		
			
			Long userId = getUserId(request);
			long prepareId = ControllerHelper.getOptionalLongParameter(request, "prepareId");
			long subjectId = ControllerHelper.getOptionalLongParameter(request, "subjectId");
			long courseId = ControllerHelper.getOptionalLongParameter(request, "courseId");

			String str = ControllerHelper.getParameter(request, "str");
			String rsize = ControllerHelper.getParameter(request, "rsize");
			String rpath = ControllerHelper.getParameter(request, "rpath");
			String subjectName = ControllerHelper.getParameter(request, "subjectName");
			// 文件服务上传v1 新增字段 上传成功之后后续任务
			String taskTypes = ControllerHelper.getOptionalParameter(request, "taskTypes");
			if (taskTypes.endsWith(",")) {
				taskTypes = taskTypes.substring(0, taskTypes.length() - 1);
			}

			// 关键字
			String keyword = ControllerHelper.getOptionalParameter(request, "keyword");
			// 资源介绍为空
			String note = "";
			// 资源类型默认为素材
			long rtype = DEFAULT_ASSET_TYPE;

			
			String[] strs = str.split(";");
			String[] paths = rpath.split("[|]");
			String[] fileFormats = new String[paths.length];
			String[] rname = new String[rpath.length()];
			String[] fileTypes = new String[rpath.length()];
			String[] task_types = taskTypes.split(",");//文件服务上传v1 新增字段    上传成功之后后续任务

			long[] resIds = new long[rname.length];
			for (int i = 0; i < paths.length; i++) {
				String format = paths[i].substring(paths[i].lastIndexOf("."), paths[i].length());
				rname[i] = strs[i].split(",")[1].split(format)[0]+format;
				fileFormats[i] = resFormatService.getFileFormatByFileExt(format).getFileformat();
				fileFormats[i] = "";
				fileTypes[i] = format;
			}

			for (int i = 0; i < paths.length; i++)
			{
				
				
				
				
//				
//				long resId = resImpl.insetAssetAndRel(userId, rname[i], paths[i], note,keyword,
//							rsize.split(",")[i], rtype, "", courseId, fileFormats[i],subjectId, dbId);
//
//				
//				//文件服务上传v1 新增字段  	后台文件操作任务 
//				String task_type =  task_types.length>i ?task_types[i] :"";
//				//是否已经完成上传并转换编码或解包等任务  
//				int isFinished = 0;
//				//若文件需要进行类型转换 ，或是有 后台文件操作任务 
//				if(isNeedConvert(paths[i]) || StringUtils.isNotEmpty(task_type)){
//					isFinished = 1;
//				}
//				//path 变量，同  z_type_convert表中的 resPath 字段
//				resImpl.afterAssetSave(userId, paths[i], isFinished,dbId);
//				
//
//				
//				
//				
//				if(resId > 0 ){
//					resIds[i] = resId ;
//				}
//				//上传资源，加入日志
//				UserLogImpl userLogImpl = (UserLogImpl) CommonConstantWeb.getLogicInterface("UserLogService");
//				UserLogBean logObj = new UserLogBean();
//				logObj.setObjId(resId);
//				String typeleixing = "我的资源";
//				logObj.setObjName(typeleixing+":" + rname[i]);
//				logObj.setUserId(userId);
//				logObj.setLogTypeCode(UserLogTypeBean.OTHER);
//				logObj.setOperTypeCode(UserOperBean.CREATE);
//				logObj.setSubjectId(subjectId);
//				userLogImpl.addUserLogWeb(logObj, dbId);
//				
//				
//				
//				
//				HashMap map = new HashMap();
//				map.put("id", resId);
//				map.put("name", rname[i]);
//				map.put("format", fileFormats[i]);
//				map.put("fileType", fileTypes[i]);
//				map.put("isfinished", isFinished);
//				
//				result.add(map);
				
			}

			
			
			
			
			
			
//			message = "success";
		} catch (Exception e) {
				
//			System.out.println("--uploadAndSaveNew---error--url:"+request.getRequestURL().toString());
//			
//			e.printStackTrace();
//			message = e.getMessage();
//			result.clear();
		}finally{
//			root.put("message", message);
//			root.put("result", result);
		}
//		return "ajax";

		
		return null;
	}

	
	
	
	

}
