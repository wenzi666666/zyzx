package net.tfedu.zhl.cloud.resource.sysRes.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.controller.ResPoolController;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

public class ResPoolsControllerTest extends BaseControllerTestCase {

    @Resource ResPoolController resPoolController;
	
	@Test
	public void testGetAllTerms() throws IOException{
		request = newGet("/resRestAPI/v1.0/pools");
		ResultJSON resultJSON = resPoolController.getAllPools(request, response);
		
		Assert.isTrue(resultJSON != null);
		List<ResPool> pools = (List<ResPool>)resultJSON.getData();
		Assert.isTrue(pools.size() > 0);
		for(int i = 0; i < pools.size(); i++){
			System.out.println(pools.get(i).getId() + ":" + pools.get(i).getName());
		}
	}
}
