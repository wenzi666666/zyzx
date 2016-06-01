package net.tfedu.zhl.cloud.resource.questionbank.service;

import net.tfedu.zhl.helper.ResultJSON;

public interface QuestionBankService {

	/**
	 * 根据用户名，查询用户信息
	 * @param userName
	 * @return
	 */
	public ResultJSON queryUserBasicInfo(String userName);
	
	/**
	 * 查询所有省份
	 * @return
	 */
	public ResultJSON queryProvince();
	
	/**
	 * 查询省份下的所有市
	 * @param provinceId
	 * @return
	 */
	public ResultJSON queryCityByProvinceId(long provinceId);
	
	/**
	 * 根据所在市，查询所有区
	 * @return
	 */
	public ResultJSON queryDistirctByCityId(int cityId);
	
	/**
	 * 根据所在区，查询所有学校
	 * @param districtIds
	 * @return
	 */
	public ResultJSON querySchoolByDistrictId(int districtId);
	
	/**
	 * 查询所有学段信息
	 * @return
	 */
	public ResultJSON queryTerm();
	
	/**
	 * 查询学段下的学科列表
	 * @param termId
	 * @return
	 */
	public ResultJSON querySubjectByTermId(long termId);
}
