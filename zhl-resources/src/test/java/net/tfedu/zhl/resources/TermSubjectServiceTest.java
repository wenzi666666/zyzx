package net.tfedu.zhl.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.util.Assert;
import net.tfedu.zhl.cloud.core.subject.entity.JSubject;
import net.tfedu.zhl.cloud.resources.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resources.navigation.service.BookService;
import net.tfedu.zhl.cloud.resources.navigation.service.EditionService;
import net.tfedu.zhl.cloud.resources.navigation.service.TermSubjectService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

/**
 * 测试 学段下的学科 service
 * @author WeiCuicui
 *
 */
public class TermSubjectServiceTest extends BaseServiceTestCase{

	@Resource 
	private BookService bookService = null;
	
	@Test
	public void testGetAllSubjectsByTerm() throws IOException{
		long pnodeId = 67527;
	
		List<JSyscourse> bookes = bookService.getAllBooks(pnodeId);
		Assert.isTrue(bookes.size() > 1);
		
		for (int i = 0; i < bookes.size(); i++) {
			System.out.println(bookes.get(i).getId() + ":" + bookes.get(i).getName() + ":" + bookes.get(i).getTfcode());
		}
		
	}
	
}
