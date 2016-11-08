package net.tfedu.zhl.cloud.resource.navigation.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.navigation.dao.JSyscourseMapper;
import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;
import net.tfedu.zhl.cloud.resource.navigation.service.TreeService;
import net.tfedu.zhl.cloud.resource.resCount.dao.ZResCountMapper;
import net.tfedu.zhl.cloud.resource.resCount.entity.ZResCount;

/**
 * 目录树 serviceImpl
 * 
 * @author WeiCuicui
 *
 */
@Service("sysCourseTreeService")
public class TreeServiceImpl implements TreeService {

    @Resource
    JSyscourseMapper jSyscourseMapper;
    
    @Resource
    ZResCountMapper zResCountMapper;
    
    /**
     * 根据提供的节点集合递归查询集合里每个节点的子节点，最终统一返回所有的子节点
     * @param parentNode
     *     父结点
     * @param topChildren
     *     是当前节点的直接子节点集合
     * @param resultTrees
     *     是最终返回的所有子节点集合
     * @return
     */
    @Override
    public List<TreeNode> getAllChildren(TreeNode parentNode,List<TreeNode> topChildren, List<TreeNode> resultTrees) {
        for (int i = 0; i < topChildren.size(); i++) {
            TreeNode node = topChildren.get(i);


            // 如果当前节点是叶子节点，跳过不进行递归查询子节点
            if (node.isLeaf()) {
                continue;
            }
            
            // 设置当前结点属于第几个结点
            int sort = i + 1;
            node.setI(parentNode.getI() + "." + sort );

            // 查询children
            List<TreeNode> children = jSyscourseMapper.getTopChildrenResultMap(node.getId());
            if (children == null || children.size() == 0) {
                node.setLeaf(true);
            } else {
                // 设置children
                node.setChildren(children);
            }

            // 递归调用
            getAllChildren(node,children, resultTrees);
        }

        return resultTrees;
    }

    /**
     * 加载父结点及其所有的子结点
     * @param pnodeId
     *     父结点(教材id)
     * @param proCode
     *     产品码
     * @return
     */
    @Override
    public List<TreeNode> getTreeNodes(long pnodeId,String proCode) {

        // 查询父结点下的直接子结点
        List<TreeNode> topChildren = jSyscourseMapper.getOneCourseInfo(pnodeId,proCode);
        
        //父结点及其所有的子结点
        List<TreeNode> resultNodes = new ArrayList<TreeNode>();

        // 查询所有的子结点
        for (int i = 0; i < topChildren.size(); i++) {
            TreeNode node = topChildren.get(i);

            // 加入到结果集中
            resultNodes.add(node);

            // 如果当前节点是叶子节点，跳过不进行递归查询子节点
            if (node.isLeaf()) {
                continue;
            }
            
            // 设置当前结点属于第几个结点
            int sort = i + 1;
            node.setI(sort + "");

            // 查询children
            List<TreeNode> children = jSyscourseMapper.getTopChildrenResultMap(node.getId());
            if (children == null || children.size() == 0) {
                node.setLeaf(true);
            } else {
                // 设置children
                node.setChildren(children);
            }

            // 递归调用，node是父结点
            getAllChildren(node,children, resultNodes);
        }

        return resultNodes;
    }

    
    
    @Override
	public List<TreeNode> getTreeNodesLimitedByPoolRes(int poolId, long pnodeId, String proCode) {

		
    	 // 查询父结点下的直接子结点
        List<TreeNode> topChildren = jSyscourseMapper.getOneCourseInfo(pnodeId,proCode);
        
        //父结点及其所有的子结点
        List<TreeNode> resultNodes = new ArrayList<TreeNode>();

        // 查询所有的子结点
        for (int i = 0; i < topChildren.size(); i++) {
            TreeNode node = topChildren.get(i);

            // 加入到结果集中
            resultNodes.add(node);

            // 如果当前节点是叶子节点，跳过不进行递归查询子节点
            if (node.isLeaf()) {
                continue;
            }
            
            // 设置当前结点属于第几个结点
            int sort = i + 1;
            node.setI(sort + "");

            // 查询children
            List<TreeNode> children = jSyscourseMapper.getTopChildrenResultMap(node.getId());
            if (children == null || children.size() == 0) {
                node.setLeaf(true);
            } else {
                // 设置children
            	checkChlidrenResCount(children, poolId);
                node.setChildren(children);
            }

            // 递归调用，node是父结点
            getAllChildrenLimit(poolId,node,children, resultNodes);
        }

        return resultNodes;
    	
    	
    	
    	
	}
	
    
    
    
    
	protected List<TreeNode> getTreeNodesLimitedByPoolRes2(int poolId, long pnodeId, String proCode) {

		
		List<TreeNode> list = getTreeNodes(pnodeId, proCode);
		for (Iterator<TreeNode> iterator = list.iterator(); iterator.hasNext();) {
			TreeNode treeNode = (TreeNode) iterator.next();
			if(!treeNode.isLeaf()){
				checkChlidrenResCount(treeNode.getChildren(), poolId);
			}
		}
		
		return list;
	}
	
	
	
	/**
	 * 查询chirdren的指定库的数目，为0的时候移出
	 * @param chirdren
	 * @param poolId  指定库
	 */
	protected void  checkChlidrenResCount(List<TreeNode> chirdren,int poolId) {
		if(null!=chirdren){
			for (Iterator iterator = chirdren.iterator(); iterator.hasNext();) {
				TreeNode treeNode = (TreeNode) iterator.next();
				 // 查询children
	            List<TreeNode> _children = jSyscourseMapper.getTopChildrenResultMap(treeNode.getId());
	            if (_children == null || _children.size() == 0) {
	            	treeNode.setLeaf(true);
	            }
				//叶结点的处理
				if(treeNode.isLeaf()){
					String tfcode =  treeNode.getTfcode();
					boolean removeFlag = false ;//是否移出
					ZResCount  count = zResCountMapper.selectByPrimaryKey(tfcode);
					switch(poolId){
					
					case 0: //全部
						
						removeFlag = null!=count&&(count.getAlCount()+count.getDhCount()+count.getJfCount()+count.getScCount()
								+count.getSyCount()+count.getWkCount())==0 ;
						break ;
					
					case 1://动画 
						removeFlag = null!=count && count.getDhCount()==0;
						break ;
						
					case 2://微课 
						removeFlag = null!=count && count.getWkCount()==0 ;
						break ;
						
					case 3://案例
						removeFlag = null!=count && count.getAlCount()==0 ;
						break ;
						
					case 4://素材
						removeFlag = null!=count && count.getScCount()==0;
						break ;
						
					case 5://教辅
						removeFlag = null!=count && count.getJfCount()==0;
						break ;
						
					case 6://理化生实验室 
						removeFlag = null!=count && count.getSyCount()>0;
						break ;
					}
					
					//如果需要移出的话
					if(removeFlag){
						iterator.remove();
					}
					
				}else{//非叶结点 递归处理 
					checkChlidrenResCount(treeNode.getChildren(), poolId);
				}
			}
		}
	}
	
	
	   /**
     * 根据提供的节点集合递归查询集合里每个节点的子节点，最终统一返回所有的子节点
     * @param parentNode
     *     父结点
     * @param topChildren
     *     是当前节点的直接子节点集合
     * @param resultTrees
     *     是最终返回的所有子节点集合
     * @return
     */
	protected List<TreeNode> getAllChildrenLimit(int poolId,TreeNode parentNode,List<TreeNode> topChildren, List<TreeNode> resultTrees) {
        for (int i = 0; i < topChildren.size(); i++) {
            TreeNode node = topChildren.get(i);


            // 如果当前节点是叶子节点，跳过不进行递归查询子节点
            if (node.isLeaf()) {
                continue;
            }
            
            // 设置当前结点属于第几个结点
            int sort = i + 1;
            node.setI(parentNode.getI() + "." + sort );

            // 查询children
            List<TreeNode> children = jSyscourseMapper.getTopChildrenResultMap(node.getId());
            if (children == null || children.size() == 0) {
                node.setLeaf(true);
            } else {
            	checkChlidrenResCount(children, poolId);
                // 设置children
                node.setChildren(children);
                
                getAllChildrenLimit(poolId,node,children, resultTrees);
                
            }

            // 递归调用
        }

        return resultTrees;
    }
	
}