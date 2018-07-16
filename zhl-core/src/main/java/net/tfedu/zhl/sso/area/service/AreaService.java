package net.tfedu.zhl.sso.area.service;

import net.tfedu.zhl.helper.ResultJSON;

public interface AreaService {
	
	
	/**
	 * 查询所有省份
	 * 
	 * @return
	 */
	public ResultJSON queryProvince();

	/**
	 * 查询省份下的所有市
	 * 
	 * @param provinceId
	 * @return
	 */
	public ResultJSON queryCityByProvinceId(long provinceId);

	/**
	 * 根据所在市，查询所有区
	 * 
	 * @return
	 */
	public ResultJSON queryDistirctByCityId(long cityId);

	/**
	 * 根据所在区，查询所有学校
	 * 
	 * @param districtIds
	 * @return
	 */
	public ResultJSON querySchoolByDistrictId(long districtId);
	
	/**
	 * 根据学校，查询所有班级
	 * 
	 * @param schoolId
	 * @return
	 */
	public ResultJSON queryClassBySchoolId(long schoolId);
	
	
	/**
	 * 同步从云平台传递过来的学校的地区信息 
	 * 
	 * 不存在新建，存在但名称不对时更新
	 * 
	 * @param provinceId    省id
	 * @param provinceName  省name
	 * @param cityId        市id
	 * @param cityName      市name
	 * @param districtId    地区id
	 * @param districtName  地区name
	 * @param schoolId      学校id
	 * @param schoolName    学校name
	 * @return
	 */
	public Long syncCloudSchoolInfo(String provinceId,String provinceName
			,String cityId,String cityName,String districtId,String districtName
			,String schoolId,String schoolName);
	
	
	

}
