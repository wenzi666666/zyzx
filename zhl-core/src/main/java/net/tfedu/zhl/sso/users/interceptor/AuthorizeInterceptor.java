package net.tfedu.zhl.sso.users.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.sso.online.entity.JOnlineUsers;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
        Object obj = cacheManager.getCache("userIds").get(token);
        if (obj == null) {
            customException = CustomException.NULOGIN;
            request.setAttribute(CustomException.request_key, customException);
            return false;
        }

        // 3判断是否有权限
        // 3.1加载所有权限，角色和api的键值对

        // 3.2 判断用户自身角色的权限
        JOnlineUsers onlineUser = (JOnlineUsers) obj;

        // 3.3 判断用户角色关系表的权限

        // 3.4 判断用户组的权限
        request.setAttribute("currentUserId", currentUserId);
        request.setAttribute(CustomException.request_key, customException);
        return false;
    }

}
