package net.tfedu.zhl.cloud.resource.back.appuserpoolconfig.controller;

import javax.annotation.Resource;

import org.junit.Test;

import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 
  
    @author wangwr
    @date 2017年2月24日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class AppUserPoolConfigControllerTest extends BaseControllerTestCase{
	
	@Resource
	AppUserPoolConfigController controller;
	
	ResultJSON result ;
	
	
	String appId = "679636";

	
	String userName = "test001";
	
	
	@Override
	public void tearDown() throws Exception {
		super.assertAndLog(result);
		super.tearDown();
	}

	@Test
	public void testPageQueryAppUserPoolConfig() throws Exception {
		result = controller.pageQueryAppUserPoolConfig(1, 10, 0, 0l,0l, "", appId);
	}

	@Test
	public void testUpdateAppUserPoolConfig()throws CustomException  {
		result = controller.updateAppUserPoolConfig(3l, 1l, 0l, 3, appId, userName, 12);
	}

	@Test
	public void testUploadExcelFile() throws Exception {
		String path = "C:\\Users\\wangwr\\Desktop\\temp\\temp\\yunzhou_test_appuserpoolconfig_indentinfo.xls";
		result =  controller.parseExcel(appId, path);
	}

	@Test
	public void testAddAppUserPoolConfigBatch() throws Exception {

		String jsonStr = "[{\"appId\":\"679636\",\"month\":1,\"pools\":[4,1,2,3,5,6,10001],\"subjectId\":0,\"termId\":3,\"userName\":\"test002\"},{\"appId\":\"679636\",\"month\":2,\"pools\":[4],\"subjectId\":0,\"termId\":2,\"userName\":\"test002\"}]";
		
		request.setParameter("indentInfo", jsonStr);
		result =  controller.addAppUserPoolConfigBatch(request);
		
	}

	@Test
	public void testDelAppUserPoolConfig() throws CustomException {
		result = controller.delAppUserPoolConfig(3l);
	}

}
