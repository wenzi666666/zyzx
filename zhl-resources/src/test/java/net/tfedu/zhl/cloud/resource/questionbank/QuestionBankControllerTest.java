package net.tfedu.zhl.cloud.resource.questionbank;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.questionbank.aop.ResultQuestion;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

public class QuestionBankControllerTest extends BaseControllerTestCase{
	
	@Resource
	QuestionBankController controller;
	
	Object result ;

	@Test
	public void testGetBasicUserInfo() throws Exception {
		result = controller.getBasicUserInfo("");
		if(result instanceof ResultJSON){
			ResultJSON  json = (ResultJSON)result;
			Assert.isTrue("ok".equalsIgnoreCase(json.getCode()));
		}else if(result instanceof ResultQuestion){
			ResultQuestion json = (ResultQuestion)result;
			Assert.isTrue("success".equals(json.getMessage()));
		}else{
			Assert.isTrue(false);
		}
	}

	@Test
	public void testGetProvince() throws Exception {
		result = controller.getProvince();
		if(result instanceof ResultJSON){
			ResultJSON  json = (ResultJSON)result;
			Assert.isTrue("ok".equalsIgnoreCase(json.getCode()));
		}else if(result instanceof ResultQuestion){
			ResultQuestion json = (ResultQuestion)result;
			Assert.isTrue("success".equals(json.getMessage()));
		}else{
			Assert.isTrue(false);
		}
	}

}
