package net.tfedu.zhl.cloud.resource.asset.controller;

import java.io.IOException;

import javax.annotation.Resource;


import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
		long id  = 164882966;
		request.setParameter("_method", "PUT");
		request.setParameter("name", "微型学习视频的设计研究_edit");
		request.setParameter("unifTypeId", "1");
		request.setParameter("scope", "0");
		request.setParameter("keyword", "视频");
		request.setParameter("desc", "学习视频的设计研");
		request.setParameter("path", "upFile\\2016\\390440126\\10105\\2016042715352584924-46.pdf");
		request.setParameter("size", "683695");
		
		result = 	assetController.updateAsset(id, request, response);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	
		
		
		
		
		
	}
	
	@Test
	public void testadd() throws Exception{
		
		
//		、deleteAsset
		
    	request.setAttribute("currentUserId", 390320126l);
	
		
		request.setParameter("names", "java面试题大全");
		request.setParameter("unifTypeIds", "2");
		request.setParameter("tfcodes", "CZYW010101");
		request.setParameter("scopes", "1");
		request.setParameter("keywords", "java");
		request.setParameter("descs", "123");
		request.setParameter("paths", "upFile\\2016\\390410126\\10105\\2016051009332331133-46.doc");
		request.setParameter("sizes", "1111111");
		request.setParameter("iscoursewares", "0");
		request.setParameter("islocals", "0");
		result = assetController.deleteAsset(request, response);
		
		
		
		
		
		
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
	
	
	@Test
	public void testUploadConvertCallBack() throws ParamsException, Exception{
    	request.setAttribute("currentUserId", 390320126l);
		request.setParameter("file", "upFile\\2016\\390410126\\10105\\2016051009332331133-46.doc");
		request.setParameter("ext", "rename&userId=390320126");
		
		String _result = assetController.uploadConvertCallBack(request, response);
		
		Assert.isTrue("SUCCESS".equals(_result)) ;
		
		
		
	}
	
	

}
