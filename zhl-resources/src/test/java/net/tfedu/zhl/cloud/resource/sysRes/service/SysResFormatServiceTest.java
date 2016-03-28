package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 系统资源格式测试
 * @author WeiCuicui
 *
 */
public class SysResFormatServiceTest extends BaseServiceTestCase {

	@Resource ResFormatService resFormatService;
	@Resource ResTypeService resTypeService;
	
	@Test
	public void testSysFormatService() throws IOException{
		
		List<String> formats = new ArrayList<String>();
		
		long poolId = 6;
		String pTfcode = "RJCZ01010501";
		int typeId = 1;
		
		//查询结果中增加一个 “全部”
		formats.add("全部");
		
		//根据poolId和typeId，查询父类型下所有子类型及其自身
		List<Integer> typeIds = resFormatService.getTypesByPMTypeAndPool(poolId, typeId);
		
		Assert.isTrue(typeIds.size() > 0);
		
		System.out.println(typeIds.size());
		for(int i = 0; i < typeIds.size(); i++)
			System.out.print(typeIds.get(i) + ",");
		
		//查询当前结点下的所有资源Id
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pTfcode", pTfcode);
		map.put("sys_from", SysFrom.sys_from);
		List<Long> resourceIds = resTypeService.getAllSysResIds(map);
		
		Assert.isTrue(resourceIds.size() > 0);
		
		System.out.println(resourceIds.size());
		for(int i = 0; i < resourceIds.size(); i++){
			if(i % 10 == 0)
				System.out.println();
			System.out.print(resourceIds.get(i) + ",");
		}
		
		//根据 resourceIds和typeIds，查询资源格式
	    formats = resFormatService.getSysResFormatsByMType(resourceIds, typeIds);
	    
	    Assert.isTrue(formats.size() > 0);
	    System.out.println(formats.size());
		for(int i = 0; i < formats.size(); i++){
			if(i % 10 == 0)
				System.out.println();
			System.out.print(formats.get(i) + ",");
		}

	}
	
}
