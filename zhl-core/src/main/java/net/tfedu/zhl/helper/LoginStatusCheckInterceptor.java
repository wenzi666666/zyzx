package net.tfedu.zhl.helper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.tfedu.zhl.cloud.online.entity.JOnlineUsers;
import net.tfedu.zhl.cloud.online.service.JOnlineUsersService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.InvalidAccessTokenException;
import net.tfedu.zhl.core.exception.MD5SignError;
import net.tfedu.zhl.core.exception.NoTokenException;
import net.tfedu.zhl.core.exception.PropertiesMissing;
import net.tfedu.zhl.helper.sign.SignUtil;
import net.tfedu.zhl.userlayer.user.entity.UserSimple;
import net.tfedu.zhl.userlayer.user.service.JUserService;

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
 *	@version 
 *		create	1.0
 *  	update  2.0 2017-01-05 兼容url中传递参数的方式
 *  
 *
 *
 */
public class LoginStatusCheckInterceptor implements HandlerInterceptor {
	
	
    @Autowired
    CacheManager cacheManager;
    
    @Autowired
    CommonWebConfig config;
    
    @Resource
    JOnlineUsersService jOnlineUsersService;

	@Resource
	JUserService userService;
    

    Logger logger = LoggerFactory.getLogger(LoginStatusCheckInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object controller, ModelAndView mv) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
    	
    	
    	
    	String method =  request.getMethod();

    	if(RequestMethod.OPTIONS.toString().equals(method)){
    		return false ;
    	}
    	
        //从header中获取token 或从 url中获取token（md5校验）
        String token = request.getHeader("Authorization");
        if(StringUtils.isEmpty(token)){
        	//获取sign
        	String sign = request.getParameter("sign");
        	if(StringUtils.isNotEmpty(sign)){
        		String appKey =  config.getAppKey();
        		//检查配置信息
        		if(StringUtils.isEmpty(appKey)){
        			throw new PropertiesMissing("appKey");
        		}
        		//md5校验
        		if(!sign.equals(SignUtil.createSign(request, appKey))){
        			throw new MD5SignError();
        		}
        		//获取token
        		token = request.getParameter("token");
        	}
        }
        
        
        
        
        
        
        Long currentUserId = null;
        String currentUserName = null ;
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
                currentUserName = us.getUserName();
        	}else{

            	
        		//如果緩存中沒有，检查online表中的记录token
        		JOnlineUsers record =  jOnlineUsersService.getUserOnlinesByToken(token);
        		if(record!=null && record.getUserid()>0){
        			//用戶有效時，补充cache

        			String model = "";
        			
        			UserSimple _us = userService.getUserSimpleById(record.getUserid(), model
        																, config.getIsRepeatLogin(), false);
        			//重置登录时间
        			_us.setLogintime(record.getLogintime());
        			_us.setToken(token);
        			
        			//cache
        			UserTokenCacheUtil.addUserInfoCache(model, cacheManager, token, _us, config.getIsRepeatLogin());
        			
                    currentUserId = _us.getUserId();
                    currentUserName = _us.getUserName();

        		}
        		
        	}
        	
        }

        if(currentUserId==null || currentUserId==0){
        	//token 无效
        	throw  new InvalidAccessTokenException();
        }else{
        	System.out.println("currentUserId--"+currentUserId+"-currentUserName-"+currentUserName+"-");
            request.setAttribute("currentUserId", currentUserId);
            request.setAttribute("currentUserName", currentUserName);
            flag = true ;
        }
        return flag;
    }

}
