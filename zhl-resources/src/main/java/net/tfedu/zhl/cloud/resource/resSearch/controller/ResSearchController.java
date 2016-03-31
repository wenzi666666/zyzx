package net.tfedu.zhl.cloud.resource.resSearch.controller;


import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.cloud.resource.resSearch.service.ResSearchService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资源跨库检索的controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class ResSearchController {
	
	@Resource ResSearchService resSearchService;

	/**
	 * 根据检索内容，跨库检索所有资源
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/v1.0/resSearchResults")
	@ResponseBody
	public ResultJSON getAllResources(HttpServletRequest request,HttpServletResponse response) throws IOException{
		/**
		 * 返回json的结果对象
		 */
		ResultJSON resultJSON = new ResultJSON();
		
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		
		//分页查询结果
		Pagination<ResSearchResultEntity> pagination = null;
		
		try {
		
			//若当前用户已经登录系统
    		if(exception == null && currentUserId != null){
    			

    			//检索范围 0 全部资源   1 系统资源  3 校本资源  4 区本资源
    			int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));
    			
    			//检索的关键词
    			String searchKeyword = request.getParameter("searchKeyword");
    			
    			//资源格式：全部，文本，图片......
    		    String format = request.getParameter("format");
    		    
    		    //页码
    		    int page = Integer.parseInt(request.getParameter("page"));
    		      
    		    //每页记录数目
    		    int perPage = Integer.parseInt(request.getParameter("perPage"));
    		    
    		    pagination = resSearchService.getResources(fromFlag, SysFrom.sys_from,searchKeyword, format, page, perPage);

    		    
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
