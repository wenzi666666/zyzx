package net.tfedu.zhl.cloud.resource.statistics.taishan.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.resource.statistics.taishan.entity.CloudPlatformUsrInfo;
import net.tfedu.zhl.cloud.resource.statistics.taishan.service.ResourceStatisticsService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.helper.sign.SignUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
public class ResourceStatisticsController {

	public static String app_key = "zhl-teaching";

	@Resource
	ResourceStatisticsService resourceStatisticsService;
	@Resource
	CommonWebConfig commonWebConfig;

	/**
	 * 1资源动态 -- 资源上传\资源共享，取最新的三条（区、校） 参数： scope String 是 查询范围 S校、D区、W全站 scopeId
	 * String 是 相应的学校id或地区id number int 否 个数，默认为3
	 * 
	 * 用户头像 用户id 真实姓名 学校 资源名称 时间
	 */
	@RequestMapping(value = "resourceData_resDynamic.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getResDynamic(String scope, Long scopeId, Integer number, String cloudPlatFormLocal,
			String cloudPlatForm) throws CustomException {
		number = number == 0 ? 3 : number;
		List<Map<String, Object>> list = resourceStatisticsService.getResDynamic(scope, scopeId, number);
		resetUserImage(cloudPlatFormLocal, cloudPlatForm, list);
		return ResultJSON.getSuccess(list);
	}

	/**
	 * 2精品系統资源 按引用次数 排序（浏览、下载、收藏）合计 支持资源预览 参数 number int 否 个数，默认为3 返回
	 * 标题、缩略图、播放链接、浏览次数、下载次数、收藏次数,文件后缀
	 */
	@RequestMapping(value = "resourceData_bastSysRes.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getBastSysRes(HttpServletRequest request, Integer number) throws CustomException {
		number = number == 0 ? 3 : number;
		List<Map<String, Object>> list = resourceStatisticsService.getBastSysRes(number);
		String resPathLocal = commonWebConfig.getResServiceLocal();
		String resCenter = commonWebConfig.getCurrentResPath(request);
		// 重新设置播放链接和缩略图
		resSetImageAndPlayUrl(resPathLocal, resCenter, list);
		return ResultJSON.getSuccess(list);
	}

	/**
	 * 3精品（区）校本资源 按引用次数 排序（浏览、下载、收藏）合计（按区或按校） 支持资源预览 scope String 是 查询范围
	 * S校、D区、W全站 scopeId String 是 相应的学校id或地区id number int 否 个数，默认为3 返回
	 * 标题、缩略图、播放链接、浏览次数、下载次数、收藏次数,文件后缀
	 */
	@RequestMapping(value = "resourceData_bastScopeRes.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getBastScopeRes(HttpServletRequest request, String scope, Long scopeId, Integer number)
			throws CustomException {
		number = number == 0 ? 3 : number;
		List<Map<String, Object>> list = resourceStatisticsService.getBastScopeRes(scope, scopeId, number);
		String resPathLocal = commonWebConfig.getResServiceLocal();
		String resCenter = commonWebConfig.getCurrentResPath(request);
		// 重新设置播放链接和缩略图
		resSetImageAndPlayUrl(resPathLocal, resCenter, list);
		return ResultJSON.getSuccess(list);
	}

	/**
	 * 4资源最新上传（按区或校） scope String 是 查询范围 S校、D区、W全站 scopeId String 是 相应的学校id或地区id
	 * number int 否 个数，默认为 4
	 * 
	 * 返回 标题、缩略图、播放链接、上传时间,文件后缀
	 * 
	 */
	@RequestMapping(value = "resourceData_lastAsset.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getLastAsset(HttpServletRequest request, String scope, Long scopeId, Integer number)
			throws CustomException {
		number = number == 0 ? 3 : number;
		List<Map<String, Object>> list = resourceStatisticsService.getLastAsset(scope, scopeId, number);
		String resPathLocal = commonWebConfig.getResServiceLocal();
		String resCenter = commonWebConfig.getCurrentResPath(request);
		// 重新设置播放链接和缩略图
		resSetImageAndPlayUrl(resPathLocal, resCenter, list);
		return ResultJSON.getSuccess(list);
	}

	/**
	 * 
	 * 5、上传资源总量（按校） 指定范围内的全部学校的上传总量的统计 scope String 是 查询范围 S校、D区、W全站 scopeId
	 * String 是 相应的学校id或地区id 学校id，学校名称、上传资源总量
	 * 
	 * @return
	 */
	@RequestMapping(value = "resourceData_schoolAssetStatistics.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getSchoolAssetStatistics(String scope, Long scopeId) throws CustomException {
		List<Map<String, Object>> list = resourceStatisticsService.getSchoolAssetStatistics(scope, scopeId);
		return ResultJSON.getSuccess(list);
	}

	/**
	 * 
	 * 6、指定学校的校本资源总量+指定学校的本周/月更新资源（校本+共享）资源达人 校本资源总量+本周更新
	 * 
	 * 
	 * schoolId
	 * 
	 * @return
	 */
	@RequestMapping(value = "resourceData_schoolStatistics.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getSchoolStatistics(Long schoolId) throws CustomException {
		return resourceStatisticsService.getSchoolStatistics(schoolId);
	}

	/**
	 * 7资源达人（学校中按人排序，最少三人）
	 * 
	 * 用户头像 用户id 真实姓名 学段、学课、上传资源总量
	 * 
	 * @return
	 */
	@RequestMapping(value = "resourceData_schoolUploadTop.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getSchoolUploadTop(String cloudPlatFormLocal, String cloudPlatForm, Long schoolId, Integer number)
			throws CustomException {
		number = number == 0 ? 3 : number;
		List<Map<String, Object>> list = resourceStatisticsService.getSchoolUploadTop(schoolId, number);
		resetUserImage(cloudPlatFormLocal, cloudPlatForm, list);
		return ResultJSON.getSuccess(list);
	}

	/**
	 * 8指定班级浏览资源次数
	 * 
	 * @return
	 */
	@RequestMapping(value = "resourceData_gradeClickTop.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getGradeClickTop(String cloudPlatFormLocal, Long gradeId, String startTime, String endTime)
			throws Exception {
		String userIds = getGradeUserIds(cloudPlatFormLocal, gradeId);
		List<Map<String, Object>> list = resourceStatisticsService.getGradeClickTop(userIds, startTime, endTime);
		return ResultJSON.getSuccess(list);
	}
	/**
	 *  9班级浏览资源日志
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "resourceData_gradeClickLog.action", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getGradeClickLog(String cloudPlatFormLocal, String cloudPlatForm, Long gradeId, Integer number)
			throws Exception {
		number = number == 0 ? 10 : number;
		String userIds = getGradeUserIds(cloudPlatFormLocal, gradeId);
		List<Map<String, Object>> list = resourceStatisticsService.getGradeClickLog(userIds, number);
		resetUserImage(cloudPlatFormLocal, cloudPlatForm, list);
		return ResultJSON.getSuccess(list);
	}

	/**
	 * //重置list中的用戶學段\學科\學校\頭像信息
	 * 
	 * @param cloudPlatFormLocal
	 * @param cloudPlatForm
	 * @param list
	 */
	private void resetUserImage(String cloudPlatFormLocal, String cloudPlatForm, List<Map<String, Object>> list) {
		Map<Long, Object> map = new HashMap<Long, Object>();// 用于记录查询结果

		StringBuffer userIds = new StringBuffer();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> _map = (Map<String, Object>) iterator.next();

			String temp = _map.get("userid").toString() + ",";
			if (!userIds.toString().contains(temp)) {
				userIds.append(temp);
			}

		}
		if (StringUtils.isNotEmpty(userIds.toString())) {
			String ids = userIds.substring(0, userIds.length() - 1);
			String url = cloudPlatFormLocal + "/base_getUserInfo.action";

			String result = createURLAndGetResult(url, "userIds", ids);

			ResultJSON json = JSONObject.parseObject(result, ResultJSON.class);

			if ("OK".equalsIgnoreCase(json.getCode()) && json.getData() != null) {

				JSONArray array = (JSONArray) json.getData();

				CloudPlatformUsrInfo userinfo = null;
				for (int i = 0; i < array.size(); i++) {

					userinfo = JSONObject.parseObject(JSONObject.toJSONString(array.get(i)),
							CloudPlatformUsrInfo.class);

					map.put(userinfo.getId(), userinfo);
				}

				if (list != null) {

					for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
						Map<String, Object> ls = iterator.next();
						CloudPlatformUsrInfo info = (CloudPlatformUsrInfo) map.get((Long) ls.get("userid"));

						if (info != null) {

							ls.put("userId", ls.get("userid"));

							ls.put("schoolname", info.getSchoolName());
							ls.put("termname", getTermName(info.getTermId()));
							ls.put("subjectname", info.getSubjects() == null || info.getSubjects().size() == 0 ? ""
									: info.getSubjects().get(0).getSubjectName());
							ls.put("truename", info.getTrueName());
							ls.put("userimage", info.getUserImage());

							ls.put("trueName", info.getTrueName());
							ls.put("userImage", info.getUserImage());
							ls.put("schoolName", info.getSchoolName());
							ls.put("termName", getTermName(info.getTermId()));
							ls.put("subjecTname", info.getSubjects() == null || info.getSubjects().size() == 0 ? ""
									: info.getSubjects().get(0).getSubjectName());

						}

					}
				}
			}

		}
	}

	private String getTermName(Long termId) {
		return 1 == termId ? "小学" : 2 == termId ? "初中" : 3 == termId ? "高中" : "其他 ";
	}

	private String createURLAndGetResult(String url, String param_key, String param_value) {

		return createURLAndGetResult(url, new String[] { param_key }, new String[] { param_value });

	}

	private String createURLAndGetResult(String url, String[] param_key, String[] param_value) {
		String sign = SignUtil.createSignMap(param_key, param_value, app_key);

		url += "?sign=" + sign;

		for (int i = 0; i < param_value.length; i++) {
			url += "&" + param_key[i] + "=" + param_value[i];
		}

		System.out.println("url---:" + url);
		String result = "";
		try {
			result = HttpClientUtils.doGET(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 重新设置播放链接和缩略图
	 * 
	 * @param resPathLocal
	 * @param resCenter
	 * @param list
	 */
	private void resSetImageAndPlayUrl(String resPathLocal, String resCenter, List<Map<String, Object>> list) {

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();

			String resType = (String) map.get("restype");
			// 系统资源的设置
			if (StringUtils.isNotEmpty(resType)) {

				String imagePath = "";
				Boolean isNet = false; // 是否为网络资源

				if ("asset".equalsIgnoreCase(resType)) {
					isNet = 1 == Integer.parseInt(map.get("islocal").toString()) ? true : false;
					if (!isNet) {
						imagePath = (String) map.get("assetpath");
					}
				} else {
					imagePath = (String) map.get("fpath") + File.separator + (String) map.get("fname");
				}

				if (!isNet && imagePath != null) {
					imagePath = imagePath.substring(0, imagePath.lastIndexOf("."))
							+ ZhlResourceCenterWrap.THUMBNAILS_IMG_TYPE;
					// 缩略图存在的话，返回最终访问地址，否则为空，这时，前端页面使用该资源的默认资源类型图标
					if (ZhlResourceCenterWrap.isFileExist(resPathLocal, imagePath)) {
						imagePath = ZhlResourceCenterWrap.getWebThumbnail(resPathLocal, imagePath);
						imagePath = imagePath.replace(resPathLocal, resCenter);
					} else {
						imagePath = "";
					}

					map.put("imagepath", imagePath);
				} else {
					map.put("imagepath", "");
				}

				map.put("playurl", "/port_view.action?fromflag=" + map.get("fromflag").toString() + "&resId="
						+ map.get("id").toString());
			}

		}

	}

	public static String getGradeUserIds(String server, long gradeId) throws Exception {

		String url = server + "/phoneWork_loadStudentByGradeId.action";

		String secretKey = "zhl.interface.tfedu.net";

		Map<String, Object> ps = new HashMap<String, Object>();
		ps.put("gradeId", gradeId);

		String paramsJson = JSONObject.toJSONString(ps);

		String sign = MD5.getMD5Str(paramsJson + secretKey);

		url = url + "?paramsJson=" + paramsJson + "&sign=" + sign;

		String result = HttpClientUtils.getUrlString(url);

		if (StringUtils.isNotEmpty(result)) {
			ResultJSON js = JSONObject.parseObject(result, ResultJSON.class);

			StringBuffer ids = new StringBuffer();

			JSONArray array = (JSONArray) js.getData();
			int length = array.size();
			for (int i = 0; i < length; i++) {
				Map map = JSONObject.parseObject(JSONObject.toJSONString(array.get(i)), HashMap.class);

				ids.append(map.get("UserId").toString());
				if (i < length - 1) {
					ids.append(",");
				}
			}

			return ids.toString();

		}

		return "";
	}
}
