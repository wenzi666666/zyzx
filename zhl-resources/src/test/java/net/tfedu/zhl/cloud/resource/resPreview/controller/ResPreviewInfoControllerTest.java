package net.tfedu.zhl.cloud.resource.resPreview.controller;


import java.io.IOException;

import javax.annotation.Resource;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;

public class ResPreviewInfoControllerTest extends BaseControllerTestCase {
	
	@Resource
	ResPreviewInfoController  controller;

	@Test
	public void test() throws IOException {

		request.setParameter("resId", "164882428");
		request.setParameter("fromFlag", "1");
		
		ResultJSON json =  controller.getResPreviewInfo(request, response);
		System.out.println(json.toString());
		
	}

}
