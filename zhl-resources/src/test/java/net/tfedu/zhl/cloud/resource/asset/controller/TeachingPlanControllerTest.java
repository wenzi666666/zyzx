package net.tfedu.zhl.cloud.resource.asset.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.asset.entity.TeachingPlan;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 * 
 * 教案模块 单元测试
 * 
 * @author wangwr
 * @date 2016年11月10日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 * 
 */
public class TeachingPlanControllerTest extends BaseControllerTestCase {

	@Resource
	TeachingPlanController controller;

	ResultJSON result;

	String tfcode = "RJCZ0101050101";

	@Test
	public void testAddTeachingPlan() throws Exception {

		TeachingPlan obj = new TeachingPlan();
		obj.setContent("示例示例示例示例示例示例示例示例 教案");
		obj.setKeyword("示例,教案");
		obj.setTfcode(tfcode);
		obj.setTitle("My示例教案");

		result = controller.addTeachingPlan(request, obj);

		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

	@Test
	public void testEditTeachingPlan() throws Exception {

		TeachingPlan obj = new TeachingPlan();
		obj.setId(164884607l);
		obj.setContent("----示例示例示例示例示例示例示例示例 教案");
		obj.setKeyword("-----示例,教案");
		obj.setTfcode(tfcode);
		obj.setTitle("-----My示例教案");

		result = controller.editTeachingPlan(request, obj, 164884607l);

		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

	}

	@Test
	public void testQueryPage() throws Exception {

		result = controller.queryPage(request, 1, 10);

		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

	@Test
	public void testGetTeachingPlan() throws Exception {

		result = controller.getTeachingPlan(request, 164884593l);

		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

	@Test
	public void testDelTeachingPlan() throws Exception {

		result = controller.delTeachingPlan(request, 164884593l);

		System.out.println(JSONObject.toJSONString(result));

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

}
