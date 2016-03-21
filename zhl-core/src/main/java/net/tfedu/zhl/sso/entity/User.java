package net.tfedu.zhl.sso.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "sys_user")
public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3508900201566884322L;

	/**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 是否删除
     */
    @Column(name = "isDelete")
    private Integer isdelete;
    
    private String salt;
    
    @Transient
    List<Role> roles; 
    
    @Transient
    List<SysResource> resources;

    public User(Integer id, String username, String password, Date createDate, Integer isdelete) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
        this.isdelete = isdelete;
    }

    public User() {
        super();
    }
    
    public User(String username){
    	this.username=username;
    }

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取创建日期
     *
     * @return create_date - 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建日期
     *
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取是否删除
     *
     * @return isDelete - 是否删除
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
     * 设置是否删除
     *
     * @param isdelete 是否删除
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<SysResource> getResources() {
		return resources;
	}

	public void setResources(List<SysResource> resources) {
		this.resources = resources;
	}


	/**
	 * 提供toString方法，方便单元测试时，直接输出对象。
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", createDate=" + createDate
				+ ", isdelete=" + isdelete + ", roles=" + roles + ", resources=" + resources + "]";
	}
    
    
}