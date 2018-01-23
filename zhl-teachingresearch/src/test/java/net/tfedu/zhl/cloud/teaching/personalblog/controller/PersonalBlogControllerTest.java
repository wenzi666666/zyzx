package net.tfedu.zhl.cloud.teaching.personalblog.controller;

import javax.annotation.Resource;

import org.junit.Test;

import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlog;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlogComment;
import net.tfedu.zhl.cloud.teaching.personalblog.entity.PersonalBlogPraiseRecord;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.sign.SignUtil;
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
	
	
	
	
	
	
	public static void main(String[] args) {
		long userId = 390320126L;
		String title = "testnnnew20170701";
		String content = "testnnnew20170701testtestcontent";
		String scope = "P";
		int scopeid = 0;
		String token = "09DA1D90C80E4260850179E1196F9EB2";
		
		
		String sign =  SignUtil.createSignMap(new String[]{"userId","title","content","scope","scopeid","token"}
			, new Object[]{userId,title,content,scope,scopeid,token}
			, "9k8i78jug6hd93kjf84h");
		
		
		System.out.println("sign:"+sign);
		
		
	}

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

		
		result = controller.query(request, 390320126L, 1, 10);
		assertAndLog(result);
	}

	@Test
	public void testGet() throws Exception {

		
		result = controller.get(request, "0960305cbbf74a24a5c042f32ebc18ea");
		assertAndLog(result);
	}

	@Test
	public void testAddPraise() throws CustomException {
	
		PersonalBlogPraiseRecord record = new PersonalBlogPraiseRecord();
		
				//
		record.setBlogUuid("699ffb1877c14ab8a2c60c785cc51355");
		
		
		result = controller.addPraise(request, record);
		assertAndLog(result);
	}

	@Test
	public void testAddComment() {
		PersonalBlogComment comment = new PersonalBlogComment();
		
		comment.setBlogUuid("699ffb1877c14ab8a2c60c785cc51355");
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
		result = controller.lastBlog(request, "W", 0L, 4);
		assertAndLog(result);
	}

	@Test
	public void testLastActive() throws Exception {
		request.setAttribute("currentUserName","panyu");
		result = controller.lastActive(request, "D", 82L, 1, 10);
		assertAndLog(result);
	}

}
