package net.tfedu.zhl.helper;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.tfedu.zhl.sso.online.entity.JOnlineUsers;
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

    Logger logger = Logger.getLogger(LoginStatusCheckInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object controller, ModelAndView mv) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
        logger.debug("------------preHandle-------------------");

        String URI = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath() + "/";

        Enumeration<String> map = request.getServletContext().getInitParameterNames();
        // 应用
        String host = request.getServletContext().getInitParameter("host");
        String host_local = request.getServletContext().getInitParameter("hostLocal");
        // 系统资源服务器地址
        String resService = request.getServletContext().getInitParameter("resService");
        String resServiceLocal = request.getServletContext().getInitParameter("resServiceLocal");

        // 当前文件 资源服务器地址
        String currentResPath = "";

        if (host == null || host_local == null || resService == null || resServiceLocal == null) {
            request.setAttribute("message", "系统应用配置信息错误");
            logger.error("--------系统应用配置信息错误------------");
        }
        else {
            if (host.contains(URI)) {
                currentResPath = resService;
            }
            else if (host_local.contains(URI)) {
                currentResPath = resServiceLocal;
            }
            else {
                logger.error("--------系统应用配置信息错误----host----host_local---URI-" + URI);
            }
        }

        // 如果内网地址有多个的话 取第一个
        if (host_local.indexOf(",") > 0) {
            request.setAttribute("hostLocal", host_local.split(",")[0]);
        }
        else {
            request.setAttribute("hostLocal", host_local);
        }
        // 指定资源服务器的内网地址和当前地址
        request.setAttribute("resServiceLocal", resServiceLocal);
        request.setAttribute("currentResPath", currentResPath);

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
            	
            	
            	
            	Object o = cacheManager.getCache("UserSimpleCache").get(token).get();
            	if(o!=null){
                	UserSimple us  = (UserSimple) o;
                    currentUserId = us.getUserId();
            		
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
            logger.debug("------------token-------------------" + token);
            logger.debug("------------currentUserId-------------------" + currentUserId);
            request.setAttribute("currentUserId", currentUserId);
            request.setAttribute(CustomException.request_key, customException);
        }

        return true;
    }

}
