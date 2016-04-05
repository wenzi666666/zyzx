package net.tfedu.zhl.sso.users.interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ZhlOnlineUtil;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.users.entity.FuncList;

/**
 * 授权拦截器
 * 
 * @author Bruce
 *
 */
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {
    @Resource
    CacheManager cacheManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 1判断是否请求是否需要拦截, 拦截器自动处理，配置一下excludedUrls
        // 获取参数
        String token = request.getHeader("Authorization");
        CustomException customException = null; // (CustomException)request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        if (token == null) {
            customException = CustomException.NOTOKEN;
            request.setAttribute(CustomException.request_key, customException);
            return false;
        }

        // 2判断是否登录
        Object obj = cacheManager.getCache("UserSimpleCache").get(token).get();
        if (obj == null) {
            customException = CustomException.NULOGIN;
            request.setAttribute(CustomException.request_key, customException);
            return false;
        }

        // 3判断用户token是否有效
        int validTime = ZhlOnlineUtil.getTokenValidTime(request);
        UserSimple us = (UserSimple) obj;
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        Calendar loginTime = Calendar.getInstance();
        loginTime.setTime(us.getLogintime());
        loginTime.add(Calendar.HOUR_OF_DAY, validTime);

        if (now.before(loginTime)) {
            customException = CustomException.OUTOFDATE;
            request.setAttribute(CustomException.request_key, customException);
            cacheManager.getCache("UserSimpleCache").evict(token);
            return false;
        }

        // 4判断是否有权限
        Set<String> funcs = us.getFuncPaths();
        String url = request.getRequestURI();      
        
        if(!funcs.contains(url)){
            customException = CustomException.WITHOUTAUTH;
            request.setAttribute(CustomException.request_key, customException);
            return false;
        }

        request.setAttribute("currentUserId", currentUserId);
        request.setAttribute(CustomException.request_key, customException);
        return true;
    }

}
