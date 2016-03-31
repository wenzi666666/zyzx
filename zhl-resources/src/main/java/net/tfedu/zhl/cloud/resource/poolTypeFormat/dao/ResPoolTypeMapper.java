package net.tfedu.zhl.cloud.resource.poolTypeFormat.dao;

import java.util.List;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPoolType;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

//资源库、资源类型的关联表的mapper
public interface ResPoolTypeMapper extends CoreMapper<ResPoolType> {
	
	//根据库id查询所有资源类型id
	public List<Integer> getAllTypeIdsByPool(@Param("poolId") long poolId);
}