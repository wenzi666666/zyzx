package net.tfedu.zhl.cloud.resource.poolTypeFormat.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPoolType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;

/**
 * 查询资源类型 mapper
 * @author WeiCuicui
 *
 */
public interface ResTypeMapper extends CoreMapper<ResType> {
	
	//获取当前节点下所有资源id，pTfcode为父结点的tfcode
	public List<Long> getAllResourceIdsByPtfcode(HashMap<String, Object> map);
	
	//当资源库选择  “全部” 或  “教学素材” 时，显示所有一级类型  
	public List<ResType> getFirstLevelType(@Param("resourceIds") List<Long> resourceIds,@Param("typeIds") List<Integer> typeIds);
	
	//当资源库选择  “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型。当资源库为“理化生实验”时，只显示“全部”
	public List<ResType> getSecondLevelType(@Param("resourceIds") List<Long> resourceIds,@Param("typeIds") List<Integer> typeIds);
	
	//根据资源库id，得到父类型的所有子类型及其自身
	public List<ResPoolType> getTypesByPMTypeAndPool(@Param("poolId") long poolId,@Param("MType") long MType);

}