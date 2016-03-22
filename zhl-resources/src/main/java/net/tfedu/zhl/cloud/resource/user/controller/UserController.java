package net.tfedu.zhl.cloud.resource.user.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.user.entity.JUser;
import net.tfedu.zhl.cloud.resource.user.entity.UserSimple;
import net.tfedu.zhl.cloud.resource.user.service.UserService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.entity.SRegister;
import net.tfedu.zhl.sso.service.RegisterService;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("/resRestAPI*")
public class UserController {
	
	
	@Resource
	private UserService userService ;
	
	@Resource
	private RegisterService registerService;
	
	/**
	 * 返回json的结果对象
	 */
	private ResultJSON result = new ResultJSON();
	
	/**
	 * 异常
	 */
	private CustomException exception ;
	
	/**
	 * 登陆、注销
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/v1.0/users/login")
	@ResponseBody
    public ResultJSON Login(HttpServletRequest request, HttpServletResponse response) { 
    	String _method = request.getParameter("_method");
    	//注销
		if(StringUtils.isNotEmpty(_method)&&HttpMethod.DELETE.name().equals(_method)){
			
			
			
			
		}else{
			String userName = request.getParameter("user.name");
			String userPwd = request.getParameter("user.pwd");
			//返回用户的信息
			JUser  data  = null;
			
			try {
				//用户登录
				SRegister reg =  registerService.login(userName, userPwd);
				//获取用户信息
				data =userService.getUserById(reg.getId());
				//成功
				
				
				
				exception = CustomException.SUCCESS;
			} catch (Exception e) {
				exception = CustomException.getCustomExceptionByCode(e.getMessage());
				e.printStackTrace();
			}finally{
				result.setCode(exception.getCode());
				result.setMessage(exception.getMessage());
				result.setData(data);
				result.setSign("");
			}

			
			
		}
    	return result;
    }

	
	
	
	/**
	 * 获取用户信息
	 * @return
	 */
	@RequestMapping(value="/v1.0/users/{id}",method=RequestMethod.GET) 
	@ResponseBody	
	public ResultJSON getUserInfo(@PathVariable Long id){
		
		JUser data =  null ;
		try{
			data =  userService.getUserById(id);
			exception = CustomException.SUCCESS;
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data);
			result.setSign("");			
		}
		return  result;
	}

	

	

}
