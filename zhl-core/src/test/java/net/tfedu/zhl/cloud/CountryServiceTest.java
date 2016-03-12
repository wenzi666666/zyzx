package net.tfedu.zhl.cloud;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.cloud.core.service.CountryService;
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

}
