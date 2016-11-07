package net.tfedu.zhl.cloud.resource.autologin;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.utils.security.MD5Util;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.core.exception.UnusualErrorException;
import net.tfedu.zhl.core.exception.WithoutAuthorizationException;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.th_register.entity.SThirdRegisterRelative;
import net.tfedu.zhl.sso.th_register.service.SThirdRegisterService;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.user.service.JUserService;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 公司内部项目（相同的SSO库） 自动登录资源中心，并跳转到页面中
 * 
 * @author wangwr
 *
 */
@Controller
@RequestMapping("/resRestAPI/thirdparty")
public class AutoLoginController {

	/**
	 * 用于生产md5的密钥
	 */
	private static final String MD5_KEY = "9k8i78jug6hd93kjf84h";

	
	/**
	 * 返回类型为json
	 */
	private static final String JSON = "JSON";
	
	

	@Resource
	JUserService userService;

	@Resource
	RegisterService registerService;
	
	@Resource
	SThirdRegisterService SThirdRegisterService;

	@Resource
	private CommonWebConfig commonWebConfig;

	Logger log = LoggerFactory.getLogger(AutoLoginController.class);

	/**
	 * 自动登录的处理方法 所需参数:
	 * 
	 * @param request
	 * @param response
	 * @return 一般采用重定向的方式，跳转到web前端页面，所以返回为null
	 * @throws Exception
	 */
	@RequestMapping("autoLogin")
	@ResponseBody
	public Object autoLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 获取args
		String args = request.getParameter("args");
		// 获取logoutUrl
		String logoutUrl = request.getParameter("logoutUrl");
		//当resultType = "JSON"时，返回json数据
		String resultType = request.getParameter("resultType");
		
		

		// 获取参数
		log.info("----args----:" + args);
		// 格式化参数并获取用户名等信息
		Map<String, String> ps = getParamMap(getParams(args));

		
		
		// 获取校验信息
		String sign = ps.get("sign");
		
		// 获取其他参数信息
		// 用户名
		String userName = ps.get("userName");
		// 对接产品code
		String dockingCode = ps.get("dockingCode");
		// 时间戳
		String timestamp = ps.get("timestamp");
		
		

		// 准备校验
		String temp = "userName=" + userName + "&dockingCode=" + dockingCode + "&timestamp=" + timestamp;
		String _sign = MD5.MD5(temp + "&key=" + MD5_KEY);
		if (StringUtils.isEmpty(sign) || !sign.equals(_sign)) {
			throw new ParamsException();
		}

		// 检查用户信息
		if (StringUtils.isEmpty(userName)) {
			throw new UnusualErrorException();
		}

		// 返回用户的信息
		UserSimple user = null;

		// 用户登录
		SRegister reg = registerService.getRegister(userName);

		if(null == reg ){
			throw new WithoutAuthorizationException(userName);
		}
		
		
		
		
		// 获取用户信息
		user = userService.getUserSimpleById(reg.getId(), "", commonWebConfig.getIsRepeatLogin());

		//设置退出url 和  对接产品的code
		user.setLogoutTarget(null==logoutUrl?"":logoutUrl);
		user.setThirdParyCode(dockingCode);
		
		
		
		
		//组装跳转链接
		String url = new StringBuffer().append(commonWebConfig.getFrontWebURL())
				.append("/systemres").append("?tocken=").append(user.getToken())
				.append("&userId=").append(user.getUserId())
				.append("&iscoursewares=").append(user.getThirdParyCode())
				.toString();
		log.info("autoLogin---url:"+url);
		
		if("JSON".equalsIgnoreCase(resultType)){
			return toJSON(user,request,url);
		}
		
		response.sendRedirect(url);
		return null;
	}
	
	
	/**
	 * 第三方对接自动登录的处理方法 所需参数: 第三方用户名，第三方对接code
	 * 
	 * s_th_register_relative
	 * 
	 * @param request
	 * @param response
	 * @return 一般采用重定向的方式，跳转到web前端页面，所以返回为null
	 * @throws Exception
	 */
	@RequestMapping("autoLoginDocking")
	@ResponseBody
	public Object autoLoginDocking(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 获取args
		String args = request.getParameter("args");
		// 获取logoutUrl
		String logoutUrl = request.getParameter("logoutUrl");
		//当resultType = "JSON"时，返回json数据
		String resultType = request.getParameter("resultType");
		
		

		// 获取参数
		log.info("----args----:" + args);
		// 格式化参数并获取用户名等信息
		Map<String, String> ps = getParamMap(getParams(args));

		
		
		// 获取校验信息
		String sign = ps.get("sign");
		
		// 获取其他参数信息
		// 用户名
		String userName = ps.get("userName");
		// 对接产品code
		String dockingCode = ps.get("dockingCode");
		// 时间戳
		String timestamp = ps.get("timestamp");
		
		

		// 准备校验
		String temp = "userName=" + userName + "&dockingCode=" + dockingCode + "&timestamp=" + timestamp;
		String _sign = MD5.MD5(temp + "&key=" + MD5_KEY);
		if (StringUtils.isEmpty(sign) || !sign.equals(_sign)) {
			throw new ParamsException();
		}

		// 检查用户信息
		if (StringUtils.isEmpty(userName)) {
			throw new UnusualErrorException();
		}

		
		//获取对接后的用户名
		SThirdRegisterRelative  relate = SThirdRegisterService.getThirdRelativeResult(userName, dockingCode);
		if(relate!=null){
			//对接后的用户名
			userName = relate.getZhlUsername();
		}else{
			throw new WithoutAuthorizationException(userName+("(对接用户,dockingCode:"+dockingCode+")"));
		}
		
		
		// 返回用户的信息
		UserSimple user = null;

		// 用户登录
		SRegister reg = registerService.getRegister(userName);

		if(null == reg ){
			throw new WithoutAuthorizationException(userName);
		}
		
		
		
		
		// 获取用户信息
		user = userService.getUserSimpleById(reg.getId(), "", commonWebConfig.getIsRepeatLogin());

		//设置退出url 和  对接产品的code
		user.setLogoutTarget(null==logoutUrl?"":logoutUrl);
		user.setThirdParyCode(dockingCode);
		
		
		
		
		//组装跳转链接
		String url = new StringBuffer().append(commonWebConfig.getFrontWebURL())
				.append("/systemres").append("?tocken=").append(user.getToken())
				.append("&userId=").append(user.getUserId())
				.append("&iscoursewares=").append(user.getThirdParyCode())
				.toString();
		log.info("autoLogin---url:"+url);
		
		if("JSON".equalsIgnoreCase(resultType)){
			return toJSON(user,request,url);
		}
		
		response.sendRedirect(url);
		return null;
	}
	
	
	
	
	
	/**
	 * 自动登录的处理方法(简化版) 所需参数:
	 * 
	 * @param request
	 * @param response
	 * @return 一般采用重定向的方式，跳转到web前端页面，所以返回为null
	 * @throws Exception
	 */
	@RequestMapping("autoLoginSimple")
	@ResponseBody
	public Object autoLoginSimple(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        String strDate = ControllerHelper.getParameter(request, "strDate");
        String  params = ControllerHelper.getParameter(request, "params");
        String userName = ControllerHelper.getParameter(request, "userName");

		
        if(!MD5Util.str2md5(userName + strDate + MD5_KEY).equals(params)){
            throw new CustomException("非法用户");
        }
		
		// 返回用户的信息
		UserSimple user = null;

		// 用户登录
		SRegister reg = registerService.getRegister(userName);

		// 获取用户信息
		user = userService.getUserSimpleById(reg.getId(), "", commonWebConfig.getIsRepeatLogin());

		return  ResultJSON.getSuccess(user);
		
	}
	
	
	
	
	
	
	/**
	 * 返回json 
	 * @param user    当前用户的信息
	 * @param request 请求
	 * @param url     web访问的url
	 * @return
	 */
	@RequestMapping("autoLoginJSON")
	@ResponseBody
	public ResultJSON  toJSON(UserSimple user, HttpServletRequest request, String url){
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("user", user);
		
		map.put("webUrl", url);
		
		
		return ResultJSON.getSuccess(map) ;
	}
	
	
	
	

	/**
	 * 返回解密后的参数
	 * 
	 * @param args
	 * @return
	 * @throws IOException
	 */
	private String getParams(String args) throws IOException {
		if(args.contains(URLEncoder.encode("=", "utf-8"))){
			args = URLDecoder.decode(args, "UTF-8");
		}

		log.info("--getParams---URLDecoder.decode--args:" + args);

		return xxtea.decryptstring(args, MD5_KEY);

	}

	/**
	 * 将参数转换为一个map
	 * 
	 * @param params
	 * @return
	 */
	private Map<String, String> getParamMap(String params) {

		Map<String, String> m = new HashMap<String, String>();
		if (StringUtils.isNotEmpty(params)) {
			String[] ps = params.split("&");
			for (int i = 0; i < ps.length; i++) {
				String p = ps[i];
				String[] kv = p.split("=");
				m.put(kv[0], kv[1]);
			}
		}
		return m;
	}
	
	
	
	
	public static void main(String[] args) {
			String strDate = String.valueOf(Calendar.getInstance().getTimeInMillis());
	        String  params = "";
	        String userName = "csls01";

			
	        params = MD5Util.str2md5(userName + strDate + MD5_KEY);
		
	        
	        System.out.println("/resRestAPI/thirdparty/autoLoginSimple?userName="+userName+"&strDate="+strDate+"&params="+params+"");
	        
		
		
	}

}
