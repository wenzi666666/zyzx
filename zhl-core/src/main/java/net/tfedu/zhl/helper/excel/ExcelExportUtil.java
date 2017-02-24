package net.tfedu.zhl.helper.excel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import net.tfedu.zhl.helper.excel.module.CellColumnInfo;
import net.tfedu.zhl.helper.excel.module.ExcelData;
import net.tfedu.zhl.helper.excel.module.SheetData;

/**
 * 导出工具类
 * @author wangwr
 *
 */
public class ExcelExportUtil {

	
	
	/**
	 * 配置文件中，excel对应的ModelAndView 的bean 的id
	 */
	private static final String ExcelExportView_ID = "ExcelRevenueSummary";
	
	
	
	
	/**
	 * 检查后缀
	 * @param name
	 * @return
	 */
	public static String getExcelName(String name){
		if(StringUtils.isEmpty(name)){
			return  null ;
		}
		if(name.contains(".xls") || name.contains(".xlsx")){
			return name ;
		}
		
		return name+".xls" ;
	}
	
	
	/**
	 * 返回导出excel的视图
	 * @param excelName
	 * @param sheetName
	 * @param c
	 * @param list
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws ParseException
	 */
	public static <T> ModelAndView getView(String excelName,String sheetName,Class<?> c,List<T> list ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ParseException{
		//检查后缀
		excelName = getExcelName(excelName);
		
		@SuppressWarnings("unused")
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		List<SheetData> sheets = new ArrayList<SheetData>();
		SheetData s= new SheetData();
		s.setName(sheetName);
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {
			Object o = (Object) iterator.next();
			@SuppressWarnings("unchecked")
			Map<String,Object> map = BeanUtils.describe(o);
			dataList.add(map);
		}
		
		
		Field[]  field = c.getDeclaredFields();
		List<CellColumnInfo> columns = null;
		CellColumnInfo info = null;
		if(null!=field && field.length>0){
			columns = new ArrayList<CellColumnInfo>();
			for (int i = 0; i < field.length; i++) {
				info = new CellColumnInfo();
				
				String name = field[i].getName();
				Annotation[] as = field[i].getDeclaredAnnotations();
				
				//指明字段名
				info.setColumn_key(name);
				if(as !=null && as.length>0){
					name = as[0].toString();
				}
				//字段的説明
				info.setColumn_name(name);
			}
		}
		s.setColumns(columns);
		s.setData(dataList);
		sheets.add(s);

		ExcelData data =  new ExcelData();
		data.setFileName(excelName);
		data.setSheets(sheets);
		
		return  new ModelAndView(ExcelExportView_ID, ExcelExportView.DATA_NAME, data);
		
	}
	
}



