package net.tfedu.zhl.helper.excel.module;

import java.util.List;

/**
 * 导出的excel的信息数据
 * @author wangwr
 *
 */
public class ExcelData {

	/**
	 * 导出文件名
	 */
	public String fileName ;
	
	
	/**
	 * 页
	 */
	public List<SheetData> sheets;


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public List<SheetData> getSheets() {
		return sheets;
	}


	public void setSheets(List<SheetData> sheets) {
		this.sheets = sheets;
	}
	
	
}
