package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.service.DisResService;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;

/**
 * 测试区本校本资源列表的service
 * @author WeiCuicui
 *
 */
public class DistrictResListServiceTest extends BaseServiceTestCase{

	@Resource ResTypeService resTypeService;
	@Resource DisResService disResService;
	@Resource SysResourceService sysResourceService;
	
	@Test
	public void disResourceServiceTest()throws IOException{
		Pagination<DisResourceEntity> pagination = null;
		long userId = 699230735;
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
		
		//资源来源 校本资源
		int fromFlag = 4;
		
		pagination = disResService.selectAllDisRes(userId, mTypeId, fileFormat, tfcode, orderBy, page, perPage, fromFlag);
		if(pagination != null){
			System.out.println(pagination.getPage());
			System.out.println(pagination.getPerPage());
			System.out.println(pagination.getTotal());
			System.out.println(pagination.getTotalLines());
			List<DisResourceEntity> list = pagination.getList();
			for (int i = 0; i < list.size() && i < 10; i++) {
				log.info(list.get(i).toString());
			}
		}
	
	}
}