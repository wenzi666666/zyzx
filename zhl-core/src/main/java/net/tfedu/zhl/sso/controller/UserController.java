package net.tfedu.zhl.sso.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.sso.entity.User;
import net.tfedu.zhl.sso.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/showUser",method=RequestMethod.GET)
	@ResponseBody
	public User getByUserId(HttpServletRequest request){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userService.getUserById(userId);
		return user;
	}
}