package net.tfedu.zhl.sso.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "sys_resource")
public class SysReource implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8441843226590192063L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 资源url
     */
    private String url;

    /**
     * 权限说明
     */
    private String description;
    
    @Transient
    private List<Role> roles;

    public SysReource(Integer id, String url, String description) {
        this.id = id;
        this.url = url;
        this.description = description;
    }

    public SysReource() {
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
     * 获取资源url
     *
     * @return url - 资源url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置资源url
     *
     * @param url 资源url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取权限说明
     *
     * @return description - 权限说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置权限说明
     *
     * @param description 权限说明
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "SysReource [id=" + id + ", url=" + url + ", description=" + description + ", roles=" + roles + "]";
	}

	
    
}