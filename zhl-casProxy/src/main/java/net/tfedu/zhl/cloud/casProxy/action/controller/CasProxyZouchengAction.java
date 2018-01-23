package net.tfedu.zhl.cloud.casProxy.action.controller;

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

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.casProxy.action.custom.jimo.controller.CasProxyCustomJimo;
import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 *
 *
 *
 * 邹城地区对接 copyRight@知好乐教育技术北京有限公司
 * 
 * 根据对接文档 1、提供批量导入（同步）用户信息的功能 2、提供验证接口用于验证是否已经同步用户 3、提供自动跳转链接
 * 
 * @author jiys
 * @date 2016-11-24
 * @version v1.0.0
 */
@Controller
@RequestMapping("casProxy/zoucheng")
public class CasProxyZouchengAction {

	Logger log = LoggerFactory.getLogger(CasProxyZouchengAction.class);

	// 对接约定的密钥
	public static final String key = "9k8i78jug6hd93kjf84h";

	/**
	 * 处理服务接口,通过修改配置文件中的实现类，切换不同的场景
	 */
	@Resource
	ProxyService proxyServiceZoucheng;

	@Resource
	ThirdPartyCASConfig casConfig;

	@Resource
	SAppService sAppService;

	@Resource
	RegisterService registerService;

	
	
	/**
	 * 获取用户的登录id
	 */
	private String getUserId(HttpServletRequest request, HttpServletResponse response) throws CustomException {
		
		return ControllerHelper.getParameter(request, "userid");
	}

	
	
	
	/**
	 * 用户校验，
	 * 第三方导入表中 是否存在这个用户 并且是否已经导入知好乐用户体系
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("userCheck")
	@ResponseBody
	public Object userCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int isSync = 0; // 其中0表示登录ID验证失败，1表示成功

		//是否已经对接过用户
		String userid = getUserId(request, response);
		
		//获取app
		SApp app = getApp();
		
		//从userdata表中获取注册信息
		RegisterAddForm form =  proxyServiceZoucheng.parseAPI(request, casConfig);
		
		//是否已经注册(没有就注册，返回新id)
		Long zhl_userid =  registerService.registerOrUpdateUserWithThirdPartyApp(form, app);
		
		
		//正常方位对接后的用户名
		isSync =  null !=zhl_userid && zhl_userid>0?1:0;
		
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("result", isSync);
		return JSONObject.toJSON(result);
	}

	/**
	 * 登录  只做用户的校验
	 */
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws CustomException, Exception {

		SApp app = getApp();
		
//		String userid = getUserId(request, response);

		log.info("----parseAPI-----with----" + proxyServiceZoucheng.getClass().getName());

		//从userdata表中获取注册信息
		RegisterAddForm form =  proxyServiceZoucheng.parseAPI(request, casConfig);
		
		
		log.info("----parseAPI---result-------" + JSONObject.toJSONString(form));

		// 同步用户信息
		try {
			registerService.registerOrUpdateUserWithThirdPartyApp(form, app);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("同步用户信息失败");
		}

		String logoutUrl = app.getThirdpartylogouturl();

		String userName = form.getUserName();

		String thirdCode = app.getPrefix();

		String params = "userName=" + userName + "&dockingCode=" + thirdCode + "&timestamp="
				+ Calendar.getInstance().getTimeInMillis();

		String sign = MD5.MD5(params + "&key=" + key);

		params += ("&sign=" + sign);

		String args = "";

		byte[] sbytes = xxtea.encrypt(params.getBytes("utf-8"), key.getBytes());
		args = Base64.encode(sbytes, 0, sbytes.length);
		args = URLEncoder.encode(args, "utf-8");

		// 举例：
		// http://192.168.111.204:8880/resRestAPI/thirdparty/autoLoginDocking
		String url = casConfig.getTARGET_REDIRECT_URL() + "?args=" + args + "&logoutUrl=" + logoutUrl;
		log.info("------casProxy-------url------:" + url);

		response.sendRedirect(url);
	}


	/**
	 * 获取app
	 * @return
	 * @throws CustomException
	 */
	protected SApp getApp() throws CustomException {
		if (null == casConfig || StringUtils.isEmpty(casConfig.getZHL_APPID())
				|| StringUtils.isEmpty(casConfig.getTHIRDPARTY_APPID())) {
			throw new CustomException("APP配置信息异常");
		}

		String appID = casConfig.getZHL_APPID();

		SApp app = (SApp) sAppService.getByPrimaryKey(Integer.parseInt(appID)).getData();
		return app;
	}


	

}
