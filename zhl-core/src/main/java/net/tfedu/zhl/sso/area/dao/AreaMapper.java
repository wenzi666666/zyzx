package net.tfedu.zhl.sso.area.dao;

import java.util.Map;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.area.entity.Area;

import org.apache.ibatis.annotations.Param;

public interface AreaMapper extends CoreMapper<Area> {
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public Map<String, String> queryAreaParent(@Param("code") String code);
}