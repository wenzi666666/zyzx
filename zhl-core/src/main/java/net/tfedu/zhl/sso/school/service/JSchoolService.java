package net.tfedu.zhl.sso.school.service;

import java.util.List;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.sso.school.entity.JSchool;

public interface JSchoolService extends BaseService<JSchool>{
	
	
	
	/**
	 * 根据地区id获取地区下的所有学校
	 * @param districtId
	 * @return
	 */
	public List<JSchool> getSchoolsByDistrictId(Long districtId);
	
	
	

}
