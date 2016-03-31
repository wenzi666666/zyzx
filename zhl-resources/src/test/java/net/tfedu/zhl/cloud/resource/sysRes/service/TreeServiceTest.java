package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;
import net.tfedu.zhl.cloud.resource.navigation.service.TreeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

public class TreeServiceTest extends BaseServiceTestCase{

	@Resource TreeService treeService;
	
	@Test
	public void testTreeService()throws IOException{
		long pnodeId = 5200105;
		List<TreeNode> resultNodes = new ArrayList<TreeNode>();
		resultNodes = treeService.geTreeNodes(pnodeId);
		
		Assert.isTrue(resultNodes.size() > 0);
		for(int i = 0; i < resultNodes.size();i++)
			log.info(resultNodes.get(i).toString());
	}
}
