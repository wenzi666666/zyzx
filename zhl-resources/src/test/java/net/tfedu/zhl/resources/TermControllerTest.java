package net.tfedu.zhl.resources;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.util.Assert;
import net.tfedu.zhl.cloud.resources.navigation.controller.EditonController;
import net.tfedu.zhl.cloud.resources.navigation.entity.JSyscourse;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

public class TermControllerTest extends BaseControllerTestCase {
    
	@Resource EditonController editonController;
	
	@Test
	public void testGetAllEditons() throws IOException{
		request = newGet("/resRestAPI/v1.0/editions");
		List<JSyscourse> editions = editonController.getAllEditions(request, response);
		Assert.isTrue(editions.size() > 1);
		
	}
	
}
