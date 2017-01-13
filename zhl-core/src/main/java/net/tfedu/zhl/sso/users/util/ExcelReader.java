/*package net.tfedu.zhl.sso.users.util;



import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
*//**
 * 从Excel 中读取注册信息  （POI）
 * @author wangwr
 *
 *//*
public class ExcelReader {
	*//**
	 * 从Excel 中获取RegisterQuery 列表
	 * @param excelFile
	 * @return
	 *//*
	
	@SuppressWarnings("deprecation")
	public List<RegisterQuery> getDocument(File excelFile) {
		StringBuffer content = new StringBuffer();
		//以下均为对应列号
		short cardId = 0;      //卡号
		short CardPwd = 1;     //卡密码
		short userName = 2;    //用户名
		short userPwd = 3;	   //用户密码
		short trueName = 4;	   //真实姓名         
		short sex = 5;         //性别
		short schoolName = 6;  //学校名称    
		short roleId = 7;      //角色
		short subject = 8;     //教师:学科,学生:班级
		short termId = 9 ;     //学段
		short gradeId = 10 ;
		
		List<RegisterQuery> list = new ArrayList<RegisterQuery>();
		try {
			FileInputStream fis = new FileInputStream(excelFile);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);// 创建对Excel工作簿文件的引用
			for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
				if (null != workbook.getSheetAt(numSheets)) {
					HSSFSheet aSheet = workbook.getSheetAt(numSheets);// 获得一个sheet
					for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
						if (null != aSheet.getRow(rowNumOfSheet)) {
							RegisterQuery query = new RegisterQuery();
							HSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一个行
							
								boolean er = false;
								String message = "";
								
								//卡号判断
								if (null != aRow.getCell(cardId) && !"".equals(getCellString(aRow.getCell(cardId)))) {									
									query.setCardId(getCellString(aRow.getCell(cardId)));
								}else{
									break;
								}
								//卡号密码判断
								if (null != aRow.getCell(CardPwd) && !"".equals(getCellString(aRow.getCell(CardPwd)))) {
									query.setCardpassword(getCellString(aRow.getCell(CardPwd)));
								} 
								//用户名判断
								if (null != aRow.getCell(userName)  && !"".equals(getCellString(aRow.getCell(userName)))) {
									query.setUserId(getCellString(aRow.getCell(userName)));
								} else {
									er = true;
									message += "未填写用户名;";
								}
								//用户密码判断
								if (aRow.getCell(userPwd) != null && !"".equals(getCellString(aRow.getCell(userPwd)))) {	
									query.setPassword(getCellString(aRow.getCell(userPwd)));
								} else {
									er = true;
									message += "未填写用户密码;";
								}
								//真实姓名判断
								if (aRow.getCell(trueName) != null && !"".equals(getCellString(aRow.getCell(trueName)))) {
									query.setTruename(getCellString(aRow.getCell(trueName)));
								} else {
									er = true;
									message += "未填写真实姓名;";
								}
								//性别判断
								if (aRow.getCell(sex) != null && !"".equals(getCellString(aRow.getCell(sex)))) {
									query.setSex(getCellString(aRow.getCell(sex)));
								} else {
									er = true;
									message += "未填写性别;";
								}
								//学校判断
								if (aRow.getCell(schoolName) != null && !"".equals(getCellString(aRow.getCell(schoolName)))) {
									query.setSchoolCode(getCellString(aRow.getCell(schoolName)));
								} else {
									er = true;
									message += "未填写学校名称;";
								}
								//角色判断
								if(aRow.getCell(roleId)!= null && !"".equals(getCellString(aRow.getCell(roleId)))){
									query.setRoleName(getCellString(aRow.getCell(roleId)));
								}else{
									er = true;
									message += "未填写角色;";
								}	
								if (aRow.getCell(subject) != null || "".equals(getCellString(aRow.getCell(subject)))) {
									query.setCourse(getCellString(aRow.getCell(subject)));
								} else {
									er = true;
									message += "未填写教师学科(或学生班级);";
								}
								//学段判断								
								if(aRow.getCell(termId)!=null && !"".equals(getCellString(aRow.getCell(termId)))){
									query.setTermId(getCellString(aRow.getCell(termId)));
								}else{
									er = true;
									message += "未填写学段;";
								}
								
								String productName = InitializingDataSource.mapValue.get("productName");
								int product = productName!=null&&!"".equals(productName)?Integer.parseInt(productName):0;

								if(product!=2 && "2".equalsIgnoreCase(getCellString(aRow.getCell(roleId)))){
									if(aRow.getCell(gradeId)!=null && !"".equals(getCellString(aRow.getCell(gradeId)))){
										query.setClassName(getCellString(aRow.getCell(gradeId)));
									}else{
										er = true;
										message += "未填写班级;";
									}
								}
								
								query.setError(er);
								query.setErrorMessage(message);
							list.add(query);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	*//**
	 * 获取cell数据
	 * @param cell
	 * @return
	 *//*
	private String getCellString(HSSFCell cell) {
		if(cell==null){
			return "";
		}
		int type = cell.getCellType();
		String cellStr = "";
		switch (type) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			Double temp = cell.getNumericCellValue();
			Long tempLong = temp.longValue();
			cellStr = String.valueOf(tempLong);
			break;
		case HSSFCell.CELL_TYPE_STRING:
			cellStr = cell.getRichStringCellValue().toString();
			break;
		default:
			cellStr = "";
			break;
		}
		
		return cellStr;
	}
	
	public static void main(String []args){
		String filePath = "E:/批量注册.xls";
		File file = new File(filePath);
		ExcelReader reader = new ExcelReader();
		
		List<RegisterQuery> list = reader.getDocument(file);
		
		
		for(int i=0;i<list.size();i++){
			
			RegisterQuery q = (RegisterQuery)list.get(i);
			System.out.println(q.getCardId() +"--------------"+q.getCardpassword());
			
		}
	}	
}
*/