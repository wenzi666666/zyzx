package net.tfedu.zhl.cloud.back.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.userlayer.city.entity.City;

/**
 
  
    @author wangwr
    @date 2017年1月6日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Transactional
public class AreaCityControllerTest extends BaseControllerTestCase{

	
	@Resource
	AreaCityController controller ;
	
	ResultJSON  result ; 
	
	City city = null ;
	
	@Test
	public void testAddCity() throws Exception {
		city = new City();
		city.setName("測試");
		city.setProvinceid(1);
		
		result  = controller.addCity(city, new BeanPropertyBindingResult(city, "city"));
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		
	}

	@Test
	public void testUpdateCity() throws Exception {
		city = new City();
		
		city.setName("北京市");
		city.setId(1);
		result  =  controller.updateCity(city, new BeanPropertyBindingResult(city, "city"));
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		
	}

	@Test
	public void testDelCity() throws Exception {
		
		result  =  controller.delCity(1);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testPageCity() throws Exception {
		
		result  =  controller.pageCity(1, 1, 10, request);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
	}

}
