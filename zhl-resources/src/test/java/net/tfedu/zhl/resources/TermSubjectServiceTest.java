package net.tfedu.zhl.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.xml.soap.Node;

import org.junit.Test;
import org.springframework.util.Assert;
import net.tfedu.zhl.cloud.core.subject.entity.JSubject;
import net.tfedu.zhl.cloud.resources.navigation.entity.TreeNode;
import net.tfedu.zhl.cloud.resources.navigation.service.TreeService;
import net.tfedu.zhl.cloud.resources.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resources.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resources.navigation.service.BookService;
import net.tfedu.zhl.cloud.resources.navigation.service.EditionService;
import net.tfedu.zhl.cloud.resources.navigation.service.TermSubjectService;
import net.tfedu.zhl.cloud.resources.navigation.service.UserDefaultService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

/**
 * 测试 学段下的学科 service
 * @author WeiCuicui
 *
 */
public class TermSubjectServiceTest extends BaseServiceTestCase{

	@Resource 
	private TermSubjectService termSubjectService = null;
	
	@Test
	public void testGetAllSubjectsByTerm() throws IOException{
		
		long termId = 1;
		
		List<JSubject> subjects = termSubjectService.getAllSubjectsByTerm(termId);
		
		Assert.isTrue(subjects.size() > 1);
		
		for (int i = 0; i < subjects.size(); i++) {
			
			System.out.println(subjects.get(i).getId() + ":" + subjects.get(i).getName());
		}

	}
	
}
