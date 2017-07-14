package net.tfedu.zhl.zns.caidian.entity;

import javax.persistence.*;

@Table(name = "edu_grade")
public class EduGrade {
    @Id
    @Column(name = "grade_id")
    private Integer gradeId;

    @Column(name = "school_guid")
    private String schoolGuid;

    @Column(name = "district_guid")
    private String districtGuid;

    @Column(name = "section_guid")
    private String sectionGuid;

    @Column(name = "year_guid")
    private String yearGuid;

    @Column(name = "teacher_guid")
    private String teacherGuid;

    private String year;

    private String grade;

    private String name;

    private String no;

    @Column(name = "start_year")
    private String startYear;

    @Column(name = "end_year")
    private String endYear;

    private String preside;

    @Column(name = "is_delete")
    private String isDelete;

    @Column(name = "data_context")
    private String dataContext;

    @SequenceGenerator(name="",sequenceName="SELECT LAST_INSERT_ID()")
    private String guid;

    public EduGrade(Integer gradeId, String schoolGuid, String districtGuid, String sectionGuid, String yearGuid, String teacherGuid, String year, String grade, String name, String no, String startYear, String endYear, String preside, String isDelete, String dataContext, String guid) {
        this.gradeId = gradeId;
        this.schoolGuid = schoolGuid;
        this.districtGuid = districtGuid;
        this.sectionGuid = sectionGuid;
        this.yearGuid = yearGuid;
        this.teacherGuid = teacherGuid;
        this.year = year;
        this.grade = grade;
        this.name = name;
        this.no = no;
        this.startYear = startYear;
        this.endYear = endYear;
        this.preside = preside;
        this.isDelete = isDelete;
        this.dataContext = dataContext;
        this.guid = guid;
    }

    public EduGrade() {
        super();
    }

    /**
     * @return grade_id
     */
    public Integer getGradeId() {
        return gradeId;
    }

    /**
     * @param gradeId
     */
    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
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
     * @return section_guid
     */
    public String getSectionGuid() {
        return sectionGuid;
    }

    /**
     * @param sectionGuid
     */
    public void setSectionGuid(String sectionGuid) {
        this.sectionGuid = sectionGuid == null ? null : sectionGuid.trim();
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * @return start_year
     */
    public String getStartYear() {
        return startYear;
    }

    /**
     * @param startYear
     */
    public void setStartYear(String startYear) {
        this.startYear = startYear == null ? null : startYear.trim();
    }

    /**
     * @return end_year
     */
    public String getEndYear() {
        return endYear;
    }

    /**
     * @param endYear
     */
    public void setEndYear(String endYear) {
        this.endYear = endYear == null ? null : endYear.trim();
    }

    /**
     * @return preside
     */
    public String getPreside() {
        return preside;
    }

    /**
     * @param preside
     */
    public void setPreside(String preside) {
        this.preside = preside == null ? null : preside.trim();
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
}