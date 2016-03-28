package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.util.Assert;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPoolType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

/**
 * 测试 学段下的学科 service
 * @author WeiCuicui
 *
 */
public class TermSubjectServiceTest extends BaseServiceTestCase{

	@Resource SysResourceService sysResourceService;
	@Resource ResTypeService resTypeService;

	@Test
	public void testSysIds() throws IOException{
/*		//资源库id
		long poolId = 0;
		
		//类型Id
		int mTypeId = 3;
		
		//资源格式
		String fileFormat = "全部";
		
		//课程tfcode
		String tfcode = "";
		
		//排序方式（综合排序；最多下载；最新发布）
		int orderBy = 1;
		
		//页码
		int page = 1;
		
		//每页的记录数
		int perPage = 4;

		
		//根据fileFormat去查询该格式下的所有 后缀
		List<String> fileExts = sysResourceService.getFileExtsByFormat(fileFormat);
		
		//根据当前结点tfcode，以及sys_from，查询系统资源id
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sys_from", SysFrom.sys_from);
		map.put("pTfcode", tfcode);
		List<Long> resourceIds = resTypeService.getAllSysResIds(map);
		
		//根据资源库id和父类型id，得到父类型的所有子类型及其自身
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("poolId", poolId);
		map1.put("MType", mTypeId);
		List<ResPoolType> typeIdsByPool = resTypeService.getTypesByPMTypeAndPool(poolId, mTypeId);
		
		List<Long> typeIds = new ArrayList<Long>();
		for (int i = 0; i < typeIdsByPool.size(); i++) {
			typeIds.add(typeIdsByPool.get(i).getRestypeid());
		}
		
		//查询出的系统资源信息
		pageInfo = sysResourceService.getSysResList(SysFrom.sys_from, fileExts, resourceIds, tfcode, orderBy, typeIds, page, perPage);
		int fromFlag = 0;
		String pTfcode = "RJXX020103";
		HashMap<String, Object> map = new HashMap<String, Object>();*/
		int fromFlag = 0;
		String pTfcode = "RJXX020103";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fromFlag", fromFlag);
		map.put("pTfcode", pTfcode);
		
		List<Long> resourceIds = resTypeService.getAllSysResIds(map);
		Assert.isTrue(resourceIds.size() > 1);
		for (int i = 0; i < resourceIds.size(); i++) {
			System.out.print(resourceIds.get(i) + ",");
		}
	}
	
}
