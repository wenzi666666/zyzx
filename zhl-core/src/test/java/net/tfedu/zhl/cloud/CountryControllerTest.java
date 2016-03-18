package net.tfedu.zhl.cloud;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import net.tfedu.zhl.cloud.core.controller.CountryController;
import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

public class CountryControllerTest extends BaseControllerTestCase{
	
	@Resource CountryController countryController;
	
	@Test
	public void testGetAll() throws IOException {
		request = newGet("/country/all");
		List<Country> list = countryController.getAll(request, response);
		Assert.isTrue(list.size() >1);
	}
}
