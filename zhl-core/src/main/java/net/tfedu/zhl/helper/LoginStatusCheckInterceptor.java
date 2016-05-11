package net.tfedu.zhl.helper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.InvalidAccessTokenException;
import net.tfedu.zhl.core.exception.NoTokenException;
import net.tfedu.zhl.sso.online.service.JOnlineUsersService;
import net.tfedu.zhl.sso.user.entity.UserSimple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录状态拦截器
 * 
 * @author wangwr
 *
 * 
 * 
 *         获取token 并验证用户，将用户信息放置到request中
 * 
 *         获取内外网路径
 *
 *
 *
 */
public class LoginStatusCheckInterceptor implements HandlerInterceptor {
	
	
    @Autowired
    CacheManager cacheManager;


    Logger logger = LoggerFactory.getLogger(LoginStatusCheckInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object controller, ModelAndView mv) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
        // 用户登录状态相关检查
        String token = request.getHeader("Authorization");
        Long currentUserId = null;
        //默认继续往下走
        boolean flag = true ;
        
        if(StringUtils.isEmpty(token)) {
        	//缺少token
        	flag = false ;
            throw new NoTokenException();
        }
        else {
        	ValueWrapper o = UserTokenCacheUtil.getValueWrapper(cacheManager, token);
            if(o!=null){
            	UserSimple us  = (UserSimple)(o.get());
            	if(us!=null){
                    currentUserId = us.getUserId();
            	}
        	}
        }

        if(currentUserId==null || currentUserId==0){
        	//token 无效
        	flag = false ;
        	throw  new InvalidAccessTokenException();
        }

        
        request.setAttribute("currentUserId", currentUserId);
        return flag;
    }

}
