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
import net.tfedu.zhl.cloud.casProxy.model.AddressEntity;
import net.tfedu.zhl.cloud.casProxy.service.AreaScopeLimitationService;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.cloud.casProxy.util.AddressUtils;
import net.tfedu.zhl.cloud.casProxy.util.HttpIpUtil;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.helper.CacheUtil;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.service.RegisterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

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

	@Resource
	AreaScopeLimitationService areaScopeLimitationService;

	/**
	 * 缓存管理
	 */
	@Autowired
	CacheManager cacheManager;

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
	 * 地域访问限制 如果不需要限制地域，可以不实现此方法
	 * 
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public abstract void areaScopeLimitation(HttpServletRequest request,
			HttpServletResponse response) throws CustomException,
			UnsupportedEncodingException, IOException;

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
		// 地域访问限制
		areaScopeLimitation(request, response);

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

		log.info("----parseAPI-----with----"
				+ baseDataCommonService.getClass().getName());
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
				+ "&logoutUrl=" + logoutUrl + "&appId=" + app.getAppid();
		log.info("------casProxy-------url------:" + url);
		/*
		 * 第三方平台对接调整地址
		 * http://192.168.111.204:8880/resRestAPI/thirdparty/autoLoginDocking
		 * ?args
		 * =f0%2FYwDWgewhf9tjRDUMgbiCMEgrWwFagpOOsJr2FjMVOV6EuVImObT5RCLzQK%
		 * 2Bd%2Bm%2
		 * B4HSBqNAn6VR3zlkpx9rUlhPcLrcNhOh5cvikqtekD43FUAASuPJqYbjZzzH94EcypIZmK9TXQl2OCBLtf6c5crxHc
		 * %3D&logoutUrl=暂无&appId=273497
		 */
		response.sendRedirect(url);
	}

	/**
	 * 地域访问限制
	 * 
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void areaScopeLimitationCommon(HttpServletRequest request,
			HttpServletResponse response) throws CustomException,
			UnsupportedEncodingException, IOException {
		// 获取客户端的真实IP地址
		String ip = HttpIpUtil.getIpAddr(request);// "115.34.102.75";//
		// 测试获取客户端真实ip
		// if(StringUtils.isNotEmpty(ip)){
		// throw new CustomException("ip", ip);
		// }
		Object isAllowToUseObj = CacheUtil.get(cacheManager,
				CacheUtil.CACHE_APP, ip);
		boolean isAllowToUse = false;
		if (isAllowToUseObj == null
				|| StringUtils.isEmpty(isAllowToUseObj.toString())) {
			// 根据真实IP获取地域信息（省市区县）
			AddressEntity addressEntity;
			try {
				addressEntity = AddressUtils.getAddresses(ip);
			} catch (Exception e) {
				throw new CustomException("NotGetAddresses", "没有获取到客户端访问所在区域信息");
			}
			// 如果地域信息为空
			if (addressEntity == null
					|| StringUtils.isEmpty(addressEntity.getRegion_id())) {
				throw new CustomException("NotGetAddresses", "没有获取到客户端访问所在区域信息");
			}
			String cityCode = addressEntity.getCity_id();
			String provinceCode = addressEntity.getRegion_id();
			isAllowToUse = areaScopeLimitationService.isAllowToUse(cityCode,
					provinceCode);
			CacheUtil.put(cacheManager, CacheUtil.CACHE_APP, ip, isAllowToUse);
		} else {
			isAllowToUse = (Boolean) isAllowToUseObj;
		}
		if (!isAllowToUse) {
			throw new CustomException("AreaPermissionDenied", "该地域没有权限使用");
		}
	}

}