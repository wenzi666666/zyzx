package net.tfedu.zhl.cloud.zns.caidian.entity;

import javax.persistence.*;

@Table(name = "edu_teacher_class")
public class EduTeacherClass {
    @Id
    @Column(name = "teacher_class_id")
    private Integer teacherClassId;

    @Column(name = "school_guid")
    private String schoolGuid;

    @Column(name = "grade_guid")
    private String gradeGuid;

    @Column(name = "class_guid")
    private String classGuid;

    @Column(name = "year_guid")
    private String yearGuid;

    @Column(name = "teacher_guid")
    private String teacherGuid;

    @Column(name = "data_context")
    private String dataContext;

    private String year;

    @Column(name = "is_delete")
    private String isDelete;

    @SequenceGenerator(name="",sequenceName="SELECT LAST_INSERT_ID()")
    private String guid;

    public EduTeacherClass(Integer teacherClassId, String schoolGuid, String gradeGuid, String classGuid, String yearGuid, String teacherGuid, String dataContext, String year, String isDelete, String guid) {
        this.teacherClassId = teacherClassId;
        this.schoolGuid = schoolGuid;
        this.gradeGuid = gradeGuid;
        this.classGuid = classGuid;
        this.yearGuid = yearGuid;
        this.teacherGuid = teacherGuid;
        this.dataContext = dataContext;
        this.year = year;
        this.isDelete = isDelete;
        this.guid = guid;
    }

    public EduTeacherClass() {
        super();
    }

    /**
     * @return teacher_class_id
     */
    public Integer getTeacherClassId() {
        return teacherClassId;
    }

    /**
     * @param teacherClassId
     */
    public void setTeacherClassId(Integer teacherClassId) {
        this.teacherClassId = teacherClassId;
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
     * @return class_guid
     */
    public String getClassGuid() {
        return classGuid;
    }

    /**
     * @param classGuid
     */
    public void setClassGuid(String classGuid) {
        this.classGuid = classGuid == null ? null : classGuid.trim();
    }

    /**
     * @return year_guid
     */
    public String getYearGuid() {
        return yearGuid;
    }

    /**
     * @param yearGuid
     */
    public void setYearGuid(String yearGuid) {
        this.yearGuid = yearGuid == null ? null : yearGuid.trim();
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
     * @return year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year
     */
    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
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
}