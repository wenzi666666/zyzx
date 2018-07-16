package net.tfedu.zhl.sso.user.entity;

/**
 * @author wangwr
 * @date 2016年9月22日
 * @desc 用户地理信息 copyRight@ 同方知好乐教育科技(北京)有限公司
 * 
 */
public class UserAreaInfo {
	
	/**
	 * 学校
	 */
	long schoolId;

	/**
	 * 学校
	 */
	String schoolName;

	/**
	 * 地区
	 */
	long districtId;

	/**
	 * 地区
	 */
	String districtName;

	/**
	 * 市
	 */
	long cityId;

	/**
	 * 市
	 */
	String cityName;

	/**
	 * 省
	 */
	long provinceId;

	/**
	 * 省
	 */
	String provinceName;

	public long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	
	
	
	
	
}
