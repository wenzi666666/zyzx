package net.tfedu.zhl.sso.back.user.entity;


import javax.persistence.*;

@Table(name = "s_product_back_userrole")
public class SProductBackUserRole {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "UserId")
    private Long userid;

    /**
     * 角色id
     */
    @Column(name = "RoleId")
    private Long roleid;

    /**
     * 是否删除标示，0-----未删除；1----删除
     */
    @Column(name = "Flag")
    private Boolean flag;

    public SProductBackUserRole(Long id, Long userid, Long roleid, Boolean flag) {
        this.id = id;
        this.userid = userid;
        this.roleid = roleid;
        this.flag = flag;
    }

    public SProductBackUserRole() {
        super();
    }

    /**
     * @return Id
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
     * @return UserId - 用户id
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
     * 获取角色id
     *
     * @return RoleId - 角色id
     */
    public Long getRoleid() {
        return roleid;
    }

    /**
     * 设置角色id
     *
     * @param roleid 角色id
     */
    public void setRoleid(Long roleid) {
        this.roleid = roleid;
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