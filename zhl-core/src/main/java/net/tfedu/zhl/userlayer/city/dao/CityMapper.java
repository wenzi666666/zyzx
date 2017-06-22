package net.tfedu.zhl.userlayer.city.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.userlayer.city.entity.City;

public interface CityMapper extends CoreMapper<City> {
 
	/**
	 * 查询省份下的市
	 * @param provinceId
	 * @return
	 */
	public List<City> queryCityByProvinceId(@Param("provinceId")long provinceId);
	
	/**
	 * 查询省份下的市
	 * @param provinceId
	 * @param name
	 * @return
	 */
	public List<City> queryCityByProvinceIdANDName(@Param("provinceId")long provinceId,@Param("name")String name);
	
	
}