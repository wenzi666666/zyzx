package net.tfedu.zhl.cloud.resource.poolTypeFormat.dao;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

/**
 * 查询资源类型 mapper
 * @author WeiCuicui
 *
 */
public interface ResTypeMapper extends CoreMapper<ResType> {
	
	
	/**
	 * 
	 * 系统资源ids
	 * @param map
	 * @return
	 */
	public List<Long> getAllSysResIds(HashMap<String, Object> map);
	
	/**
	 * 区本、校本资源ids
	 * @param map
	 * @return
	 */
	public List<Long> getAllDisResIds(HashMap<String, Object> map);
	
	/**
	 * 系统资源：当资源库选择  “全部” 或  “教学素材” 时，显示所有一级类型  
	 * @param resourceIds
	 * @param typeIds
	 * @return
	 */
	public List<ResType> getSysFirstLevelType(@Param("resourceIds") List<Long> resourceIds,@Param("typeIds") List<Integer> typeIds);
	
	/**
	 * 系统资源：当资源库选择  “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型。当资源库为“理化生实验”时，只显示“全部”
	 * @param resourceIds
	 * @param typeIds
	 * @return
	 */
	public List<ResType> getSysSecondLevelType(@Param("resourceIds") List<Long> resourceIds,@Param("typeIds") List<Integer> typeIds);
	
	/**
	 * 系统资源：根据资源库id，得到父类型的所有子类型及其自身
	 * @param poolId
	 * @param MType
	 * @return
	 */
	public List<Integer> getTypesByPMTypeAndPool(@Param("poolId") long poolId,@Param("MType") int MType);
	
	/**
	 * 区本校本资源：查询资源类型
	 * @return
	 */
	public List<ResType> getDisResType(@Param("resourceIds") List<Long> resourceIds,@Param("fromFlag") int fromFlag);
	
	
	/**
	 * 
	 * 区本校本资源：查询父类型及其所有子类型
	 */
	public List<Integer> getDisResTypesByPMType(@Param("MType") int MType);
	


	/**
	 *  自建资源 ： 查询全部一级资源类型
	 */
	public List<FirstLevelResType> getAllFirstLevelResType();
}