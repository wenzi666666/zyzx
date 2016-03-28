package net.tfedu.zhl.cloud.resource.sysRes.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.core.subject.entity.JSubject;
import net.tfedu.zhl.cloud.resource.navigation.controller.TermSubjectController;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 测试学科controller
 * @author WeiCuicui
 *
 */
public class SubjectControllerTest extends BaseControllerTestCase {

	@Resource TermSubjectController termSubjectController;
	
	@Test
	public void testGetAllSubjectsByTerm() throws IOException{
		request = newGet("/resRestAPI/v1.0/subjects");
		request.setParameter("termId", "1");
		ResultJSON resultJSON = termSubjectController.getAllSubjectsByTerm(request, response);
		Assert.isTrue(resultJSON != null);
		List<JSubject> subjects = (List<JSubject>)resultJSON.getData();
		Assert.isTrue(subjects.size() > 0);
		for(int i = 0; i < subjects.size(); i++){
			System.out.println(subjects.get(i).getId() + ":" + subjects.get(i).getName());
		}
	}
}
