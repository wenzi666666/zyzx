package net.tfedu.zhl.cloud.resource.intergral.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_resource_intergral_record")
public class UserResourceIntergralRecord {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 积分相关用户主键
     */
    private Long userid;

    /**
     * 记录创建人
     */
    private Long createman;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 失效时间，null为永久有效
     */
    private Date invalidtime;

    /**
     * 删除标示（默认未删除false）
     */
    private Boolean flag;

    /**
     * 积分操作类型
     */
    private String operatetype;

    /**
     * 积分操作对象的主键
     */
    private Long operateid;

    public UserResourceIntergralRecord(Long id, Long userid, Long createman, Integer score, Date createtime, Date invalidtime, Boolean flag, String operatetype, Long operateid) {
        this.id = id;
        this.userid = userid;
        this.createman = createman;
        this.score = score;
        this.createtime = createtime;
        this.invalidtime = invalidtime;
        this.flag = flag;
        this.operatetype = operatetype;
        this.operateid = operateid;
    }

    public UserResourceIntergralRecord() {
        super();
    }

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取积分相关用户主键
     *
     * @return userid - 积分相关用户主键
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置积分相关用户主键
     *
     * @param userid 积分相关用户主键
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 获取记录创建人
     *
     * @return createman - 记录创建人
     */
    public Long getCreateman() {
        return createman;
    }

    /**
     * 设置记录创建人
     *
     * @param createman 记录创建人
     */
    public void setCreateman(Long createman) {
        this.createman = createman;
    }

    /**
     * 获取分值
     *
     * @return score - 分值
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置分值
     *
     * @param score 分值
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取失效时间，null为永久有效
     *
     * @return invalidtime - 失效时间，null为永久有效
     */
    public Date getInvalidtime() {
        return invalidtime;
    }

    /**
     * 设置失效时间，null为永久有效
     *
     * @param invalidtime 失效时间，null为永久有效
     */
    public void setInvalidtime(Date invalidtime) {
        this.invalidtime = invalidtime;
    }

    /**
     * 获取删除标示（默认未删除false）
     *
     * @return flag - 删除标示（默认未删除false）
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置删除标示（默认未删除false）
     *
     * @param flag 删除标示（默认未删除false）
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * 获取积分操作类型
     *
     * @return operatetype - 积分操作类型
     */
    public String getOperatetype() {
        return operatetype;
    }

    /**
     * 设置积分操作类型
     *
     * @param operatetype 积分操作类型
     */
    public void setOperatetype(String operatetype) {
        this.operatetype = operatetype == null ? null : operatetype.trim();
    }

    /**
     * 获取积分操作对象的主键
     *
     * @return operateid - 积分操作对象的主键
     */
    public Long getOperateid() {
        return operateid;
    }

    /**
     * 设置积分操作对象的主键
     *
     * @param operateid 积分操作对象的主键
     */
    public void setOperateid(Long operateid) {
        this.operateid = operateid;
    }
}