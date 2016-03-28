package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 资源类型 的 service测试
 * @author WeiCuicui
 *
 */
public class SysResTypeServiceTest extends BaseServiceTestCase{

	@Resource ResTypeService resTypeService;
	
	@Test
	public void testResTypeService()throws IOException{
		
		List<ResType> types = new ArrayList<ResType>();
		
		//资源类型中增加一个“全部”
		ResType all = new ResType();
		all.setId(0);
		all.setMtype("全部");
		types.add(all);
		
		long poolId = 4;
		String pTfcode = "RJCZ01010501";
		//查询当前结点下的所有资源Id
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pTfcode", pTfcode);
		map.put("sys_from", SysFrom.sys_from);
		List<Long> resourceIds = resTypeService.getAllSysResIds(map);
		
	    Assert.isTrue(resourceIds.size() > 0);
	    
	   
		//根据资源库，查询所有资源类型id
		
		List<Integer> typeIds = resTypeService.getAllTypeIdsByPool(poolId);
		
		Assert.isTrue(typeIds.size() > 0);
		
		System.out.println(typeIds.size());
		 
		for(int i = 0; i < typeIds.size(); i++){
			if(i % 10 == 0)
	    		System.out.println();
	    	System.out.print(typeIds.get(i) + ",");
		}
		
		 
		/**
		 * 当资源库选择  “全部” 或  “教学素材” 时
		 * 显示所有一级类型
		 */
		if(poolId == 0 || poolId == 4){
			
			types = resTypeService.getSysFirstLevelType(resourceIds, typeIds);
			
		} else {  //当资源库选择  “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型；当资源库为“理化生实验”时，只显示“全部”。
			types = resTypeService.getSysSecondLevelType(resourceIds, typeIds);
		}
		
		
		
		Assert.isTrue(types.size() > 0);
		
		for(int i = 0; i < types.size(); i++){
			if(i % 10 == 0)
	    		System.out.println();
	    	System.out.print(types.get(i) + ",");
		}
	}
}
