package net.tfedu.zhl.cloud.resource.basedata;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.resource.config.ResourceWebConfig;
import net.tfedu.zhl.cloud.resource.navigation.service.BookService;
import net.tfedu.zhl.cloud.resource.navigation.service.EditionService;
import net.tfedu.zhl.cloud.resource.navigation.service.TermService;
import net.tfedu.zhl.cloud.resource.navigation.service.TermSubjectService;
import net.tfedu.zhl.cloud.resource.navigation.service.TreeService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResPoolService;
import net.tfedu.zhl.cloud.resource.poolconfig.service.SAppUserPoolConfigService;
import net.tfedu.zhl.cloud.resource.prepare.entity.ResourceSimpleInfo;
import net.tfedu.zhl.cloud.resource.prepare.service.JPrepareService;
import net.tfedu.zhl.cloud.resource.prepare.util.JPrepareConstant;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResRecommendationEntity;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.cloud.resource.resourceList.util.ResThumbnailPathUtil;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.exception.NoAuthorizationException;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.sign.SignUtil;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;

/**
 * 
 * 资源平台的数据层接口
 * 
 * @author wangwr
 * @date 2017年2月20日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
@RequestMapping(value = "baseData/resource")
public class BaseResourceDataAPIController {

	@Resource
	TermService termService;

	@Resource
	TermSubjectService termSubjectService;

	@Resource
	EditionService editionService;

	@Resource
	BookService bookService;

	@Resource
	TreeService treeService;

	@Resource
	ResFormatService resFormatService;

	@Resource
	ResPoolService resPoolService;

	@Resource
	SAppUserPoolConfigService appUserPoolConfigService;

	@Resource
	SysResourceService sysResourceService;

	@Resource
	ResPreviewService resPreviewService;
	
	@Resource
	JPrepareService jPrepareService;

	@Resource
	SAppService sAppService;
	

	@Resource
	private CommonWebConfig commonWebConfig;

	@Resource
	private ResourceWebConfig resourceWebConfig;
	
	
	/**
	 * 
	 * 资源中心的基础数据 一般为系统资源
	 * 
	 * 系统资源的fromFlag为0
	 */
	private static final int fromFlag = 0;


	/**
	 * 根据参数生成sign
	 * 
	 * @return
	 * @throws ParamsException 
	 */
	@RequestMapping(value="v1.0/createSign",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON createSign(HttpServletRequest request) throws ParamsException {
		
		
		Integer appId = ControllerHelper.getIntParameter(request, "appId");

		SApp app = (SApp)( sAppService.get(appId).getData());
		
		String appKey = app.getAppkey();
		
		String sign = SignUtil.createSign(request, appKey);
		
		
		return ResultJSON.getSuccess(sign);
	}
	
	/**
	 * 获取全部学段
	 * 
	 * @return
	 */
	@RequestMapping(value="v1.0/terms",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON terms() {
		return ResultJSON.getSuccess(termService.selectAll());
	}

	/**
	 * 查询学段下的所有学科
	 * 
	 * @param termId
	 */
	@RequestMapping(value="v1.0/subjects",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON subjects(Long termId) {
		return ResultJSON.getSuccess(termSubjectService.getAllSubjectsByTerm(termId));
	}

	/**
	 * 根据学段、学科，查询所有版本教材
	 * 
	 * @param termId
	 * @param subjectId
	 */
	@RequestMapping(value="v1.0/editions",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON editions(Long termId, Long subjectId) {

		return ResultJSON.getSuccess(editionService.getAllEditionsByTermAndSub(termId, subjectId));
	}

	/**
	 * 获取指定版本下的全部教材列表
	 * 
	 * @param pnodeId
	 */
	@RequestMapping(value="v1.0/books",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON books(Long pnodeId) {

		// 产品码
		String proCode = resourceWebConfig.getProCode();

		return ResultJSON.getSuccess(bookService.getAllBooks(pnodeId, proCode));
	}

	/**
	 * 获取全部资源库
	 * 
	 * @param termId
	 * @param subjectId
	 * @throws CustomException
	 */
	@RequestMapping(value="v1.0/pools",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON pools(Long termId, Long subjectId, HttpServletRequest request) throws CustomException {

		String userName = ControllerHelper.getParameter(request, "userName");
		String appId = ControllerHelper.getParameter(request, "appId");

		return ResultJSON
				.getSuccess(appUserPoolConfigService.getAppUserPoolConfig(termId, subjectId, userName, appId));
	}

	/**
	 * 教材目录树接口 （一次性）获取指定教材的全部目录接口
	 * 
	 * @param pnodeId
	 */
	@RequestMapping(value="v1.0/contents",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON contents(Long pnodeId) {

		// 产品码
		String proCode = resourceWebConfig.getProCode();

		return ResultJSON.getSuccess(treeService.getTreeNodes(pnodeId, proCode));
	}

	/**
	 * 
	 * 资源格式查询接口 根据教材目录节点、资源库查询资源格式
	 * 
	 * @param poolId
	 *            资源库id
	 * @param pTfcode
	 *            课程结点的tfcode
	 * @param typeId
	 *            资源类型Id（可以指定为全部，即0
	 */
	@RequestMapping(value="v1.0/formats",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON formats(Long poolId, String pTfcode, int typeId, HttpServletRequest request) {

		// 系统资源来源
		List<Integer> sys_from = resourceWebConfig.getSys_from(request);

		List<String> formats = resFormatService.getSysResFormats(pTfcode, typeId, sys_from, poolId);

		return ResultJSON.getSuccess(formats);
	}

	/**
	 * 资源分页查询接口 根据教材目录节点、资源库、资源格式、分页信息等查询资源
	 * 
	 * @param request
	 */
	@RequestMapping(value="v1.0/sysResource",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON sysResource(HttpServletRequest request) throws CustomException {

		// 获取文件服务器的访问url
		String resServiceLocal = commonWebConfig.getResServiceLocal();
		String currentResPath = commonWebConfig.getCurrentResPath(request);


		// 判断是否为最新资源的期限
		int expire = resourceWebConfig.getExpire(request);

		// 资源来源
		List<Integer> sys_from = resourceWebConfig.getSys_from(request);

		// 资源库id
		long poolId = ControllerHelper.getLongParameter(request, "poolId");
		// 类型Id
		int mTypeId = ControllerHelper.getIntParameter(request, "mTypeId");
		// 资源格式
		String fileFormat = ControllerHelper.getParameter(request, "fileFormat");
		// 课程tfcode
		String tfcode = ControllerHelper.getParameter(request, "tfcode");
		// 排序方式（综合排序；最多下载；最新发布）
		int orderBy = ControllerHelper.getIntParameter(request, "orderBy");
		// 页码
		int page = ControllerHelper.getIntParameter(request, "page");
		// 每页的记录数
		int perPage = ControllerHelper.getIntParameter(request, "perPage");

		// 查询出的系统资源信息
		// 查询结果，封装为pagination
		Pagination<SysResourceEntity> pagination = sysResourceService.getAllSysRes(poolId, mTypeId, fileFormat, tfcode,
				orderBy, page, perPage, sys_from, expire);

		// 生成文件的缩略图路径
		ResThumbnailPathUtil.convertToPurpos_sys(pagination.getList(), resServiceLocal, currentResPath);

		return ResultJSON.getSuccess(pagination);
	}

	/**
	 * 
	 * 资源详细信息接口 指定资源的详细信息
	 * 
	 * @param resId
	 */
	@RequestMapping(value="v1.0/syscourseResInfo",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON syscourseResInfo(Long resId) {


		return ResultJSON.getSuccess(resPreviewService.getResPreviewInfo(resId, 0, fromFlag));
	}

	/**
	 * 资源的推荐列表
	 * @param request
	 * @throws ParamsException
	 */
	@RequestMapping(value="v1.0/resRecommendation",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON resRecommendation(HttpServletRequest request) throws ParamsException {

		// 查询结果
		@SuppressWarnings("unused")
		Pagination<ResRecommendationEntity> pagination = null;

		List<Integer> sys_from = resourceWebConfig.getSys_from(request);


		String tfcode = "";

		// 当前预览的资源id
		long resId = ControllerHelper.getLongParameter(request, "resId");


		// 当前页码
		int page = ControllerHelper.getIntParameter(request, "page");

		// 每页多少条记录
		int perPage = ControllerHelper.getIntParameter(request, "perPage");

		// 是否为资源检索：0 否，1 是
		//int isSearch = 0;

		// 格式
		String fileFormat = ControllerHelper.getParameter(request, "format");

		// 排序方式
		int orderBy = ControllerHelper.getIntParameter(request, "orderBy");

		// 资源的tfcode
		tfcode = ControllerHelper.getParameter(request, "tfcode");

		// 资源类型id，默认为全部
		int typeId = ControllerHelper.getOptionalIntegerParameter(request, "typeId");

		// 系统资源
		// 资源库id，默认为全部
		long poolId = ControllerHelper.getLongParameter(request, "poolId");
		pagination = resPreviewService.sysRecommendation(tfcode, typeId, resId, poolId, page, perPage, sys_from,
				orderBy, fileFormat);

		return ResultJSON.getSuccess("");
	}

	/**
	 * 资源的播放链接
	 * @param appId
	 * @param userName
	 * @param resId
	 * @param request
	 * @throws CustomException 
	 */
	@RequestMapping(value="v1.0/sysResourcePlay",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON sysResourcePlay(String appId,String userName, Long resId,HttpServletRequest request) throws CustomException {
		
		
		if(!sysResourceService.hasPermissionToViewAndDown(userName, appId, resId)){
			throw new NoAuthorizationException();
		}
		
		
		// 获取文件服务器的访问url
		String resServiceLocal = commonWebConfig.getResServiceLocal();
		String currentResPath = commonWebConfig.getCurrentResPath(request);

		List<ResourceSimpleInfo> list = jPrepareService
				.getResourceSimpleInfoForView(new String[]{String.valueOf(resId)}, new String[]{String.valueOf(fromFlag)},
						0l);
		
		// 将原始的path重置为可用的web链接

		JPrepareConstant.resetResourceViewUrl(list, resServiceLocal,
				currentResPath,false);
		
		if(list!=null&&list.size()>0){
			return ResultJSON.getSuccess(list.get(0));
		}

		return ResultJSON.getSuccess("");
	}

	/**获取资源的下载链接
	 * @param appId
	 * @param userName
	 * @param resId
	 * @param request	 * @throws UnsupportedEncodingException 
	 * @throws CustomException 
	 * @throws NoAuthorizationException 
	 */
	@RequestMapping(value="v1.0/sysResourceDown",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON sysResourceDown(String appId,String userName,Long resId,HttpServletRequest request) throws UnsupportedEncodingException, NoAuthorizationException, CustomException {
		
		if(!sysResourceService.hasPermissionToViewAndDown(userName, appId, resId)){
			throw new NoAuthorizationException();
		}
		
		// 获取文件服务器的访问url
		String resServiceLocal = commonWebConfig.getResServiceLocal();
		String currentResPath = commonWebConfig.getCurrentResPath(request);

		
		List<ResourceSimpleInfo> list = jPrepareService
				.getResourceSimpleInfoForDownload(new String[]{String.valueOf(resId)}, 
				new String[]{String.valueOf(fromFlag)}, 0l
				);

		
		if(list!=null&&list.size()>0){

			JPrepareConstant.resetResourceDownLoadURLWeb(list,
					resServiceLocal, currentResPath);
			return ResultJSON.getSuccess(list.get(0));
		}
		return ResultJSON.getSuccess("");
	}

}
