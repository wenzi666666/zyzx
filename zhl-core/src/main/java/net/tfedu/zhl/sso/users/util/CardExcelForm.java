package net.tfedu.zhl.sso.users.util;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *CardWebForm.java
 *wangwr
 *excel 批量注册form
 *
 * 2015-3-10下午4:09:56
 * 
 *   用于增加用户时，需要校验
 * 
 * 
 */

public class CardExcelForm {

	/**
	 * 卡号
	 */
	 @NotEmpty(message="注册卡号不能为空")
	 public String cardNumber = "";
	 /**
	  * 卡密码
	  */
	 @NotEmpty(message="注册卡密码不能为空")
	 public String cardPwd = "";
	 /**
	  * 用户名
	  */
	 @NotEmpty(message="注册用户名不能为空")
	 public String userName = "";
	 /**
	  * 用户密码
	  */
	 @NotEmpty(message="注册用户密码不能为空")
	 public String userPwd = "";
	 /**
	  * 角色
	  */
	 public String role = "";
	 /**
	  * 真实姓名
	  */
	 @NotEmpty(message="注册真实姓名不能为空")
	 public String trueName = "";
	 /**
	  * 昵称
	  */
	 public String nickName = "";
	 /**
	  * 性别
	  */
	 public String sexName = "";
	 
	 /**
	  * 性別
	  */
	 public Boolean male;

	 
	 /**
	  * 学段名称
	  */
	 public String termName = "";
	 /**
	  * 学校名称
	  */
	 public String schoolName = "";
	 /**
	  * 学科名称
	  */
	 public String subjectName = "";
	 /**
	  * 班级名称
	  */
	 public String className = "";
	 
	 /**
	  * 注册信息是否有错
	  */
	 public boolean hasError= false ;
	 
	 /**
	  *  注册信息有错的话  具体信息
	  */
	 public String message = "";
	 
	 
	 
	 
	 /**
	  * 卡的有效期
	  */
	 public int cardExpNum = 0;
	 
	 /**
	  * 注册学校id
	  * @return
	  */
	 @Min(value=1,message="注册学校不能为空")
	 public long schoolId = 0;
	 
	 
	 @Min(value=1,message="注册学段不能为空")
	 public long termId = 0 ;
	 
	 
	 public long classId = 0 ;
	
	 
	 public long roleId = 0 ;
	 
	 @Min(value=1,message="注册学课不能为空")
	 public long subjectId = 0 ;
	 
	 public long cardId = 0 ;
	 
	 
	 
	 
	public Boolean getMale() {
		return male;
	}

	public void setMale(Boolean male) {
		this.male = male;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getCardExpNum() {
		return cardExpNum;
	}

	public void setCardExpNum(int cardExpNum) {
		this.cardExpNum = cardExpNum;
	}

	public long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}

	public long getTermId() {
		return termId;
	}

	public void setTermId(long termId) {
		this.termId = termId;
	}

	public long getClassId() {
		return classId;
	}

	public void setClassId(long classId) {
		this.classId = classId;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardPwd() {
		return cardPwd;
	}

	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	 
	 
	
	
	
	
	
}
