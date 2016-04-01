package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.cloud.resource.resSearch.service.ResSearchService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;

/**
 * 测试资源检索结果
 * @author WeiCuicui
 *
 */
public class ResSearchServiceTest extends BaseServiceTestCase{

	@Resource ResSearchService resSearchService;
	@Test
	public void sysResourceServiceTest()throws IOException{
		
		//查询结果，封装为pagination
		Pagination<ResSearchResultEntity> pagination = null;
		

		//检索范围   0 全部资源   1 系统资源  3 校本资源  4 区本资源
		int fromFlag = 1;
		
		String format = "全部";
		
		String searchKeyword = "荷塘";
		
		//页码
		int page = 1;
		
		//每页记录数目
		int perPage = 10;
		
		pagination = resSearchService.getResources(fromFlag, SysFrom.sys_from,searchKeyword, format, page, perPage);

		
		if(pagination != null){
			System.out.println(pagination.getPage());
			System.out.println(pagination.getPerPage());
			System.out.println(pagination.getTotal());
			System.out.println(pagination.getTotalLines());
			List<ResSearchResultEntity> list = pagination.getList();
			for (int i = 0; i < list.size() && i < 10; i++) {
				log.info(list.get(i).toString());
			}
		}
		
	}
}
