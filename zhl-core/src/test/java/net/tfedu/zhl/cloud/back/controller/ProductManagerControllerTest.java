package net.tfedu.zhl.cloud.back.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.back.product.entity.SProduct;

/**
 
  
    @author wangwr
    @date 2017年1月16日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Transactional
public class ProductManagerControllerTest extends BaseControllerTestCase {
	
	@Resource
	ProductManagerController controller;
	
	ResultJSON result ; 
	

	@Test
	public void testAddProduct() throws Exception {
		
		SProduct pro = new SProduct();
		pro.setName("測試產品");
		pro.setCode("testPro");
		
		result  =  controller.addProduct(pro, new BeanPropertyBindingResult(pro, "pro"), request);
		
		super.assertAndLog(result);
		
	}

	@Test
	public void testUpdateProduct() throws Exception {

		SProduct pro = new SProduct();
		pro.setId(5l);
		pro.setName("測試產品");
		pro.setCode("testPro");
	
		result  =  controller.updateProduct(pro, new BeanPropertyBindingResult(pro, "pro"));
		
		super.assertAndLog(result);
	
	}

	@Test
	public void testDelProduct() throws Exception {
		result  =  controller.delProduct(5l);
		
		super.assertAndLog(result);

	}

	@Test
	public void testPageProduct() throws Exception {
		result  =  controller.pageProduct(1, 10, request, response);
		
		super.assertAndLog(result);
		
	}
	

}
