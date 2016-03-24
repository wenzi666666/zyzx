package net.tfedu.zhl.cloud.resources.poolTypeFormats.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.ResPoolType;

//资源库、资源类型的关联表的mapper
public interface ResPoolTypeMapper extends CoreMapper<ResPoolType> {
	
	//根据库id、资源ids查询所有资源类型id
	public List<ResPoolType> getAllTypeIdsByPool(@Param("poolId")long poolId);
}