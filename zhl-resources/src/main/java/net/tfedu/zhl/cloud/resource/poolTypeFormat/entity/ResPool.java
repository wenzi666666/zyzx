package net.tfedu.zhl.cloud.resource.poolTypeFormat.entity;

import javax.persistence.*;

@Table(name = "z_res_pool")
public class ResPool {
    /**
     * 资源库id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 资源库名称
     */
    private String name;

    /**
     *   资源库描述
     */
    private String description;

    /**
     *  删除标记
     */
    private Boolean flag;

    public ResPool(Long id, String name, String description, Boolean flag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.flag = flag;
    }

    public ResPool() {
        super();
    }

    /**
     * 获取资源库id
     *
     * @return id - 资源库id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置资源库id
     *
     * @param id 资源库id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取资源库名称
     *
     * @return name - 资源库名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源库名称
     *
     * @param name 资源库名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取  资源库描述
     *
     * @return description -   资源库描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置  资源库描述
     *
     * @param description   资源库描述
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