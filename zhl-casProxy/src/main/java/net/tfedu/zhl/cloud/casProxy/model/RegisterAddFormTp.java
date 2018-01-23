package net.tfedu.zhl.cloud.casProxy.model;

import java.io.Serializable;

/**
 * 用户基础信息 copyRight@知好乐教育技术北京有限公司
 * 
 * @author jiys
 * @date 2016-11-23
 * @version v1.0.0
 */
@SuppressWarnings("serial")
public class RegisterAddFormTp implements Serializable {

	/**
	 * 第三方用户在其系统中的唯一主键
	 */
	private String uuid;

	/**
	 * 必选 登录名
	 */
	private String username;
	/**
	 * 必选 真实姓名
	 */
	private String truename;
	/**
	 * 必选 呢称
	 */
	private String nickname;
	/**
	 * 必选 性别 true 女 false 男
	 */
	private String sex;
	/**
	 * 角色名称（学生、教师）
	 */
	private String rolename;

	/**
	 * 必选 格言
	 */
	private String motto;

	/**
	 * 必选 生日
	 */
	private String birthdate;

	/**
	 * 必选 区编码
	 */
	private String areacode;

	/**
	 * 必选 区名称
	 */
	private String areaname;
	/**
	 * 必选 校
	 */
	private String schoolname;

	/**
	 * 学段
	 */
	private String termname;

	/**
	 * 学科
	 */
	private String subjectname;

	/**
	 * 时间戳，日期毫秒数
	 */
	private String timestamp;

	/**
	 * sign之外的参数拼接字符串加预定秘钥字符串计算MD5值
	 */
	private String sign;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getTermname() {
		return termname;
	}

	public void setTermname(String termname) {
		this.termname = termname;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
