package net.tfedu.zhl.cloud.resource.back.appuserpoolconfig.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;
import net.tfedu.zhl.cloud.resource.poolconfig.module.AppUserPoolConfigIndentInfo;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.sso.term.entity.JTerm;

/**
 	
            读取excel 文件的单个sheet 
    并将数据解析成 AppUserConfigIndentInfo(物权订货单)
    @author wangwr
    @date 2017年2月24日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司
    
    
     
*/
public class ExcelUtilToParseAppUserConfigIndentInfo {
	
	
	/**
	 * 读取excel，获取订货单信息
	 * @param excelPath
	 * @param appId 
	 * @param allPools 
	 * @param allTerms 
	 * @return
	 *  返回订货信息(尚缺少appid)
	 * @throws ParamsException 
	 * @throws IOException 
	 * @throws BiffException 
	 * 
	 *  处理excel的表头：
     	序号	账号	学段	教学素材库	动画教具库	名师微课库	教学案例库	多媒体教辅库	理化生实验室	情景英语	年限

		说明：
	    1、本模板的所有sheet和属性字段的名称和顺序都是固定的，不可改变
		2、学段的取值:小学、初中、高中
		3、教学素材库等库的取值:1（购买）、0（不购买）
		4、年限的取值:根据用户的实际购买情况取正整数

	 * 
	 */
	public static List<AppUserPoolConfigIndentInfo> parse(String excelPath, String appId,
					List<JTerm> allTerms, List<ResPool> allPools) throws ParamsException, BiffException, IOException{
		
		File file = new File(excelPath);

		if (!file.exists() || file.isDirectory()) {
			throw new ParamsException("文件不存在:"+excelPath);
		}
		//准备学段，库map
		Map<String,Long> termMap = new HashMap<String,Long>();
		Map<String,Long> poolMap = new HashMap<String,Long>();
		
		for (ResPool resPool : allPools) {
			poolMap.put(resPool.getName(), resPool.getId());
		}
		
		for (JTerm term : allTerms) {
			termMap.put(term.getName(), term.getId());
		}
		
		

		//准备读取excel
		List<AppUserPoolConfigIndentInfo> list = new ArrayList<AppUserPoolConfigIndentInfo>();
		AppUserPoolConfigIndentInfo info = null;
		List<Long> pools =  null;
		int rowOffest = 1 ;//第一行开始读。第0行为属性
		int rowNums = 0 ; //属性的个数

		Workbook  workbook = Workbook.getWorkbook(file);
		Sheet[] sheets = workbook.getSheets();
		//只读取第一个sheet
		Sheet sheet = sheets[0];
		
		rowNums = sheet.getRows();
		for (rowOffest = 1; rowOffest < rowNums; rowOffest++) {
			
			
			Cell[] aRow =  sheet.getRow(rowOffest);
			
			//如果用戶名為空   跳過
			if(StringUtils.isEmpty(aRow[1].getContents())){
				continue;
			}
			
			
			
			info = new AppUserPoolConfigIndentInfo();
			
			info.setAppId(appId);
			info.setUserName(aRow[1].getContents().trim());
			info.setSubjectId(0l);
			info.setTermId(termMap.get(aRow[2].getContents().trim()));
			
			pools = new ArrayList<Long>();
			if("1".equals(aRow[3].getContents().trim())){
				pools.add(poolMap.get((sheet.getRow(0))[3].getContents().trim()));
			}
			
			if("1".equals(aRow[4].getContents().trim())){
				pools.add(poolMap.get((sheet.getRow(0))[4].getContents().trim()));
			}
			
			if("1".equals(aRow[5].getContents().trim())){
				pools.add(poolMap.get((sheet.getRow(0))[5].getContents().trim()));
			}
			
			if("1".equals(aRow[6].getContents().trim())){
				pools.add(poolMap.get((sheet.getRow(0))[6].getContents().trim()));
			}
			
			if("1".equals(aRow[7].getContents().trim())){
				pools.add(poolMap.get((sheet.getRow(0))[7].getContents().trim()));
			}
			
			if("1".equals(aRow[8].getContents().trim())){
				pools.add(poolMap.get((sheet.getRow(0))[8].getContents().trim()));
			}
			
			if("1".equals(aRow[9].getContents().trim())){
				pools.add(poolMap.get((sheet.getRow(0))[9].getContents().trim()));
			}
			
			info.setPools(pools);
			
			int month = 0 ;
			
			if(StringUtils.isNotEmpty(aRow[10].getContents().trim())){
				try {
					month = Integer.parseInt(aRow[10].getContents().trim());
				} catch (Exception e) {}
			}
			
			info.setMonth(month);
			list.add(info);
		}
		
		
		return list;
	}
	
	
	

}
