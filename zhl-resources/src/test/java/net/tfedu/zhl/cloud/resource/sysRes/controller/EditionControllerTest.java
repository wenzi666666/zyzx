package net.tfedu.zhl.cloud.resource.sysRes.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.controller.EditonController;
import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 测试版本信息
 * @author WeiCuicui
 *
 */
public class EditionControllerTest extends BaseControllerTestCase{

	@Resource EditonController editonController;
	
	@Test
	public void testEditionController()throws IOException{
		request = newGet("/resRestAPI/v1.0/editions");
		request.setParameter("termId", "1");
		request.setParameter("subjectId", "2");
		
		ResultJSON resultJSON = editonController.getAllEditions(request, response);
		Assert.isTrue(resultJSON != null);
		List<JSyscourse> editons = (List<JSyscourse>)resultJSON.getData();
		Assert.isTrue(editons.size() > 0);
		for(int i = 0; i < editons.size(); i++){
			System.out.println(editons.get(i).getId() + ":" + editons.get(i).getName() + ":" + editons.get(i).getTfcode());
		}
		
	}
	
	
}
