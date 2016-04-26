package net.tfedu.zhl.cloud.resource.asset.controller;

import java.io.IOException;

import javax.annotation.Resource;


import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
public class AssetControllerTest extends BaseControllerTestCase {

	
	@Resource
	AssetController assetController;
	
	
	ResultJSON result = null;
	
	@Test
	public  void testquery(){
		
	
		
	}
	
	
	@Test
	public void testDel(){
		
		
		
	}
	
	@Test
	public void testupdate(){
		
		
		
	}
	
	@Test
	public void testadd(){
		
		
		
	}
	

	
	
	
	@Test
	public  void testgetAssetOne() throws Exception{
		
		result =  assetController.getAssetOne(164882909l, request, response);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
	}
	
	
	
	
	@Test
	public void testQueryMyAssets(){
		
    	request.setAttribute("currentUserId", 390320126l);

		result = assetController.queryAsset(request, response);
		
		Assert.isTrue("ok".equals(result.getCode()));
		
	}
	
	
	
	

	@Test
	public  void testgetCourseAssetType() throws Exception{
		
    	request.setAttribute("currentUserId", 390330126l);
    	request.setParameter("tfcode", "CZYW010101");
    	request.setParameter("title", "2");
		result =  assetController.getCourseAssetType(request, response);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
	}
	
	
	
	
	@Test
	public void testgetCourseAsset() throws ParamsException, IOException{
		
    	request.setAttribute("currentUserId", 390320126l);
    	request.setParameter("tfcode", "CZYW010101");
    	request.setParameter("title", "");

		result = assetController.getCourseAsset(request, response);
		
		Assert.isTrue("OK".equals(result.getCode()));
		
	}
	
	
	
	
	

}
