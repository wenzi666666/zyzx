package net.tfedu.zhl.sso.users.util;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
/**
 * 从Excel 中读取注册信息  JXL
 * @author wangwr
 *
 */
public class ExcelReaderJXL {
	/**
	 * 从Excel 中获取RegisterQuery 列表
	 * @param excelFile
	 * @return
	 */
	
	public List<CardExcelForm> getDocument(File excelFile) {
		//以下均为对应列号
		short cardId = 0;      //卡号
		short CardPwd = 1;     //卡密码
		short userName = 2;    //用户名
		short userPwd = 3;	   //用户密码
		short trueName = 4;	   //真实姓名         
		short sex = 5;         //性别
		short schoolName = 6;  //学校名称    
		short subject = 7;     //教师:学科,学生:班级
		short termId = 8 ;     //学段
		
		List<CardExcelForm> list = new ArrayList<CardExcelForm>();
		try {
			
			Workbook  workbook = Workbook.getWorkbook(excelFile);
			
			Sheet[] sheets = workbook.getSheets();
			
			int rowOffest = 1 ;//第一行开始读。第0行为属性
			int rowNums = 0 ;
			CardExcelForm query = null;
			
			for (int i = 0; i < sheets.length; i++) {
				Sheet sheet = sheets[i];
				rowNums = sheet.getColumns();
				for (rowOffest = 1; rowOffest < rowNums; rowOffest++) {
					Cell[] aRow =  sheet.getRow(rowOffest);
					if(null!=aRow && aRow.length>0){
						query = new CardExcelForm();
						
						boolean er = false;  //是否错误
						String message = "";  //错误信息
						
						
						
						//卡号判断
						if (null != aRow[cardId] && !"".equals(getCellString(aRow[cardId]))) {									
							query.setCardNumber(getCellString(aRow[cardId]));
						}else{
							break;
						}
						//卡号密码判断
						if (null != aRow[CardPwd] && !"".equals(getCellString(aRow[CardPwd]))) {
							query.setCardPwd(getCellString(aRow[CardPwd]));
						} 
						//用户名判断
						if (null != aRow[userName]  && !"".equals(getCellString(aRow[userName]))) {
							query.setUserName(getCellString(aRow[userName]));
						} else {
							er = true;
							message += "未填写用户名;";
						}
						//用户密码判断
						if (aRow[userPwd] != null && !"".equals(getCellString(aRow[userPwd]))) {	
							query.setUserPwd(getCellString(aRow[userPwd]));
						} else {
							er = true;
							message += "未填写用户密码;";
						}
						//真实姓名判断
						if (aRow[trueName] != null && !"".equals(getCellString(aRow[trueName]))) {
							query.setTrueName(getCellString(aRow[trueName]));
						} else {
							er = true;
							message += "未填写真实姓名;";
						}
						//性别判断
						if (aRow[sex] != null && !"".equals(getCellString(aRow[sex]))) {
							query.setSexName(getCellString(aRow[sex]));
						} else {
							er = true;
							message += "未填写性别;";
						}
						//学校判断
						if (aRow[schoolName] != null && !"".equals(getCellString(aRow[schoolName]))) {
							query.setSchoolName(getCellString(aRow[schoolName]));
						} else {
							er = true;
							message += "未填写学校名称;";
						}
						
						if (aRow[subject] != null || "".equals(getCellString(aRow[subject]))) {
							query.setSubjectName(getCellString(aRow[subject]));
						} else {
							er = true;
							message += "未填写教师学科";
						}
						//学段判断								
						if(aRow[termId]!=null && !"".equals(getCellString(aRow[termId]))){
							query.setTermName(getCellString(aRow[termId]));
						}else{
							er = true;
							message += "未填写学段;";
						}
						
						//String productName = "";
						//int product = productName!=null&&!"".equals(productName)?Integer.parseInt(productName):0;

						
						
						query.setHasError(er);
						query.setMessage(message);
						list.add(query);
						
						
						
					}
				}
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取cell数据
	 * @param cell
	 * @return
	 */
	private String getCellString(Cell cell) {
		if(cell==null){
			return "";
		}
		
	
		
//		CellType type = cell.getType();
		
		String cellStr = cell.getContents();
		

		return cellStr ;
	}
	
	public static void main(String []args){
//		String filePath = "E:/批量注册.xls";
//		File file = new File(filePath);
//		ExcelReaderJXL reader = new ExcelReaderJXL();
//		
//		List<CardExcelForm> list = reader.getDocument(file);
//		
//		
//		
//		for(int i=0;i<list.size();i++){
//			
//			CardExcelForm q = (CardExcelForm)list.get(i);
//			
//		}
	}	
}
