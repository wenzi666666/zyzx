package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 测试资源预览时的所有资源目录
 * @author WeiCuicui
 *
 */
public class ResNavServiceTest extends BaseServiceTestCase {

    @Resource ResPreviewService resPreviewService;
	
	@Test
	public void testResPreview()throws IOException{
		
		//0 系统资源；1 自建资源；2 共享资源；3 校本资源；4 区本资源
		int fromFlag = 0;
		
		//资源id
		long resId = 1;
		
		//当前所在结点
		String curTfcode = "RJXX0101";
		
		List<List<ResNavEntity>> result = new ArrayList<List<ResNavEntity>>();
		
		result = resPreviewService.getAllResNavs(resId, fromFlag, curTfcode);
		
		Assert.isTrue(result.size() > 0);
		
		for(int i = 0; i < result.size(); i++)
			for (int j = 0; j < result.get(i).size(); j++) {
				log.info(result.get(i).get(j).toString());
			}
	}
}
