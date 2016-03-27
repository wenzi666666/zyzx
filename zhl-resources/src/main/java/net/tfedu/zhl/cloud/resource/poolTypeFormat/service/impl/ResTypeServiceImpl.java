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
	
	/**
	 * 系统资源
	 * 获取当前节点下所有资源id，pTfcode为父结点的tfcode
	 * @param map
	 * @return
	 */
	@Override
	public List<Long> getAllSysResIds(HashMap<String, Object> map){
		return resTypeMapper.getAllSysResIds(map);
	}
	
	/**
	 * 区本校本资源
	 * 获取当前节点下所有资源id，pTfcode为父结点的tfcode
	 * @param map
	 * @return
	 */
	@Override
	public List<Long> getAllDisResIds(HashMap<String, Object> map){
		return resTypeMapper.getAllDisResIds(map);
	}
	
	/**
	 * 系统资源：根据资源库id，得到父类型的所有子类型及其自身
	 */
	@Override
	public List<ResPoolType> getAllTypeIdsByPool(long poolId){
		return resPoolTypeMapper.getAllTypeIdsByPool(poolId);
	}
	
	@Override
	/**
	 * 系统资源：当资源库选择  “全部” 或  “教学素材” 时，显示所有一级类型    
	 */
	public List<ResType> getSysFirstLevelType(List<Long> resourceIds,List<Integer> typeIds){
		return resTypeMapper.getSysFirstLevelType(resourceIds, typeIds);
	}
	
	/**
	 * 系统资源：当资源库选择  “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型。当资源库为“理化生实验”时，只显示“全部”
	 */
	@Override
	public List<ResType> getSysSecondLevelType(List<Long> resourceIds, List<Integer> typeIds){
		return resTypeMapper.getSysSecondLevelType(resourceIds, typeIds);
	}
	
	/**
	 * 区本校本资源：根据资源ids和fromFlag（区本/校本），查询资源类型
	 * @param resourceIds
	 * @param fromFlag
	 * @return
	 */
	@Override
	public List<ResType> getDisResType(List<Long> resourceIds,int fromFlag){
		return resTypeMapper.getDisResType(resourceIds, fromFlag);
	}
	
	
	/**
	 * 根据资源库id，得到父类型的所有子类型及其自身
	 * @param map
	 * @return
	 */
	@Override
	public List<ResPoolType> getTypesByPMTypeAndPool(long poolId,int MType){
		return resTypeMapper.getTypesByPMTypeAndPool(poolId,MType);
	}
	
	/**
	 * 区本校本资源：根据父类型，查询所有类型
	 * @param typeId
	 * @return
	 */
	@Override
	public List<Long> getAllDisTypeByPType(int typeId){
		return resTypeMapper.getAllDisTypeByPType(typeId);
	}
	
	/**
	 * 查询所有资源类型
	 * @return
	 */
	public List<Long> getAllDisType(){
		return resTypeMapper.getAllDisType();
	}

}
