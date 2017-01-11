package net.tfedu.zhl.helper.excel.module;


/**
 * 单元格的信息
 * @author wangwr
 *
 */
public class CellColumnInfo {
	
	
	/**
	 * 获取单元格的内容时使用的key
	 */
	String column_key ;
	
	
	/**
	 * 单元格的名称（描述）
	 */
	String column_name ;


	public String getColumn_key() {
		return column_key;
	}


	public void setColumn_key(String column_key) {
		this.column_key = column_key;
	}


	public String getColumn_name() {
		return column_name;
	}


	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	
}
