package net.tfedu.zhl.cloud.resource.sysRes.typeAndFormats;

import java.io.IOException;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.controller.TypesAndFormatsController;
import net.tfedu.zhl.cloud.utils.datatype.JsonUtil;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

public class TypeAndFormatControllerTest extends BaseControllerTestCase{

	@Resource TypesAndFormatsController typesAndFormatsController;
	
	/**
	 * 查询所有的资源库 controller单元测试
	 * @throws IOException
	 */
	@Test
    public void testGetAllTerms() throws IOException {
      
        ResultJSON json = typesAndFormatsController.getAllPools(request, response);

        JsonUtil.toJsonString(json);
        
        Assert.isTrue("OK".equals(json.getCode()));
    }
	
	/**
	 * 系统资源   类型
	 */
	@Test
	public void testGetSysTypes()throws IOException{
		
		request.setParameter("pTfcode", "RJGZ040101"); //设置课程结点tfcode
		request.setParameter("poolId", "0");
		
		ResultJSON json = typesAndFormatsController.getSysResTypesByPool(request, response);
		JsonUtil.toJsonString(json);
        Assert.isTrue("OK".equals(json.getCode()));
	}
	
	/**
	 * 系统资源   类型  e备课
	 */
	@Test
	public void testGetSysTypes_ePrepare()throws IOException{
		
		request.setParameter("pTfcode", "RJGZ040101"); //设置课程结点tfcode
		request.setParameter("poolId", "0");
		request.setParameter("isEPrepare", "1");
		
		ResultJSON json = typesAndFormatsController.getSysResTypesByPool(request, response);
		JsonUtil.toJsonString(json);
        Assert.isTrue("OK".equals(json.getCode()));
	}
	
	/**
	 * 系统资源   格式
	 * @throws IOException
	 */
	@Test
	public void testGetSysFormats()throws IOException{
		request.setParameter("pTfcode", "RJGZ040101");
		request.setParameter("typeId", "0");
		request.setParameter("poolId", "0");
		
		ResultJSON json = typesAndFormatsController.getSysResFormatsByMtype(request, response);
		JsonUtil.toJsonString(json);
        Assert.isTrue("OK".equals(json.getCode()));
	}
	
	/**
	 * 区本、校本资源   类型
	 * @throws IOException
	 */
	@Test
	public void testGetDisTypes()throws IOException{
		request.setParameter("tfcode", "RJGZ040101");
		request.setParameter("fromFlag", "3");
		
		ResultJSON json = typesAndFormatsController.getDisResTypesByPool(request, response);
		JsonUtil.toJsonString(json);
        Assert.isTrue("OK".equals(json.getCode()));
	}
	
	/**
	 * 区本、校本资源   类型 e备课
	 * @throws IOException
	 */
	@Test
	public void testGetDisTypes_ePrepare()throws IOException{
		request.setParameter("tfcode", "RJGZ040101");
		request.setParameter("fromFlag", "3");
		request.setParameter("isEPrepare", "1");
		
		ResultJSON json = typesAndFormatsController.getDisResTypesByPool(request, response);
		JsonUtil.toJsonString(json);
        Assert.isTrue("OK".equals(json.getCode()));
	}
	
	/**
	 * 区本、校本资源   格式
	 * @throws IOException
	 */
	@Test
	public void testGetDisFormats()throws IOException{
		request.setParameter("tfcode", "RJGZ040101");
		request.setParameter("fromFlag", "3");
		
		ResultJSON json = typesAndFormatsController.getDisFormats(request, response);
		JsonUtil.toJsonString(json);
        Assert.isTrue("OK".equals(json.getCode()));
	}
}
