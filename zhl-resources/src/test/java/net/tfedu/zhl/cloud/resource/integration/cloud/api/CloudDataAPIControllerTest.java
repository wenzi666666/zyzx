package net.tfedu.zhl.cloud.resource.integration.cloud.api;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.integration.api.CloudDataAPIController;
import net.tfedu.zhl.cloud.resource.integration.entity.ResultInfo;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 * 
 * 
 * @author wangwr
 * @date 2017年8月3日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Transactional
public class CloudDataAPIControllerTest extends BaseControllerTestCase {

	@Resource
	CloudDataAPIController controller;

	private void setCloudCheck(MockHttpServletRequest request) {
		request.setParameter("nowdate", "1501723609827");
		request.setParameter("mdStr", "882FB8F8059C44ED05ADE85B2BFF3844");
	}

	@Test
	public void testAreaUpload() {

		setCloudCheck(request);

		request.setParameter("schoolIds", "126,127");
		System.out.println(controller.areaUpload(request));

	}

	@Test
	public void testUpdateTermSubject() {
		setCloudCheck(request);

		request.setParameter("userName", "csls11");
		request.setParameter("termId", "GZ");
		request.setParameter("subjectIds", "1,2,3,4,5,6");

		System.out.println(controller.updateTermSubject(request));

	}

	@Test
	public void testCloudTree() throws Exception {
		setCloudCheck(request);

		request.setParameter("userName", "csls01");
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");
		request.setParameter("cloudPlatForm", "http://192.168.111.8");

		System.out.println(JSONObject.toJSONString(controller.cloudTree(response, request)));

		request.setParameter("id", "5603493");
		System.out.println(JSONObject.toJSONString(controller.cloudTree(response, request)));

	}

	@Test
	public void testdelAsset() throws Exception {
		setCloudCheck(request);

		request.setParameter("resId", "164884632");
		System.out.println(JSONObject.toJSONString(controller.delAsset(request, response)));

	}

	@Test
	public void testDownRecord() throws Exception {
		setCloudCheck(request);
		/*
		 * //自建资源 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
		 * request.setParameter("fromflag", "0");
		 * System.out.println(JSONObject.toJSONString(controller.downRecord(
		 * request, response,164884630L)));
		 * 
		 * //系统资源 request.setParameter("fromflag", "1");
		 * System.out.println(JSONObject.toJSONString(controller.downRecord(
		 * request, response,1L)));
		 * 
		 * //共享资源 request.setParameter("fromflag", "2");
		 * System.out.println(JSONObject.toJSONString(controller.downRecord(
		 * request, response,164884632L)));
		 * 
		 * //3 区本 4 校本
		 * 
		 * 
		 * request.setParameter("fromflag", "3");
		 * System.out.println(JSONObject.toJSONString(controller.downRecord(
		 * request, response,871648L)));
		 */

		request.setParameter("fromflag", "4");
		System.out.println(JSONObject.toJSONString(controller.downRecord(request, response, 871647L)));

	}

	@Test
	public void testdownUrlForZip() throws Exception {
		setCloudCheck(request);

		request.setParameter("ids", "164884630,1,164884632,871648,871647");
		// 自建资源 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
		request.setParameter("fromflags", "0,1,2,3,4");
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");
		request.setParameter("cloudPlatForm", "http://192.168.111.8");

		System.out.println(JSONObject.toJSONString(controller.downUrlForZip(request, response)));

	}
	
	@Test
	public void testGetUpUrl() throws Exception {
		setCloudCheck(request);
		
		request.setParameter("userName", "csls01");
	
		System.out.println(JSONObject.toJSONString(controller.getUpUrl(request, response)));
		
	}
	
	
	@Test
	public void testPool() throws Exception {
		
		setCloudCheck(request);

		
		System.out.println(JSONObject.toJSONString(controller.pool(request, response)));

		
	}
	@Test
	public void testResInfo() throws Exception {
		setCloudCheck(request);
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");
		request.setParameter("cloudPlatForm", "http://192.168.111.8");

		
		 //自建资源 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
		  request.setParameter("fromflag", "0");
		  request.setParameter("resId", "164884630");
		  System.out.println(JSONObject.toJSONString(controller.resInfo(request, response)));

		  
		  
		  //自建资源 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
		  request.setParameter("fromflag", "1");
		  request.setParameter("resId", "1");
		  System.out.println(JSONObject.toJSONString(controller.resInfo(request, response)));
		  
		  
		  
		  //自建资源 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
		  request.setParameter("fromflag", "2");
		  request.setParameter("resId", "164884632");
		  System.out.println(JSONObject.toJSONString(controller.resInfo(request, response)));
		  
		  
		  
		  //自建资源 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
		  request.setParameter("fromflag", "3");
		  request.setParameter("resId", "871648");
		  System.out.println(JSONObject.toJSONString(controller.resInfo(request, response)));
		  
		  
		  
		  //自建资源 0 自建资源 1 系统资源 2共享资源 (3 区本 4 校本资源 暂不支持)
		  request.setParameter("fromflag", "4");
		  request.setParameter("resId", "871647");
		  System.out.println(JSONObject.toJSONString(controller.resInfo(request, response)));
		  
		  
		 


	}
	@Test
	public void testIsUserIntegralEnough() throws Exception {
		setCloudCheck(request);
		request.setParameter("userId", "390320126");

		
		request.setParameter("sharedAssetIds", "164884632");
		request.setParameter("sharedUserIds", "395680107");
		request.setParameter("sharedAssetIntegrals", "10");
		System.out.println(JSONObject.toJSONString(controller.isUserIntegralEnough(request, response)));


	}
	@Test
	public void testIsUserIntegralEnough2() throws Exception {
		setCloudCheck(request);
		request.setParameter("userId", "390320126");

		
		request.setParameter("sharedAssetIds", "164884626");
		request.setParameter("sharedUserIds", "390330126");
		request.setParameter("sharedAssetIntegrals", "5");
		System.out.println(JSONObject.toJSONString(controller.isUserIntegralEnough(request, response)));
		
		
	}
	@Test
	public void testIsUserIntegralEnough3() throws Exception {
		setCloudCheck(request);
		request.setParameter("userId", "390320126");

		
		request.setParameter("sharedAssetIds", "164884623");
		request.setParameter("sharedUserIds", "395680174");
		request.setParameter("sharedAssetIntegrals", "2");
		System.out.println(JSONObject.toJSONString(controller.isUserIntegralEnough(request, response)));
		
		
	}
	@Test
	public void testIsUserIntegralEnough4() throws Exception {
		setCloudCheck(request);
		request.setParameter("userId", "390320126");

		
		request.setParameter("sharedAssetIds", "164884632,164884626,164884623");
		request.setParameter("sharedUserIds", "395680107,390330126,395680174");
		request.setParameter("sharedAssetIntegrals", "10,5,2");
		System.out.println(JSONObject.toJSONString(controller.isUserIntegralEnough(request, response)));
		
		
	}
	@Test
	public void testUserIntegralExpend() throws Exception {
		setCloudCheck(request);

		request.setParameter("sharedAssetIds", "164884623");
		request.setParameter("sharedUserIds", "395680174");
		request.setParameter("sharedAssetIntegrals", "2");

		request.setParameter("userId", "390320126");
		
			
		System.out.println(JSONObject.toJSONString(controller.userIntegralExpend(request, response)));

		
	}
	
	@Test
	public void testSchoolList() throws Exception {
		setCloudCheck(request);
		
		request.setParameter("userId", "390320126");
		
		System.out.println(JSONObject.toJSONString(controller.schoolList(request, response)));
		
		
	}
	@Test
	public void testxueResInfo() throws Exception {
		setCloudCheck(request);
		


		request.setParameter("userName", "csls01");
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");
		request.setParameter("cloudPlatForm", "http://192.168.111.8");

		ResultInfo result = controller.xueResInfo(request, "0,1,2,3,4", "164884630,1,164884632,871648,871647");
		
		System.out.println(JSONObject.toJSONString(result));

	}
	@Test
	public void testResType() throws Exception {
		setCloudCheck(request);
		request.setParameter("userName", "csls01");
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");
		request.setParameter("cloudPlatForm", "http://192.168.111.8");
		
		
		//type 0 为自建课程树 1 系统资源 2 共享资源 3校本资源 4 区本资源
		//isCollect 自建资源页面区分0:所有1:自建2:收藏3我的共享
		int type = 4 ;
		int isCollect =0;
		Integer  fromFlag = 1 ;
		Long id =  12650L  ;
		String  tfcode = "RJGZ010101";
		Integer searchFlag = 1 ;
		Long mType= 0L ;
		

		
		ResultInfo result = controller.resType(request, type, isCollect, fromFlag, id, tfcode, searchFlag, mType);
		
		System.out.println(JSONObject.toJSONString(result));
		
	}
	@Test
	public void testResList() throws Exception {
		setCloudCheck(request);
		request.setParameter("userName", "csls01");
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");
		request.setParameter("cloudPlatForm", "http://192.168.111.8");
		
		
		//type 0 为自建课程树 1 系统资源 2 共享资源 3校本资源 4 区本资源
		//isCollect 自建资源页面区分0:所有1:自建2:收藏3我的共享
		int type = 0 ;
		int curPage =1;
		int perPage =10;
		int isCollect =1;
		Integer  fromFlag = 1 ;
		Long id =  12650L  ;
		String  tfcode = "RJGZ010101";
		Integer searchFlag = 1 ;
		Long mTypeId= 0L ;
		String  resType = "all";
		String  resPattern = "all";
		
		
		ResultInfo result = controller.resList(request, type, isCollect, fromFlag, id, tfcode, searchFlag, 
				mTypeId, resPattern, resType, curPage, perPage);
		
		System.out.println(JSONObject.toJSONString(result));
		
	}

}
