package net.tfedu.zhl.sso.users.entity;

/**
 * 
 * 云平台用户信息（对接使用）
 * 
 * @author wangwr
 * @date 2017年8月3日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class CloudUserInfoForm {

	/**
	 * 用户id
	 */
	Long userId;
	/**
	 * 必选 登录名
	 */
	String userName;
	
	/**
	 * 密码
	 */
	String password ; 
	
	
	
	/**
	 * 必选 真实姓名
	 */
	String trueName;
	/**
	 * 必选 呢称
	 */
	String nickName;
	/**
	 * 必选 性别 true 女 false 男
	 */
	boolean sex;
	/**
	 * 必选 1 学生 2 老师
	 */
	long role;

	/**
	 * 学段
	 */
	String termCode;

	/**
	 * 学科
	 */
	String subjectCodes;

	/**
	 * 学校id
	 */
	Long schoolId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public String getSubjectCodes() {
		return subjectCodes;
	}

	public void setSubjectCodes(String subjectCodes) {
		this.subjectCodes = subjectCodes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
