package net.tfedu.zhl.cloud.resources.poolTypeFormats.service;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.ResPoolType;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.ResType;


/**
 * 资源类型 service
 * @author WeiCuicui
 *
 */
public interface ResTypeService {

	//获取当前节点下所有资源id，pTfcode为父结点的tfcode
	public List<Long> getAllResourceIdsByPtfcode(HashMap<String, Object> map);
	
	//根据库id、资源ids查询所有资源类型id
	public List<ResPoolType> getAllTypeIdsByPool(long poolId);
	
	//当资源库选择  “全部” 或  “教学素材” 时，显示所有一级类型  
	public List<ResType> getFirstLevelType(List<Long> resourceIds,List<Integer> typeIds);
	
	
	//当资源库选择  “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型。当资源库为“理化生实验”时，只显示“全部”
	public List<ResType> getSecondLevelType(List<Long> resourceIds, List<Integer> typeIds);
	
}
