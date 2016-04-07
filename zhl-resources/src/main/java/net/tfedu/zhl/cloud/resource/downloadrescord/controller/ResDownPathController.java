package net.tfedu.zhl.cloud.resource.downloadrescord.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.downloadrescord.service.ResDownPathService;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获取资源下载地址 controller
 * 
 * 获取下载路径后，下载次数加1
 * @author WeiCuicui
 *
 */

@Controller
@RequestMapping("/resRestAPI")
public class ResDownPathController {

	@Resource ResDownPathService resDownPathService;
	
	@Resource ResPreviewService resPreviewService;
	
	//写入日志
    Logger logger = Logger.getLogger(ResDownPathController.class);
	
	@RequestMapping("/v1.0/res_down")
	@ResponseBody
	public ResultJSON getResDownPath(HttpServletRequest request,HttpServletResponse response)throws IOException{
		 /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        
        //查询结果
        List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
        
        try {
			
        	// 当前用户已经登录系统
            if (exception == null && currentUserId != null) {
            	
            	//当前用户id
            	long resId = currentUserId;    
            	
            	// 0 为自建资源   1 为系统资源  （0  自建资源 1 系统资源    2共享资源  3 区本  4 校本资源）
				int fromFlag = Integer.parseInt(request.getParameter("fromFlag")); 
				
				// 当 getSource = 1 时   1 为需要源文件 0 为不需要源文件  ； getSource = 0 时  不启用
				int getSource = 0;  
	
				logger.debug("---------------获取资源的下载路径----------".toString());
				
            	//获取文件服务器的访问url 
				String resServiceLocal = (String)request.getAttribute("resServiceLocal");
				String currentResPath = (String)request.getAttribute("currentResPath");
				
			
				String assetPath = "";
				String imgDownPath = ""; 
				String pdfPath = "";
				int diskOrder= 1 ;
				
				//获取一条资源的详细信息(自建、系统、区本、校本)
				ResPreviewInfo info = resPreviewService.getResPreviewInfo(resId, fromFlag);
				
            }
        	
		} catch (Exception e) {
			 // TODO: handle exception
			 // 捕获异常信息
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();
            
		} finally {
			
			//封装结果集
            resultJSON.setCode(exception.getCode());
            resultJSON.setData("");
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign("");
		}
        
        return resultJSON;
	}
}