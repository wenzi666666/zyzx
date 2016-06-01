package net.tfedu.zhl.sso.city.dao;

import java.util.List;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.city.entity.City;

import org.apache.ibatis.annotations.Param;

public interface CityMapper extends CoreMapper<City> {
 
	/**
	 * 查询省份下的市
	 * @param provinceId
	 * @return
	 */
	public List<City> queryCityByProvinceId(@Param("provinceId")long provinceId);
}