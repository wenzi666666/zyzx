package net.tfedu.zhl.sso.thirdpartyAPI.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.httpclient.HttpClientUtils;


/**
 * 第三方对接的工具
 * @author wangwr
 *
 */
@Controller
@RequestMapping("/thirdparty")
public class ThirdPartyContoller {
	
	
	
	
	/**
	 * 登录页面
	 * @param request
	 * @param response
	 * @param platformcode 请求来源
	 * @param targetPage   登录后首页面地址
	 *       /thirdparty/login/jnzx/res_dhjj/       济宁中兴访问资源动画教具库
	 * @return
	 * @throws URISyntaxException 
	 * @throws CustomException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping(value="/login/{platformcode}/{targetPage}")	
	public Object login(HttpServletRequest request ,HttpServletResponse response
			,@PathVariable String platformcode
			,@PathVariable String targetPage) throws ClientProtocolException, IOException,Exception{
		
		
		
		/**
		 * 传递Token   Token=Default--31d04cf9638240cd968425fdcae80e93
		 */
		String  Token = request.getParameter("Token");
		
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("SERVICE_CODE", "zteict.proxy.user.LoginStatus");
		params.put("CONSUMER_ID", "Default--31d04cf9638240cd968425fdcae80e93");

		//接口中传递json数据
		String url = "http://edu.myjining.cn/serviceProxy/servlet/";
		
		url += "json="+JSONObject.toJSONString(params);
		String result = HttpClientUtils.doGet(url);
		
		
		
		
		
		return null ;
	}
	
	
	
	
	
	
	
	

}
