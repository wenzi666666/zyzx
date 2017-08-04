package net.tfedu.zhl.cloud.resource.integration.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.resource.asset.service.ZAssetService;
import net.tfedu.zhl.cloud.resource.constant.ResourcePlatformWebConstant;
import net.tfedu.zhl.cloud.resource.integration.util.CloudClientMD5;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.sso.user.service.JUserService;

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
	 * 云平台 用于加密的md5的密钥
	 */
	private static final String CLIENT_KEY = "tfedu1234&*()";

	/**
	 * 云平台对接链接中用于对接校验的时间戳的参数名称
	 */
	private static final String PARAM_MD5_CHECK_NOWDATE = "nowdate";

	/**
	 * 云平台对接链接中的校验字符串
	 */

	private static final String PARAM_MD5_CHECK_NOWDATE_MD5_STR = "mdStr";

	/**
	 * 通过接口获取资源中心数据时，排除多媒体教辅库 (exceptPoolIds = "5")
	 * 
	 */

	public static final String EXCEPTPOOLIDS = "5";
	
	
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
	
	
	

	private static String getMdStr(String newdate) {
		return newdate != null ? CloudClientMD5.Md5(newdate + CLIENT_KEY, 1, 1) : "";
	}

	private static boolean isCloud(HttpServletRequest request) {
		String newdate = request.getParameter(PARAM_MD5_CHECK_NOWDATE);
		String mdStr = request.getParameter(PARAM_MD5_CHECK_NOWDATE_MD5_STR);
		return newdate != null && mdStr != null && getMdStr(newdate).equals(mdStr);
	}
	
	
	private static Map<String ,Object> checkCloudParams(HttpServletRequest request) {
		
		Map<String,Object> map = new HashMap<String,Object>();

		if(!isCloud(request)){
			map.put(ResourcePlatformWebConstant.MESSAGE, ResourcePlatformWebConstant.ERROR);
			return map;
		}
		return map;
	}
	
	
	
	@Resource
	ZAssetService zAssetService; 
	
	@Resource
	JUserService  userService; 
	
	
	
	/**
	 * 获取（地区下）学校的上传统计信息
	 * @return
	 */
	@RequestMapping("cloudData_areaUpload.action")
	@ResponseBody
	public Map<String,Object> areaUpload(HttpServletRequest request){

		
		Map<String,Object> map = checkCloudParams(request);
		
		if(map.isEmpty()){
			String message = ResourcePlatformWebConstant.SUCCESS;

			String schoolIds;
			Map<String, Object> result = null;
			try {
				schoolIds = ControllerHelper.getParameter(request, "schoolIds");
				result = zAssetService.statisticsSchoolUpload(schoolIds.split(","));
			} catch (Exception e) {
				e.printStackTrace();
				message = ResourcePlatformWebConstant.ERROR;
			}finally {
				map.put(ResourcePlatformWebConstant.RESULT, result);
				map.put(ResourcePlatformWebConstant.MESSAGE, message);
			}
		}
		return map;
	}
	
	
	
	/**
	 * 更新用户的学段
	 * 学科
	 */
	@RequestMapping("cloudData_updateTermSubject.action")
	@ResponseBody
	public Map<String,Object> updateTermSubject(HttpServletRequest request){
		
		Map<String,Object> map = checkCloudParams(request);
		
		if(map.isEmpty()){
			
			String message = ResourcePlatformWebConstant.SUCCESS;
		
			try {
			
				String userName =ControllerHelper.getParameter(request,"userName");
				String termId = ControllerHelper.getParameter(request,"termId");
				String subjectIds = ControllerHelper.getParameter(request,"subjectIds");
				
				userService.updateUserTermSubject(userName, termId,
							StringUtils.isEmpty(subjectIds)?null:subjectIds.split(","));
				
				
			} catch (Exception e) {
				e.printStackTrace();
				message = ResourcePlatformWebConstant.ERROR;
			}finally {
				map.put(ResourcePlatformWebConstant.MESSAGE, message);
			}
			
			
		}
		return map;
	}
	
	
	
	/**
	 * 获取tree 目錄
	 * @param request
	 * @param id      為null時，獲取全部目錄，不為null時，獲取字節點
	 * @param type    區分目錄的來源：0 为自建课程树   1 系统目录树 2 为知识点
	 * @return
	 */
	@RequestMapping("cloudData_tree.action")
	@ResponseBody
	public Map<String,Object> tree(HttpServletRequest request,String id,String type){
		
		Map<String,Object> map = checkCloudParams(request);
		
		if(map.isEmpty()){
			
			String message = ResourcePlatformWebConstant.SUCCESS;
			
			try {
				Long userId = 0L ;

				id = ControllerHelper.checkEmpty(id);
				
				int typeInt = StringUtils.isEmpty(type)?COURSE_NAVIGATION_TYPE
						:(ResourcePlatformWebConstant.COURSE.equals(type))?COURSE_NAVIGATION_TYPE
						: Integer.parseInt(type.trim());
				
				switch(typeInt){
				
					case COURSE_NAVIGATION_TYPE:  break ;
					case SYSCOURSE_NAVIGATION_TYPE:  break ;
					case KONW_NAVIGATION_TYPE:  break ;
				
				}
				

				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				message = ResourcePlatformWebConstant.ERROR;
			}finally {
				map.put(ResourcePlatformWebConstant.MESSAGE, message);
			}
			
			
		}
		return map;
	}
	
	
	
	
	
	
	
	

}
