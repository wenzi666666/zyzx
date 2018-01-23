package net.tfedu.zhl.cloud.basedata.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.tfedu.zhl.core.exception.MD5SignError;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.sign.SignUtil;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;

/**
 
    
    @author wangwr
    @date 2017年2月20日
    @desc   用户基础数据接口中的参数校验
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class BaseDataAPISignCheckInterceptor implements HandlerInterceptor {
	
	
	@Resource
	SAppService sAppService;
	

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {

		//OPTIONS请求直接放过
		String method = request.getMethod();
		if (RequestMethod.OPTIONS.toString().equals(method)) {
			return false;
		}
		
		//上传请求不过滤
		if(request instanceof  DefaultMultipartHttpServletRequest){
			return true ;
		}
		
		
		// 获取sign\appId
		String sign = request.getParameter("sign");
		String appId = request.getParameter("appId");
		SApp app = null ;
		
		
		if(StringUtils.isEmpty(appId)|| StringUtils.isEmpty(sign)){
			throw new ParamsException();
		}
		
		//获取指定app
		app = sAppService.getSApp(appId);
		
		//校验参数	 md5校验
		if (!sign.equals(SignUtil.createSign(request, app.getAppkey()))) {
			throw new MD5SignError();
		}
		
//		request.setAttribute("appKey", app.getAppkey());
		
		return true;
	}

}
