package net.tfedu.zhl.cloud.casProxy.action.custom.nation.controller;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.casProxy.action.custom.CasProxyCustomBase;
import net.tfedu.zhl.cloud.casProxy.action.custom.nation.bean.NationTokenInfo;
import net.tfedu.zhl.cloud.casProxy.action.custom.nation.bean.NationUserInfo;
import net.tfedu.zhl.cloud.casProxy.action.custom.nation.util.NationCasUtil;
import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.exception.OutOfDateException;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.ZWXMRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;
import net.tfedu.zhl.sso.users.service.ZWXMRegisterService;

/**
 * 央教馆对接
 * 
 * 增加情景英语、云平台、助我学的对接
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
public class CasProxyCustomNationAppend extends CasProxyCustomBase {

	/**
	 * 序列化
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private static final String PROVINCE_NAME = "北京";
	private static final String CITY_NAME = "北京市";
	private static final String DISTRICT_NAME = "海淀区";
	private static final String SCHOOL_NAME = "国家数字教育资源_公共服务体系";

	Logger log = LoggerFactory.getLogger(CasProxyCustomNationAppend.class);

	@Resource
	ThirdPartyCASConfig casConfig;
	@Resource
	CommonWebConfig webConfig;

	@Resource
	SAppService sAppService;

	@Resource
	RegisterService registerService;
	
	@Resource
	ZWXMRegisterService mRegisterService;

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
	
	
	
	/**
	 * 自动对接到情景英语
	 * @param ticket
	 * @param sysCode
	 * @param request
	 * @param response
	 * @return
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/sceneEnglish")
	public String sceneEnglish(String ticket, String sysCode, HttpServletRequest request, HttpServletResponse response)
			throws CustomException, Exception {

		SApp zhlApp = getApp();


		if (StringUtils.isEmpty(ticket)) {
			throw new CustomException("ticket为空");
		}

		//获取央馆的用户信息
		NationUserInfo userInfo = getNationalUserInfo(ticket, sysCode);
		
		//获取用户名
		String userName = userInfo.getUserId();
		String pwd_str =  zhlApp.getUserdefaultpwd();
		String additional_key = "9k8i78jug6hd93kjf84h";
		// page 学生为1 教师为0
		String page = ("0".equals(userInfo.getDafaultIdentity())) ? "1" : "0";

		String sign = MD5.MD5("user=" + userName + "&pass=" + pwd_str + "&page=" + page + "&key=" + additional_key);
		String s = "user=" + userName + "&pass=" + pwd_str + "&page=" + page + "&sign=" + sign;

		byte[] sbytes = xxtea.encrypt(s.getBytes("utf-8"), additional_key.getBytes());
		s = Base64.encode(sbytes, 0, sbytes.length);
		s = URLEncoder.encode(s, "utf-8");

		// 情景英语
		String url = webConfig.getCurrentSceneEnglish(request).trim() + "?s=" + s;
		System.out.println("------sceneEnglish----" + url);
		response.sendRedirect(url);
		return null;
	}
	
	
	/**
	 * 登录云平台接口
	 * 
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/loginYun")
	public String loginYun(String ticket, String sysCode, HttpServletRequest request, HttpServletResponse response)
			throws CustomException, Exception {

		long nodeId_JX = 1700105;
		
		
		SApp zhlApp = getApp();

		log.debug("----zhlApp---" + JSONObject.toJSONString(zhlApp));

		if (StringUtils.isEmpty(ticket)) {
			throw new CustomException("ticket为空");
		}

		//获取央馆的用户信息
		NationUserInfo userInfo = getNationalUserInfo(ticket, sysCode);
		//将央馆的信息格式化为注册form表单
		RegisterAddForm form = NationCasUtil.formatRegisterFormWithDefaultTeacherRole(userInfo, PROVINCE_NAME, CITY_NAME,
				DISTRICT_NAME,SCHOOL_NAME);

		//设置jx的节点号
		form.setNodeId(nodeId_JX);
		
		log.debug("----parseAPI---result-------" + JSONObject.toJSONString(form));

		// 同步用户信息
		try {
			Long zhl_userId = registerService.registerOrUpdateUserWithThirdPartyApp(form, zhlApp);
			log.debug("---同步用户信息成功--zhl_userId：" + zhl_userId);
		} catch (OutOfDateException e) {
			return "redirect:/common/exception/expired.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("同步用户信息失败");
		}

		String _url = casConfig.getTARGET_REDIRECT_URL()+"?user.name="+form.getUserName()
						+"&user.pwd="+zhlApp.getUserdefaultpwd()+"&checkwd=8888&init_Code=8888";
		System.out.println("------loginYun----" + _url);

		response.sendRedirect(_url);

		return null;
	}
	
	/**
	 * 登录云平台接口
	 * 
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/loginZWX")
	public String loginZWX(String ticket, String sysCode, HttpServletRequest request, HttpServletResponse response)
			throws CustomException, Exception {
		
		SApp zhlApp = getApp();
		
		Long mRegisterId = null ;
		
		log.debug("----zhlApp---" + JSONObject.toJSONString(zhlApp));
		
		if (StringUtils.isEmpty(ticket)) {
			throw new CustomException("ticket为空");
		}
		
		//获取央馆的用户信息
		NationUserInfo userInfo = getNationalUserInfo(ticket, sysCode);
		//将央馆的信息格式化为注册form表单
		RegisterAddForm form = NationCasUtil.formatRegisterFormWithDefaultTeacherRole(userInfo, PROVINCE_NAME, CITY_NAME,
				DISTRICT_NAME,SCHOOL_NAME);
		
		//设为学生
		form.setRole(1L);
		
		log.debug("----parseAPI---result-------" + JSONObject.toJSONString(form));
		
		// 同步用户信息
		try {
			//助我学m_register
			
			mRegisterId = mRegisterService.syncMRegister(form,zhlApp);
			
			log.debug("---同步用户信息成功--mregister：" + mRegisterId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("同步用户信息失败");
		}
		return "redirect:/casProxyNation/check?mRegisterId="+mRegisterId;
	}


	
	
	
	/**
	 * 跳转到此方法，提供二维码
	 * @param mRegisterId
	 * @param request
	 * @param response
	 * @return
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/check")
	public String check(@RequestParam Integer mRegisterId,HttpServletRequest request, HttpServletResponse response)
			throws CustomException, Exception {
		
		ControllerHelper.checkIntegerEmpty(mRegisterId);
		
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		//扫描二位码之后的网址
		String targetPath = basePath+"/casProxyNation/qrcodeAfter?mRegisterId="+mRegisterId;
		
		request.setAttribute("targetPath", targetPath);
		return "zwx";
	}
	
	/**
	 * 跳转到此方法，提供二维码
	 * @param mRegisterId
	 * @param request
	 * @param response
	 * @return
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/qrcodeAfter")
	public String qrcodeAfter(@RequestParam Integer mRegisterId,HttpServletRequest request, HttpServletResponse response)
			throws CustomException, Exception {
		
		ControllerHelper.checkIntegerEmpty(mRegisterId);
		
		ZWXMRegister reg = (ZWXMRegister) mRegisterService.getByPrimaryKey(mRegisterId).getData();
		request.setAttribute("userName", reg.getUsername());
		request.setAttribute("pwd", new String(reg.getUserpwd()));
		return "zwx-intro/index";
	}
	
	

}
