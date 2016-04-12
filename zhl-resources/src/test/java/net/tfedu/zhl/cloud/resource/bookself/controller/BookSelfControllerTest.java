package net.tfedu.zhl.cloud.resource.bookself.controller;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;

import com.sun.tools.internal.xjc.generator.bean.ImplStructureStrategy.Result;

public class BookSelfControllerTest extends BaseControllerTestCase {

	
	@Resource
	BookSelfController controller;
	
	

	@Test
	public void test() {
		
		request.setParameter("tfcode", "CXCZ01010101");
		request.setAttribute("currentUserId", 390320126l);
		request.setAttribute("resServiceLocal", "http://192.168.111.22:8099/down/");
		request.setAttribute("currentResPath", "http://192.168.111.22:8099/down/");
		ResultJSON json =  controller.getUserCourseware(request, response);
		System.out.println(json.toString());
		
	}

}
