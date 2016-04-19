package net.tfedu.zhl.helper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.tfedu.zhl.sso.online.service.JOnlineUsersService;
import net.tfedu.zhl.sso.user.entity.UserSimple;

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


    @Resource
    private JOnlineUsersService jOnlineUsersService;

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
        CustomException customException = null;
        Long currentUserId = null;

        try {
            if (token == null) {
                customException = CustomException.NOTOKEN;
            }
            else {
            	

/*                // token的有效时间
                int validTime = ZhlOnlineUtil.getTokenValidTime(request);

                JOnlineUsers user = jOnlineUsersService.getUserOnlinesByToken(token, validTime);
                currentUserId = user.getId();*/
            	
            	
            	ValueWrapper o = cacheManager.getCache("UserSimpleCache").get(token);
                if(o!=null){
                	UserSimple us  = (UserSimple)(o.get());
                	if(us!=null){
                        currentUserId = us.getUserId();
                	}
                    logger.debug("------------token-------------------" + token+"------------currentUserId----------"+currentUserId);
                    if(currentUserId==null || currentUserId==0){
                    	customException = CustomException.INVALIDACCESSTOKEN;
                    }
            	}
            }
        }
        catch (Exception e) {
            customException = CustomException.getCustomExceptionByCode(e.getMessage());
            // 如果是普通的异常
            if (customException.getStatus() == 500) {
                e.printStackTrace();
            }
        }
        finally {
            // 传递 currentUserId customException 判断正常登录的条件为 currentUserId!=null
            // && customException==null;
            request.setAttribute("currentUserId", currentUserId);
            request.setAttribute(CustomException.request_key, customException);
        }

        return true;
    }

}
