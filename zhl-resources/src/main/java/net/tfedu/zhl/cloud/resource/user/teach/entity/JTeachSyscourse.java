package net.tfedu.zhl.cloud.resource.user.teach.entity;

import javax.persistence.*;

@Table(name = "j_teach_syscourse")
public class JTeachSyscourse {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "userId")
    private Long userid;

    /**
     * 教材目录id
     */
    @Column(name = "syscourseId")
    private Long syscourseid;

    /**
     * 学段id
     */
    @Column(name = "termId")
    private Integer termid;

    /**
     * 学科id
     */
    @Column(name = "subjectId")
    private Integer subjectid;

    /**
     * 删除标识
     */
    private Boolean flag;

    public JTeachSyscourse(Long id, Long userid, Long syscourseid, Integer termid, Integer subjectid, Boolean flag) {
        this.id = id;
        this.userid = userid;
        this.syscourseid = syscourseid;
        this.termid = termid;
        this.subjectid = subjectid;
        this.flag = flag;
    }

    public JTeachSyscourse() {
        super();
    }

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return userId - 用户id
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 获取教材目录id
     *
     * @return syscourseId - 教材目录id
     */
    public Long getSyscourseid() {
        return syscourseid;
    }

    /**
     * 设置教材目录id
     *
     * @param syscourseid 教材目录id
     */
    public void setSyscourseid(Long syscourseid) {
        this.syscourseid = syscourseid;
    }

    /**
     * 获取学段id
     *
     * @return termId - 学段id
     */
    public Integer getTermid() {
        return termid;
    }

    /**
     * 设置学段id
     *
     * @param termid 学段id
     */
    public void setTermid(Integer termid) {
        this.termid = termid;
    }

    /**
     * 获取学科id
     *
     * @return subjectId - 学科id
     */
    public Integer getSubjectid() {
        return subjectid;
    }

    /**
     * 设置学科id
     *
     * @param subjectid 学科id
     */
    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    /**
     * 获取删除标识
     *
     * @return flag - 删除标识
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置删除标识
     *
     * @param flag 删除标识
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}