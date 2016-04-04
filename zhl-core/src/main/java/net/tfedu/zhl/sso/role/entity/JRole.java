package net.tfedu.zhl.sso.role.entity;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "j_role")
public class JRole implements Serializable {
    /**
     * 自增id
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "Name")
    private String name;

    /**
     * 是否删除标识，0-------否；1------是
     */
    @Column(name = "Flag")
    private Boolean flag;

    public JRole(Long id, String name, Boolean flag) {
        this.id = id;
        this.name = name;
        this.flag = flag;
    }

    public JRole() {
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
     * @param id
     *            自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return Name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name
     *            角色名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取是否删除标识，0-------否；1------是
     *
     * @return Flag - 是否删除标识，0-------否；1------是
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置是否删除标识，0-------否；1------是
     *
     * @param flag
     *            是否删除标识，0-------否；1------是
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}