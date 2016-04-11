package net.tfedu.zhl.cloud.resource.sysRes.resPreview;

import java.io.IOException;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.resPreview.controller.ResPreviewController;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;

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
	public void test() throws IOException {

		request.setParameter("resId", "164882428");
		request.setParameter("fromFlag", "1");
		
		ResultJSON json =  controller.getResPreviewInfo(request, response);
		System.out.println(json.toString());
		
	}
}
