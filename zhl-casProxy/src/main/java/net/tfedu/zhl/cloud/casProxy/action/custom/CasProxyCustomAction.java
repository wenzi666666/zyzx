package net.tfedu.zhl.cloud.casProxy.action.custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.zns.caidian.dao.EduTeacherMapper;

/**
             各地自定义的接口
       
    @author wangwr
    @date 2016年12月29日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Controller
@RequestMapping("/casProxyCustom/")
public class CasProxyCustomAction extends CasProxyCustomBase {
	

	/**
	 * 处理服务接口,通过修改配置文件中的实现类，切换不同的场景
	 */
	ProxyService proxy;

	
	
	/**
	 * 潍坊的登录接口
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/loginWF")
	public void loginWeiFang(HttpServletRequest request, HttpServletResponse response) throws CustomException, Exception {
		
		if(proxy==null){
			
			@SuppressWarnings("resource")
			ApplicationContext context = 
					new FileSystemXmlApplicationContext("classpath*:applicationContext-casProxy.xml");
			
			proxy =  (ProxyService)context.getBean("proxyServiceImpl_weifang");
			
		}

		
		super.login(request, response, proxy);
		

	}
	
	/**
	 * 潍坊的登录接口
	 * @param request
	 * @param response
	 * @throws CustomException
	 * @throws Exception
	 */
	@RequestMapping("/loginCommon")
	public void loginCommon(HttpServletRequest request, HttpServletResponse response) throws CustomException, Exception {
		
		if(proxy==null){
			
			@SuppressWarnings("resource")
			ApplicationContext context = 
					new FileSystemXmlApplicationContext("classpath*:applicationContext-casProxy.xml");
			
			proxy =  (ProxyService)context.getBean("commonProxyServiceImpl");
						
		}

		
		super.login(request, response, proxy);
		

	}
	
	

}
