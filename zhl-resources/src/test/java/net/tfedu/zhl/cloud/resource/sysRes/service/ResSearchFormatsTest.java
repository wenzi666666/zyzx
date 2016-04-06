package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resSearch.service.ResSearchService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 资源检索结果的格式 单元测试
 * @author WeiCuicui
 *
 */
public class ResSearchFormatsTest extends BaseServiceTestCase{

	@Resource ResSearchService resSearchService;
	
	@Test
	public void testResSearchFormat()throws IOException{
		String searchKeyword = "荷塘";
		int fromFlag = 1; //系统资源
		long userId = 699230735;
		
		List<String> list = resSearchService.getFileFormats(searchKeyword, fromFlag, SysFrom.sys_from,userId);
		
		Assert.isTrue(list.size() > 0);
		
		for(int i = 0; i < list.size(); i++){
			System.out.print(list.get(i) + ", ");
		}
	}
}
