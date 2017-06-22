package net.tfedu.zhl.userlayer.school.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.userlayer.school.entity.JSchool;

public interface JSchoolMapper extends CoreMapper<JSchool> {
	/**
	 * 查询区下的所有学校
	 * @param districtId
	 * @return
	 */
	public List<JSchool> querySchoolByDistrictId(@Param("districtId")long districtId);
	
	/**
	 * 查询区下的所有学校
	 * @param districtId
	 * @param name
	 * @return
	 */
	public List<JSchool> querySchoolByDistrictIdAndName(@Param("districtId")long districtId,@Param("name")String name);
	
	
	
}