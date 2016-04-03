package net.tfedu.zhl.message.service;

import javax.annotation.Resource;

import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;

import com.github.pagehelper.PageInfo;

public class JMessageServiceTest extends BaseControllerTestCase {
	
	@Resource
	JMessageService jMessageService;
	
	
	long userId = 390370126;
	
	@Test
	public void testNUm() {
		jMessageService.getUserNewMessageNumber(userId);
	}
	
	@Test
	public void testQuery(){
		
		int page =1 ;
		int perPage = 10 ;
		PageInfo obj =  jMessageService.queryMessage(userId, page, perPage);
		
		System.out.println(obj.getList().size());
	}

}
