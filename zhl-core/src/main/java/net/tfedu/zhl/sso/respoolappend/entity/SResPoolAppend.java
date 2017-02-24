package net.tfedu.zhl.sso.respoolappend.entity;

import javax.persistence.*;

@Table(name = "s_res_pool_append")
public class SResPoolAppend {
    /**
     * 库id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 库名称
     */
    private String name;

    /**
     * 库描述
     */
    private String description;

    /**
     *  删除标记
     */
    private Boolean flag;

    public SResPoolAppend(Long id, String name, String description, Boolean flag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.flag = flag;
    }

    public SResPoolAppend() {
        super();
    }

    /**
     * 获取库id
     *
     * @return id - 库id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置库id
     *
     * @param id 库id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取库名称
     *
     * @return name - 库名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置库名称
     *
     * @param name 库名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取库描述
     *
     * @return description - 库描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置库描述
     *
     * @param description 库描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取 删除标记
     *
     * @return flag -  删除标记
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置 删除标记
     *
     * @param flag  删除标记
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}