package net.tfedu.zhl.cloud.back.login.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.InvalidAccessTokenException;
import net.tfedu.zhl.core.exception.MD5SignError;
import net.tfedu.zhl.core.exception.NoTokenException;
import net.tfedu.zhl.core.exception.PropertiesMissing;
import net.tfedu.zhl.helper.BackManagerTokenCacheUtil;
import net.tfedu.zhl.helper.sign.SignUtil;
import net.tfedu.zhl.sso.back.online.entity.SOnlineManager;
import net.tfedu.zhl.sso.back.online.service.SOnlineUsersService;
import net.tfedu.zhl.sso.back.user.bean.ManagerSimple;
import net.tfedu.zhl.sso.back.user.service.SBackUserService;

/**
 * 
 * 后台登录的状态拦截器
 * 
 * 仅支持url中传递参数，且md5校验
 * 
 * @author wangwr
 * @date 2017年1月5日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class LoginBackStatusInterceptor implements HandlerInterceptor {

	@Autowired
	CacheManager cacheManager;

	@Autowired
	CommonWebConfig config;

	@Resource
	SBackUserService sBackUserService;

	@Resource
	SOnlineUsersService sOnlineUsersService;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String method = request.getMethod();

		if (RequestMethod.OPTIONS.toString().equals(method)) {
			return false;
		}

		String token = null;
		// 获取token
		token = request.getParameter("token");
		// 获取sign
		String sign = request.getParameter("sign");
		if (StringUtils.isNotEmpty(sign)) {
			String appKey = config.getAppKey();
			// 检查配置信息
			if (StringUtils.isEmpty(appKey)) {
				throw new PropertiesMissing("appKey");
			}
			// md5校验
			if (!sign.equals(SignUtil.createSign(request, appKey))) {
				throw new MD5SignError();
			}
		}else{
			throw new MD5SignError();
		}

		Long currentUserId = null;
		String currentUserName = null;
		// 默认继续往下走
		boolean flag = false;

		if (StringUtils.isEmpty(token)) {
			// 缺少token
			throw new NoTokenException();
		} else {

			ManagerSimple us = BackManagerTokenCacheUtil.getUserInfoValueWrapper(cacheManager, token,
					config.getIsRepeatLogin());
			if (us != null) {
				currentUserId = us.getUserId();
				currentUserName = us.getUserName();
			} else {

				// 如果緩存中沒有，检查online表中的记录token
				SOnlineManager record = sOnlineUsersService.getUserOnlinesByToken(token);
				if (record != null && record.getUserid() > 0) {
					// 用戶有效時，补充cache

					String model = "";

					ManagerSimple _us = sBackUserService.getManagerSimpleById(record.getUserid(), model,
							config.getIsRepeatLogin(), config.getProductCode());
					// 重置登录时间
					_us.setLoginTime(record.getLogintime());
					_us.setToken(token);

					// cache
					BackManagerTokenCacheUtil.addUserInfoCache(model, cacheManager, token, _us,
							config.getIsRepeatLogin());

					currentUserId = _us.getUserId();
					currentUserName = _us.getUserName();

				}

			}

		}

		if (currentUserId == null || currentUserId == 0) {
			// token 无效
			throw new InvalidAccessTokenException();
		} else {
			System.out.println("currentUserId--" + currentUserId + "-currentUserName-" + currentUserName + "-");
			request.setAttribute("currentUserId", currentUserId);
			request.setAttribute("currentUserName", currentUserName);
			flag = true;
		}
		return flag;

	}

	
	
	
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		String s = SignUtil.createSignMap(new String[]{"token"}, new String[]{"D88ED104F3FA408AB1BAF3CE69DFA9BC"}, "zhlresource");
		System.out.println(s);
	}
}
