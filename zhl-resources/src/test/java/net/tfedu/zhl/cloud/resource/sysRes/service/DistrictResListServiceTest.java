package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisAndSchoolEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.service.DisResService;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

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
		int page = 3;
		
		//每页的记录数
		int perPage = 10;
		
		//资源来源 校本资源
		int fromFlag = 3;
		
		//根据fileFormat去查询该格式下的所有 后缀
		List<String> fileExts = sysResourceService.getFileExtsByFormat(fileFormat);
		
		Assert.isTrue(fileExts.size() > 0);
		System.out.println(fileExts.size());
		 
		for(int i = 0; i < fileExts.size(); i++){
			if(i % 10 == 0)
	    		System.out.println();
	    	System.out.print(fileExts.get(i) + ",");
		}
		
		//查询资源类型的子类型
		List<Integer> typeIds = resTypeService.getDisResTypesByPMType(mTypeId);
	
		Assert.isTrue(typeIds.size() > 0);
		System.out.println(typeIds.size());
		 
		for(int i = 0; i < typeIds.size(); i++){
			if(i % 10 == 0)
	    		System.out.println();
	    	System.out.print(typeIds.get(i) + ",");
		}
		
		long schoolId = 0;
		long districtId = 0;
		
		//根据userId查询schoolId 和 districtId
		DisAndSchoolEntity disAndSchoolIds = disResService.getDisAndSchool(userId);
		if(disAndSchoolIds != null){
			schoolId = disAndSchoolIds.getSchoolId();
			districtId = disAndSchoolIds.getDistrictId();
		}
		
		System.out.println("schoolId" + schoolId + ": districtId" + districtId);
		
		pagination = disResService.selectDisRes(fromFlag, fileExts, typeIds, tfcode, orderBy,schoolId,districtId,page,perPage);
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
