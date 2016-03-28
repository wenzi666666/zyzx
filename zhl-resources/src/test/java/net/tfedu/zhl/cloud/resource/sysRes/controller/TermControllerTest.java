package net.tfedu.zhl.cloud.resource.sysRes.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.util.Assert;

import net.tfedu.zhl.cloud.core.term.entity.JTerm;
import net.tfedu.zhl.cloud.resource.navigation.controller.TermController;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 * 测试学段controller
 * @author WeiCuicui
 *
 */
public class TermControllerTest extends BaseControllerTestCase {
    
	@Resource TermController termController;
	
	@Test
	public void testGetAllTerms() throws IOException{
		request = newGet("/resRestAPI/v1.0/terms");
		ResultJSON resultJSON = termController.selectAllTerms(request, response);
		
		Assert.isTrue(resultJSON != null);
		List<JTerm> terms = (List<JTerm>)resultJSON.getData();
		Assert.isTrue(terms.size() > 1);
		for(int i = 0; i < terms.size(); i++){
			System.out.println(terms.get(i).getId() + ":" + terms.get(i).getName());
		}
	}
}
