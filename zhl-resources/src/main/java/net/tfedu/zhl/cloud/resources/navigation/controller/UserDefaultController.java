package net.tfedu.zhl.cloud.resources.navigation.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resources.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resources.navigation.service.UserDefaultService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户历史选择 controller
 * @author WeiCuicui
 * 返回结果为统一类型
 */
@Controller
@RequestMapping("/resRestAPI")
public class UserDefaultController {

	@Resource UserDefaultService userDefaultService;
	
	//查询用户历史选择
	@RequestMapping(value="/v1.0/history",method=RequestMethod.GET) 
	@ResponseBody
	public ResultJSON getUserDefault(HttpServletRequest request,HttpServletResponse response) throws IOException{
		long userId = request.getParameter("userId") != null ? Long.parseLong(request.getParameter("userId").toString().trim()) : 0;
		int type = request.getParameter("type") != null ? Integer.parseInt(request.getParameter("type").toString().trim()) : -1;
		
	}
	
	//修改或者增加用户历史选择
	@RequestMapping(value="/v1.0/history") 
	@ResponseBody
	public void updateUserDefault(HttpServletRequest request,HttpServletResponse response) throws IOException{
		long userId = request.getParameter("userId") != null ? Long.parseLong(request.getParameter("userId").toString().trim()) : 0;
		int type = request.getParameter("type") != null ? Integer.parseInt(request.getParameter("type").toString().trim()) : -1;
		String tfcode = request.getParameter("tfcode") ;
		String _method = request.getParameter("_method");
		
		
		if (StringUtils.isEmpty(_method) && RequestMethod.PATCH.equals(_method)) {//修改用户历史选择
			
		} else if(StringUtils.isNotEmpty(_method) && RequestMethod.POST.name().equals(_method)){//增加用户历史选择
			
		} 
	}
	
}
