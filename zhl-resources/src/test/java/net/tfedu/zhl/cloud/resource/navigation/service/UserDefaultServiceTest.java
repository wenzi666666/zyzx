package net.tfedu.zhl.cloud.resource.navigation.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.navigation.entity.JUserDefault;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

/**
 
  
  @author wangwr
  @date 2016年11月24日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public class UserDefaultServiceTest extends BaseServiceTestCase {
	
	@Resource
	UserDefaultService userDefaultService;
	
	long userId = 390430126;
	
	int type = 1 ; 
	
	String tfcode = "RJCZ010104";

	@Test
	public void testGetUserHistoryDefault() {
		JUserDefault result =  userDefaultService.getUserHistoryDefault(390430126, 1);
		
		System.out.println(JSONObject.toJSON(result));
		
		Assert.isTrue(null!=result&& result.getId()>0);
	}

	@Test
	public void testUpdateUserHistoryDefault() {
		
		userDefaultService.updateUserHistoryDefault(userId, tfcode, type);;
		
	}

}
