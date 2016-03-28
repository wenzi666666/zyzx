package net.tfedu.zhl.cloud.resource.navigation.service;

import java.util.List;

import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;

/**
 * 目录树 service
 * @author WeiCuicui
 *
 */
public interface TreeService {

	/**
	 * 查询某父节点下的直接子节点(即不包含孙子节点等)
	 * @param pid
	 * @return
	 */
	public List<TreeNode> getTopChildren(long pnodeId);
	
	/**
	 * 根据提供的节点集合递归查询集合里每个节点的子节点，最终统一返回所有的子节点
	 * @param topChildren 是当前节点的直接子节点集合
	 * @param resultTrees 是最终返回的所有子节点集合
	 * @return
	 */
	public List<TreeNode> getAllChildren(List<TreeNode> topChildren,List<TreeNode> resultTrees);
}
