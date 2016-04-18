package net.tfedu.zhl.cloud.resource.asset.controller;

import javax.annotation.Resource;


import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;

public class AssetControllerTest extends BaseControllerTestCase {

	
	@Resource
	AssetController assetController;
	
	
	ResultJSON result = null;
	
/*	@Test
	public  void testquery(){
		
//		request = this.newGet("/resRestAPI/v1.0/resource/asset");
		
		
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
	
*/
	
	
	
	@Test
	public  void testgetAssetOne(){
		
		result =  assetController.getAssetOne(105l, request, response);
		System.out.println(result.toString());
		
	}
	
	
	
	
	@Test
	public void testQueryMyAssets(){
		
    	request.setAttribute("currentUserId", 390320126l);

		result = assetController.queryAsset(request, response);
		
		System.out.println(result==null?"":result.getData().toString());
		
	}
	
	
	
	
	
	
	

}
