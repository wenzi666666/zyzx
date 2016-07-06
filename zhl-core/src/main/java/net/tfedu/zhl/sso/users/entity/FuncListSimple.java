package net.tfedu.zhl.sso.users.entity;

import java.io.Serializable;

/**
 * 资源或功能列表
 * @author wangwr
 *
 */
public class FuncListSimple implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
     * 功能名称
     */
    private String name;

    
    /**
     * 路径
     */
    private String path;


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
	
	
    
    
}
