package net.tfedu.zhl.cloud.resource.navigation.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;
import net.tfedu.zhl.cloud.resource.navigation.service.TreeService;
import net.tfedu.zhl.cloud.resource.navigation.dao.JSyscourseMapper;


/**
 * 目录树 serviceImpl
 * @author WeiCuicui
 *
 */
@Service("sysCourseTreeService")
public class TreeServiceImpl implements TreeService{
	
	@Resource JSyscourseMapper jSyscourseMapper;
	/**
	 * 查询某父节点下的直接子节点(即不包含孙子节点等)
	 * @param pTfcode 父结点的tfcode
	 * @return
	 */
	@Override
	public List<TreeNode> getTopChildren(long pnodeId){
		return jSyscourseMapper.getTopChildrenResultMap(pnodeId);
	}
	
	/**
	 * 根据提供的节点集合递归查询集合里每个节点的子节点，最终统一返回所有的子节点
	 * @param topChildren 是当前节点的直接子节点集合
	 * @param resultTrees 是最终返回的所有子节点集合
	 * @return
	 */
	@Override
	public List<TreeNode> getAllChildren(List<TreeNode> topChildren,List<TreeNode> resultTrees){
		for (int i = 0; i < topChildren.size(); i++) {
			TreeNode node = topChildren.get(i);
			
			//加入到结果集中
			resultTrees.add(node);
			
			//设置当前结点属于第几个结点
			node.setI(i + 1);
			
			//如果当前节点是叶子节点，跳过不进行递归查询子节点
            if(node.isLeaf()){
                continue;
            }
            
            //查询children
            List<TreeNode> children = getTopChildren(node.getId());
            if(children == null || children.size() == 0){
            	node.setLeaf(true);
            } else {
                //设置children
                node.setChildren(children);        
			}
            	
            //递归调用
            getAllChildren(children,resultTrees);
		}
		
        return resultTrees;
	}
}
