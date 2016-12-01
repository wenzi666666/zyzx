package net.tfedu.zhl.cloud.resource.autologin.aop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.tfedu.zhl.core.exception.CustomException;

/**
 * 对第三方或公司内部的对接，提供友好的提示(返回500、404页面) 面向切面
 * 
 * @author wangwr
 *
 */
@Component
@Aspect
public class AutoLoginAOP {

	@Pointcut("within(net.tfedu.zhl.cloud.resource.autologin.AutoLoginController)")
	public void autoLogin() {
	}


	@Around("autoLogin()")
	public Object doAround(ProceedingJoinPoint pjp) throws ServletException, IOException {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//				.getRequest();

		Object result = null;
//		String kind = pjp.getKind();
//		Object target = pjp.getTarget();
//		String uri = request.getRequestURI();
		try {

			result = pjp.proceed();
		} catch (CustomException e) {
			return showErrorPage(e);

		} catch (Throwable e) {
			return showErrorPage(e);

		}
		return result;
	}

	/**
	 * 跳转到错误页面
	 * 
	 * @param e
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String showErrorPage(Throwable e) throws ServletException, IOException {
		e.printStackTrace();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		request.setAttribute("message", e.getMessage() == null ? e.getCause() : e.getMessage());
		request.getRequestDispatcher("/common/exception/500.jsp").forward(request, response);
		return null;

	}

}
