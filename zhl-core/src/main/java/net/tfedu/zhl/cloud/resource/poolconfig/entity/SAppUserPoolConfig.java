package net.tfedu.zhl.cloud.resource.poolconfig.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "s_appuser_pool_config")
public class SAppUserPoolConfig implements Serializable {
    /**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 记录id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * appId
     */
    @Column(name = "appId")
    private Long appid;

    /**
     * 用户名称或uuid
     */
    private String userid;

    /**
     * 资源库id
     */
    private Long poolid;

    /**
     * 学段id,0为全部学段
     */
    private Long termid;

    /**
     * 学科id,0为全部学课
     */
    private Long subjectid;

    /**
     * 开始日期
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 结束日期
     */
    @Column(name = "expire_date")
    private Date expireDate;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 修改时间
     */
    @Column(name = "edit_time")
    private Date editTime;

    /**
     * 删除标示
     */
    private Boolean flag;

    public SAppUserPoolConfig(Long id, Long appid, String userid, Long poolid, Long termid, Long subjectid, Date startDate, Date expireDate, Date addTime, Date editTime, Boolean flag) {
        this.id = id;
        this.appid = appid;
        this.userid = userid;
        this.poolid = poolid;
        this.termid = termid;
        this.subjectid = subjectid;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.addTime = addTime;
        this.editTime = editTime;
        this.flag = flag;
    }

    public SAppUserPoolConfig() {
        super();
    }

    /**
     * 获取记录id
     *
     * @return id - 记录id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置记录id
     *
     * @param id 记录id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取appId
     *
     * @return appId - appId
     */
    public Long getAppid() {
        return appid;
    }

    /**
     * 设置appId
     *
     * @param appid appId
     */
    public void setAppid(Long appid) {
        this.appid = appid;
    }

    /**
     * 获取用户名称或uuid
     *
     * @return userid - 用户名称或uuid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置用户名称或uuid
     *
     * @param userid 用户名称或uuid
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * 获取资源库id
     *
     * @return poolid - 资源库id
     */
    public Long getPoolid() {
        return poolid;
    }

    /**
     * 设置资源库id
     *
     * @param poolid 资源库id
     */
    public void setPoolid(Long poolid) {
        this.poolid = poolid;
    }

    /**
     * 获取学段id,0为全部学段
     *
     * @return termid - 学段id,0为全部学段
     */
    public Long getTermid() {
        return termid;
    }

    /**
     * 设置学段id,0为全部学段
     *
     * @param termid 学段id,0为全部学段
     */
    public void setTermid(Long termid) {
        this.termid = termid;
    }

    /**
     * 获取学科id,0为全部学课
     *
     * @return subjectid - 学科id,0为全部学课
     */
    public Long getSubjectid() {
        return subjectid;
    }

    /**
     * 设置学科id,0为全部学课
     *
     * @param subjectid 学科id,0为全部学课
     */
    public void setSubjectid(Long subjectid) {
        this.subjectid = subjectid;
    }

    /**
     * 获取开始日期
     *
     * @return start_date - 开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置开始日期
     *
     * @param startDate 开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束日期
     *
     * @return expire_date - 结束日期
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * 设置结束日期
     *
     * @param expireDate 结束日期
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取修改时间
     *
     * @return edit_time - 修改时间
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 设置修改时间
     *
     * @param editTime 修改时间
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * 获取删除标示
     *
     * @return flag - 删除标示
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置删除标示
     *
     * @param flag 删除标示
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}