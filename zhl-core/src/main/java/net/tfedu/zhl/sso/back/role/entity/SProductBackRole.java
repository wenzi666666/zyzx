package net.tfedu.zhl.sso.back.role.entity;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "s_product_back_role")
public class SProductBackRole {
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

    
    /**
     * 产品标示
     */
    @Column(name = "product_code")
    private String productCode;
    
    public SProductBackRole(Long id, String name, Boolean flag,String productCode) {
        this.id = id;
        this.name = name;
        this.flag = flag;
        this.productCode = productCode;
    }

    public SProductBackRole() {
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
     * @param name 角色名称
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
     * @param flag 是否删除标识，0-------否；1------是
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
    
    
    
}