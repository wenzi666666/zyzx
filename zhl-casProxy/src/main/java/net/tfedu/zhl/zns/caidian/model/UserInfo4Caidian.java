package net.tfedu.zhl.zns.caidian.model;

import java.io.Serializable;

/**
 * 蔡甸区的用户信息查询结果
 * 
 * @author wangwr
 * @date 2017年7月11日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class UserInfo4Caidian implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户的uuid
	 */
	String uuid;
	/**
	 * 学校uuid
	 */
	String school_id;
	/**
	 * 学校名称
	 */
	String school_name;
	/**
	 * 真实姓名
	 */
	String true_name;
	/**
	 * 性别 1 男 2女 9 其他未知性别
	 */
	Integer gender;
	/**
	 * 生日
	 */
	String birthday;
	/**
	 * 邮箱
	 */
	String email;
	/**
	 * 学段名称
	 */
	String termname;
	/**
	 * 真实姓名
	 */
	String user_name;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSchool_id() {
		return school_id;
	}

	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public String getTrue_name() {
		return true_name;
	}

	public void setTrue_name(String true_name) {
		this.true_name = true_name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTermname() {
		return termname;
	}

	public void setTermname(String termname) {
		this.termname = termname;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
