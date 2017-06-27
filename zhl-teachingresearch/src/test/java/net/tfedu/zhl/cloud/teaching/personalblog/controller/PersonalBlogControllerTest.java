package net.tfedu.zhl.cloud.teaching.personalblog.controller;

import javax.annotation.Resource;

import org.junit.Test;

import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlog;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlogComment;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlogPraiseRecord;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 
  
    @author wangwr
    @date 2017年6月27日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class PersonalBlogControllerTest extends BaseControllerTestCase {
	
	
	@Resource
	PersonalBlogController controller ; 
	
	
	ResultJSON result ; 
	
	
	

	@Test
	public void testAdd() {
		
		long  userId =(Long) request.getAttribute("currentUserId");
		
		PersonalBlog blog = new PersonalBlog();
		
		
		blog.setUserId(userId);
		blog.setTitle("testnnnew");
		blog.setContent("testtestcontent");
		blog.setScope("P");
		blog.setScopeid(0L);
		
		
		
		result = controller.add(request, blog);
		assertAndLog(result);
	}

	@Test
	public void testEdit() {
		
		
		PersonalBlog blog = new PersonalBlog();
		
		
		blog.setUuid("558ffd9223bc40f68dfbd02e3e811c5b");
		blog.setTitle("test_edit");
		blog.setContent("test_edittesttestcontent");
		blog.setScope("P");
		blog.setScopeid(0L);
		
		
		
		result = controller.edit(request, blog);
		assertAndLog(result);
	
	}

	@Test
	public void testDelete() {
		
		result = controller.delete(request, "558ffd9223bc40f68dfbd02e3e811c5b");
		assertAndLog(result);
		
	}

	@Test
	public void testQuery() {
		long  userId =(Long) request.getAttribute("currentUserId");

		
		result = controller.query(request, userId, 1, 10);
		assertAndLog(result);
	}

	@Test
	public void testGet() {

		
		result = controller.get(request, "558ffd9223bc40f68dfbd02e3e811c5b");
		assertAndLog(result);
	}

	@Test
	public void testAddPraise() {
		long  userId =(Long) request.getAttribute("currentUserId");
	
		PersonalBlogPraiseRecord record = new PersonalBlogPraiseRecord();
		
				//
		record.setBlogUuid("558ffd9223bc40f68dfbd02e3e811c5b");
		
		
		result = controller.addPraise(request, record);
		assertAndLog(result);
	}

	@Test
	public void testAddComment() {
		PersonalBlogComment comment = new PersonalBlogComment();
		
		comment.setBlogUuid("558ffd9223bc40f68dfbd02e3e811c5b");
		comment.setContent("contesnt1212121-");
		
		
		result = controller.addComment(request, comment);
		assertAndLog(result);
		
	}

	@Test
	public void testQueryComment() {
		
		result = controller.queryComment(request, "558ffd9223bc40f68dfbd02e3e811c5b", 1, 10);
		assertAndLog(result);
	}

	@Test
	public void testLastBlog() throws Exception {
		result = controller.lastBlog(request, "S", 1001L, 5);
		assertAndLog(result);
	}

	@Test
	public void testLastActive() {
		
	}

}
