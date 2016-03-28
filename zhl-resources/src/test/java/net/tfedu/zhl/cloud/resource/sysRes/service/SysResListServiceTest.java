package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 系统资源查询列表 service 测试
 * @author WeiCuicui
 *
 */
public class SysResListServiceTest extends BaseServiceTestCase{

	@Resource SysResourceService sysResourceService;
	@Resource ResTypeService resTypeService;
	
	@Test
	public void sysResourceServiceTest()throws IOException{
		//查询结果，封装为pagination
		Pagination<SysResourceEntity> pagination = null;
		//资源库id
		long poolId = 0;
		
		//类型Id
		int mTypeId = 1;
		
		//资源格式
		String fileFormat = "图片";
		
		//课程tfcode
		String tfcode = "RJGZ040101";
		
		//排序方式（综合排序；最多下载；最新发布）
		int orderBy = 0;
		
		//页码
		int page = 1;
		
		//每页的记录数
		int perPage = 10;
		
		//根据fileFormat去查询该格式下的所有 后缀
		List<String> fileExts = sysResourceService.getFileExtsByFormat(fileFormat);
		
		//根据当前结点tfcode，以及sys_from，查询系统资源id
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sys_from", SysFrom.sys_from);
		map.put("pTfcode", tfcode);
		List<Long> resourceIds = resTypeService.getAllSysResIds(map);
		
		Assert.isTrue(resourceIds.size() > 0);
		System.out.println(resourceIds.size());
		 
		for(int i = 0; i < resourceIds.size(); i++){
			if(i % 10 == 0)
	    		System.out.println();
	    	System.out.print(resourceIds.get(i) + ",");
		}
		
		//根据资源库id和父类型id，得到父类型的所有子类型及其自身
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("poolId", poolId);
		map1.put("MType", mTypeId);
		List<Integer> typeIds = resTypeService.getTypesByPMTypeAndPool(poolId, mTypeId);
		
		Assert.isTrue(typeIds.size() > 0);
		System.out.println(typeIds.size());
		 
		for(int i = 0; i < typeIds.size(); i++){
			if(i % 10 == 0)
	    		System.out.println();
	    	System.out.print(typeIds.get(i) + ",");
		}
		
		//查询出的系统资源信息
		pagination = sysResourceService.getSysResList(SysFrom.sys_from, fileExts, resourceIds, tfcode, orderBy, typeIds, page, perPage);
		if(pagination != null){
			System.out.println(pagination.getPage());
			System.out.println(pagination.getPerPage());
			System.out.println(pagination.getTotal());
			System.out.println(pagination.getTotalLines());
			List<SysResourceEntity> list = pagination.getList();
			for (int i = 0; i < list.size() && i < 10; i++) {
				log.info(list.get(i).toString());
			}
		}
		
	}
	
}
