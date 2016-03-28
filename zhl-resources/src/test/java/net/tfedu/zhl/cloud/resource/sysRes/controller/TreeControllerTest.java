package net.tfedu.zhl.cloud.resource.sysRes.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.controller.TreeController;
import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 测试目录树
 * @author WeiCuicui
 *
 */
public class TreeControllerTest extends BaseControllerTestCase{

    @Resource TreeController treeController;
	
	@Test
	public void testTreeController() throws IOException{
		request = newGet("/resRestAPI/v1.0/contents");
		request.setParameter("pnodeId", "102150105");
		ResultJSON resultJSON = treeController.getTreeNodes(request, response);
		Assert.isTrue(resultJSON != null);
		List<TreeNode> nodes = (List<TreeNode>)resultJSON.getData();
		Assert.isTrue(nodes.size() > 0);
		for(int i = 0; i < nodes.size(); i++){
			log.info(nodes.get(i).toString());
		}
	}
}
