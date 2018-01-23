package net.tfedu.zhl.cloud.resource.thirdpartyAPI.controller;

import javax.annotation.Resource;

import org.junit.Test;

import net.tfedu.zhl.helper.tests.BaseControllerTestCase;




public class ThirdPartyContollerTest extends BaseControllerTestCase {
	
	
	@Resource
	ThirdPartyContoller controller;
	
	

	@Test
	public void testLogin() throws Exception{
		
		
		Object o =  controller.login(request, response, "jnzx", "respool_2", "Default--565e866489c54b2ea4094f9cc938b27c");
		
		
		
	}

}
