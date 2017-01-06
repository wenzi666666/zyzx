package net.tfedu.zhl.sso.district.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "q_district")
public class District {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @Min(value=1,message="所在市的id不能小于1")
    @Column(name = "CityId")
    private String cityid;

    /**
     * orcale 
     */
    @Column(name = "Code")
    private String code;

    @NotEmpty(message="不能缺少名称")
    @Column(name = "Name")
    private String name;

    /**
     * 是否删除标示，0-----未删除；1----删除
     */
    @Column(name = "Flag")
    private Boolean flag;

    public District(Integer id, String cityid, String code, String name, Boolean flag) {
        this.id = id;
        this.cityid = cityid;
        this.code = code;
        this.name = name;
        this.flag = flag;
    }

    public District() {
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
     * @return CityId
     */
    public String getCityid() {
        return cityid;
    }

    /**
     * @param cityid
     */
    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    /**
     * 获取orcale 
     *
     * @return Code - orcale 
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置orcale 
     *
     * @param code orcale 
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
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