package net.tfedu.zhl.cloud.resource.integration.cloud.api;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import net.tfedu.zhl.cloud.resource.integration.api.CloudDataAPIController;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 
  
    @author wangwr
    @date 2017年8月3日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class CloudDataAPIControllerTest extends BaseControllerTestCase{

	@Resource
	CloudDataAPIController controller;
	
	
	private void setCloudCheck(MockHttpServletRequest request){
		request.setParameter("nowdate", "1501723609827");
		request.setParameter("mdStr", "882FB8F8059C44ED05ADE85B2BFF3844");
	}
	
	
	
	@Test
	public void testAreaUpload() {
		
		setCloudCheck(request);
		
		request.setParameter("schoolIds", "126,127");
		System.out.println(controller.areaUpload(request));
		
	}

	@Test
	public void testUpdateTermSubject() {
		setCloudCheck(request);
		
		request.setParameter("userName", "csls11");
		request.setParameter("termId", "GZ");
		request.setParameter("subjectIds", "1,2,3,4,5,6");
		
		
		System.out.println(controller.updateTermSubject(request));
	
	}

}
