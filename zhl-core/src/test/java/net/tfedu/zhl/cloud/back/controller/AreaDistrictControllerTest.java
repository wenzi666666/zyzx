package net.tfedu.zhl.cloud.back.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.district.entity.District;

/**
 * 
 * 
 * @author wangwr
 * @date 2017年1月6日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Transactional
public class AreaDistrictControllerTest extends BaseControllerTestCase {

	@Resource
	AreaDistrictController controller;

	ResultJSON result;

	District district = null;

	@Test
	public void testAddDistrict() throws Exception {
		
		district = new District();
		
		district.setName("測試區");
		district.setCityid("1");
		
		result = controller.addDistrict(district, new BeanPropertyBindingResult(district, "district"));
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testUpdateDistrict() throws Exception {
		
		district = new District();
		
		district.setName("測試區");
		district.setId(1);
		
		result = controller.updateDistrict(district, new BeanPropertyBindingResult(district, "district") );
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testDelDistrict() throws Exception {
		result =  controller.delDistrict(1);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testPageDistrict() throws Exception {
		
		result =  controller.pageDistrict(1, 1, 10, request, response);

		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

		log.info(JSONObject.toJSONString(result));
	}

}
