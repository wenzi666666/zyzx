package net.tfedu.zhl.cloud.resource.integration.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.resource.config.ResourceWebConfig;
import net.tfedu.zhl.cloud.resource.integration.entity.CourseNode;
import net.tfedu.zhl.cloud.resource.integration.entity.ResultInfo;
import net.tfedu.zhl.cloud.resource.integration.util.BeanConvertUtil;
import net.tfedu.zhl.cloud.resource.integration.util.CloudHttpInterfaceUtil;
import net.tfedu.zhl.cloud.resource.integration.util.CloudIntegralParamCheckUtil;
import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;
import net.tfedu.zhl.cloud.resource.navigation.service.BookService;
import net.tfedu.zhl.cloud.resource.navigation.service.EditionService;
import net.tfedu.zhl.cloud.resource.navigation.service.TreeService;
import net.tfedu.zhl.cloud.resource.user.teach.service.JTeachSyscourseService;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.sso.subject.service.TeacherSubjectService;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 
 * 
 * 语文互动课堂--用户自主选教材3.1
 * 
 * 资源中心和云平台公用action 资源中心使用时： personSelect_* 云平台使用时： personSelectAJAX_*
 * 
 * 
 * @author wangwr
 * @date 2017年8月10日
 * @desc copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
public class PersonTeachingSelectController {

	/**
	 * 云平台请求的前缀
	 */
	private static final String REQUEST_PRIFIX = "personSelectAJAX_";

	@Resource
	JTeachSyscourseService jTeachSyscourseService;
	@Resource
	TeacherSubjectService teacherSubjectService;
	@Resource
	ResourceWebConfig resourceWebConfig;
	@Resource
	CommonWebConfig commonWebConfig;
	@Resource
	EditionService editionService;
	@Resource
	BookService bookService;
	@Resource
	TreeService sysCourseTreeService;
	@Resource
	RegisterService registerService;

	/**
	 * 检查云平台请求的参数是否合法(合法时返回null)
	 * 
	 * @param request
	 * @return
	 */
	private ResultInfo checkCloudRequestParams(HttpServletRequest request) {

		String path = request.getServletPath();

		// 判断是否云平他的请求
		if (path.indexOf(REQUEST_PRIFIX) >= 0) {
			return CloudIntegralParamCheckUtil.checkCloudParams(request);
		}

		return null;
	}

	/**
	 * 是否是第一次使用
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "personSelectAJAX_isFirstSelect.action,personSelect_isFirstSelect.action")
	@ResponseBody
	public ResultInfo isFirstSelect(HttpServletRequest request) throws Exception {

		ResultInfo result = checkCloudRequestParams(request);

		if (result == null) {
			try {

				Long userId = getUserId(request);
				
				
				String tfcode = jTeachSyscourseService.queryTeachingSelectTfcode(userId);

				result = ResultInfo.success(tfcode);
			} catch (Exception e) {
				return ResultInfo.error();
			}

		}
		return result;

	}

	/**
	 * 获取用户学科
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "personSelectAJAX_getSubject.action,personSelect_getSubject.action")
	@ResponseBody
	public ResultInfo getSubject(HttpServletRequest request) throws Exception {
		ResultInfo result = checkCloudRequestParams(request);

		if (result == null) {
			try {
				Long userId = getUserId(request);

				List<Map<String, Object>> list = teacherSubjectService.getUserSubjects(userId);
				result = ResultInfo.success(list);

			} catch (Exception e) {
				return ResultInfo.error();
			}

		}
		return result;
	}

	/**
	 * 获取版本
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "personSelectAJAX_version.action,personSelect_version.action")
	@ResponseBody
	public ResultInfo version(HttpServletRequest request) throws Exception {

		ResultInfo result = checkCloudRequestParams(request);

		if (result == null) {
			try {

				// 资源平台的产品编码
				String proCode = resourceWebConfig.getProCode();
				Long termId = ControllerHelper.getLongParameter(request, "termId");
				Long subjectId = ControllerHelper.getLongParameter(request, "subjectId");

				List<Map<String, Object>> list = editionService.getSyscourseVersion(proCode, termId, subjectId);

				result = ResultInfo.success(list);

			} catch (Exception e) {
				return ResultInfo.error();
			}

		}
		return result;
	}

	/**
	 * 获取版本下的教材
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "personSelectAJAX_book.action,personSelect_book.action")
	@ResponseBody
	public ResultInfo book(HttpServletRequest request) throws Exception {

		ResultInfo result = checkCloudRequestParams(request);

		if (result == null) {
			try {

				// 资源平台的产品编码
				String proCode = resourceWebConfig.getProCode();
				Long versionId = ControllerHelper.getLongParameter(request, "versionId");
				String resServiceLocal = commonWebConfig.getResServiceLocal();
				String currentResPath = commonWebConfig.getCurrentResPath(request);

				List<JSyscourse> list = bookService.getAllBooks(versionId, proCode);
				List<Map<String, Object>> listMap = BeanConvertUtil.toListMapLowerCase(list);
				result = ResultInfo.success(getBookImag(listMap, resServiceLocal, currentResPath));

			} catch (Exception e) {
				return ResultInfo.error();
			}

		}
		return result;
	}

	/**
	 * 更新教材用户的教材选择
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "personSelectAJAX_teachingSelect.action,personSelect_teachingSelect.action")
	@ResponseBody
	public ResultInfo teachingSelect(HttpServletRequest request) throws Exception {

		ResultInfo result = checkCloudRequestParams(request);

		if (result == null) {
			try {
				Long userId = getUserId(request);

				long termId = ControllerHelper.getLongParameter(request, "termId");
				long subjectId = ControllerHelper.getLongParameter(request, "subjectId");
				long syscourseId = ControllerHelper.getLongParameter(request, "syscourseId");

				jTeachSyscourseService.replaceUserTeachingSelect(userId, termId, subjectId, syscourseId);

				result = ResultInfo.success();
			} catch (Exception e) {
				return ResultInfo.error();
			}

		}
		return result;
	}

	
	/**
	 * 返回系统教材给云平台
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "personSelectAJAX_tree.action,personSelect_tree.action")
	@ResponseBody
	public ResultInfo tree(HttpServletRequest request) throws Exception {
		ResultInfo result = checkCloudRequestParams(request);

		if (result == null) {
			try {

				// 资源平台的产品编码
//				String proCode = resourceWebConfig.getProCode();
				Long id = ControllerHelper.getOptionalLongParameter(request, "id");

				Long userId = getUserId(request);
				
				List<CourseNode> treeNodes = new ArrayList<CourseNode>();

				if (null == id || 0 == id) {// 获取用户选择的教材节点
					String tfcode = jTeachSyscourseService.queryTeachingSelectTfcode(userId);
					if (tfcode != null) {
						treeNodes.add(sysCourseTreeService.getTreeNodeByCode(tfcode));
					}

				} else {// 获取当前节点的下一层字节点

					treeNodes = sysCourseTreeService.searchSysChildren(id);
				}

				result = ResultInfo.success(treeNodes);
			} catch (Exception e) {
				return ResultInfo.error();
			}

		}
		return result;
	}

	/**
	 * 获取指定教材的全部树形节点
	 * 
	 * @return
	 */
	@RequestMapping(value = "personSelectAJAX_getAllTree.action,personSelect_getAllTree.action")
	@ResponseBody
	public ResultInfo getAllTree(HttpServletRequest request) {
		ResultInfo result = checkCloudRequestParams(request);

		if (result == null) {
			try {

				// 资源平台的产品编码
				String proCode = resourceWebConfig.getProCode();
				Long versionId = ControllerHelper.getLongParameter(request, "versionId");

				List<TreeNode> list = sysCourseTreeService.getTreeNodes(versionId, proCode);

				result = ResultInfo.success(BeanConvertUtil.toListMapLowerCase(list));

			} catch (Exception e) {
				return ResultInfo.error();
			}

		}
		return result;

	}

	/**
	 * 获取教材的封面 规则:CONCAT(s.EbookPath,'/',s.tfcode,'.jpg')
	 * 
	 * @param list
	 * @return
	 */
	private List<Map<String, Object>> getBookImag(List<Map<String, Object>> list, String resService,
			String currentResPath) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> book = list.get(i);
				String ebookpath = (String) book.get("ebookpath");
				String tfcode = (String) book.get("tfcode");
				String imgpath = ZhlResourceCenterWrap.getEBOOkImagePath(ebookpath, tfcode, resService, currentResPath);
				book.put("imgpath", imgpath);
			}
		}
		return list;
	}

	/**
	 * 获取用户的userId
	 * 	      对接资源前端需要参数userId
	 *    对接云平台时需要参数： userName、cloudPlatFormLocal
	 * 	  
	 * @param request
	 * @return
	 * @throws ParamsException
	 * @throws Exception
	 */
	protected Long getUserId(HttpServletRequest request) throws ParamsException, Exception {
		//传递userId
		Long userId = ControllerHelper.getOptionalLongParameter(request, "userId");
		
		if(null == userId || userId ==0){
			userId = CloudHttpInterfaceUtil.getUserId(request, registerService);
		}
		return userId;
	}

}
