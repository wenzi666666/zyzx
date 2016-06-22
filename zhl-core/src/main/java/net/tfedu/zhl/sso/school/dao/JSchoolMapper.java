package net.tfedu.zhl.sso.school.dao;

import java.util.List;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.school.entity.JSchool;

import org.apache.ibatis.annotations.Param;

public interface JSchoolMapper extends CoreMapper<JSchool> {
	/**
	 * 查询区下的所有学校
	 * @param districtId
	 * @return
	 */
	public List<JSchool> querySchoolByDistrictId(@Param("districtId")long districtId);
}