package net.tfedu.zhl.cloud.casProxy.action.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.data.service.BaseDataCommonService;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.service.RegisterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 单点登录通用代理程序 copyRight@知好乐教育技术北京有限公司
 * 
 * @author jiys
 * @date 2016-11-22
 * @version v1.0.0
 */
public abstract class CasProxyCommon2Action {

	Logger log = LoggerFactory.getLogger(CasProxyCommon2Action.class);

	/**
	 * 处理服务接口,通过修改配置文件中的实现类，切换不同的场景
	 */
	@Resource
	ProxyService proxyService;

	@Resource
	ThirdPartyCASConfig casConfig;

	@Resource
	public SAppService sAppService;

	@Resource
	RegisterService registerService;

	@Resource
	BaseDataCommonService baseDataCommonService;

	public abstract void login(HttpServletRequest request,
			HttpServletResponse response) throws CustomException, Exception;

	/**
	 * 得到用户id
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract String getUserId(HttpServletRequest request,
			HttpServletResponse response) throws CustomException;

	/**
	 * 通用登录处理
	 * 
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void loginCommon(HttpServletRequest request,
			HttpServletResponse response) throws CustomException,
			UnsupportedEncodingException, IOException {

		if (null == casConfig || StringUtils.isEmpty(casConfig.getZHL_APPID())
				|| StringUtils.isEmpty(casConfig.getTHIRDPARTY_APPID())) {
			throw new CustomException("APP配置信息异常");
		}

		String appID = casConfig.getZHL_APPID();

		SApp app = sAppService.getSApp(appID);
		if (app == null) {
			throw new CustomException("没有注册APP信息");
		}

		// 获取用户id
		String userid = getUserId(request, response);
		if (StringUtils.isEmpty(userid)) {
			throw new CustomException("NotGetUserId", "获取用户登录id失败");
		}

		log.info("----parseAPI-----with----" + baseDataCommonService.getClass().getName());
		RegisterAddForm form = null;
		try {
			form = baseDataCommonService.parseAPI(userid, casConfig, app);
		} catch (CustomException e1) {
			throw e1;
		}
		if (form == null) {
			throw new CustomException("NotGetToBaseData", "没有获取到用户基础信息");
		}
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

		String key = app.getAppkey();

		String params = "userName=" + userName + "&dockingCode=" + thirdCode
				+ "&timestamp=" + Calendar.getInstance().getTimeInMillis();

		String sign = MD5.MD5(params + "&key=" + key);

		params += ("&appId=" + appID);
		params += ("&sign=" + sign);

		String args = "";

		byte[] sbytes = xxtea.encrypt(params.getBytes("utf-8"), key.getBytes());
		args = Base64.encode(sbytes, 0, sbytes.length);
		args = URLEncoder.encode(args, "utf-8");

		// 举例：
		// http://192.168.111.204:8880/resRestAPI/thirdparty/autoLoginDocking
		String url = casConfig.getTARGET_REDIRECT_URL() + "?args=" + args
				+ "&logoutUrl=" + logoutUrl+"&appId="+app.getAppid();
		log.info("------casProxy-------url------:" + url);

		response.sendRedirect(url);
	}

}