package net.tfedu.zhl.helper.excel.module;

import java.util.List;
import java.util.Map;

/**
 * 每一页的内容
 * @author wangwr
 *
 */
public class SheetData {
	
	
	/**
	 * 名称
	 */
	String name ; 
	
	
	
	/**
	 * 本页中的单元格属性
	 */
	List<CellColumnInfo> columns ;
	
	
	/**
	 * 页面数据的集合
	 * map的key 和 CellColumnInfo的 column_key大小写一致
	 *
	 */
	List<Map<String,Object>> data ;


	
	/**
	 * 是否一行行的展示  默认为true
	 */
	public  Boolean LineLayout = true ;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<CellColumnInfo> getColumns() {
		return columns;
	}


	public void setColumns(List<CellColumnInfo> columns) {
		this.columns = columns;
	}


	public List<Map<String, Object>> getData() {
		return data;
	}


	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}


	public Boolean getLineLayout() {
		return null==LineLayout?true:LineLayout;
	}


	public void setLineLayout(Boolean lineLayout) {
		LineLayout = lineLayout;
	}
	

}
