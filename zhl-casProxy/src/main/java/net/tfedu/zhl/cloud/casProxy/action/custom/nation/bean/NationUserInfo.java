package net.tfedu.zhl.cloud.casProxy.action.custom.nation.bean;


/**
 
  
    @author wangwr
    @date 2017年9月18日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class NationUserInfo {
	
	String userId;
	String name;
	/**
	 * 性别：0-未知；1-男；2-女
	 */
	String gender;
	/**
	 * 默认身份信息（0:学生，1：老师，
2：家长）
	 */
	String dafaultIdentity;
	/**
	 * 用户与机构关系列表
	 */
	String orgRelList;
	/**
	 * 机构码，全国唯一的机构编码
	 */
	String orgCode;
	/**
	 * 机构或者学校名称
	 */
	String orgName;
	
	/**
	 * 默认身份信息（0:学生，1：老师，
2：家长）
	 */
	String orgIdentity;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDafaultIdentity() {
		return dafaultIdentity;
	}

	public void setDafaultIdentity(String dafaultIdentity) {
		this.dafaultIdentity = dafaultIdentity;
	}

	public String getOrgRelList() {
		return orgRelList;
	}

	public void setOrgRelList(String orgRelList) {
		this.orgRelList = orgRelList;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgIdentity() {
		return orgIdentity;
	}

	public void setOrgIdentity(String orgIdentity) {
		this.orgIdentity = orgIdentity;
	}

	@Override
	public String toString() {
		return "NationUserInfo [userId=" + userId + ", name=" + name + ", gender=" + gender + ", dafaultIdentity="
				+ dafaultIdentity + ", orgRelList=" + orgRelList + ", orgCode=" + orgCode + ", orgName=" + orgName
				+ ", orgIdentity=" + orgIdentity + "]";
	}
	
	
	
	

}
