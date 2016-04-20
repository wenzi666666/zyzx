package net.tfedu.zhl.cloud.resource.asset.controller;

import javax.annotation.Resource;


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
		
		result =  assetController.getAssetOne(105l, request, response);
		Assert.isTrue("ok".equals(result.getCode()));
		
	}
	
	
	
	
	@Test
	public void testQueryMyAssets(){
		
    	request.setAttribute("currentUserId", 390320126l);

		result = assetController.queryAsset(request, response);
		
		Assert.isTrue("ok".equals(result.getCode()));
		
	}
	
	
	
	
	
	
	

}
