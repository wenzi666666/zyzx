package net.tfedu.zhl.cloud.casProxy.entity;

import javax.persistence.*;

@Table(name = "tp_area_scope_limitation")
public class AreaScopeLimitation {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 行政编码
     */
    @Column(name = "Code")
    private String code;

    /**
     * 父级行政编码
     */
    @Column(name = "pCode")
    private String pcode;

    /**
     * 级次
     */
    private Integer level;

    /**
     * 区县名称
     */
    @Column(name = "Name")
    private String name;

    /**
     * 是否删除标示，0-----未删除；1----删除
     */
    @Column(name = "Flag")
    private Boolean flag;

    public AreaScopeLimitation(Integer id, String code, String pcode, Integer level, String name, Boolean flag) {
        this.id = id;
        this.code = code;
        this.pcode = pcode;
        this.level = level;
        this.name = name;
        this.flag = flag;
    }

    public AreaScopeLimitation() {
        super();
    }

    /**
     * @return Id
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
     * 获取行政编码
     *
     * @return Code - 行政编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置行政编码
     *
     * @param code 行政编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取父级行政编码
     *
     * @return pCode - 父级行政编码
     */
    public String getPcode() {
        return pcode;
    }

    /**
     * 设置父级行政编码
     *
     * @param pcode 父级行政编码
     */
    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }

    /**
     * 获取级次
     *
     * @return level - 级次
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置级次
     *
     * @param level 级次
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取区县名称
     *
     * @return Name - 区县名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置区县名称
     *
     * @param name 区县名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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