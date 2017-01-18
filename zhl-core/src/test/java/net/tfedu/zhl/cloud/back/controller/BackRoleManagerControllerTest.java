package net.tfedu.zhl.cloud.back.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 
  
    @author wangwr
    @date 2017年1月18日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Transactional
public class BackRoleManagerControllerTest extends BaseControllerTestCase {

	@Resource 
	BackRoleManagerController controller;
	
	
	ResultJSON result ;
	
	
	@Rollback
	@Test
	public void testAddBackRole() throws Exception {
		
		
		result = controller.addBackRole("测试角色", "test_product", request);
		
		
		super.assertAndLog(result);
		
	}

	@Test
	@Rollback
	public void testUpdateBackRole() throws Exception {
		
		result = controller.updateBackRole(6l, "商务", null);
		
		super.assertAndLog(result);
	}

	@Test
	@Rollback
	public void testDelBackRole() throws Exception {
		
		result = controller.delBackRole(6l);
		
		super.assertAndLog(result);
	}

	@Test
	public void testPageBackRole() throws Exception {
		result = controller.pageBackRole(1, 10, request, response);
		
		super.assertAndLog(result);
	}

	@Test
	public void testQueryRoleConfig() throws Exception {
		
		result = controller.queryRoleConfig(1l, "resourceV3.2", request, response);
		
		super.assertAndLog(result);
	}

	@Test
	@Rollback
	public void testAddRoleConfig() throws Exception {
	
		result = controller.addRoleConfig(1l, new Long[]{222L,333L,444L});
		
		super.assertAndLog(result);
	}

	@Test
	@Rollback
	public void testDelRoleConfig() throws Exception {
		
		result = controller.delRoleConfig(1l,new Long[]{222L,333L,444L});
		
		super.assertAndLog(result);
	}

	@Test
	public void testQueryRoleConfigDetail() throws Exception {
		
		result = controller.queryRoleConfigDetail(2l, 1l);
		
		super.assertAndLog(result);
	}

	@Test
	@Rollback
	public void testAddRoleConfigDetail() throws Exception {
			result = controller.addRoleConfigDetail(1l, new Long[]{222L,333L,444L});
		
		super.assertAndLog(result);
	}

	@Test
	@Rollback
	public void testDelRoleConfigDetail() throws Exception {
			result = controller.delRoleConfigDetail(1l,new Long[]{222L,333L,444L});
		
		super.assertAndLog(result);
	}

}
