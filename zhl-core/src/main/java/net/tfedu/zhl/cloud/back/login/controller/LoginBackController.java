package net.tfedu.zhl.cloud.back.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.back.user.bean.ManagerSimple;
import net.tfedu.zhl.sso.back.user.service.SBackUserService;

/**
 * copyRight@ 同方知好乐教育科技(北京)有限公司
 * 
 * @author wangwr
 * @date
 * @desc LoginBackController.java
 */
@Controller
@RequestMapping("*BackAPI")
public class LoginBackController {
	
	
	@Resource
	SBackUserService sBackUserService;
	
	@Resource
	CommonWebConfig commonWebConfig;
	

	
	
	
	
	/**
	 * 后台登录
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	@RequestMapping(value="/v1.0/login",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON login(String username, String password,HttpServletRequest request) throws Exception {
		
		ControllerHelper.checkEmpty(username);
		ControllerHelper.checkEmpty(password);
		//登录
		ResultJSON json = sBackUserService.login(username, password,commonWebConfig.getProductCode());
		
		ManagerSimple simple = (ManagerSimple)json.getData();
		//修改登录状态
		sBackUserService.addUserLoginStatusWeb(simple.getUserId(), simple.getNodeId(), simple.getToken(), request);
		
		return json;
	}

	
	/**
	 * 后台退出
	 * @return
	 */
	@RequestMapping(value="/v1.0/logout",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON logout(HttpServletRequest request)throws Exception {
		
		long userId =  ControllerHelper.getCurrentUserId(request);
		
		String token = ControllerHelper.getTokenByURL(request);
		
		sBackUserService.logout(token, userId, commonWebConfig.getIsRepeatLogin());
		
		return ResultJSON.getSuccess("");
	}
	
	
	

}