package net.tfedu.zhl.cloud.resource.sysRes.resPreview;

import java.io.IOException;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.resPreview.controller.ResPreviewController;
import net.tfedu.zhl.cloud.utils.datatype.JsonUtil;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 资源预览页面的controller单元测试
 * @author WeiCuicui
 *
 */
public class ResPreviewControllerTest extends BaseControllerTestCase{

	@Resource ResPreviewController  controller;

	/**
	 * 查询一条资源的详细信息controller单元测试
	 * @throws IOException
	 */
	@Test
	public void testGetOneResController() throws IOException {

		request.setParameter("resId", "164882428");
		request.setParameter("fromFlag", "1");
		
		ResultJSON json =  controller.getResPreviewInfo(request, response);
		
        JsonUtil.toJsonString(json);
        
        Assert.isTrue("OK".equals(json.getCode()));
		
	}
	
	/**
	 * 资源预览页面的资源推荐列表
	 * @throws IOException
	 *//*
	@Test
	public void testGetAllRes()throws IOException{
		
		request.setParameter("resId", "164882420");
		request.setParameter("userId", "699230735");
		request.setParameter("fromFlag", "1");
		
		ResultJSON json =  controller.getResRecommendation(request, response);
		
		JsonUtil.toJsonString(json);
        
        Assert.isTrue("OK".equals(json.getCode()));
	}*/
}
