package net.tfedu.zhl.cloud.resource.sysRes.resList;

import java.io.IOException;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.resourceList.controller.ResourceListController;
import net.tfedu.zhl.cloud.utils.datatype.JsonUtil;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 系统、区本、校本资源controller单元测试
 * @author WeiCuicui
 *
 */
public class ResListControllerTest extends BaseControllerTestCase{

	@Resource ResourceListController resourceListController;
	
	/**
	 * 系统资源controller单元测试
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Test
	public void testSysResController()throws IOException{
		
		request.setParameter("poolId", "0");
		
		request.setParameter("mTypeId", "0");
		
		request.setParameter("fileFormat", "全部");
		
		request.setParameter("tfcode", "RJGZ040101");
		
		request.setParameter("orderBy", "0");
		
		request.setParameter("page", "1");
		
		request.setParameter("perPage", "10");
		
		ResultJSON json = resourceListController.getSysResources(request, response);
		
		JsonUtil.toJsonString(json);
        Assert.isTrue("OK".equals(json.getCode()));
		
	}
	
	/**
	 * 区本、校本资源controller单元测试
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Test
	public void testDisResController()throws IOException{
        
		request.setParameter("fromFlag", "3");
		
		request.setParameter("mTypeId", "0");
		
		request.setParameter("fileFormat", "全部");
		
		request.setParameter("tfcode", "RJGZ040101");
		
		request.setParameter("orderBy", "0");
		
		request.setParameter("page", "1");
		
		request.setParameter("perPage", "10");
		
		ResultJSON json = resourceListController.getDisResource(request, response);
		
		JsonUtil.toJsonString(json);
        Assert.isTrue("OK".equals(json.getCode()));
	}
}
