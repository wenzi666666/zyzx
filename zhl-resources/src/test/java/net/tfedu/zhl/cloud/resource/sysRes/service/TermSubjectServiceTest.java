package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.util.Assert;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

/**
 * 测试 学段下的学科 service
 * @author WeiCuicui
 *
 */
public class TermSubjectServiceTest extends BaseServiceTestCase{

	
	@Resource ResTypeService resTypeService;
	@Test
	public void testSysIds() throws IOException{
		int fromFlag = 0;
		String pTfcode = "RJXX020103";
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("fromFlag", fromFlag);
		map.put("pTfcode", pTfcode);
		
		List<Long> resourceIds = resTypeService.getAllResourceIdsByPtfcode(map);
		Assert.isTrue(resourceIds.size() > 1);
		for (int i = 0; i < resourceIds.size(); i++) {
			System.out.print(resourceIds.get(i) + ",");
		}
	}
	
}
