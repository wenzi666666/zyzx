package net.tfedu.zhl.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 跨域请求拦截器
 * @author wangwr
 *
 */
public class CrossDomainInterceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object controller, Exception e)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object controller, ModelAndView model)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
		//如果是OPTIONS请求，直接返回浏览器
    	String method =  request.getMethod();
    	if(RequestMethod.OPTIONS.toString().equals(method)){
    		return false ;
    	}  
		
		return true;
		
	}

}
