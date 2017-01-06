package net.tfedu.zhl.cloud.back.login.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 
 	登录测试类
  
    @author wangwr
    @date 2017年1月5日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class LoginBackControllerTest  extends BaseControllerTestCase{
	
	@Resource
	LoginBackController controller;
	
	ResultJSON result ; 

	@Test
	public void testLogin()throws Exception {
		
		result =  controller.login("admin", "000000", request);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		
	}

	@Test
	public void testLogout() throws Exception{
		//BED5EAEE776342BAA6828084FB70BF5E
		
		request.setParameter("token", "BED5EAEE776342BAA6828084FB70BF5E");
		request.setAttribute("currentUserId", 3l);
		
		result =  controller.logout(request);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
	}

}
