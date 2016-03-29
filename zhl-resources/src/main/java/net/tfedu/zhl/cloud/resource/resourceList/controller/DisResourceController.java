package net.tfedu.zhl.cloud.resource.resourceList.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.service.DisResService;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 区本校本资源的controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class DisResourceController {

	@Resource DisResService disResService;
	@Resource SysResourceService sysResourceService;
	@Resource ResTypeService resTypeService;
	
	@RequestMapping("/v1.0/districtResource")
	@ResponseBody
	public ResultJSON getDisResource(HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 返回json的结果对象
		 */
		ResultJSON resultJSON = new ResultJSON();
		
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		Pagination<DisResourceEntity> pagination = null;
		try {
			//当前用户已经登录系统
    		if(exception == null && currentUserId != null){
    			long userId = currentUserId; //获得用户id
    			//类型Id
    			int mTypeId = Integer.parseInt(request.getParameter("mTypeId"));
    			
    			//资源格式
    			String fileFormat = request.getParameter("fileFormat");
    			
    			//课程tfcode
    			String tfcode = request.getParameter("tfcode");
    			
    			//排序方式（综合排序；最多下载；最新发布）
    			int orderBy = Integer.parseInt(request.getParameter("orderBy"));
    			
    			//页码
    			int page = Integer.parseInt(request.getParameter("page"));
    			
    			//每页的记录数
    			int perPage = Integer.parseInt(request.getParameter("perPage"));
    			
    			//资源来源
    			int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));

    			pagination = disResService.selectAllDisRes(userId, mTypeId, fileFormat, tfcode, orderBy, page, perPage, fromFlag);
    			
    			exception = CustomException.SUCCESS;
    		}
				
		} catch (Exception e) {
			// TODO: handle exception
			//捕获异常信息
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
		} finally {
			//封装结果集
			resultJSON.setCode(exception.getCode());
			resultJSON.setData(pagination);
			resultJSON.setMessage(exception.getMessage());
			resultJSON.setSign("");
		}
		
		return resultJSON;
		
		
	}
	
}
