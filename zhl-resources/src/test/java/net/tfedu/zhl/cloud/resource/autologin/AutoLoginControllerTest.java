package net.tfedu.zhl.cloud.resource.autologin;

import java.net.URLEncoder;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.autologin.controller.AutoLoginController;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.helper.sign.SignUtil;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

public class AutoLoginControllerTest extends BaseControllerTestCase {
	
	@Resource
	AutoLoginController controller;
	
	
	String appId = "672470";
	String appkey = "a89f9a5cd7c5";
	
	/**
	 * 用于生产md5的密钥
	 */
	private static final String MD5_KEY = "9k8i78jug6hd93kjf84h";

	@Test
	public void test() throws Exception {
		
		//对接产品code
		String dockingCode = "jimo";
		
		if(null!=request.getServletContext().getInitParameter("resourceCenterAutoLoginUrl")){
			dockingCode = request.getServletContext().getInitParameter("resourceCenterAutoLoginUrl");
		}
		
		
		
		String  params = "userName=jszc&dockingCode=jimo&timestamp=1491564760940";
		
		String sign = MD5.MD5(params+"&key="+appkey);
		
		params	+= ("&sign=" + sign);
		
		String args = "";
		
		byte[] sbytes = xxtea.encrypt(params.getBytes("utf-8"),appkey.getBytes());
		args = Base64.encode(sbytes, 0, sbytes.length);
		args = URLEncoder.encode(args, "utf-8");
		
		String logoutUrl = "logout.jsp";
		
		request.setParameter("args", args);
		request.setParameter("logoutUrl", logoutUrl);
//		request.setParameter("appId", "672470");

		controller.autoLogin(request, response);
//		controller.autoLogin(request, response);
		
		
	}
	
	@Test
	public void testAutoLoginDocking() throws Exception {
		
		String args = "9ztQlGZ%2BfUNZ%2BMI4UjF86WGE4rMeB%2BFgvrtxGPM%2FktbVFbbvxOQT7EhXfHRJ8FXS2mc%2Bhl%2Fn%2FXEq5AjDD4met3S4Gs0lC4e87Ez2fGo%2BdfT5%2BC6RBywVA8PNFZwt2UOO";
		String logoutUrl = "";
		
		String resultType = "";
		
		request.setParameter("args", args);
		request.setParameter("logoutUrl", logoutUrl);
		request.setParameter("resultType", resultType);
//		request.setParameter("appId", appId);
		
		
		Object o =  controller.autoLogin(request, response);
		
		System.out.println(JSONObject.toJSONString(o));
		
	}
	
	@Test
	public void testautoLoginDockingSimple() throws Exception {
		String  userName = "qyjiaoyan";
		String  dockingCode = "xlfres";
		String  timestamp = "1491564760940";
		String appId = "540725";
		String appkey = "fff0ed67247c";

		
		request.setParameter("userName", userName);
		request.setParameter("dockingCode", dockingCode);
		request.setParameter("timestamp", timestamp);
		request.setParameter("appId", appId);
		
		
		String sign =  SignUtil.createSign(request, appkey);
		
		
		request.setParameter("sign", sign);

		
		Object o =  controller.autoLoginDockingSimple(request, response);
		
		System.out.println(JSONObject.toJSONString(o));
		
	}

}
