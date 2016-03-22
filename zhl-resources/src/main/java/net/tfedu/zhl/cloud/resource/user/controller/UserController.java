package net.tfedu.zhl.cloud.resource.user.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.user.entity.JUser;
import net.tfedu.zhl.cloud.resource.user.service.UserService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.sso.entity.SRegister;
import net.tfedu.zhl.sso.service.RegisterService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("/resRestAPI*")
public class UserController {
	
	
	@Resource
	private UserService userService ;
	
	@Resource
	private RegisterService registerService;
	
	/**
	 * 登陆、注销
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/v1.0/users/login")
	@ResponseBody
    public JUser Login(HttpServletRequest request, HttpServletResponse response) { 
    	String _method = request.getParameter("_method");
    	//注销
		if(StringUtils.isNotEmpty(_method)&&HttpMethod.DELETE.name().equals(_method)){
			
			
			
			
		}else{
			String userName = request.getParameter("user.name");
			String userPwd = request.getParameter("user.pwd");
			//用户登录
			SRegister reg =  registerService.login(userName, userPwd);
			//获取用户信息
			JUser user =userService.getUserById(reg.getId());
			
			
			
			
			
		}
    	return null;
    }

	
	
	
	/**
	 * 获取用户信息
	 * @return
	 */
	@RequestMapping(value="/v1.0/users/{id}",method=RequestMethod.GET) 
	@ResponseBody	
	public JUser getUserInfo(@PathVariable Long id){
		return  userService.getUserById(id);
	}
	
	
	
	

	
	
	

	

}
