package net.tfedu.zhl.cloud.teaching.personalblog.model;



import java.util.List;

/**
 * @author wangwr
 * @date 2017-6-21
 * @desc copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class CloudPlatformUsrInfo {

	/**
	 * 用户id
	 */
	Long id;
	/**
	 * 学校名称
	 */
	String schoolName;
	/**
	 * 真实姓名
	 */
	String trueName;
	/**
	 * 用户名
	 */
	String name;
	/**
	 * 头像
	 */
	String userImage;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<SubjectInfo> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectInfo> subjects) {
		this.subjects = subjects;
	}

	/**
	 * 学校id
	 */
	Long schoolId;
	/**
	 * 学段id
	 */
	Long termId;
	/**
	 * 角色id
	 */
	Long roleId;

	/**
	 * 教师学科
	 */
	List<SubjectInfo> subjects;

	
	
	
}
