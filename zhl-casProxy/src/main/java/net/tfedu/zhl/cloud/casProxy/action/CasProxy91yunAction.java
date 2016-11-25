package net.tfedu.zhl.cloud.casProxy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.casProxy.action.base.CasProxyCommon2Action;
import net.tfedu.zhl.cloud.casProxy.util.SignVerify;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.sso.app.entity.SApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 91云校对接 copyRight@知好乐教育技术北京有限公司
 * 
 * @author jiys
 * @date 2016-11-24
 * @version v1.0.0
 */
@Controller
@RequestMapping("/casProxy/")
public class CasProxy91yunAction extends CasProxyCommon2Action {

	@RequestMapping("/login/91yun")
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws CustomException, Exception {
		super.loginCommon(request, response);
	}

	@Override
	public String getUserId(HttpServletRequest request,
			HttpServletResponse response) throws CustomException {
		String uid = "4028803441e9dc4c0141e9dd3c090000";
//		String uid = ControllerHelper.getParameter(request, "uid");
//		String appid = ControllerHelper.getParameter(request, "appid");
//		String sign = ControllerHelper.getParameter(request, "sign");
//		SApp app = sAppService.getSApp(appid);
//		if (app == null) {
//			throw new CustomException("传入appid不正确");
//		}
//		String appkey=app.getAppkey();
//		String signLocal=SignVerify.createSignVerify(uid, app.getAppid(), appkey);
//		if (!StringUtils.equalsIgnoreCase(sign, signLocal)) {
//			throw new CustomException("ParamsIllegal","非法参数");
//		}
		return uid;
	}

}
