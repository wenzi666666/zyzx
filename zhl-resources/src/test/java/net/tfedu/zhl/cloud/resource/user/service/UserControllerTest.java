package net.tfedu.zhl.cloud.resource.user.service;


import javax.annotation.Resource;
import javax.servlet.http.HttpUtils;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.RequestMethod;

import net.tfedu.zhl.cloud.resource.user.controller.UserController;
import net.tfedu.zhl.cloud.resource.user.entity.JUser;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;


public class UserControllerTest extends BaseControllerTestCase {
	
	
	@Resource
	UserController controller ;
	
	

	@Test
	public void test() {
		
		request = newGet("/resRestAPI/v1.0/users/{id}");
		ResultJSON result = controller.getUserInfo(1l);
		System.out.println(result.toString());
		
	}

}
