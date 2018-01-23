package net.tfedu.zhl.cloud.resource.poolTypeFormat.controller;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

public class TypesAndFormatsControllerTest extends BaseControllerTestCase {

	@Resource
	TypesAndFormatsController controller;
	
	
	@Test
	public void testGetAllPools() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSysResTypesByPool() throws Exception {
		
		request.setParameter("poolId", "0");
		request.setParameter("pTfcode", "RJCZ0201080101");
		request.setParameter("isEPrepare", "ePrepare");
		//pTfcode
		ResultJSON result = controller.getSysResTypesByPool(request, response);
		
		
		System.out.println(JSONObject.toJSONString(result));
		
	}

	@Test
	public void testGetSysResFormatsByMtype() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDisResTypesByPool() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDisFormats() {
		fail("Not yet implemented");
	}

}
