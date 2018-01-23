package net.tfedu.zhl.userlayer.user.entity;


/**
 * 
 * 用户查询条件表单
 * 
 * @author wangwr
 * @date 2017年1月13日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class UserQueryForm {

	/**
	 * 省
	 */
	Long provinceId;

	/**
	 * 市
	 */
	Long cityId;

	/**
	 * 区县
	 */
	Long districtId;

	/**
	 * 校
	 */
	Long schoolId;

	/**
	 * 学科
	 */
	Long subjectId;

	/**
	 * 查询关键字
	 */
	String keyword;

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
