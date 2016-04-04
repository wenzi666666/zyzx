package net.tfedu.zhl.cloud.resource.asset.controller;

import javax.annotation.Resource;


import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;

public class AssetControllerTest extends BaseControllerTestCase {

	
	@Resource
	AssetController assetController;
	
	@Test
	public  void testquery(){
		
		request = this.newGet("/resRestAPI/v1.0/resource/asset");
		
		
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
	
	
	
	
	
	
	

}
