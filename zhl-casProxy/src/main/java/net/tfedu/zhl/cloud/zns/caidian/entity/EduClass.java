package net.tfedu.zhl.cloud.zns.caidian.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "edu_class")
public class EduClass {
    @Id
    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "school_guid")
    private String schoolGuid;

    @Column(name = "district_guid")
    private String districtGuid;

    @Column(name = "grade_guid")
    private String gradeGuid;

    @Column(name = "teacher_guid")
    private String teacherGuid;

    private String no;

    @Column(name = "class")
    private String _class;

    @Column(name = "preside_no")
    private String presideNo;

    @Column(name = "end_date")
    private String endDate;

    private String grade;

    @Column(name = "is_delete")
    private String isDelete;

    @Column(name = "data_context")
    private String dataContext;

    @SequenceGenerator(name="",sequenceName="SELECT LAST_INSERT_ID()")
    private String guid;

    public EduClass(Integer classId, String schoolGuid, String districtGuid, String gradeGuid, String teacherGuid, String no, String _class, String presideNo, String endDate, String grade, String isDelete, String dataContext, String guid) {
        this.classId = classId;
        this.schoolGuid = schoolGuid;
        this.districtGuid = districtGuid;
        this.gradeGuid = gradeGuid;
        this.teacherGuid = teacherGuid;
        this.no = no;
        this._class = _class;
        this.presideNo = presideNo;
        this.endDate = endDate;
        this.grade = grade;
        this.isDelete = isDelete;
        this.dataContext = dataContext;
        this.guid = guid;
    }

    public EduClass() {
        super();
    }

    /**
     * @return class_id
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * @param classId
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * @return school_guid
     */
    public String getSchoolGuid() {
        return schoolGuid;
    }

    /**
     * @param schoolGuid
     */
    public void setSchoolGuid(String schoolGuid) {
        this.schoolGuid = schoolGuid == null ? null : schoolGuid.trim();
    }

    /**
     * @return district_guid
     */
    public String getDistrictGuid() {
        return districtGuid;
    }

    /**
     * @param districtGuid
     */
    public void setDistrictGuid(String districtGuid) {
        this.districtGuid = districtGuid == null ? null : districtGuid.trim();
    }

    /**
     * @return grade_guid
     */
    public String getGradeGuid() {
        return gradeGuid;
    }

    /**
     * @param gradeGuid
     */
    public void setGradeGuid(String gradeGuid) {
        this.gradeGuid = gradeGuid == null ? null : gradeGuid.trim();
    }

    /**
     * @return teacher_guid
     */
    public String getTeacherGuid() {
        return teacherGuid;
    }

    /**
     * @param teacherGuid
     */
    public void setTeacherGuid(String teacherGuid) {
        this.teacherGuid = teacherGuid == null ? null : teacherGuid.trim();
    }

    /**
     * @return no
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

  
    /**
     * @return preside_no
     */
    public String getPresideNo() {
        return presideNo;
    }

    /**
     * @param presideNo
     */
    public void setPresideNo(String presideNo) {
        this.presideNo = presideNo == null ? null : presideNo.trim();
    }

    /**
     * @return end_date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    /**
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     * @return is_delete
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    /**
     * @return data_context
     */
    public String getDataContext() {
        return dataContext;
    }

    /**
     * @param dataContext
     */
    public void setDataContext(String dataContext) {
        this.dataContext = dataContext == null ? null : dataContext.trim();
    }

    /**
     * @return guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}
    
    
}