package net.tfedu.zhl.sso.back.user.bean;

import java.io.Serializable;
import java.util.List;

/**
 	功能菜單信息 
  
    @author wangwr
    @date 2017年1月5日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class FuncInfo implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private long id ;
	/**
	 * 名称
	 */
	private String name ;
	/**
	 * 路径
	 */
	private String path ;
	/**
	 * 缩略图路径
	 */
	private String iconPath ;
	
	/**
	 * 子功能
	 */
	private List<FuncInfo> children;
	
	/**
	 * 功能点
	 */
	private List<FuncDetail> details;	
	
	
	/**
	 * 排序字段
	 */
	private int orderNum;


	public long getId() {
		return id;
	}


	public void setId(long id) {
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


	public String getIconPath() {
		return iconPath;
	}


	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}


	public List<FuncInfo> getChildren() {
		return children;
	}


	public void setChildren(List<FuncInfo> children) {
		this.children = children;
	}


	public List<FuncDetail> getDetails() {
		return details;
	}


	public void setDetails(List<FuncDetail> details) {
		this.details = details;
	}


	public int getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}


	
}
