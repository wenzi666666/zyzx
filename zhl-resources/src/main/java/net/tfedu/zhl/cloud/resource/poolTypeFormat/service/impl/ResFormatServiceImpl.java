package net.tfedu.zhl.cloud.resource.poolTypeFormat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPoolType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;

import org.springframework.stereotype.Service;

/**
 * 资源格式 serviceImpl
 * @author WeiCuicui
 *
 */
@Service("resFormatService")
public class ResFormatServiceImpl implements ResFormatService{

	@Resource ResTypeMapper resTypeMapper;
	@Resource FileFormatMapper fileFormatMapper;
	
	//根据资源库id，得到父类型的所有子类型及其自身
	@Override
	public List<ResPoolType> getTypesByPMTypeAndPool(long poolId, int MType){
		return resTypeMapper.getTypesByPMTypeAndPool(poolId, MType);
	}
	
	//系统资源，根据资源ids和typeIds，查询得到资源格式
	@Override
	public List<String> getSysResFormatsByMType(List<Long> resourceIds, List<Integer> typeIds){
		return fileFormatMapper.getSysResFormatsByMType(resourceIds, typeIds);
	}
	
	//区本校本资源，查询资源格式
	@Override
	public List<String> getDisResFormatsByMType(List<Long> resourceIds, int fromFlag){
		return fileFormatMapper.getDisResFormatsByMType(resourceIds, fromFlag);
	}
}
