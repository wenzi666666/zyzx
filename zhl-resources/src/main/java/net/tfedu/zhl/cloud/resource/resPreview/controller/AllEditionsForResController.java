package net.tfedu.zhl.cloud.resource.resPreview.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获得一条资源的所有版本目录
 * @author WeiCuicui
 */
@Controller
@RequestMapping("/resRestAPI")
public class AllEditionsForResController {

	@Resource ResPreviewService resPreviewService;
	
	@RequestMapping(value="/v1.0/preview/lists",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllLists(HttpServletRequest request,HttpServletResponse response)throws IOException{
		/**
		 * 返回json的结果对象
		 */
		ResultJSON resultJSON = new ResultJSON();
		
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		
		//各个版本的资源目录
		List<List<ResNavEntity>> result = new ArrayList<List<ResNavEntity>>();
		
		try {
			
			//当前用户已经登录系统
    		if(exception == null && currentUserId != null){
    			
    			//资源id
    			long resId = Long.parseLong(request.getParameter("resId"));
    			
    			//资源来源
    			int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));
    			
    			
    			//当前所在结点
    			String curTfcode = request.getParameter("curTfcode ");
    			
    			
    			//查询所有版本的资源目录
    			result = resPreviewService.getAllResNavs(resId, fromFlag, curTfcode);
    			
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
			resultJSON.setData(result);
			resultJSON.setMessage(exception.getMessage());
			resultJSON.setSign("");
		}
		
		return resultJSON;
	}
}
