package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 区本校本资源类型测试
 * @author WeiCuicui
 *
 */
public class DistrictResTypeServiceTest extends BaseServiceTestCase{

	@Resource ResTypeService resTypeService;
	@Test
	public void testDisResTypeService()throws IOException{
		//定义类型结果集
	    List<ResType> types = new ArrayList<ResType>();
	    
		//资源类型中增加一个“全部”
		ResType all = new ResType();
		all.setId(0);
		all.setMtype("全部");
		types.add(all);
		
	    
		//传递参数
		String tfcode = "RJGZ040101";
		//3校本 4区本
		int fromFlag = 3;
		
		//根据tfcode获得区本校本资源ids
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pTfcode", tfcode);
		List<Long> resourceIds = resTypeService.getAllDisResIds(map);
		
		Assert.isTrue(resourceIds.size() > 0);
		System.out.println(resourceIds.size());
		for(int i = 0; i < resourceIds.size(); i++)
			System.out.print(resourceIds.get(i) + ",");
		
		//查询类型id
		types = resTypeService.getDisResType(resourceIds, fromFlag);
		
		Assert.isTrue(types.size() > 0);
		System.out.println(types.size());
		for(int i = 0; i < types.size(); i++)
			System.out.print(types.get(i) + ",");
	}
	
}
