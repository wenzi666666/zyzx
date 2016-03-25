package net.tfedu.zhl.cloud.resource.resourceList.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "z_resnav")
public class SysResNav {
    /**
     * 自增id
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ResCode")
    private String rescode;

    @Column(name = "StructCode")
    private String structcode;

    @Column(name = "ReschptMtno")
    private Long reschptmtno;

    @Column(name = "ReschptPage")
    private String reschptpage;

    @Column(name = "SubjectCode")
    private String subjectcode;

    @Column(name = "Subject")
    private String subject;

    @Column(name = "Grade")
    private String grade;

    @Column(name = "Vesion")
    private String vesion;

    @Column(name = "ResUnitname")
    private String resunitname;

    /**
     * 系统课程id（记录课程节点）
     */
    @Column(name = "SysCourseId")
    private Long syscourseid;

    /**
     * 是否删除数据标识；0----否；1-----是
     */
    @Column(name = "Flag")
    private Boolean flag;

    @Column(name = "Ordernum")
    private Integer ordernum;

    private String xueke;

    /**
     * 添加时间
     */
    @Column(name = "CreateDT")
    private Date createdt;

    /**
     * 更新时间
     */
    @Column(name = "UpdateDT")
    private Date updatedt;

    public SysResNav(Long id, String rescode, String structcode, Long reschptmtno, String reschptpage, String subjectcode, String subject, String grade, String vesion, String resunitname, Long syscourseid, Boolean flag, Integer ordernum, String xueke, Date createdt, Date updatedt) {
        this.id = id;
        this.rescode = rescode;
        this.structcode = structcode;
        this.reschptmtno = reschptmtno;
        this.reschptpage = reschptpage;
        this.subjectcode = subjectcode;
        this.subject = subject;
        this.grade = grade;
        this.vesion = vesion;
        this.resunitname = resunitname;
        this.syscourseid = syscourseid;
        this.flag = flag;
        this.ordernum = ordernum;
        this.xueke = xueke;
        this.createdt = createdt;
        this.updatedt = updatedt;
    }

    public SysResNav() {
        super();
    }

    /**
     * 获取自增id
     *
     * @return Id - 自增id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return ResCode
     */
    public String getRescode() {
        return rescode;
    }

    /**
     * @param rescode
     */
    public void setRescode(String rescode) {
        this.rescode = rescode == null ? null : rescode.trim();
    }

    /**
     * @return StructCode
     */
    public String getStructcode() {
        return structcode;
    }

    /**
     * @param structcode
     */
    public void setStructcode(String structcode) {
        this.structcode = structcode == null ? null : structcode.trim();
    }

    /**
     * @return ReschptMtno
     */
    public Long getReschptmtno() {
        return reschptmtno;
    }

    /**
     * @param reschptmtno
     */
    public void setReschptmtno(Long reschptmtno) {
        this.reschptmtno = reschptmtno;
    }

    /**
     * @return ReschptPage
     */
    public String getReschptpage() {
        return reschptpage;
    }

    /**
     * @param reschptpage
     */
    public void setReschptpage(String reschptpage) {
        this.reschptpage = reschptpage == null ? null : reschptpage.trim();
    }

    /**
     * @return SubjectCode
     */
    public String getSubjectcode() {
        return subjectcode;
    }

    /**
     * @param subjectcode
     */
    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode == null ? null : subjectcode.trim();
    }

    /**
     * @return Subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * @return Grade
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
     * @return Vesion
     */
    public String getVesion() {
        return vesion;
    }

    /**
     * @param vesion
     */
    public void setVesion(String vesion) {
        this.vesion = vesion == null ? null : vesion.trim();
    }

    /**
     * @return ResUnitname
     */
    public String getResunitname() {
        return resunitname;
    }

    /**
     * @param resunitname
     */
    public void setResunitname(String resunitname) {
        this.resunitname = resunitname == null ? null : resunitname.trim();
    }

    /**
     * 获取系统课程id（记录课程节点）
     *
     * @return SysCourseId - 系统课程id（记录课程节点）
     */
    public Long getSyscourseid() {
        return syscourseid;
    }

    /**
     * 设置系统课程id（记录课程节点）
     *
     * @param syscourseid 系统课程id（记录课程节点）
     */
    public void setSyscourseid(Long syscourseid) {
        this.syscourseid = syscourseid;
    }

    /**
     * 获取是否删除数据标识；0----否；1-----是
     *
     * @return Flag - 是否删除数据标识；0----否；1-----是
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置是否删除数据标识；0----否；1-----是
     *
     * @param flag 是否删除数据标识；0----否；1-----是
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * @return Ordernum
     */
    public Integer getOrdernum() {
        return ordernum;
    }

    /**
     * @param ordernum
     */
    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    /**
     * @return xueke
     */
    public String getXueke() {
        return xueke;
    }

    /**
     * @param xueke
     */
    public void setXueke(String xueke) {
        this.xueke = xueke == null ? null : xueke.trim();
    }

    /**
     * 获取添加时间
     *
     * @return CreateDT - 添加时间
     */
    public Date getCreatedt() {
        return createdt;
    }

    /**
     * 设置添加时间
     *
     * @param createdt 添加时间
     */
    public void setCreatedt(Date createdt) {
        this.createdt = createdt;
    }

    /**
     * 获取更新时间
     *
     * @return UpdateDT - 更新时间
     */
    public Date getUpdatedt() {
        return updatedt;
    }

    /**
     * 设置更新时间
     *
     * @param updatedt 更新时间
     */
    public void setUpdatedt(Date updatedt) {
        this.updatedt = updatedt;
    }
}