package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

public class DistrictResFormatServiceTest extends BaseServiceTestCase {

	@Resource ResTypeService resTypeService;
	@Resource ResFormatService resFormatService;
	@Test
	public void testDisResTypeService()throws IOException{
		//格式
		List<String> formats = new ArrayList<String>();
		
		//查询结果中增加一个 “全部”
		formats.add("全部");
		
		//资源类型id
		String tfcode = "RJGZ040101";
		int fromFlag = 3;
		
		//获得资源id
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pTfcode", tfcode);
		List<Long> resourceIds = resTypeService.getAllDisResIds(map);
		
		Assert.isTrue(resourceIds.size() > 0);
		System.out.println(resourceIds.size());
		for(int i = 0; i < resourceIds.size(); i++)
			System.out.print(resourceIds.get(i) + ",");
		
		formats = resFormatService.getDisResFormatsByMType(resourceIds, fromFlag);
		
		Assert.isTrue(formats.size() > 0);
		System.out.println(formats.size());
		for(int i = 0; i < formats.size(); i++)
			System.out.print(formats.get(i) + ",");
	}
}
