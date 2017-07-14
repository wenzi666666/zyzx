package net.tfedu.zhl.zns.caidian.entity;

import javax.persistence.*;

@Table(name = "edu_section")
public class EduSection {
    @Id
    @Column(name = "section_id")
    private Integer sectionId;

    @Column(name = "school_guid")
    private String schoolGuid;

    @Column(name = "district_guid")
    private String districtGuid;

    @Column(name = "teacher_guid")
    private String teacherGuid;

    @Column(name = "data_context")
    private String dataContext;

    private String code;

    private String name;

    @Column(name = "edu_system")
    private String eduSystem;

    @Column(name = "entrance_age")
    private String entranceAge;

    private String preside;

    @Column(name = "is_delete")
    private String isDelete;

    @SequenceGenerator(name="",sequenceName="SELECT LAST_INSERT_ID()")
    private String guid;

    public EduSection(Integer sectionId, String schoolGuid, String districtGuid, String teacherGuid, String dataContext, String code, String name, String eduSystem, String entranceAge, String preside, String isDelete, String guid) {
        this.sectionId = sectionId;
        this.schoolGuid = schoolGuid;
        this.districtGuid = districtGuid;
        this.teacherGuid = teacherGuid;
        this.dataContext = dataContext;
        this.code = code;
        this.name = name;
        this.eduSystem = eduSystem;
        this.entranceAge = entranceAge;
        this.preside = preside;
        this.isDelete = isDelete;
        this.guid = guid;
    }

    public EduSection() {
        super();
    }

    /**
     * @return section_id
     */
    public Integer getSectionId() {
        return sectionId;
    }

    /**
     * @param sectionId
     */
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
     * @return edu_system
     */
    public String getEduSystem() {
        return eduSystem;
    }

    /**
     * @param eduSystem
     */
    public void setEduSystem(String eduSystem) {
        this.eduSystem = eduSystem == null ? null : eduSystem.trim();
    }

    /**
     * @return entrance_age
     */
    public String getEntranceAge() {
        return entranceAge;
    }

    /**
     * @param entranceAge
     */
    public void setEntranceAge(String entranceAge) {
        this.entranceAge = entranceAge == null ? null : entranceAge.trim();
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