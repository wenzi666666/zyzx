package net.tfedu.zhl.cloud.resource.portal.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 
  
  @author wangwr
  @date 2016年11月11日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
@Transactional
public class PortalControllerTest extends BaseControllerTestCase {

	@Resource
	PortalController controller;

	ResultJSON result;

	long schoolId = 10105;
	long districtId = 10;
	
	@Test
	public void testDynamic() throws Exception {

		
		System.out.println("------------school---testDynamic-------");

		
		result = controller.dynamic(request, 1, schoolId);

		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

		System.out.println("------------districtId---testDynamic-------");
		
		result = controller.dynamic(request, 2, districtId);

		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		
	}

	@Test
	public void testSchoolResStatistics()  throws Exception {
		
		result = controller.schoolResStatistics(request, schoolId);
		
		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

	@Test
	public void testSchoolUploadTop()  throws Exception {
		
		result = controller.schoolUploadTop(request, districtId);
		
		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

	@Test
	public void testSharedResTop()  throws Exception {
		
		result = controller.sharedResTop(request, districtId);
				
		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

	@Test
	public void testResourceViewTop()  throws Exception {
		
		result = controller.resourceViewTop(request);
		
		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}
	
	
	
	

}
