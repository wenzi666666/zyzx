package net.tfedu.zhl.cloud.back.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclist;

/**
 
  
    @author wangwr
    @date 2017年1月17日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Rollback
@Transactional
public class ProductBackFunlistControllerTest  extends BaseControllerTestCase{
	
	@Resource
	ProductBackFunlistController controller;
	
	ResultJSON  result ; 

	@Test
	public void testAddFunc() throws Exception {
		
		SProductBackFunclist func = new SProductBackFunclist();
		
		func.setFlag(false);
		func.setFuncdesc("测试功能");
		func.setName("测试功能");
		func.setProductCode("resourceV3.2");
		func.setPath("testFunc");
		
		 result = controller.addFunc(func, new BeanPropertyBindingResult(func, "func"), request);
		 
		 super.assertAndLog(result);
	}

	@Test
	public void testUpdateFunc() throws Exception {
		
		SProductBackFunclist func = new SProductBackFunclist();
		
		func.setId(11111l);
		func.setFuncdesc("测试功能");
		func.setName("测试功能");
		func.setProductCode("resourceV3.2");

		result = controller.updateFunc(func, new BeanPropertyBindingResult(func, "func"));
		 
		 super.assertAndLog(result);
	}

	@Test
	public void testDelFunc() throws Exception {
		
		result = controller.delFunc(11111l);
		 
		super.assertAndLog(result);
		
	}

	@Test
	public void testPageFunc() throws Exception {
		result = controller.pageFunc(1, 100, request, response);
		 
		super.assertAndLog(result);
	}

	@Test
	public void testQueryDetail() throws Exception {
		
		
		result = controller.queryDetail(1l, request, response);
		 
		super.assertAndLog(result);
		
	}

	@Test
	public void testDelDetail() throws Exception {
		result = controller.delDetail(1111l, request, response);
		 
		super.assertAndLog(result);
	}

	@Test
	public void testUpdateDetail() throws Exception {
		result = controller.updateDetail(1111l, "测试功能点", "testDetail", "testDetail", "测试功能点");
		 
		super.assertAndLog(result);
	}

	@Test
	public void testAddDetail() throws Exception {
		result = controller.addDetail("测试功能点", "testDetail", "testDetail", "测试功能点",1l);
		 
		super.assertAndLog(result);
	}

	@Test
	public void testAddDetailRoleConfig() throws Exception {
		result = controller.addDetailRoleConfig(2l, new Long[]{1l,2l,3l,4l}, 111111l);
		 
		super.assertAndLog(result);
	}

}
