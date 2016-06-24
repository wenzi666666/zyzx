package net.tfedu.zhl.sso.thirdpartyAPI.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.sso.thirdpartyAPI.jnzx.entity.ZXCheckResult;


/**
 * 第三方对接的工具
 * @author wangwr
 *
 */
@Controller
@RequestMapping("/resRestAPI/thirdparty")
public class ThirdPartyContoller {
	
	
	
	Logger log = LoggerFactory.getLogger(ThirdPartyContoller.class);
	
	
	
	
	/**
	 * 登录页面
	 * @param request
	 * @param response
	 * @param platformcode 请求来源
	 * @param targetPage   登录后首页面地址
	 * @param thirdPartyToken   第三方登录的token
	 *       /thirdparty/login/jnzx/res_dhjj/xxx       济宁中兴访问资源动画教具库
	 * @return
	 * @throws URISyntaxException 
	 * @throws CustomException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping(value="/v1.0/login/{platformcode}/{targetPage}/{thirdPartyToken}")	
	public Object login(HttpServletRequest request ,HttpServletResponse response
			,@PathVariable String platformcode
			,@PathVariable String targetPage
			,@PathVariable String thirdPartyToken
			) throws ClientProtocolException, IOException,Exception{
		
		
		
		/**
		 * 传递Token   Token=Default--31d04cf9638240cd968425fdcae80e93
		 */
		String  Token = ControllerHelper.checkEmpty(thirdPartyToken);
		Token = Token.replace("--", "-");
		

		//接口中传递json数据
		String url = "http://edu.myjining.cn/serviceProxy/servlet/?json=";
        // 接收参数json列表  
        JSONObject json = new JSONObject();  
        json.put("SERVICE_CODE", "zteict.proxy.user.LoginStatus");
        json.put("CONSUMER_ID", Token);

		String result = HttpClientUtils.doGET(url+URLEncoder.encode(json.toJSONString(), "utf-8"));
		
		ZXCheckResult _result = JSONObject.parseObject(result, ZXCheckResult.class);
		//USER_ID不为空，就说明验证成功
		if(_result!=null && null!=_result.getBODY() && StringUtils.isNotEmpty(_result.getBODY().getUSER_ID())){
			String userName = _result.getBODY().getUSER_ID();
			
			//获取用户信息 
			
	        json.put("SERVICE_CODE", "com.zte.space.homework.business.BusinessCenter4Homework.open__getUserInfo");
	        json.put("CONSUMER_ID", Token);
	        json.put("USER_ID", userName);
	        
	        result = HttpClientUtils.doGET(url+URLEncoder.encode(json.toJSONString(), "utf-8"));
			log.debug(result);
			
			
			
			
			
			
			
			
			
			
			
		}else{
			log.error("校验第三方登录用户失败,平台编码("+platformcode+"),目标页面("+targetPage+"),thirdPartyToken("+thirdPartyToken+")");
			//校验失败
			response.sendRedirect("http://edu.myjining.cn/portal/login.html");
			return null;
		}
		return ResultJSON.getSuccess("") ;
	}
	
	
	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "http://192.168.111.160:8080/zhl-resources/resRestAPI/thirdparty/v1.0/login/jnzx/res_dhjj/";
		
		System.out.println(URLEncoder.encode(url, "utf-8"));
		
	}
	
	

}
