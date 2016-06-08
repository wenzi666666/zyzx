package net.tfedu.zhl.cloud.teaching.teachCases.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_casecontents")
public class TCaseContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 案例id
     */
    @Column(name = "caseId")
    private Long caseid;

    /**
     * 案例内容类型id
     */
    @Column(name = "ContentTypeId")
    private Integer contenttypeid;

    /**
     * 文件名
     */
    private String fname;

    /**
     * 文件路径
     */
    private String fpath;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 创建日期
     */
    private Date createdate;

    /**
     * 删除标记：0 未删除 1 删除
     */
    private Boolean flag;

    public TCaseContents(Long id, Long caseid, Integer contenttypeid, String fname, String fpath, Long creator, Date createdate, Boolean flag) {
        this.id = id;
        this.caseid = caseid;
        this.contenttypeid = contenttypeid;
        this.fname = fname;
        this.fpath = fpath;
        this.creator = creator;
        this.createdate = createdate;
        this.flag = flag;
    }

    public TCaseContents() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取案例id
     *
     * @return caseId - 案例id
     */
    public Long getCaseid() {
        return caseid;
    }

    /**
     * 设置案例id
     *
     * @param caseid 案例id
     */
    public void setCaseid(Long caseid) {
        this.caseid = caseid;
    }

    /**
     * 获取案例内容类型id
     *
     * @return ContentTypeId - 案例内容类型id
     */
    public Integer getContenttypeid() {
        return contenttypeid;
    }

    /**
     * 设置案例内容类型id
     *
     * @param contenttypeid 案例内容类型id
     */
    public void setContenttypeid(Integer contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    /**
     * 获取文件名
     *
     * @return fname - 文件名
     */
    public String getFname() {
        return fname;
    }

    /**
     * 设置文件名
     *
     * @param fname 文件名
     */
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    /**
     * 获取文件路径
     *
     * @return fpath - 文件路径
     */
    public String getFpath() {
        return fpath;
    }

    /**
     * 设置文件路径
     *
     * @param fpath 文件路径
     */
    public void setFpath(String fpath) {
        this.fpath = fpath == null ? null : fpath.trim();
    }

    /**
     * 获取创建者
     *
     * @return creator - 创建者
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator 创建者
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * 获取创建日期
     *
     * @return createdate - 创建日期
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置创建日期
     *
     * @param createdate 创建日期
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取删除标记：0 未删除 1 删除
     *
     * @return flag - 删除标记：0 未删除 1 删除
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置删除标记：0 未删除 1 删除
     *
     * @param flag 删除标记：0 未删除 1 删除
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}