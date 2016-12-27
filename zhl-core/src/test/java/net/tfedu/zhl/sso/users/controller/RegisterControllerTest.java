package net.tfedu.zhl.sso.users.controller;

import javax.annotation.Resource;

import org.junit.Test;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 
  
    @author wangwr
    @date 2016年12月27日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class RegisterControllerTest extends BaseControllerTestCase{

	@Resource
	RegisterController controller;
	
	ResultJSON result ; 
	
	
	
	
	@Test
	public void testIsCardAvailable() throws Exception {
		
		//正常card
		result =  controller.isCardAvailable("2926744475", "914275");
		super.assertAndLog(result);
		//密碼錯誤
//		result =  controller.isCardAvailable("1974951881", "xxxxx");
//		super.assertAndLog(result);
		//不存在
//		result =  controller.isCardAvailable("29340", "xxxxx");
//		super.assertAndLog(result);
		
		//已經被使用
//		result =  controller.isCardAvailable("2934094038", "999531");
//		super.assertAndLog(result);
		
	
	}

	@Test
	public void testAllTerms()throws Exception  {
		result =  controller.allTerms();
		super.assertAndLog(result);
	}

	@Test
	public void testTermSubject() throws Exception {
		result =  controller.termSubject(1l);
		super.assertAndLog(result);
	}

	@Test
	public void testProvinces() throws Exception {
		result =  controller.provinces();
		super.assertAndLog(result);
	}

	@Test
	public void testCitys() throws Exception {
		result =  controller.citys(1l);
		super.assertAndLog(result);
	}

	@Test
	public void testDistricts()throws Exception  {
		result =  controller.districts(1l);
		super.assertAndLog(result);
	}

	@Test
	public void testSchools()throws Exception  {
		result =  controller.schools(10l);
		super.assertAndLog(result);
	}

	@Test
	public void testClassList()throws Exception  {
		result =  controller.classList(10105l);
		super.assertAndLog(result);
	}

	@Test
	public void testRegister() throws Exception {
	}

}
