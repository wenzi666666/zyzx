package net.tfedu.zhl.sso.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 
  	用户查询结果
  
    @author wangwr
    @date 2017年1月13日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class UserQueryResult  implements Serializable{
	
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 用戶id
	 */
	public Long id ;
	
	/**
	 * 用户名
	 */
	public String userName;
	
	/**
	 * 真实姓名
	 */
	public String trueName;
	
	/**
	 * 用户性别 true 为女，false 为男
	 */
	public Boolean male ; 
	
	
	/**
	 * 学校
	 */
	public String schoolName;
	
	/**
	 * 教師學科（多个以逗号分隔）
	 */
	public String subjectNames;
	
	/**
	 * 注册时间
	 */
	public Date registerTime ;
	

	/**
	 * 过期时间
	 */
	public Date endTime ; 
	

	/**
	 * 	用户状态   0为启用  1  为停用	 
	 */
	public Integer status ;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public Boolean getMale() {
		return male;
	}


	public void setMale(Boolean male) {
		this.male = male;
	}


	public String getSchoolName() {
		return schoolName;
	}


	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	public String getSubjectNames() {
		return subjectNames;
	}


	public void setSubjectNames(String subjectNames) {
		this.subjectNames = subjectNames;
	}


	public Date getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	} 
	
}
