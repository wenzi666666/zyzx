package net.tfedu.zhl.userlayer.user.entity;


import javax.validation.constraints.NotNull;

/**
 
            用戶編輯属性的表单类
            暂时仅用于后台的用户管理功能
    @author wangwr
    @date 2017年1月13日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class UserEditForm {


	@NotNull(message="未指定用戶")
	public Long id;
	
	
	@NotNull(message="真实姓名不能为空")
	public String trueName;
	
	/**
	 * 用戶昵称为空时，默认为真实姓名
	 */
	public String nickName;
	
	
	@NotNull(message="未指定用戶的性別")
	public Boolean Male;
	
	
	@NotNull(message="未指定用戶的学段")
	public Long termId;
	
	
	@NotNull(message="未指定用戶学科")
	public Long subjectId;

	
	@NotNull(message="未指定用戶学校")
	public Long schoolId;
	
	
	
	
	
	

	public Long getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public Boolean getMale() {
		return Male;
	}


	public void setMale(Boolean male) {
		Male = male;
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
