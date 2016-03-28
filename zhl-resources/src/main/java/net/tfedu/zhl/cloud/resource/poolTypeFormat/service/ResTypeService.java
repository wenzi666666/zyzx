package net.tfedu.zhl.cloud.resource.poolTypeFormat.service;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPoolType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;


/**
 * 资源类型 service
 * @author WeiCuicui
 *
 */
public interface ResTypeService {
	
	/**
	 * 系统资源
	 * 获取当前节点下所有资源id，pTfcode为父结点的tfcode
	 * @param map
	 * @return
	 */
	public List<Long> getAllSysResIds(HashMap<String, Object> map);
	
	/**
	 * 区本校本资源
	 * 获取当前节点下所有资源id，pTfcode为父结点的tfcode
	 * @param map
	 * @return
	 */
	public List<Long> getAllDisResIds(HashMap<String, Object> map);

	
	/**
	 * 系统资源：根据资源库id，得到父类型的所有子类型及其自身
	 * @param poolId
	 * @return
	 */
	public List<ResPoolType> getAllTypeIdsByPool(long poolId);
	
	
	/**
	 * 系统资源：当资源库选择  “全部” 或  “教学素材” 时，显示所有一级类型   
	 */
	public List<ResType> getSysFirstLevelType(List<Long> resourceIds,List<Integer> typeIds);
	
	
	/**
	 * 系统资源：当资源库选择  “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型。当资源库为“理化生实验”时，只显示“全部”
	 * @param resourceIds
	 * @param typeIds
	 * @return
	 */
	public List<ResType> getSysSecondLevelType(List<Long> resourceIds, List<Integer> typeIds);
	
	/**
	 * 区本校本资源：根据资源ids和fromFlag（区本/校本），查询资源类型
	 * @param resourceIds
	 * @param fromFlag
	 * @return
	 */
	public List<ResType> getDisResType(List<Long> resourceIds,int fromFlag);
	
	/**
	 * 根据资源库id，得到父类型的所有子类型及其自身
	 * @param map
	 * @return
	 */
	public List<ResPoolType> getTypesByPMTypeAndPool(long poolId,int MType);
	
	/**
	 * 区本校本资源：根据父类型，查询所有类型
	 * @param typeId
	 * @return
	 */
	public List<Long> getAllDisTypeByPType(int typeId);
	
	/**
	 * 查询所有资源类型
	 * @return
	 */
	public List<Long> getAllDisType();
	
}
