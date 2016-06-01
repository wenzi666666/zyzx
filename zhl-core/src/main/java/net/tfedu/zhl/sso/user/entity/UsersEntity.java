package net.tfedu.zhl.sso.user.entity;

import java.io.Serializable;

/**
 * 题库对接，根据user_name查询用户信息的entity
 * @author WeiCuicui
 *
 */
public class UsersEntity implements Serializable {
	 private static final long serialVersionUID = -4154529847915546034L;
	 
	 private Long id;

	 private String name;
	 
	 private String trueName;
	 
	 private String userImage;
	 
	 private Long schoolId;
	 
	 /**
      * 用户学校
      */
     private String schoolName;
     
     private Long districtId;
	 
	 /**
      * 用户学校
      */
     private String districtName;
	 
	 /**
      * 用户学段
      */
     private String termName;

     private Long termId;

    
     /**
      * 用户学科id
      */
     private String subjectId;

     /**
      * 用户学科名
      */
     private String subjectName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
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

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
