package net.tfedu.zhl.sso.back.role.entity;


import javax.persistence.*;

@Table(name = "s_product_back_roleconfig_detail")
public class SProductBackRoleConfigDetail {
    /**
     * 自增id
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "RoleId")
    private Long roleid;

    /**
     * 功能id
     */
    @Column(name = "funcDetailId")
    private Long funcdetailid;

    /**
     * 是否删除标示，0-----未删除；1----删除
     */
    @Column(name = "Flag")
    private Boolean flag;

    public SProductBackRoleConfigDetail(Long id, Long roleid, Long funcdetailid, Boolean flag) {
        this.id = id;
        this.roleid = roleid;
        this.funcdetailid = funcdetailid;
        this.flag = flag;
    }

    public SProductBackRoleConfigDetail() {
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
     * 获取用户id
     *
     * @return RoleId - 用户id
     */
    public Long getRoleid() {
        return roleid;
    }

    /**
     * 设置用户id
     *
     * @param roleid 用户id
     */
    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取功能id
     *
     * @return funcDetailId - 功能id
     */
    public Long getFuncdetailid() {
        return funcdetailid;
    }

    /**
     * 设置功能id
     *
     * @param funcdetailid 功能id
     */
    public void setFuncdetailid(Long funcdetailid) {
        this.funcdetailid = funcdetailid;
    }

    /**
     * 获取是否删除标示，0-----未删除；1----删除
     *
     * @return Flag - 是否删除标示，0-----未删除；1----删除
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置是否删除标示，0-----未删除；1----删除
     *
     * @param flag 是否删除标示，0-----未删除；1----删除
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}