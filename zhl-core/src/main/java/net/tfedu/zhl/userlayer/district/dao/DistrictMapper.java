package net.tfedu.zhl.userlayer.district.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.userlayer.district.entity.District;

public interface DistrictMapper extends CoreMapper<District> {
	
	/**
	 * 查询市下的区
	 * @param cityId
	 * @return
	 */
	public List<District> queryDistirctByCityId(@Param("cityId")long cityId);
	
	/**
	 * 查询市下的区
	 * @param cityId
	 * @param name
	 * @return
	 */
	public List<District> queryDistirctByCityIdANDName(@Param("cityId")long cityId,@Param("name")String name);
}