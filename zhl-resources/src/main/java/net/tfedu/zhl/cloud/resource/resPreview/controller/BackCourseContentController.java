package net.tfedu.zhl.cloud.resource.resPreview.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 从资源预览页面返回时，查询所属的目录
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class BackCourseContentController {

	@Resource ResPreviewService resPreviewService;
	
	@RequestMapping(value = "/v1.0/backCourseContent",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getCourseContent(HttpServletRequest request,HttpServletResponse response)throws IOException{
		/**
		 * 返回json的结果对象
		 */
		ResultJSON resultJSON = new ResultJSON();
		
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		JUserDefault courseContent = null;
		try {
			
			
			//当前用户已经登录系统
    		if(exception == null && currentUserId != null){
    			
    			String tfcode = request.getParameter("tfcode");
    			
    			//查询课程目录
    			courseContent = resPreviewService.getPnodes(tfcode);
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
			resultJSON.setData(courseContent);
			resultJSON.setMessage(exception.getMessage());
			resultJSON.setSign("");
		}
		
		return resultJSON;
	}
	
}
