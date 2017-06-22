package net.tfedu.zhl.cloud.back.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.userlayer.province.entity.Province;

/**
 
  
    @author wangwr
    @date 2017年1月6日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Transactional
public class AreaProvinceControllerTest  extends BaseControllerTestCase{

	@Resource
	AreaProvinceController controller ;
	
	ResultJSON  result ; 
	
	
	@Test
	public void testAddProvince() throws Exception{
		
		Province province = new Province();
		
		province.setCode("xxx");
		province.setName("");
		
		

        
        
		result =  controller.addProvince(province, new BeanPropertyBindingResult(province, "province"));
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		
	}

	@Test
	public void testUpdateProvince() throws Exception {
		Province province = new Province();
		
		province.setId(10l);
		province.setName("山东省");
		
		
		result =  controller.updateProvince(province, new BeanPropertyBindingResult(province, "province"));
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testDelProvince() throws Exception {
		
		result =  controller.delProvince(36l);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testPageProvince() throws Exception{
		
		result =  controller.pageProvince(1, 10, request, response);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		
		
	}

}
