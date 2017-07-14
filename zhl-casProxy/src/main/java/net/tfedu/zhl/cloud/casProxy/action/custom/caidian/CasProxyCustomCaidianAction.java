package net.tfedu.zhl.cloud.casProxy.action.custom.caidian;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.tfedu.zhl.cloud.casProxy.action.custom.CasProxyCustomBase;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.core.exception.CustomException;

/**
 * 
 * 
 * @author wangwr
 * @date 2017年7月13日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
@RequestMapping("/casProxyCaidian/")
public class CasProxyCustomCaidianAction extends CasProxyCustomBase {

	/**
	 * 处理服务接口,通过修改配置文件中的实现类，切换不同的场景
	 */
	@Resource
	ProxyService proxyCaidian;

	/**
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public void loginCommon(HttpServletRequest request, HttpServletResponse response)
			throws CustomException, Exception {

		super.login(request, response, proxyCaidian);

	}

	/**
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws CustomException, Exception {

		Thread.sleep(1000);
		request.getSession().invalidate();
		response.sendRedirect(request.getServletContext().getInitParameter("casServerLogoutUrl")

				+ "?service=" + request.getServletContext().getInitParameter("serverName"));

	}

}
