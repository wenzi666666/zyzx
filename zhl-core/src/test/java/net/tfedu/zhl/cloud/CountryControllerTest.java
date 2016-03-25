package net.tfedu.zhl.cloud;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import net.tfedu.zhl.cloud.core.controller.CountryController;
import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.cloud.utils.datatype.JsonUtil;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

public class CountryControllerTest extends BaseControllerTestCase {

	@Resource
	CountryController countryController;

	@Test
	public void testAdd() throws Exception {
		Country c = new Country();
		c.setCname("china");
		c.setCcode("1000");
		Map<?, ?> map = countryController.create(request, response, c);
		Assert.isTrue(map.containsKey("code"));
	}

	@Test
	public void testAddArray() throws Exception {
		Country[] list = new Country[10];
		for (int i = 0; i < 10; i++) {
			Country c = new Country();
			c.setCname("china");
			c.setCcode("1000" + i);
			list[i]=c;
		}

		Map<?, ?> map = countryController.addArray(request, response, list);
		Assert.isTrue(map.containsKey("code"));
	}

	@Test
	public void testUpdate() throws Exception {
		Country entity = new Country();
		entity.setCname("china");
		Map<?, ?> map = countryController.update(request, response, entity);
		Assert.isTrue(map.containsKey("code"));
	}
	
	@Test
	public void testDelete() throws Exception {
		Map<?, ?> map = countryController.delete(request, 2);
		Assert.isTrue(map.containsKey("code"));
	}

	@Test
	public void testGetPage() {
		request.addParameter("pageNum", "1");
		request.addParameter("pageSize", "10");
		Map<?, ?> map = countryController.getPage(request, response);
		JsonUtil.toJsonString(map);
		Assert.isTrue(map.containsKey("code"));
	}

	@Test
	public void testGetSelective() {
		Map<?, ?> map = countryController.queryMaps(request, response);
		JsonUtil.toJsonString(map);
		Assert.isTrue(map.containsKey("code"));
	}

	@Test
	public void testGetByProperty() {
		Map<?, ?> map = countryController.queryIds(request, response);
		JsonUtil.toJsonString(map);
		Assert.isTrue(map.containsKey("code"));
	}
}
