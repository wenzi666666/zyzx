package net.tfedu.zhl.cloud.back.controller;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 
  
    @author wangwr
    @date 2017年1月17日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Transactional
public class RoleManagerControllerTest extends BaseControllerTestCase {

	

	
	@Resource RoleManagerController controller; 
	
	ResultJSON result = null ;
	
	
	@Test
	@Rollback
	public void testAddRole() throws Exception {
		
		result =  controller.addRole("测试角色", "测试角色123", request);
	
		super.assertAndLog(result);
	}

	@Test
	@Rollback
	public void testUpdateRole() throws Exception {
		
		result =  controller.updateRole(22222l,"测试角色", "测试角色123");
		
		super.assertAndLog(result);
	}

	@Test
	@Rollback
	public void testDelRole() throws Exception {
		result =  controller.delRole(22222l);
		
		super.assertAndLog(result);
	}

	@Test
	public void testPageRole() throws Exception {
		result =  controller.pageRole(1, 10, request, response);
		
		super.assertAndLog(result);
	}

}
