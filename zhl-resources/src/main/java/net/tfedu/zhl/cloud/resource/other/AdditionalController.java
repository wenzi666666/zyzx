package net.tfedu.zhl.cloud.resource.other;

import java.net.URLEncoder;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.resource.config.ResourceWebConfig;
import net.tfedu.zhl.cloud.resource.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resource.navigation.service.UserDefaultService;
import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 资源中心3.1 接入公司其他产品（非第三方对接） 登录用户可以使用
 * 
 * @author wangwr
 *
 */

@Controller
@RequestMapping("/resRestAPI/additional")
public class AdditionalController {

	@Resource
	RegisterService regSerivce;

	@Resource
	CommonWebConfig config;

	@Resource
	ResourceWebConfig resConfig;

	@Resource
	UserDefaultService userDefaultService;

	/**
	 * 约定的key
	 */
	private static final String additional_key = "9k8i78jug6hd93kjf84h";

	/**
	 * 情景英语
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sceneEnglish", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON sceneEnglish(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 当前登录用户id
		Long currentUserId = (Long) request.getAttribute("currentUserId");

		SRegister register = regSerivce.getRegister(currentUserId);
		String userName = register.getName();
		String pwd_str = PWDEncrypt.getPWD(register.getPwd());
		// page 学生为1 教师为0
		String page = "1".equals(register.getRoleid().toString()) ? "1" : "0";

		String sign = MD5.MD5("user=" + userName + "&pass=" + pwd_str + "&page=" + page + "&key=" + additional_key);
		String s = "user=" + userName + "&pass=" + pwd_str + "&page=" + page + "&sign=" + sign;

		byte[] sbytes = xxtea.encrypt(s.getBytes("utf-8"), additional_key.getBytes());
		s = Base64.encode(sbytes, 0, sbytes.length);
		s = URLEncoder.encode(s, "utf-8");

		// 情景英语
		String url = config.getCurrentSceneEnglish(request).trim() + "?s=" + s;
		System.out.println("------sceneEnglish----" + url);
		return ResultJSON.getSuccess(url);
	}

	/**
	 * 题库
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/questionLibrary", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON questionLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 当前登录用户id
		Long currentUserId = (Long) request.getAttribute("currentUserId");

		JUserDefault userDefault = userDefaultService.getUserHistoryDefault(currentUserId, 1);

		SRegister register = regSerivce.getRegister(currentUserId);
		String userName = register.getName();
		String password = PWDEncrypt.getPWD(register.getPwd());

		// userName = "tfzuyalan";
		// password = "000000";

		String key = additional_key;
		String params = "username=" + userName + "&userpwd=" + password;
		String _sign = MD5.MD5(params + "&key=" + key);
		params += "&sign=" + _sign;
		byte[] b1 = xxtea.encrypt(params.getBytes("utf-8"), key.getBytes("utf-8"));
		String s1 = Base64.encode(b1);
		s1 = URLEncoder.encode(s1, "utf-8");

		String url = config.getCurrentTkHost(request) + "?args=" + s1 + "&platform=resourcecenter&tfcode="
				+ (userDefault == null ? "" : userDefault.getTfcode());
		System.out.println("------questionLibrary----" + url);
		return ResultJSON.getSuccess(url);
	}

	/**
	 * 论坛3.0
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/forum3", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON forum3(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 当前登录用户id
		Long currentUserId = (Long) request.getAttribute("currentUserId");

		SRegister register = regSerivce.getRegister(currentUserId);
		String userName = register.getName();
		String password = PWDEncrypt.getPWD(register.getPwd());
		String forum3 = config.getCurrentForum3(request);
		forum3 += "?username=" + userName + "&token=" + UUID.randomUUID().toString();
		System.out.println("------forum3----" + forum3);
		return ResultJSON.getSuccess(forum3);
	}

	/**
	 * 论坛3.0(old)
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/forum3_old", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON forum3_old(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 当前登录用户id
		Long currentUserId = (Long) request.getAttribute("currentUserId");

		SRegister register = regSerivce.getRegister(currentUserId);
		String userName = register.getName();
		String password = PWDEncrypt.getPWD(register.getPwd());

		String url = config.getCurrentForum3(request);
		if (url == null || "".equals(url.trim())) {
			url = "http://192.168.111.175:1337/auth/process";
		}
		String token = MD5.MD5(userName + "&key=" + additional_key);
		String pwd = "000000";
		String userid = userName + "_forum3" + "_" + token;
		String prams = "username=" + userid + "&password=" + pwd + "&token=" + token + "&redirect=true";
		url += "?" + prams;
		System.out.println("------forum3_old----" + url);
		return ResultJSON.getSuccess(url);
	}

	/**
	 * 
	 * /resRestAPI/additional/teachingResearch 增加对接教研平台
	 * 
	 * @return 重定向到教研平台，返回为null
	 */
	@RequestMapping(value = "/teachingResearch", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON teachingResearch(HttpServletRequest request) throws Exception {

		// 当前登录用户id
		Long currentUserId = (Long) request.getAttribute("currentUserId");

		SRegister register = regSerivce.getRegister(currentUserId);

		String userName = register.getName();
		String password = PWDEncrypt.getPWD(register.getPwd());

		String key = additional_key;
		String params = "user=" + userName + "&userpwd=" + password;
		String _sign = MD5.MD5(params + "&key=" + key);
		byte[] b1 = xxtea.encrypt(params.getBytes("utf-8"), key.getBytes("utf-8"));
		String s1 = Base64.encode(b1, 0, b1.length);
		s1 = URLEncoder.encode(s1, "utf-8");
		
		String defaultHOST = "http://101.201.197.110:8080";
		
		String url = resConfig.getTeachingResearchAPIHost()==null
				?defaultHOST:resConfig.getTeachingResearchAPIHost()
				+ "/teachingAutoLogin/v1.0/login?args=" + s1 + "&sign="
				+ _sign + "&platform=cloud&tfcode=";
		System.out.println("---url-----teachingresearch:" + url);
		return ResultJSON.getSuccess(url);

	}

	public static void main(String[] args) {
		String s = "";
	}

}
