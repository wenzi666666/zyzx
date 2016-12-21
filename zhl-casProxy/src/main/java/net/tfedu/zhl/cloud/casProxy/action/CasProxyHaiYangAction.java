package net.tfedu.zhl.cloud.casProxy.action;

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

import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 
 * 单点登录通用代理程序
 * 
 * @author wangwr
 * @date 2016年11月16日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 * 
 */
@Controller
@RequestMapping("/casProxyHaiyang/")
public class CasProxyHaiYangAction {

	Logger log = LoggerFactory.getLogger(CasProxyHaiYangAction.class);

	// 对接约定的密钥
	public static final String key = "9k8i78jug6hd93kjf84h";

	/**
	 * 处理服务接口,通过修改配置文件中的实现类，切换不同的场景
	 */
	@Resource
	ProxyService proxyServiceHaiyang;

	@Resource
	ThirdPartyCASConfig casConfig;

	@Resource
	SAppService sAppService;

	@Resource
	RegisterService registerService;

	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws CustomException, Exception {

		if (null == casConfig || StringUtils.isEmpty(casConfig.getZHL_APPID())
				|| StringUtils.isEmpty(casConfig.getTHIRDPARTY_APPID())) {
			throw new CustomException("APP配置信息异常");
		}

		String appID = casConfig.getZHL_APPID();

		SApp app = (SApp) sAppService.getByPrimaryKey(Integer.parseInt(appID)).getData();

		log.info("----parseAPI-----with----" + proxyServiceHaiyang.getClass().getName());

		RegisterAddForm form = proxyServiceHaiyang.parseAPI(request, casConfig);

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

}