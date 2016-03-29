package net.tfedu.zhl.sso.service;


import javax.annotation.Resource;

import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.entity.SRegister;

import org.junit.Test;
import org.springframework.util.Assert;

public class RegisterServiceTest extends BaseControllerTestCase{
	
	
	
	@Resource
	RegisterService registerService ;

	@Test
	public void testGetRegister() {
		
		SRegister r =  registerService.getRegister(1l);
		System.out.println(r.getName());
		
	}

	
	@Test
	public void testGetRegisterName() {
		SRegister r =  registerService.getRegister("admin");
		System.out.println(r.getName());

	}
	
	
	@Test
	public void testModifyRegisterPassword() {
		long id = 1 ;
		String pwd = "123456";
		registerService.modifyRegisterPassword(id, pwd);
	
	}
	
	
	@Test
	public void testLogin() {
		String  userName = "admin";
		String password = "000000";
		try {
			registerService.login(userName, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
