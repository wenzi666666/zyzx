package net.tfedu.zhl.cloud.resource.asset.service;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.asset.entity.ReviewResultStatis;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;

public class ZAssetServiceTest  extends BaseControllerTestCase{

	
	@Resource
	ZAssetService  zAssetService ;
	
	
	@Test
	public void testGetAllResType() {
		List<FirstLevelResType> list =  zAssetService.getAllFirstLevelResType();
		for (int i = 0; i < list.size(); i++) {
			FirstLevelResType o  = list.get(i);
			System.out.println(o.getId()+o.getMtype()+o.getCode());			
		}
	}

	
	@Test
	public void testGetAllFileFormat() {
	   List<String> list = zAssetService.getAllFileFormat();
	   for (String string : list) {
		   System.out.println(string);
	   }
	}

	
	
	
	@Test
	public void testReviewStatic(){
		long userId =390330126;
		ReviewResultStatis s =  zAssetService.getReviewStatis(userId);
		System.out.println(s.getReviewNumber()+"-------"+s.getUnReviewNumber());
	}
	
	
	@Test
	public void testAdd(){
		
	}
	
	
	@Test
	public void testUpdate(){
		
	}
	
	@Test
	public void testDel(){
		
	}

}
