package net.tfedu.zhl.sso.user.controller;

import java.util.Arrays;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.cloud.utils.security.VerificationCodeGenerator;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.user.service.UserService;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resRestAPI")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RegisterService registerService;
    
    
    @Resource
    private CommonWebConfig commonWebConfig;
    

    Logger logger = LoggerFactory.getLogger(UserController.class);

    
	
   /**
    * 登录获取验证码 
    * 不用登录
    * @return
    */
   @RequestMapping(value = "/v1.0/verificationcode", method = RequestMethod.GET)
   @ResponseBody
   public ResultJSON getVerificationCode(HttpServletRequest request, HttpServletResponse response) {

       // 返回json的结果对象
       ResultJSON result = new ResultJSON();
       // 异常
       CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
       // 当前登录用户id
       Long currentUserId = (Long) request.getAttribute("currentUserId");
       // 返回
       Object data = null;

 	   data = VerificationCodeGenerator.getCode();
   	   exception = CustomException.SUCCESS;
       result.setCode(exception.getCode());
       result.setMessage(exception.getMessage());
       result.setData(data == null ? "" : data);
       result.setSign("");
       return result;
   }
    
    
    
    
    
    /**
     * 登陆、注销
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/v1.0/users/login")
    @ResponseBody
    public ResultJSON Login(HttpServletRequest request, HttpServletResponse response) {

        // 返回json的结果对象
        ResultJSON result = new ResultJSON();
        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        String _method = request.getParameter("_method");

        // 不同的子系统，使用不同的model参数
        String model = request.getParameter("model") == null ? " " : request.getParameter("model");

        // 注销
        if (StringUtils.isNotEmpty(_method) && HttpMethod.DELETE.name().equals(_method)) {

            try {

                if (currentUserId != null && exception == null) {
                    String token = request.getHeader("Authorization");
                    userService.logout(token);
                    exception = CustomException.SUCCESS;
                }else{
                	exception = CustomException.INVALIDACCESSTOKEN;
                }
            } catch (Exception e) {
                exception = CustomException.getCustomExceptionByCode(e.getMessage());
                // 如果是普通的异常
                if (exception.getStatus() == 500) {
                    e.printStackTrace();
                }
            } finally {
                result.setCode(exception.getCode());
                result.setMessage(exception.getMessage());
                result.setData("");
                result.setSign("");
            }
        } else {
            String userName = request.getParameter("user_name");
            String userPwd = request.getParameter("user_pwd");
            // 返回用户的信息
//            UserLoginResultInfo data = new UserLoginResultInfo();
            UserSimple user = null;
            try {
                // 用户登录
                SRegister reg = registerService.login(userName, userPwd);
                // 获取用户信息
                user = userService.getUserSimpleById(reg.getId(), model);

                //如果头像不是系统头像，而是在文件服务中保存的头像的话，需要修改userimage 为 （文件服务中保存的）头像的可访问路径
				if(user.getUserImage()!=null && user.getUserImage().trim().contains(ZhlResourceCenterWrap.userimage_upload_prefix)){
					
					//获取文件服务器的访问url 
					String resServiceLocal = commonWebConfig.getResServiceLocal();
					String currentResPath = commonWebConfig.getCurrentResPath(request);

					
					String temp = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, user.getUserImage()) ;
					temp = temp.replace(resServiceLocal, currentResPath);
					user.setUserImage(temp);
				}
                
                
//                // 成功,增加用户的在线信息
//                Boolean repeatLoginVaildFlag = false;// repeatLoginVaildFlag资源中心不允许一个用户重复登录
//                JOnlineUsers online = jOnlineUsersService.getUserOnlines(reg.getId(), request, repeatLoginVaildFlag);

//                BeanUtils.copyProperties(data, user);
//                data.setToken(user.getToken());
                exception = CustomException.SUCCESS;
            } catch (Exception e) {
                exception = CustomException.getCustomExceptionByCode(e.getMessage());
                // 如果是普通的异常
                if (exception.getStatus() == 500) {
                    e.printStackTrace();
                }
            } finally {
                result.setCode(exception.getCode());
                result.setMessage(exception.getMessage());
                result.setData(user == null ? "" : user);
                result.setSign("");
            }
        }
        return result;
    }

    /**
     * 获取用户信息
     * 
     * @return
     */
    @RequestMapping(value = "/v1.0/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getUserInfo(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        // 返回json的结果对象
        ResultJSON result = new ResultJSON();
        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        // 不同的子系统，使用不同的model参数
        String model = request.getParameter("model") == null ? " " : request.getParameter("model");

        // 返回
        Object data = null;

        try {
            if (currentUserId != null && exception == null) {
                data = userService.getUserSimpleById(id, model);
                exception = CustomException.SUCCESS;
            }else{
            	exception = CustomException.INVALIDACCESSTOKEN;
            }
        } catch (Exception e) {
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            // 如果是普通的异常
            if (exception.getStatus() == 500) {
                e.printStackTrace();
            }
        } finally {
            result.setCode(exception.getCode());
            result.setMessage(exception.getMessage());
            result.setData(data == null ? "" : data);
            result.setSign("");
        }
        return result;
    }

    /**
     * 修改用户信息
     * 
     * @return
     */
    @RequestMapping(value = "/v1.0/users/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResultJSON updateUserInfo(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        // 返回json的结果对象
        ResultJSON result = new ResultJSON();
        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        // 返回
        Object data = null;

        try {
            if (currentUserId != null && exception == null) {
                long userId = currentUserId;
                boolean male = false;
                long termId = 0;
                long subjectId = 0;
                String trueName = request.getParameter("trueName");
                String _termId = request.getParameter("termId");
                String _subjectId = request.getParameter("subjectId");
                String _male = request.getParameter("male");
                String _method = request.getParameter("_method");

                if (StringUtils.isNotEmpty(_male) && ("Y".equalsIgnoreCase(_male) || "true".equalsIgnoreCase(_male))) {
                    male = true;
                }
                if (StringUtils.isNotEmpty(_termId)) {
                    termId = Long.parseLong(_termId);
                }
                if (StringUtils.isNotEmpty(_subjectId)) {
                    subjectId = Long.parseLong(_subjectId);
                }

                if (!RequestMethod.PATCH.name().equals(_method)) {// _method!=patch
                    exception = CustomException.PARAMSERROR;
                } else {

                	String token = request.getHeader("Authorization");
                	userService.updateUserInfo(userId, trueName, male, termId, subjectId);
                    
                    exception = CustomException.SUCCESS;
                    data = "";
                }
            }else{
            	exception = CustomException.INVALIDACCESSTOKEN;
            }

        } catch (Exception e) {
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            // 如果是普通的异常
            if (exception.getStatus() == 500) {
                e.printStackTrace();
            }
        } finally {
            result.setCode(exception.getCode());
            result.setMessage(exception.getMessage());
            result.setData(data);
            result.setSign("");
        }
        return result;
    }

    /**
     * 修改用户信息
     * 
     * @return
     */
    @RequestMapping(value = "/v1.0/users/userimage/{userid}", method = RequestMethod.POST)
    @ResponseBody
    public ResultJSON updateUserImage(@PathVariable Long userid, HttpServletRequest request,
            HttpServletResponse response) {

        // 返回json的结果对象
        ResultJSON result = new ResultJSON();
        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        // 返回
        Object data = null;

        try {
            if (currentUserId != null && exception == null) {
                long userId = currentUserId;
                String userImage = request.getParameter("userImage");
                userService.updateUserImage(userId, userImage);
                exception = CustomException.SUCCESS;
                data = "";
            }else{
            	exception = CustomException.INVALIDACCESSTOKEN;
            }
        } catch (Exception e) {
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            // 如果是普通的异常
            if (exception.getStatus() == 500) {
                e.printStackTrace();
            }
        } finally {
            result.setCode(exception.getCode());
            result.setMessage(exception.getMessage());
            result.setData(data == null ? "" : data);
            result.setSign("");
        }
        return result;
    }

    /**
     * 修改用户密码
     * 
     * @return
     */
    @RequestMapping(value = "/v1.0/users/password", method = RequestMethod.POST)
    @ResponseBody
    public ResultJSON updateUserPwd(HttpServletRequest request, HttpServletResponse response) {

        // 返回json的结果对象
        ResultJSON result = new ResultJSON();
        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        // 返回
        Object data = null;

        try {

            if (currentUserId != null && exception == null) {
                long userId = currentUserId;
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                String _method = request.getParameter("_method");
                byte[] temp = PWDEncrypt.doEncryptByte(oldPassword);

                if (!RequestMethod.PATCH.name().equals(_method)) {// _method!=patch
                    exception = CustomException.PARAMSERROR;
                } else {
                    SRegister register = registerService.getRegister(userId);

                    if (register != null) {
                        byte[] pwd = register.getPwd();
                        // 旧密码是否匹配
                        if (register != null && !Arrays.equals(register.getPwd(), temp)) {
                            exception = CustomException.INVALIDPASSWORD;
                        } else {
                            registerService.modifyRegisterPassword(userId, newPassword);
                            exception = CustomException.SUCCESS;
                            data = "";
                        }
                    } else {
                        exception = CustomException.INVALIDPASSWORD;
                    }

                }
            }else{
            	exception = CustomException.INVALIDACCESSTOKEN;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exception = CustomException.getCustomExceptionByCode(e.getMessage());

        } finally {
            result.setCode(exception.getCode());
            result.setMessage(exception.getMessage());
            System.out.println("----data-----" + data.toString());
            result.setData(data == null ? "" : data);
            result.setSign("");
        }
        return result;
    }

    


    
    /**
	 * 获取用户头像的专用上传链接
	 * 
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/users/userImageUploadUrl",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON  getUserImageUrl(HttpServletRequest request, HttpServletResponse response){
		//返回json的结果对象
		ResultJSON result = new ResultJSON();
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		//返回
		Object data = null;
		
		try{
			if(currentUserId!=null && exception==null){	
				//获取文件服务器的访问url 
				String resServiceLocal = commonWebConfig.getResServiceLocal();
				String currentResPath = commonWebConfig.getCurrentResPath(request);
				String hostLocal =commonWebConfig.getHostLocalOne();
				
				long userId = currentUserId;
				JUser  user =  userService.getUserById(userId);
				long schoolId = user.getSchoolid();
				//组装上传路径
				String uploadPath = ZhlResourceCenterWrap.getUserImageUploadPath(userId, schoolId);
				//获取上传文件路径
				String uploadUrl=  ZhlResourceCenterWrap.getUploadUrlConvert(uploadPath, resServiceLocal, currentResPath, hostLocal, userId);
				
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("uploadUrl", uploadUrl);
				map.put("uploadPath", uploadPath);
				data = map ; 
				exception = CustomException.SUCCESS;
			}else{
            	exception = CustomException.INVALIDACCESSTOKEN;
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
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;		
		
	}
	
	
    
    

}
