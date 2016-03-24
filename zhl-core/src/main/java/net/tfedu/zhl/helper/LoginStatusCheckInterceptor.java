package net.tfedu.zhl.helper;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.core.online.service.JOnlineUsersService;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;



/**
 * 登录状态拦截器
 * @author wangwr
 *
 */
public class LoginStatusCheckInterceptor implements WebRequestInterceptor {
	
	@Resource
	private JOnlineUsersService jOnlineUsersService;

	@Override
	public void preHandle(WebRequest request) throws Exception {
		CustomException exception  = null;
		// TODO Auto-generated method stub
		String token = request.getHeader("Authorization");
		
		try {
			if(token == null){
				exception = CustomException.NOTOKEN;
			}else{
				jOnlineUsersService.getUserOnlinesByToken(token);
			}
		} catch (Exception e) {
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {

	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex)
			throws Exception {

	}

}
