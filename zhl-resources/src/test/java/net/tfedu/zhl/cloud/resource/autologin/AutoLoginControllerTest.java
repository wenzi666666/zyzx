package net.tfedu.zhl.cloud.resource.autologin;

import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Controller;

import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

public class AutoLoginControllerTest extends BaseControllerTestCase {
	
	@Resource
	AutoLoginController controller;
	
	
	/**
	 * 用于生产md5的密钥
	 */
	private static final String MD5_KEY = "9k8i78jug6hd93kjf84h";

	@Test
	public void test() throws Exception {
		
		//对接产品code
		String dockingCode = "xlfres";
		
		if(null!=request.getServletContext().getInitParameter("resourceCenterAutoLoginUrl")){
			dockingCode = request.getServletContext().getInitParameter("resourceCenterAutoLoginUrl");
		}
		
		
		
		String  params = "userName=csls01&dockingCode="+dockingCode+"&timestamp="+Calendar.getInstance().getTimeInMillis();
		
		String sign = MD5.MD5(params+"&key="+MD5_KEY);
		
		params	+= ("&sign=" + sign);
		
		String args = "";
		
		byte[] sbytes = xxtea.encrypt(params.getBytes("utf-8"),MD5_KEY.getBytes());
		args = Base64.encode(sbytes, 0, sbytes.length);
		args = URLEncoder.encode(args, "utf-8");
		
		String logoutUrl = "logout.jsp";
		
		request.setParameter("args", args);
		request.setParameter("logoutUrl", logoutUrl);
		controller.autoLogin(request, response);
		
	}

}
