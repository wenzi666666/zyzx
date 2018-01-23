package net.tfedu.zhl.cloud.casProxy.action.custom.jimo.controller;

import java.io.IOException;
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
import net.tfedu.zhl.cloud.casProxy.action.custom.jimo.passport.dto.User;
import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.constant.ConstantForUserRole;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.sign.SignUtil;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.service.RegisterService;
import net.zdsoft.passport.entity.Account;
import net.zdsoft.passport.entity.Score;
import net.zdsoft.passport.service.client.PassportClient;

/**
 
  
    @author wangwr
    @date 2017年1月11日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Controller
@RequestMapping("/casProxyJimo/")
public class CasProxyCustomJimo extends CasProxyCustomBase {
	
	/**
	 * 序列化
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;


	
	Logger log = LoggerFactory.getLogger(CasProxyCustomJimo.class);

	
	@Resource
	ThirdPartyCASConfig casConfig;

	@Resource
	SAppService sAppService;

	@Resource
	RegisterService registerService;




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
	
	/**
	 * 即墨的登录接口
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String loginWeiFang(HttpServletRequest request, HttpServletResponse response) throws CustomException, Exception {
		
		SApp app = getApp();

		log.debug("----SApp---"+JSONObject.toJSONString(app));

		
		String ticket = ControllerHelper.getOptionalParameter(request, "ticket");
		String auth = ControllerHelper.getOptionalParameter(request, "auth");
		String input = ControllerHelper.getOptionalParameter(request, "input");
		String url = ControllerHelper.getOptionalParameter(request, "url");

		
		
		
		
		if(StringUtils.isEmpty(ticket)){
			log.error("----Passport ticket为空----");
			
			redirectPassport(request, response);
			
			return null;
		}
		
		
		if(StringUtils.isEmpty(auth)){
			log.error("----Passport校验码为空----");
			redirectPassport(request, response);
			
			return null;
		}
		
		
		PassportClient passportClient=PassportClient.getInstance();
		if (StringUtils.isEmpty(input)) {
		    if (passportClient==null||!passportClient.isValidVerifyAuth(ticket, url, auth)) {
				log.error("----Passport校验码非法----");
				redirectPassport(request, response);
				return null;

		    }
		}else if (passportClient==null||!passportClient.isValidVerifyAuth(ticket, url, input, auth)) {
			log.error("----Passport校验码非法---");
			redirectPassport(request, response);
			return null;
		}
		
		Account account = null;
		try {
		    account = PassportClient.getInstance().checkTicket(ticket);
		}
		catch (Exception e) {
			
			log.error(e.getMessage());
			redirectPassport(request, response);

			return null;
		}
		if (account == null) {
			log.error("Passport ticket[" + ticket + "]对应的帐号不存在");
			redirectPassport(request, response);
		}
		
		log.debug("----jimo--Account----" + JSONObject.toJSONString(account));


		User user = new User();
		user.setAccountId(account.getId());
		user.setUsername(account.getUsername());
		user.setNickname(account.getNickname());

		// 获取用户在Passport里的积分
		Score score = null;
		try {
		    score = PassportClient.getInstance().queryScore(account.getId());

		}
		catch (Exception e) {
			throw new CustomException(e);
			
		}

		// 如果获取到了用户积分, 则设置到用户信息里
		if (score != null) {
		    user.setScore(score.getScore());
		}
		
		
		
		RegisterAddForm form =  new RegisterAddForm();
		form.setTh_uuid(user.getAccountId());
		form.setUserName(user.getUsername());
		form.setNickName(user.getNickname());
		form.setTrueName(account.getRealName());
		//account 性别，1：男、2：女、0：未知
		form.setSex(2==account.getSex()?true:false);
		
		
		form.setProvinceName("山东");
		form.setCityName("青岛市");
		form.setArealName("即墨区");
		form.setSchoolName("即墨市教育体育局");
		form.setRole(ConstantForUserRole.ROLE_SEX_POOL);
		form.setTermName(ConstantForUserRole.DEFAULT_TERMNAME);
		form.setSubjectName(ConstantForUserRole.DEFAULT_SUBJECTMNAME);
		form.setMotto("");
		
		log.debug("----parseAPI---result-------" + JSONObject.toJSONString(form));

		// 同步用户信息
		try {
			Long zhl_userId =  registerService.registerOrUpdateUserWithThirdPartyApp(form, app);
			log.debug("---同步用户信息成功--zhl_userId："+zhl_userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("同步用户信息失败");
		}


		String userName = form.getUserName();

		String thirdCode = app.getPrefix();

		
		long timestamp = Calendar.getInstance().getTimeInMillis();
		

		String sign = SignUtil.createSignMap(new String[]{"userName","dockingCode","appId","timestamp"}
		, new String[]{userName,thirdCode,String.valueOf(app.getAppid()),String.valueOf(timestamp)}
		, app.getAppkey());
		
		
		// 举例：
		// http://192.168.111.204:8880/resRestAPI/thirdparty/autoLoginDocking
		String _url = casConfig.getTARGET_REDIRECT_URL() + "?userName="+userName+"&dockingCode="+thirdCode
				+"&appId="+app.getAppid()+"&timestamp="+timestamp
				+"&sign="+sign;
		log.debug("------casProxy-------url------:" + _url);

		response.sendRedirect(_url);
		
		return null;
	}
	
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws CustomException, Exception {
		 /*// 获取ticket
        String ticket = (String) request.getSession().getAttribute(SessionManager.PASSPORT_TICKET_KEY);
        // 从SessionManager中清除ticket
        SessionManager.removeTicket(ticket);

        // 清除ticket是为了在session失效的时候不再重复通知Passport
        request.getSession().removeAttribute(SessionManager.PASSPORT_TICKET_KEY);
        request.getSession().removeAttribute(User.KEY);
        request.getSession().invalidate();
        // 跳转到Passport的退出系统的地址
        String passportLogoutURL = PassportClient.getInstance().getLogoutURL(ticket, getWebsiteRoot(request), null);
        log.debug("--passportLogoutURL---"+passportLogoutURL);*/
        try {
			response.sendRedirect("http://office.jimoedu.net/index.action");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	 @SuppressWarnings("unused")
	private  String getWebsiteRoot(HttpServletRequest request) {
	        int serverPort = request.getServerPort();
	        return request.getScheme() + "://" + request.getServerName() + (serverPort == 80 ? "" : ":" + serverPort)
	                + request.getContextPath();
	    }
	
	 
	public void redirectPassport(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		
		
		
		String action="casProxyJimo/login";
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+action;
		
		
		String backURL = basePath;
		String contextPath = path;
		
		String url =  PassportClient.getInstance().getLoginURL(backURL, contextPath);
		
		
		
		log.debug("----JimoPassportInitFilter.passportURL---"+url);
		
		
		
		
		response.sendRedirect(url);
		
	}
	
	

}
