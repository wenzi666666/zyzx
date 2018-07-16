package net.tfedu.zhl.cloud.resource.navigation.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import net.tfedu.zhl.cloud.resource.integration.entity.CourseNode;
import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;
import net.tfedu.zhl.core.exception.CustomException;

/**
 * 目录树 service
 * 
 * @author WeiCuicui
 *
 */
public interface TreeService {

	/**
	 * 根据提供的节点集合递归查询集合里每个节点的子节点，最终统一返回所有的子节点
	 * 
	 * @param topChildren
	 *            是当前节点的直接子节点集合
	 * @param resultTrees
	 *            是最终返回的所有子节点集合
	 * @return
	 */
	public List<TreeNode> getAllChildren(TreeNode parentNode, List<TreeNode> topChildren, List<TreeNode> resultTrees);

	/**
	 * 加载父结点及其所有的子结点
	 * 
	 * @return
	 */
	public List<TreeNode> getTreeNodes(long pnodeId, String proCode);

	/**
	 * 加载父结点及其所有的子结点（指定资源6大库下有资源的）
	 * 
	 * @return
	 */
	public List<TreeNode> getTreeNodesLimitedByPoolRes(int poolId, long pnodeId, String proCode);

	/**
	 * 根据code获取指定CourseNode对象
	 * 
	 * @param tfcode
	 * @return
	 * @throws CustomException
	 */
	public CourseNode getTreeNodeByCode(String tfcode) throws CustomException;

	/**
	 * 根据父节点查询子节点（系统目录）
	 * 
	 * @param pNodeId 
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	public List<CourseNode> searchSysChildren(Long pNodeId);
	
	
	/**
	 * 根据父节点查询子节点（系统目录）
	 * 返回字节点的code的集合
	 * @param  tfcode 
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	public List<String> querySysChildren(String tfcode);
	
	
	/**
	 * 根据id获取tfcode
	 * @param id
	 * @return
	 */
	public String getTfCodeById(Long id);
	

}
