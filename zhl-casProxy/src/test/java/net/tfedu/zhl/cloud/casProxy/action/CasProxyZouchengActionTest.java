package net.tfedu.zhl.cloud.casProxy.action;

import javax.annotation.Resource;

import org.junit.Test;

import net.tfedu.zhl.cloud.casProxy.action.controller.CasProxyZouchengAction;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 
  
    @author wangwr
    @date 2017年3月21日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class CasProxyZouchengActionTest extends BaseControllerTestCase{

	@Resource
	CasProxyZouchengAction controller;
	
	
	String userid = "310107197711181237";//测试使用的用户登录名

	
	
	@Test
	public void testLogin() throws CustomException, Exception {
		
		request.setParameter("userid", userid);
		
		controller.login(request, response);
		
	}

	@Test
	public void testUserCheck() throws Exception {
		request.setParameter("userid", userid);
		
		Object o =  controller.userCheck(request, response);

		System.out.println(o.toString());
	}

}
