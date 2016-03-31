package net.tfedu.zhl.cloud.resource.resPreview.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获取一条预览资源的信息 controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class ResPreviewInfoController {
	
	@Resource ResPreviewService resPreviewService;

	@RequestMapping(value = "/v1.0/resPreviewInfo",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getResPreviewInfo(HttpServletRequest request,HttpServletResponse response)throws IOException{
		/**
		 * 返回json的结果对象
		 */
		ResultJSON resultJSON = new ResultJSON();
		
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		
		ResPreviewInfo previewInfo = null;
		
		try {
			//当前用户已经登录系统
    		if(exception == null && currentUserId != null){
    			
    			//资源来源
    			int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));
    			//资源id
    			long resId = Long.parseLong(request.getParameter("resId"));
    			
    			//查询一条资源的详细信息
    			previewInfo = resPreviewService.getResPreviewInfo(resId, fromFlag);
    			
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
			resultJSON.setData(previewInfo);
			resultJSON.setMessage(exception.getMessage());
			resultJSON.setSign("");
		}
		
		return resultJSON;
	}
}
