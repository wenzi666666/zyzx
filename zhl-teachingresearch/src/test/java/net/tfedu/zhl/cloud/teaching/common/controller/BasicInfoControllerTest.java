package net.tfedu.zhl.cloud.teaching.common.controller;

import javax.annotation.Resource;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

public class BasicInfoControllerTest  extends BaseControllerTestCase{

	
	@Resource
	BasicInfoController controller;
	
	ResultJSON result ;
	
	
	@Test
	public void testGetAllTerm() throws Exception {
		result = controller.getAllTerm();
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}

}
