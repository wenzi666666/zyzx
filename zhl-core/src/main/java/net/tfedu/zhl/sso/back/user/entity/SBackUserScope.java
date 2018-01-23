package net.tfedu.zhl.sso.back.user.entity;


import javax.persistence.*;

@Table(name = "s_back_user_scope")
public class SBackUserScope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "userId")
    private Long userid;

    /**
     * 范围 1全国 2省 3市 4区 5校
     */
    private String scope;

    /**
     * 省Id
     */
    @Column(name = "provinceId")
    private Long provinceid;

    /**
     * 市Id
     */
    @Column(name = "cityId")
    private Long cityid;

    /**
     * 区Id
     */
    @Column(name = "districtId")
    private Long districtid;

    /**
     * 校Id
     */
    @Column(name = "schoolId")
    private Long schoolid;

    private Boolean flag;

    public SBackUserScope(Long id, Long userid, String scope, Long provinceid, Long cityid, Long districtid, Long schoolid, Boolean flag) {
        this.id = id;
        this.userid = userid;
        this.scope = scope;
        this.provinceid = provinceid;
        this.cityid = cityid;
        this.districtid = districtid;
        this.schoolid = schoolid;
        this.flag = flag;
    }

    public SBackUserScope() {
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
     * 获取范围 1全国 2省 3市 4区 5校
     *
     * @return scope - 范围 1全国 2省 3市 4区 5校
     */
    public String getScope() {
        return scope;
    }

    /**
     * 设置范围 1全国 2省 3市 4区 5校
     *
     * @param scope 范围 1全国 2省 3市 4区 5校
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * 获取省Id
     *
     * @return provinceId - 省Id
     */
    public Long getProvinceid() {
        return provinceid;
    }

    /**
     * 设置省Id
     *
     * @param provinceid 省Id
     */
    public void setProvinceid(Long provinceid) {
        this.provinceid = provinceid;
    }

    /**
     * 获取市Id
     *
     * @return cityId - 市Id
     */
    public Long getCityid() {
        return cityid;
    }

    /**
     * 设置市Id
     *
     * @param cityid 市Id
     */
    public void setCityid(Long cityid) {
        this.cityid = cityid;
    }

    /**
     * 获取区Id
     *
     * @return districtId - 区Id
     */
    public Long getDistrictid() {
        return districtid;
    }

    /**
     * 设置区Id
     *
     * @param districtid 区Id
     */
    public void setDistrictid(Long districtid) {
        this.districtid = districtid;
    }

    /**
     * 获取校Id
     *
     * @return schoolId - 校Id
     */
    public Long getSchoolid() {
        return schoolid;
    }

    /**
     * 设置校Id
     *
     * @param schoolid 校Id
     */
    public void setSchoolid(Long schoolid) {
        this.schoolid = schoolid;
    }

    /**
     * @return flag
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * @param flag
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}