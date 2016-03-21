package net.tfedu.zhl.sso.entity;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "sys_role_permisson")
public class RolePermisson  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2415251722472565249L;

	/**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色编号
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 资源编号
     */
    @Column(name = "resource_id")
    private Integer resourceId;

    public RolePermisson(Integer id, Integer roleId, Integer resourceId) {
        this.id = id;
        this.roleId = roleId;
        this.resourceId = resourceId;
    }

    public RolePermisson() {
        super();
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
     * 获取角色编号
     *
     * @return role_id - 角色编号
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色编号
     *
     * @param roleId 角色编号
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取资源编号
     *
     * @return resource_id - 资源编号
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源编号
     *
     * @param resourceId 资源编号
     */
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

	@Override
	public String toString() {
		return "RolePermisson [id=" + id + ", roleId=" + roleId + ", resourceId=" + resourceId + "]";
	}
    
}