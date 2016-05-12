package net.tfedu.zhl.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.InvalidAccessTokenException;
import net.tfedu.zhl.core.exception.NoTokenException;
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
    
    @Autowired
    CommonWebConfig config;


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
        boolean flag = false ;
        
        if(StringUtils.isEmpty(token)) {
        	//缺少token
            throw new NoTokenException();
        }
        else {
        	
        	UserSimple us  = UserTokenCacheUtil.getUserInfoValueWrapper(cacheManager, token, config.getIsRepeatLogin());
        	if(us!=null){
                currentUserId = us.getUserId();
        	}
        	/*ValueWrapper value =  cacheManager.getCache("UserSimpleCache").get(token);
        	if(value!=null){
        		UserSimple us  = (UserSimple)value.get();
            	if(us!=null){
                    currentUserId = us.getUserId();
            	}
        	}*/
        	
        	
        }

        if(currentUserId==null || currentUserId==0){
        	//token 无效
        	throw  new InvalidAccessTokenException();
        }else{
            request.setAttribute("currentUserId", currentUserId);
            flag = true ;
        }
        return flag;
    }

}
