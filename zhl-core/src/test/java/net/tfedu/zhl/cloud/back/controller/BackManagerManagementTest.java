package net.tfedu.zhl.cloud.back.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.back.user.entity.SBackUser;
import net.tfedu.zhl.sso.back.user.entity.SBackUserScope;

/**
 
  
    @author wangwr
    @date 2017年1月20日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Transactional("ssoTransactionManager")
public class BackManagerManagementTest  extends BaseControllerTestCase {

	@Resource
	BackManagerManagement controller;
	
	ResultJSON result ; 
	
	
	@Test
	public void testAddManager() throws Exception{
		
		
		SBackUser user = new SBackUser();
		user.setName("testManager");
		user.setTruename("测试管理员");
		request.setParameter("pwd", "000000");
		result =  controller.addManager(user, new BeanPropertyBindingResult(user, "user"), request);
		
		
		super.assertAndLog(result);
	}

	@Test
	public void testUpdateManager() throws Exception{
		SBackUser user = new SBackUser();
		user.setId(3l);
		user.setName("admin");
		user.setTruename("测试管理员");		
		result =  controller.updateManager(user, new BeanPropertyBindingResult(user, "user"), request);				
		super.assertAndLog(result);
	}

	@Test
	public void testDelManager() throws Exception{
		result =  controller.delManager(3l, request);				
		super.assertAndLog(result);
	}

	@Test
	public void testPageManager() throws Exception{
		result =  controller.pageManager("dis", 1, 10, request);			
		super.assertAndLog(result);
		
	}

	@Test
	public void testAddManagerRoleAndScope() throws Exception{
		
		SBackUserScope userScope = new SBackUserScope();
		
		
		userScope.setProvinceid(0l);
		userScope.setCityid(0l);
		userScope.setDistrictid(0l);
		userScope.setSchoolid(0l);
		
		
		result =  controller.addManagerRoleAndScope(3l, userScope, request);			
		super.assertAndLog(result);
		
	}

	@Test
	public void testResetPwd() throws Exception{
		result =  controller.resetPwd(3l, "123456");			
		super.assertAndLog(result);
		
	}

}
