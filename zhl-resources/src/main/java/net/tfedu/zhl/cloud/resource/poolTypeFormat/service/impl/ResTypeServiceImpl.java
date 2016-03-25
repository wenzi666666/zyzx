package net.tfedu.zhl.cloud.resource.poolTypeFormat.service.impl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResPoolTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPoolType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;


import org.springframework.stereotype.Service;

/**
 * 资源类型的serviceImpl
 * @author WeiCuicui
 *
 */
@Service("resTypeService")
public class ResTypeServiceImpl implements ResTypeService{
    
	//资源类型的mapper
	@Resource ResTypeMapper resTypeMapper;
	
	//资源库、资源类型的关联表的mapper
	@Resource ResPoolTypeMapper resPoolTypeMapper;
	
	//获取当前节点下所有资源id，pTfcode为父结点的tfcode
	@Override
	public List<Long> getAllResourceIdsByPtfcode(HashMap<String, Object> map){
		return resTypeMapper.getAllResourceIdsByPtfcode(map);
	}
	
	//根据库id、资源ids查询所有资源类型id
	@Override
	public List<ResPoolType> getAllTypeIdsByPool(long poolId){
		return resPoolTypeMapper.getAllTypeIdsByPool(poolId);
	}
	
	@Override
	//当资源库选择  “全部” 或  “教学素材” 时，显示所有一级类型  
	public List<ResType> getFirstLevelType(List<Long> resourceIds,List<Integer> typeIds){
		return resTypeMapper.getFirstLevelType(resourceIds, typeIds);
	}
	
	//当资源库选择  “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型。当资源库为“理化生实验”时，只显示“全部”
	@Override
	public List<ResType> getSecondLevelType(List<Long> resourceIds, List<Integer> typeIds){
		return resTypeMapper.getSecondLevelType(resourceIds, typeIds);
	}

}
