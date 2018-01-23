package net.tfedu.zhl.helper.excel;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.tfedu.zhl.helper.excel.module.CellColumnInfo;
import net.tfedu.zhl.helper.excel.module.ExcelData;
import net.tfedu.zhl.helper.excel.module.SheetData;



/**
 * 通用导出excel工具类
 * @author wangwr
 *
 */
@SuppressWarnings("deprecation")
public class ExcelExportView extends AbstractJExcelView {
	
	/**
	 * 指定
	 */
	public static final String DATA_NAME = "ExcelData";
	
	
	
	
	
	

	@Override
	protected void buildExcelDocument(Map<String, Object> model, WritableWorkbook book, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ExcelData data = (ExcelData)model.get(DATA_NAME);
		
		String fileName =data.getFileName();
		List<SheetData> ss = data.getSheets();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		for (Iterator<SheetData> iterator = ss.iterator(); iterator.hasNext();) {
			//获取单页信息
			SheetData sheetData = (SheetData) iterator.next();
			
			//一行行的展示  默认为true
			Boolean lineLayout = sheetData.getLineLayout();
			
			String sheet_name = sheetData.getName();
			List<Map<String,Object>> dataList = sheetData.getData();
			List<CellColumnInfo> columns  = sheetData.getColumns();
			//创建单页
			WritableSheet sheet = book.createSheet(sheet_name, 0);
			//插入table标题
			for (int i = 0; i < columns.size(); i++) {
				
				if(lineLayout){
					sheet.addCell(new Label(i, 0, columns.get(i).getColumn_name()));
				}else{
					sheet.addCell(new Label(0, i, columns.get(i).getColumn_name()));
				}
			}
			//插入table数据
			for (int i = 0; i < dataList.size(); i++) {
				Map<String,Object> map = dataList.get(i); 
				for (int j = 0; j < columns.size(); j++) {
					String key = columns.get(j).getColumn_key();
					Object val = (Object)map.get(key);
					String viewStr = "";
					if(null !=val && val instanceof Date){
						viewStr = format.format(val);
					}else if(null !=val){
						viewStr = val.toString();
					}
					if(lineLayout){
						sheet.addCell(new Label( j,i+1, viewStr));
					}else{
						sheet.addCell(new Label(i+1,j, viewStr));
					}
				}
			}
		}
		
		fileName = encodeFilename(fileName, request);//处理中文文件名  
        response.setContentType("application/vnd.ms-excel");     
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);     

	}
	
	
	
	
	
	/**  
     * 设置下载文件中文件的名称  
     *   
     * @param filename  
     * @param request  
     * @return  
     */    
    public static String encodeFilename(String filename, HttpServletRequest request) {    
      /**  
       * 获取客户端浏览器和操作系统信息  
       * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
       * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
       */    
      String agent = request.getHeader("USER-AGENT");    
      try {    
        if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
          String newFileName = URLEncoder.encode(filename, "UTF-8");    
          newFileName = StringUtils.replace(newFileName, "+", "%20");    
          if (newFileName.length() > 150) {    
            newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
            newFileName = StringUtils.replace(newFileName, " ", "%20");    
          }    
          return newFileName;    
        }    
        if ((agent != null) && (-1 != agent.indexOf("Mozilla")))    
          return URLEncoder.encode(filename, "UTF-8");   //MimeUtility.encodeText(filename, "UTF-8", "B");    
      
        return filename;    
      } catch (Exception ex) {    
        return filename;    
      }    
    }   
}
