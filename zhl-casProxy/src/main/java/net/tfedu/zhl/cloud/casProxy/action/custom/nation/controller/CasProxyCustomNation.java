package net.tfedu.zhl.cloud.casProxy.action.custom.nation.controller;

import java.net.URLEncoder;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.casProxy.action.custom.CasProxyCustomBase;
import net.tfedu.zhl.cloud.casProxy.action.custom.nation.bean.NationTokenInfo;
import net.tfedu.zhl.cloud.casProxy.action.custom.nation.bean.NationUserInfo;
import net.tfedu.zhl.cloud.casProxy.action.custom.nation.util.NationCasUtil;
import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.constant.ConstantForUserRole;
import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.helper.sign.SignUtil;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 央教馆对接资源中心
 * 
 * 
 * @author wangwr
 * @date 2017年9月18日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
@RequestMapping("/casProxyNation/")
public class CasProxyCustomNation extends CasProxyCustomBase {

	/**
	 * 序列化
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private static final String PROVINCE_NAME = "北京";
	private static final String CITY_NAME = "北京市";
	private static final String DISTRICT_NAME = "海淀区";
	private static final String SCHOOL_NAME = "国家数字教育资源_公共服务体系";

	Logger log = LoggerFactory.getLogger(CasProxyCustomNation.class);

	@Resource
	ThirdPartyCASConfig casConfig;
	@Resource
	CommonWebConfig webConfig;

	@Resource
	SAppService sAppService;

	@Resource
	RegisterService registerService;

	/**
	 * 获取app
	 * 
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

	/**
	 * 登录接口
	 * 
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(String ticket, String sysCode, HttpServletRequest request, HttpServletResponse response)
			throws CustomException, Exception {

		SApp zhlApp = getApp();

		log.debug("----zhlApp---" + JSONObject.toJSONString(zhlApp));

		if (StringUtils.isEmpty(ticket)) {
			throw new CustomException("ticket为空");
		}

		//获取央馆的用户信息
		NationUserInfo userInfo = getNationalUserInfo(ticket, sysCode);

		RegisterAddForm form = new RegisterAddForm();
		form.setTh_uuid(userInfo.getUserId());
		form.setUserName(userInfo.getUserId());
		form.setNickName(userInfo.getName());
		form.setTrueName(userInfo.getName());
		// 性别：0-未知；1-男；2-女
		String gender = userInfo.getGender();
		form.setSex("2".equals(gender) ? true : false);

		form.setProvinceName(PROVINCE_NAME);
		form.setCityName(CITY_NAME);
		form.setArealName(DISTRICT_NAME);
		form.setSchoolName(SCHOOL_NAME);
		form.setRole(ConstantForUserRole.ROLE_SEX_POOL);
		form.setTermName(ConstantForUserRole.DEFAULT_TERMNAME);
		form.setSubjectName(ConstantForUserRole.DEFAULT_SUBJECTMNAME);
		form.setMotto("");

		log.debug("----parseAPI---result-------" + JSONObject.toJSONString(form));

		// 同步用户信息
		try {
			Long zhl_userId = registerService.registerOrUpdateUserWithThirdPartyApp(form, zhlApp);
			log.debug("---同步用户信息成功--zhl_userId：" + zhl_userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("同步用户信息失败");
		}

		String userName = form.getUserName();

		String thirdCode = zhlApp.getPrefix();

		long timestamp = Calendar.getInstance().getTimeInMillis();

		String sign = SignUtil.createSignMap(new String[] { "userName", "dockingCode", "appId", "timestamp" },
				new String[] { userName, thirdCode, String.valueOf(zhlApp.getAppid()), String.valueOf(timestamp) },
				zhlApp.getAppkey());

		// 举例：
		// http://192.168.111.204:8880/resRestAPI/thirdparty/autoLoginDocking
		String _url = casConfig.getTARGET_REDIRECT_URL() + "?userName=" + userName + "&dockingCode=" + thirdCode
				+ "&appId=" + zhlApp.getAppid() + "&timestamp=" + timestamp + "&sign=" + sign;
		log.debug("------casProxy-------url------:" + _url);

		response.sendRedirect(_url);

		return null;
	}

	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws CustomException, Exception {

	}

	@SuppressWarnings("unused")
	private String getWebsiteRoot(HttpServletRequest request) {
		int serverPort = request.getServerPort();
		return request.getScheme() + "://" + request.getServerName() + (serverPort == 80 ? "" : ":" + serverPort)
				+ request.getContextPath();
	}
	
	
	
	/**
	 * 获取央馆的用户信息
	 * 
	 * @param ticket
	 * @param sysCode
	 * @return
	 * @throws Exception
	 */
	protected NationUserInfo getNationalUserInfo(String ticket, String sysCode) throws Exception {
		String appId = casConfig.getTHIRDPARTY_APPID();
		String appKey = casConfig.getTHIRDPARTY_APPKEY();
		String apiServer = casConfig.getTHIRDPARTY_API_SERVER();

		NationTokenInfo info = NationCasUtil.getToken(appId, appKey, apiServer, sysCode);

		System.out.println("---NationTokenInfo---" + info);

		String accessToken = info.getAccessToken();

		NationUserInfo userInfo = NationCasUtil.getUserInfoByCheckTicket(apiServer, accessToken, ticket);

		System.out.println("---userInfo---" + userInfo);
		return userInfo;
	}
	
	

	
	
	
	

}
