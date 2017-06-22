package net.tfedu.zhl.cloud.resource.thirdpartyAPI.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.tfedu.zhl.cloud.resource.config.ResourceThirdPartyConfig;
import net.tfedu.zhl.cloud.resource.thirdpartyAPI.jnzx.entity.ZXCheckResult;
import net.tfedu.zhl.cloud.resource.thirdpartyAPI.jnzx.entity.ZXUserInfoResult;
import net.tfedu.zhl.cloud.resource.thirdpartyAPI.jnzx.util.JNZXRelativeUtil;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.UserTokenCacheUtil;
import net.tfedu.zhl.sso.th_register.entity.SThirdRegisterRelative;
import net.tfedu.zhl.sso.th_register.service.SThirdRegisterService;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;
import net.tfedu.zhl.userlayer.user.UserImageCheckUtil;
import net.tfedu.zhl.userlayer.user.entity.UserSimple;
import net.tfedu.zhl.userlayer.user.service.JUserService;




/**
 * 第三方对接的工具
 * @author wangwr
 *
 */
@Controller
@RequestMapping("/*RestAPI/thirdparty")
public class ThirdPartyContoller {
	
	
	
	Logger log = LoggerFactory.getLogger(ThirdPartyContoller.class);
	
	@Resource
	private JUserService userService;

	@Resource
	private RegisterService registerService;

	@Resource
	private CommonWebConfig commonWebConfig;
	
	@Resource 
	SThirdRegisterService sThirdRegisterService;

	@Autowired
	CacheManager cacheManager;
	
	
	@Resource
	ResourceThirdPartyConfig resourceThirdPartyConfig;

	
	
	
	
	
	/**
	 * 登录页面
	 * 	1	动画教具库                /thirdparty/login/jnzx/respool_1
		2	名师微课库		/thirdparty/login/jnzx/respool_2
		3	教学案例库		/thirdparty/login/jnzx/respool_3
		4	教学素材库		
		5	多媒体教辅库	/thirdparty/login/jnzx/respool_5
		6	理化生实验室	/thirdparty/login/jnzx/respool_6

	 * @param request
	 * @param response
	 * @param platformcode 请求来源    "jnzx" 为济宁中兴平台
	 * @param targetPage   登录后显示的第一个页面的地址    respool_1 
	 * 
	 * @param thirdPartyToken   第三方登录的token
	 *       /thirdparty/login/jnzx/respool_1/${token}       济宁中兴访问资源动画教具库（systemres）
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
		
		
		
		
		//第三方系统中的用户名
		String userName = null;
		//zhl系统中的用户名
		String zhl_username = null ;
		//zhl系统中的用户id
		long zhl_userid = 0 ;
		
		long roleId = JNZXRelativeUtil.roleId;
		
		/** 
		 * 传递Token   token=Default--31d04cf9638240cd968425fdcae80e93
		 */
		String  token = ControllerHelper.checkEmpty(thirdPartyToken);
		token = token.replace("--", "-");
		
		
		//验证token 并返回
		ZXCheckResult _result = JNZXRelativeUtil.checkToken(token,resourceThirdPartyConfig.getJnzx_host());

		//USER_ID不为空，就说明验证成功
		if(_result!=null && null!=_result.getBODY() && StringUtils.isNotEmpty(_result.getBODY().getUSER_ID())){
			userName = _result.getBODY().getUSER_ID();
			//当前username是否已经映射过
			SThirdRegisterRelative  relative =  sThirdRegisterService.getThirdRelativeResult(userName, platformcode);
			//如果已经映射过，前往目标页面
			SRegister register =  null;
			if(relative!=null && relative.getId()>0){
				
				zhl_userid = Long.parseLong(relative.getZhlUserid());
			}else{
		        register =  registerService.getRegister(userName);
				//获取用户信息 
				ZXUserInfoResult info = JNZXRelativeUtil.getUserInfo(userName, token,resourceThirdPartyConfig.getJnzx_host());						
		        if(register !=null && register.getId()>0){
		    		//如果用户存在 ，修正用户名(在原有的用户名后面增加 “_”和platformcode和序号),如果还有重复，增加序号即：username_jnzx1、username_jnzx2
		    		int i=0;
		    		boolean breakFlag = true ;
		    		while(breakFlag){
		    			zhl_username = new StringBuffer().append(userName).append("_")
		    										.append(platformcode).append(i).toString();
		    			
		    			register =  registerService.getRegister(zhl_username);
		    			//可用的用户名
		    			if(register ==null || register.getId()<1){
		    				breakFlag = false ;
		    			}
		    			i++;
		    		}
		    		//可以注册了
		    		info.getBODY().setUSER_ID(zhl_username);		    		
		    	}
	    		register =  JNZXRelativeUtil.registNewUserByZXAPI(info, registerService,userName, platformcode);
	    		zhl_userid =  register.getId();
			}
			
			// 获取用户信息
			String model= "";
			//获取用户基本信息但不缓存			
			UserSimple	user = userService.getUserSimpleById(zhl_userid, model,commonWebConfig.getIsRepeatLogin(),false);
			//检测用户的头像
			UserImageCheckUtil.checkUserImage(user, commonWebConfig, request);
			user.setLogoutTarget(JNZXRelativeUtil.getOUTURL(resourceThirdPartyConfig.getJnzx_host()));
			user.setThirdParyCode(platformcode);
			//显示将用户信息写入缓存
			String _token = user.getToken();
			UserTokenCacheUtil.addUserInfoCache(model,cacheManager, _token, user, commonWebConfig.getIsRepeatLogin());
			
			if(targetPage.contains("respool_")){
				String frontWebURL = commonWebConfig.getFrontWebURL();
				//组装跳转链接
				String url = new StringBuffer().append(frontWebURL)
						.append("/systemres").append("?tocken=").append(_token)
						.append("&userId=").append(user.getUserId())
						.append("&iscoursewares=").append(platformcode)
						.append("&respool=").append(targetPage.replace("respool_", ""))
						.toString();
				log.info("jnzx---url:"+url);
				response.sendRedirect(url);
				
			}else{
				throw new CustomException("不能处理的访问请求，targetPage:"+targetPage);
			}
			
		}else{
			log.error("校验第三方登录用户失败,平台编码("+platformcode+"),目标页面("+targetPage+"),thirdPartyToken("+thirdPartyToken+")");
			//校验失败
			response.sendRedirect(JNZXRelativeUtil.getOUTURL(resourceThirdPartyConfig.getJnzx_host()));
		}
		return  null;
	}
	

	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "http://192.168.111.160:8080/zhl-resources/resRestAPI/thirdparty/v1.0/login/jnzx/res_dhjj/";
		
		System.out.println(URLEncoder.encode(url, "utf-8"));
		
	}
	
	

}
