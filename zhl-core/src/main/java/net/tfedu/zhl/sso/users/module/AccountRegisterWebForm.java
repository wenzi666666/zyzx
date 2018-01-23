package net.tfedu.zhl.sso.users.module;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户注册前端表单
 * 
 */
public class AccountRegisterWebForm {
	
	/**
	 * 
	 * 注册卡id
	 * 
	 */
	@NotNull(message = "未指定注册使用的卡号")
	private Long cardId;
	
	
	/**
	 * 用户名
	 * 
	 */
	@NotEmpty(message = "用户名不能为空！")
	@Pattern(regexp = "\\w{6,14}", message = "用户名6-14位")
	private String userName;
	
	
	/**
	 * 用户密码
	 * 
	 */
	@NotEmpty(message = "密码不能为空")
	@Length(max = 20, min = 6, message = "密码不能小于6位")
	private String userPassword;
	
	
	/**
	 * 真实姓名
	 * 
	 */
	@NotEmpty(message = "真实姓名不能为空！")
	private String trueName;
	
	
	/**
	 * 学校id
	 * 
	 */
	@NotNull(message = "学校不能为空")
	@Min(value = 1, message = "不能为0")
	private Long schoolId;
	
	/**
	 * 性别
	 * 
	 */
	private Boolean sex = true;
	
	
	/**
	 * 年级id
	 * 
	 */
	@Min(value = 1, message = "注册年级不能为0")
	private Long gradeId;
	
	
	/**
	 * 班级id
	 * 
	 */
	@Min(value = 1, message = "注册班级不能为0")
	private Long classId;
	
	
	/**
	 * 学段id
	 * 
	 */
	@NotNull(message = "注册学段不能为空")
	@Min(value = 1, message = "注册学段不能为0")
	private Long termId;
	
	/**
	 * 学科id
	 * 
	 */
	@NotNull(message = "注册学科不能为空")
	@Min(value = 1, message = "注册学科不能为0")
	private Long subjectId;

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	
	
	
	
	
	
	

}