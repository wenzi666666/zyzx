package net.tfedu.zhl.cloud.resource.thirdpartyAPI.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import net.tfedu.zhl.helper.tests.BaseControllerTestCase;


@Transactional
public class ThirdPartyYanQingControllerTest extends BaseControllerTestCase{

	
	@Resource
	ThirdPartyYanQingController controller;
	
	
	@Test
	public void testLogin() throws Exception {
		
		
		request.setParameter("sessid", "8j463t1ksmgrjge9bj9m0i8pp0");
		controller.login(request, response);
	}

}
