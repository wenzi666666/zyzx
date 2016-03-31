package net.tfedu.zhl.sso.entity;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "sys_permission")
public class Permission implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6556629915035500331L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    /**
     * 资源url
     */
    private String url;

    /**
     * 所属角色编号
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 权限说明
     */
    private String description;

    public Permission(Integer id, String token, String url, Integer roleId, String description) {
        this.id = id;
        this.token = token;
        this.url = url;
        this.roleId = roleId;
        this.description = description;
    }

    public Permission() {
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
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
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
     * 获取所属角色编号
     *
     * @return roleId - 所属角色编号
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置所属角色编号
     *
     * @param roleid 所属角色编号
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

	@Override
	public String toString() {
		return "Permission [id=" + id + ", token=" + token + ", url=" + url + ", roleId=" + roleId + ", description="
				+ description + "]";
	}
    
    
}