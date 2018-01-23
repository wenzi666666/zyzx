package net.tfedu.zhl.cloud.back.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.developer.entity.SDeveloper;

/**
 
  
    @author wangwr
    @date 2017年1月19日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Transactional(value="ssoTransactionManager")
public class DeveloperAboutControllerTest extends BaseControllerTestCase{

	
	@Resource
	DeveloperAboutController controller;
	
	
	ResultJSON result ; 
	
	
	@Test
	public void testAddDeveloper() throws Exception {
		
		SDeveloper developer = new SDeveloper();
		
		developer.setCompanyname("測試開發單位");
		
		result = controller.addDeveloper(developer, request);
		
		super.assertAndLog(result);
	}

	@Test
	public void testUpdateDeveloper() throws Exception {
		
		SDeveloper developer = new SDeveloper();
		
		developer.setDeveloperid(1212121);
		developer.setCompanyname("測試開發單位");
		
		result = controller.updateDeveloper(developer, request);
		
		super.assertAndLog(result);
		
	}

	@Test
	public void testDelDeveloper() throws Exception {
		
		result = controller.delDeveloper(11243, request);
		
		super.assertAndLog(result);
		
	}

	@Test
	public void testPageDeveloper() throws Exception {
		
		result = controller.pageDeveloper(1, 10, request);
		
		super.assertAndLog(result);
		
	}

	@Test
	public void testAddApp() throws Exception {
		
		SApp app = new SApp();
		app.setAppid(11111);
		app.setAppkey("xxxxxxxx");
		app.setDeveloperid(1);
		
		
		
		result = controller.addApp(app, new BeanPropertyBindingResult(app, "app"), request);
		
		super.assertAndLog(result);
		
	}

	@Test
	public void testUpdateApp() throws Exception {
		
		SApp app = new SApp();
		app.setAppid(11111);
		app.setAppkey("xxxxxxxx");
		app.setDeveloperid(1);
	
		
		result = controller.updateApp(app, request);
		
		super.assertAndLog(result);
	}

	@Test
	public void testDelApp() throws Exception {
		result = controller.delApp(12345, request);
		
		super.assertAndLog(result);
	}

	@Test
	public void testPageApp() throws Exception {
		result = controller.pageApp(1, 10, request);
		
		super.assertAndLog(result);
	}

}
