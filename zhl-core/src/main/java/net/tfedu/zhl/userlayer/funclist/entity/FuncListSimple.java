package net.tfedu.zhl.userlayer.funclist.entity;

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
    
    
    
    /**
     * 功能编码（暂缺）
     */
    private String code ;


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


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "FuncListSimple[name:"+name+",code;"+code+",path:"+path+"]";
	}
	
	
	
	
}
