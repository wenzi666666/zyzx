package net.tfedu.zhl.sso.back.role.bean;

import java.util.List;

/**
 
  
  
  	后台角色功能权限展示工具类
  
    @author wangwr
    @date 2017年1月17日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class SProductBackFuncInfoPermissionView {
	
	
	/***
	 * 功能id或功能点的id
	 */
	public Long id ;
	
	/**
	 * 功能名称或功能点的名称
	 */
	public String name ; 
	
	/**
	 * 功能路徑或功能点的路徑
	 */
	public String path ; 
	
	/**
	 * 是否有权限
	 */
	public Boolean permission ; 
	
	
	
	/**
	 * 子功能
	 */
	public List<SProductBackFuncInfoPermissionView> children ;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public Boolean getPermission() {
		return permission;
	}



	public void setPermission(Boolean permission) {
		this.permission = permission;
	}



	public List<SProductBackFuncInfoPermissionView> getChildren() {
		return children;
	}



	public void setChildren(List<SProductBackFuncInfoPermissionView> children) {
		this.children = children;
	}
	
	
	
	
	
	
	

}
