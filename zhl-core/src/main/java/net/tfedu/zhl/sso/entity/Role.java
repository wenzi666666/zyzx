package net.tfedu.zhl.sso.entity;

import javax.persistence.*;

@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色说明
     */
    private String description;

    public Role(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Role() {
        super();
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色名
     *
     * @return name - 角色名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名
     *
     * @param name 角色名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取角色说明
     *
     * @return description - 角色说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置角色说明
     *
     * @param description 角色说明
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}