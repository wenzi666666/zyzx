package net.tfedu.zhl.cloud.resources.poolTypeFormats.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resources.poolTypeFormats.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.FileFormat;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.ResPoolType;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.service.ResFormatService;

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
	public List<ResPoolType> getTypesByPMTypeAndPool(long poolId, long MType){
		return resTypeMapper.getTypesByPMTypeAndPool(poolId, MType);
	}
	
	//根据资源ids和typeIds，查询得到资源格式
	public List<FileFormat> getFormatsByMType(List<Long> resourceIds, List<Integer> typeIds){
		return fileFormatMapper.getFormatsByMType(resourceIds, typeIds);
	}
}
