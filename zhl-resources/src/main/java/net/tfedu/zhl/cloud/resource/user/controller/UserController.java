package net.tfedu.zhl.cloud.resource.user.controller;


import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.core.online.entity.JOnlineUsers;
import net.tfedu.zhl.cloud.core.online.service.JOnlineUsersService;
import net.tfedu.zhl.cloud.resource.user.entity.JUser;
import net.tfedu.zhl.cloud.resource.user.entity.UserSimple;
import net.tfedu.zhl.cloud.resource.user.service.UserService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.entity.SRegister;
import net.tfedu.zhl.sso.service.RegisterService;

import org.apache.commons.beanutils.BeanUtils;
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
	
	@Resource
	private JOnlineUsersService jOnlineUsersService;
	
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
					String token = request.getHeader("Authorization");
					try {
						if(token == null){
							exception = CustomException.NOTOKEN;
						}else{
							jOnlineUsersService.logout(token);
							exception = CustomException.SUCCESS;
						}
						
					} catch (Exception e) {
						exception = CustomException.getCustomExceptionByCode(e.getMessage());
						//如果是普通的异常
						if(exception.getStatus()==500){
							e.printStackTrace();
						}
					}finally{
						result.setCode(exception.getCode());
						result.setMessage(exception.getMessage());
						result.setData("");
						result.setSign("");
					}
		}else{
					String userName = request.getParameter("user_name");
					String userPwd = request.getParameter("user_pwd");
					//返回用户的信息
					UserLoginResultInfo  data  = new UserLoginResultInfo();
					try {
						//用户登录
						SRegister reg =  registerService.login(userName, userPwd);
						//获取用户信息
						UserSimple user =userService.getUserSimpleById(reg.getId());
						//成功,增加用户的在线信息		
						Boolean repeatLoginVaildFlag = false ;//repeatLoginVaildFlag资源中心不允许一个用户重复登录
						JOnlineUsers online =  jOnlineUsersService.getUserOnlines(reg.getId(), request,repeatLoginVaildFlag);
					
						BeanUtils.copyProperties(data, user);
						data.setToken(online.getToken());
						exception = CustomException.SUCCESS;
					} catch (Exception e) {
						exception = CustomException.getCustomExceptionByCode(e.getMessage());
						//如果是普通的异常
						if(exception.getStatus()==500){
							e.printStackTrace();
						}
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
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data);
			result.setSign("");			
		}
		return  result;
	}

	
	
	/**
	 * 修改用户信息
	 * @return
	 */
	@RequestMapping(value="/v1.0/users/{id}",method=RequestMethod.POST) 
	@ResponseBody	
	public ResultJSON updateUserInfo(@PathVariable Long id,HttpServletRequest request, HttpServletResponse response){
		
		String token = request.getHeader("Authorization");
		String  data =  "" ;
		try{
			
			long userId = 0 ;
			boolean male = false ;
			long termId = 0 ;
			long subjectId = 0 ;
			String  trueName = request.getParameter("trueName");
			String  _termId = request.getParameter("termId");
			String  _subjectId = request.getParameter("subjectId");
			String  _male = request.getParameter("male");
			String  _method = request.getParameter("_method");
			String  userImage = request.getParameter("userImage");

			if(StringUtils.isNotEmpty(_male)&&("Y".equalsIgnoreCase(_male)||"true".equalsIgnoreCase(_male))){
				male = true ;
			}
			if(StringUtils.isNotEmpty(_termId)){
				termId = Long.parseLong(_termId);				
			}
			if(StringUtils.isNotEmpty(_subjectId)){
				subjectId = Long.parseLong(_subjectId);
			}
			
			
			if(token == null){//没有token
				exception = CustomException.NOTOKEN;
			}else{
				userId =   jOnlineUsersService.getUserOnlinesByToken(token).getUserid();				
			}

			
			if(!RequestMethod.PATCH.name().equals(_method)){//_method!=patch
				exception = CustomException.PARAMSERROR;
			}else{
				
				userService.updateUserInfo(userId, trueName, male, termId, subjectId);
				
				exception = CustomException.SUCCESS;
			}
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data);
			result.setSign("");			
		}
		return  result;
	}

	
	/**
	 * 修改用户信息
	 * @return
	 */
	@RequestMapping(value="/v1.0/users/userimage/{userid}",method=RequestMethod.POST) 
	@ResponseBody	
	public ResultJSON updateUserImage(@PathVariable Long userid,HttpServletRequest request, HttpServletResponse response){
		
		String token = request.getHeader("Authorization");
		String  data =  "" ;
		try{
			
			String  userImage = request.getParameter("userImage");
			if(token == null){//没有token
				exception = CustomException.NOTOKEN;
			}else{
				if(userid !=  jOnlineUsersService.getUserOnlinesByToken(token).getUserid()){ 				
					exception = CustomException.INVALIDACCESSTOKEN;
				}
			}
			userService.updateUserImage(userid, userImage);
			exception = CustomException.SUCCESS;
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data);
			result.setSign("");			
		}
		return  result;
	}

	
	
	
	
	
	/**
	 * 修改用户密码
	 * @return
	 */
	@RequestMapping(value="/v1.0/users/password",method=RequestMethod.POST) 
	@ResponseBody	
	public ResultJSON updateUserPwd(HttpServletRequest request, HttpServletResponse response){
		try{
			String token = request.getHeader("Authorization");		
			String oldPassword =  request.getParameter("oldPassword");
			String newPassword =  request.getParameter("newPassword");
			String _method =  request.getParameter("_method");
			long userId = 0 ;
			if(token == null){
				exception = CustomException.NOTOKEN;
			}else{
				userId = jOnlineUsersService.getUserOnlinesByToken(token).getUserid();
			}
			
			
			if(!RequestMethod.PATCH.name().equals(_method)){//_method!=patch
				exception = CustomException.PARAMSERROR;
			}else{
				SRegister register =  registerService.getRegister(userId);
				byte[] pwd = register.getPwd();
				
				byte[] temp = PWDEncrypt.doEncryptByte(oldPassword);
				//旧密码是否匹配
				if(register!=null&&!Arrays.equals(register.getPwd(), PWDEncrypt.doEncryptByte(oldPassword))){
					exception = CustomException.INVALIDPASSWORD;
				}else{
					registerService.modifyRegisterPassword(userId, newPassword);
					exception = CustomException.SUCCESS;
				}
			}
			
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData("");
			result.setSign("");			
		}
		return  result;
	} 
	
	
	
	
	

	

}
