package net.tfedu.zhl.cloud.resource.basedata;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.junit.Test;

import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.exception.NoAuthorizationException;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 
  
    @author wangwr
    @date 2017年2月21日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class BaseResourceDataAPIControllerTest extends BaseControllerTestCase {

	@Resource
	BaseResourceDataAPIController controller;
	
	
	ResultJSON result ; 
	
	
	long poolId =1 ;
	
	int typeId = 0 ;
	
	String fileFormat = "全部";
	
	String tfcode = "BJCZ030801";
	
	int orderBy =  0 ;
	
	int page = 1 ; 
	
	int perPage= 10 ;
	
	
	String userName = "test001";
	
	String appId = "679636";
	
	
		@Override
	public void tearDown() throws Exception {
		
		super.assertAndLog(result);
		
		super.tearDown();
	}

	@Test
	public void testTerms() {
		
		result = controller.terms();
		
	}

	@Test
	public void testSubjects() {
		
		result = controller.subjects(3l);
		
	}

	@Test
	public void testEditions() {
		result = controller.editions(3l, 1L);
	}

	@Test
	public void testBooks() {
		result = controller.books(1l);
	}

	@Test
	public void testPools() throws CustomException {
		request.setParameter("userName", userName);
		request.setParameter("appId", appId);
		
		result = controller.pools(3l, 1l, request);
	}

	@Test
	public void testContents() {
		result = controller.contents(2l);
	}

	@Test
	public void testFormats() {
		result = controller.formats(1l, "BJCZ030801", 0, request);
	}

	@Test
	public void testSysResource() throws CustomException {
		
		
		
		
		request.setParameter("mTypeId", String.valueOf(typeId));
		request.setParameter("poolId", String.valueOf(poolId));
		request.setParameter("fileFormat", fileFormat);
		request.setParameter("tfcode", tfcode);
		request.setParameter("orderBy", String.valueOf(orderBy));
		request.setParameter("page", String.valueOf(page));
		request.setParameter("perPage", String.valueOf(perPage));
		
		
		result = controller.sysResource(request);
	}

	@Test
	public void testSyscourseResInfo() {
		
		result = controller.syscourseResInfo(1l);
	}

	@Test
	public void testResRecommendation() throws ParamsException {
		
		request.setParameter("mTypeId", String.valueOf(typeId));
		request.setParameter("poolId", String.valueOf(poolId));
		request.setParameter("format", fileFormat);
		request.setParameter("tfcode", tfcode);
		request.setParameter("orderBy", String.valueOf(orderBy));
		request.setParameter("page", String.valueOf(page));
		request.setParameter("perPage", String.valueOf(perPage));
		request.setParameter("resId", String.valueOf(1l));
		
		
		result = controller.resRecommendation(request);
	}

	@Test
	public void testSysResourcePlay() throws CustomException {
		result = controller.sysResourcePlay(appId,userName,1l, request);
	}

	@Test
	public void testSysResourceDown() throws UnsupportedEncodingException, NoAuthorizationException, CustomException {
		result = controller.sysResourceDown(appId,userName,1l, request);
	}
	
	

	
	
	public static void main(String[] args) {
//		Sheet sheet =  null;
		
		
	}
}
