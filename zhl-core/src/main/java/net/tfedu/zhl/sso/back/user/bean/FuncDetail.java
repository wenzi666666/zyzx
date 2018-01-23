package net.tfedu.zhl.sso.back.user.bean;

import java.io.Serializable;

/**
 
  	功能点信息
  
    @author wangwr
    @date 2017年1月5日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class FuncDetail implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 功能点id
	 */
	private  long id ; 
	
	/**
	 * 功能点所在功能
	 */
	private  long funcId ; 
	
	/**
	 * 名称
	 */
	private  String name ; 
	
	/**
	 * 编码
	 */
	private  String code ; 
	
	/**
	 * 路径
	 * 
	 * 操作該功能時，url的結尾字符片段。用于权限判断
	 * 
	 */
	private  String path ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFuncId() {
		return funcId;
	}

	public void setFuncId(long funcId) {
		this.funcId = funcId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}



	
	
	
	
	
}
