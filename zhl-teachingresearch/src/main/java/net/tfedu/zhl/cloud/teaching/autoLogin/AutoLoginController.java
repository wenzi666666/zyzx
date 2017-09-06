package net.tfedu.zhl.cloud.teaching.autoLogin;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.InvalidAccessTokenException;
import net.tfedu.zhl.core.exception.KickOutTokenException;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.core.exception.UnusualErrorException;
import net.tfedu.zhl.core.exception.WithoutAuthorizationException;
import net.tfedu.zhl.core.exception.WithoutSignError;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.UserTokenCacheUtil;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;
import net.tfedu.zhl.userlayer.user.entity.UserSimple;
import net.tfedu.zhl.userlayer.user.service.JUserService;

/**
 * 自动登录的 controller
 * 
 * @author wangwr
 * @desc
 * 
 * 		教研服务平台 使用相同的SSO的公司内部项目（例如资源中心3.1等），通过传递用户名、密码过来， 验证通过之后自动跳转到登录成功之后的首页
 *
 */
@Controller
@RequestMapping("/teachingAutoLogin")
public class AutoLoginController {

	/**
	 * 用于生产md5的教研信息
	 */
	private static final String MD5_KEY = "9k8i78jug6hd93kjf84h";

	@Resource
	JUserService userService;

	@Resource
	RegisterService registerService;

	@Resource
	private CommonWebConfig commonWebConfig;

	@Autowired
	CacheManager cacheManager;

	Logger log = LoggerFactory.getLogger(AutoLoginController.class);

	/**
	 * 
	 * 登录处理方法
	 * 
	 * @param request
	 * @param response
	 * @return 返回为null（直接重定向到登录成功之后的首页）
	 * @throws Exception
	 * 
	 * 
	 *             ?args=YCRYAmjxmlXvuNHTA1gC9hlyLRB%2FG0YHh5BzNJrUyBc%3D&sign=
	 *             4b2d7d9602e7a3310b5828423423de04
	 */
	@RequestMapping("/v1.0/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取args
		String args = request.getParameter("args");
		// 获取校验信息
		String sign = request.getParameter("sign");
		if (StringUtils.isEmpty(args)) {
			throw new ParamsException();
		}
		// 获取参数
		log.info("----args----:" + args);
		log.info("----sign----:" + sign);
		String parmas = getParams(args);

		// 校验
		String _sign = MD5.MD5(parmas + "&key=" + MD5_KEY);

		// 校验信息异常
		if (StringUtils.isEmpty(sign) || !_sign.equals(sign)) {
			throw new WithoutSignError();
		}

		// 格式化参数并获取用户名密码
		Map<String, String> ps = getParamMap(parmas);
		String userName = ps.get("user");
		String userPwd = ps.get("userpwd");

		// 检查用户信息
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPwd)) {
			throw new UnusualErrorException();
		}

		// 返回用户的信息
		UserSimple user = null;

		// 用户登录
		SRegister reg = registerService.login(userName, userPwd);

		// 获取用户信息
		user = userService.getUserSimpleById(reg.getId(), "", commonWebConfig.getIsRepeatLogin());

		// 重定向到教研
		String url = commonWebConfig.getTeachingResearchWebUrl() + "/router?userId=" + user.getUserId() + "&token="
				+ user.getToken();
		log.info("-autologin--url--" + url);
		response.sendRedirect(url);

		return null;
	}

	/**
	 * 
	 * 登录处理方法
	 * 
	 * @param request
	 * @param response
	 * @return 返回ResultJSON(UserSimple)
	 * @throws Exception
	 * 
	 * 
	 *             ?args=YCRYAmjxmlXvuNHTA1gC9hlyLRB%2FG0YHh5BzNJrUyBc%3D&sign=
	 *             4b2d7d9602e7a3310b5828423423de04
	 */
	@RequestMapping("/v2.0/login")
	@ResponseBody
	public ResultJSON login2(HttpServletRequest request, String userName) throws Exception {
		
		log.info("--autologin------start");

		UserSimple user = null;

		String model = "";

		ControllerHelper.checkEmpty(userName);

		SRegister register = registerService.getRegister(userName);

		if (null == register) {
			throw new WithoutAuthorizationException(userName);
		}

		String key = UserTokenCacheUtil.getUserTokenCacheKey(model, register.getId().toString());
		// 用户token未被缓存
		if (cacheManager.getCache(UserTokenCacheUtil.USERINFO_CACHE_NAMESPACE).get(key) == null) {
			// 获取用户信息
			user = userService.getUserSimpleById(register.getId(), model, commonWebConfig.getIsRepeatLogin());

			// 记录用户的登录状态
			userService.addUserLoginStatusWeb(register.getId(), register.getNodeid(), user.getToken(), request);

		} else {
			String token = UserTokenCacheUtil.getUserTokenCacheKey(model, register.getId().toString());

			user = UserTokenCacheUtil.getUserInfoValueWrapper(cacheManager, token, commonWebConfig.getIsRepeatLogin());

		}

		log.info("--autologin--UserSimple:{}", user);
		log.info("--autologin--token:{}", user.getToken());
		log.info("--autologin--ifCached:{}", UserTokenCacheUtil.getUserInfoValueWrapper(cacheManager, user.getToken(),
				commonWebConfig.getIsRepeatLogin()));
		log.info("--autologin------end");

		return ResultJSON.getSuccess(user);
	}

	/**
	 * token获取缓存的UserSimple 对象
	 * 
	 * @param token
	 * @return
	 * @throws InvalidAccessTokenException
	 * @throws KickOutTokenException
	 */
	public UserSimple getCachedUserSimple(String token) throws InvalidAccessTokenException, KickOutTokenException {

		return UserTokenCacheUtil.getUserInfoValueWrapper(cacheManager, token, commonWebConfig.getIsRepeatLogin());

	}

	/**
	 * 返回解密后的参数
	 * 
	 * @param args
	 * @return
	 * @throws IOException
	 */
	private String getParams(String args) throws IOException {

		// args = URLDecoder.decode(args, "UTF-8");

		log.info("--getParams---URLDecoder.decode--args:" + args);

		return xxtea.decryptstring(args, MD5_KEY);

	}

	/**
	 * 将参数转换为一个map
	 * 
	 * @param params
	 * @return
	 */
	private Map<String, String> getParamMap(String params) {

		Map<String, String> m = new HashMap<String, String>();
		if (StringUtils.isNotEmpty(params)) {
			String[] ps = params.split("&");
			for (int i = 0; i < ps.length; i++) {
				String p = ps[i];
				String[] kv = p.split("=");
				m.put(kv[0], kv[1]);
			}
		}
		return m;
	}

	public static void main(String[] args) throws IOException {
		String ps = "RS6XaCLv1TOS8tlcmM4ecQipNX%2FylE%2BuPeCqSh%2BM0EA%3D";

		String _args = URLDecoder.decode(ps, "utf-8");

		byte[] bytes = Base64.decode(_args);

		byte[] temp = xxtea.decrypt(bytes, MD5_KEY.getBytes("utf-8"));

		System.out.println(new String(temp, "UTF-8"));
		System.out.println(xxtea.decryptstring(_args, MD5_KEY));

	}

}
