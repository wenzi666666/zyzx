package net.tfedu.zhl.cloud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.cloud.core.service.CountryService;
import net.tfedu.zhl.cloud.utils.datatype.JsonUtil;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

public class CountryServiceTest  extends BaseServiceTestCase {
	
	@Resource
	private CountryService countryService = null;
	
	@Test
	public void testAdd() {
		Country c = new Country();
		c.setCname("china");
		c.setCcode("1000");
		int i = countryService.insert(c);
		Assert.isTrue(i>0);
	}
	
	@Test
	public void testGetAll() {
		List<Country> list = countryService.selectAll();
		Assert.notEmpty(list);
	}
	
	@Test
	public void testInsertAll() {
		List<Country> list = new ArrayList<Country>();
		for(int i=0;i<100;i++){
			Country c = new Country();
			c.setCname("china");
			c.setCcode("1000" + i);
			list.add(c);
		}
		
		int num = countryService.insert(list);
		Assert.isTrue(num>0);
	}
	
	@Test
	public void testupdate() {
		int num = countryService.update(2,"haha kingdom");
		Assert.isTrue(num>0);
	}

	@Test
	public void testgetPage() {
		List<Country> list = countryService.getPage(1,10);
		JsonUtil.toJsonString(list);
		Assert.notEmpty(list);
	}
	
	@Test
	public void testgetSelective() {
		List<Map> list = countryService.getSelective();
		JsonUtil.toJsonString(list);
		Assert.notEmpty(list);
	}
	
	@Test
	public void testqueryIds() {
		List<Long> list = countryService.queryIds();
		JsonUtil.toJsonString(list);
		Assert.notEmpty(list);
	}
	

}
