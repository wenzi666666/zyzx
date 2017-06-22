package net.tfedu.zhl.userlayer.role.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "j_role")
public class JRole implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色说明
     */
    private String note;

    /**
     * 系统模块
     */
    private String model;

    /**
     * 0-公共角色,1-子系统自定义角色
     */
    private Boolean tag;
    
    
    /**
     * 是否删除标识；0-----未删除；1----删除
     */
    @Column(name = "Flag")
    private Boolean flag;

    public JRole(Long id, String name, String note, String model, Boolean tag,Boolean flag) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.model = model;
        this.tag = tag;
        this.flag = flag ;
    }

    public JRole() {
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
     * 获取系统模块
     *
     * @return model - 系统模块
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置系统模块
     *
     * @param model 系统模块
     */
    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    /**
     * 获取0-公共角色,1-子系统自定义角色
     *
     * @return tag - 0-公共角色,1-子系统自定义角色
     */
    public Boolean getTag() {
        return tag;
    }

    /**
     * 设置0-公共角色,1-子系统自定义角色
     *
     * @param tag 0-公共角色,1-子系统自定义角色
     */
    public void setTag(Boolean tag) {
        this.tag = tag;
    }

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
    
    
    
    
}