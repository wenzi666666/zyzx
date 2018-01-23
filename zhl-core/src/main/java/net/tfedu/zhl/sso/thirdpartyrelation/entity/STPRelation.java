package net.tfedu.zhl.sso.thirdpartyrelation.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_tp_relation")
public class STPRelation {
	
	public static  final String OPERATION_TYPE_REGISTER = "注册信息";
	
	
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作类型
     */
    @Column(name = "operationType")
    private String operationtype;

    /**
     * 知好乐数据主键
     */
    @Column(name = "zhlId")
    private Long zhlid;

    /**
     * 第三方平台数据主键
     */
    @Column(name = "tpId")
    private String tpid;

    @Column(name = "appId")
    private Integer appid;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 删除标示 0正常 1删除
     */
    private Boolean flag;

    public STPRelation(Long id, String operationtype, Long zhlid, String tpid, Integer appid, Date createtime, Boolean flag) {
        this.id = id;
        this.operationtype = operationtype;
        this.zhlid = zhlid;
        this.tpid = tpid;
        this.appid = appid;
        this.createtime = createtime;
        this.flag = flag;
    }

    public STPRelation() {
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
     * 获取操作类型
     *
     * @return operationType - 操作类型
     */
    public String getOperationtype() {
        return operationtype;
    }

    /**
     * 设置操作类型
     *
     * @param operationtype 操作类型
     */
    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype == null ? null : operationtype.trim();
    }

    /**
     * 获取知好乐数据主键
     *
     * @return zhlId - 知好乐数据主键
     */
    public Long getZhlid() {
        return zhlid;
    }

    /**
     * 设置知好乐数据主键
     *
     * @param zhlid 知好乐数据主键
     */
    public void setZhlid(Long zhlid) {
        this.zhlid = zhlid;
    }

    /**
     * 获取第三方平台数据主键
     *
     * @return tpId - 第三方平台数据主键
     */
    public String getTpid() {
        return tpid;
    }

    /**
     * 设置第三方平台数据主键
     *
     * @param tpid 第三方平台数据主键
     */
    public void setTpid(String tpid) {
        this.tpid = tpid == null ? null : tpid.trim();
    }

    /**
     * @return appId
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * @param appid
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
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
     * 获取删除标示 0正常 1删除
     *
     * @return flag - 删除标示 0正常 1删除
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置删除标示 0正常 1删除
     *
     * @param flag 删除标示 0正常 1删除
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}