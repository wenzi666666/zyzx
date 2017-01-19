package net.tfedu.zhl.cloud.casProxy.action.custom.jimo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;

import net.tfedu.zhl.cloud.casProxy.action.custom.CasProxyCustomBase;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.core.exception.CustomException;

/**
 
  
    @author wangwr
    @date 2017年1月11日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class CasProxyCustomJimo extends CasProxyCustomBase {
	
	
	
	/**
	 * 处理服务接口,通过修改配置文件中的实现类，切换不同的场景
	 */
	ProxyService proxy;

	
	
	/**
	 * 即墨的登录接口
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public void loginWeiFang(HttpServletRequest request, HttpServletResponse response) throws CustomException, Exception {
		
		if(proxy==null){
			
			ApplicationContext context = 
					new FileSystemXmlApplicationContext("classpath*:applicationContext-casProxy.xml");
			
			proxy =  (ProxyService)context.getBean("proxyServiceImpl_weifang");
			
		}

		
		super.login(request, response, proxy);
		

	}
	
	
	@RequestMapping("/login")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws CustomException, Exception {
		
	}
	
	
	
	
	
	

}