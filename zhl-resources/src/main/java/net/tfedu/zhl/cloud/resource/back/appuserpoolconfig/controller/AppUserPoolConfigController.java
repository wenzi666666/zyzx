package net.tfedu.zhl.cloud.resource.back.appuserpoolconfig.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.resource.back.appuserpoolconfig.util.ExcelUtilToParseAppUserConfigIndentInfo;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResPoolService;
import net.tfedu.zhl.cloud.resource.poolconfig.module.AppUserPoolConfigIndentInfo;
import net.tfedu.zhl.cloud.resource.poolconfig.module.AppUserPoolConfigRecord;
import net.tfedu.zhl.cloud.resource.poolconfig.service.SAppUserPoolConfigService;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.term.entity.JTerm;
import net.tfedu.zhl.sso.term.service.JTermService;


/**
 * 第三方用户和资源库的物权管理（云洲）
copyRight@ 同方知好乐教育科技(北京)有限公司
@author　wangwr
@date 　
@desc 　　AppUserPoolConfigController.java
*/
@Controller
@RequestMapping(value="/resBackAPI/appUserPoolConfig")
public class AppUserPoolConfigController {
	/** 第三方用户和资源物权管理的service */
	@Resource
	SAppUserPoolConfigService appUserPoolConfigService;
	
	@Resource
	ResPoolService resPoolService;
	
	@Resource
	JTermService jTermService;
	

	/**
	 * 分页查询物权分配的历史记录
	 * 
	 * @param page
	 *            页码
	 * @param perPage
	 *            每页条目数
	 * @param year
	 *            开放年份
	 * @param termId
	 *            学段Id，全部时为0
	 * @param poolId
	 *            资源库Id，全部时为0
	 * @param userName
	 *            第三方用户主键（可选）
	 * @param appId
	 *            第三方app主键
	 * @throws Exception 
	 */
	@RequestMapping(value="/v1.0/pageQuery",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageQueryAppUserPoolConfig(Integer page, Integer perPage, Integer year, Long termId,Long poolId,
			String userName, String appId) throws Exception {
		
		Map<Long,String> pools = new HashMap<Long,String>();
		List<ResPool> ls = resPoolService.getAllPoolsWithAppend();
		for (Iterator<ResPool> iterator = ls.iterator(); iterator.hasNext();) {
			ResPool resPool = (ResPool) iterator.next();
			pools.put(resPool.getId(), resPool.getName());
		}
		
		ResultJSON result =  appUserPoolConfigService.pageQueryAppUserPoolConfig(page, perPage, year, termId,poolId, userName, appId);
		
		@SuppressWarnings("unchecked")
		PageInfo<AppUserPoolConfigRecord> _page = (PageInfo<AppUserPoolConfigRecord>)result.getData();
		
		List<AppUserPoolConfigRecord> list =  _page.getList();
		AppUserPoolConfigRecord appUserPoolConfigRecord = null;
		for (Iterator<AppUserPoolConfigRecord> iterator = list.iterator(); iterator.hasNext();) {
			appUserPoolConfigRecord = (AppUserPoolConfigRecord) iterator.next();
			
			appUserPoolConfigRecord.setPoolName(pools.get(appUserPoolConfigRecord.getPoolId()));
		}
		
		return result ;
	}

	/**
	 * 更新用户的资源库授权
     * @param recordId 
     * 			      需要更新的记录的主键
	 * @param termId
	 *            学段id
	 * @param subjectId
	 *            学科id
	 * @param poolId
	 *            资源库Id
	 * @param appId
	 *            第三方应用主键
	 * @param userName
	 *            第三方用户主键
	 * @param months
	 *            月份数
	 * @throws CustomException 
	 */
	@RequestMapping(value="/v1.0/updateAppUserPoolConfig",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateAppUserPoolConfig(Long recordId,Long termId,Long subjectId, Integer poolId, String appId, String userName,
			Integer months) throws CustomException {
		return appUserPoolConfigService.updateAppUserPoolConfig(termId,subjectId, poolId, appId, userName, months, recordId);
	}

	/**
	 * 上传订单excel 返回SheetData
	 * 
	 * @param file
	 *            上传文件对象
	 * @param request
	 * @throws CustomException 
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@ResponseBody
	@RequestMapping(value="/v1.0/uploadExcelFile",method=RequestMethod.POST)
	public ResultJSON uploadExcelFile(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) throws CustomException, Exception {
		
		String appId = ControllerHelper.getParameter(request, "appId");
		
		
		if (file == null || file.getSize() == 0) {
			throw new CustomException("未指定上传文件或上传文件大小为0");
		}

		Calendar c = Calendar.getInstance();
		// 相对路径
		String _path = File.separatorChar + "upload" + File.separatorChar + "excelAppUserPoolConfig" + File.separatorChar
				+ c.get(Calendar.YEAR) + File.separatorChar + c.get(Calendar.MONTH) + File.separatorChar;

		// 文件名
		String filename = c.getTimeInMillis() + "_" + file.getOriginalFilename();

		String finalPath = _path + filename;

		File targetFile = new File(request.getServletContext().getRealPath("/") + finalPath);
		if (!targetFile.exists()) {
			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdirs();
			}
			targetFile.createNewFile();
		}

		file.transferTo(targetFile);
		
		return  parseExcel(appId, targetFile.getAbsolutePath());
	}
	
	
	/**
	 * 解析excel
	 * （为方便单元测试）
	 * @param appId
	 * @param path
	 * @return
	 * @throws Exception
	 */
	ResultJSON parseExcel(String appId,String path) throws Exception{
		//读取excel，获取订货单信息
		@SuppressWarnings("unchecked")
		List<JTerm> allTerms =  (List<JTerm>)(jTermService.queryAllTerm().getData());
		List<ResPool> allPools = resPoolService.getAllPoolsWithAppend();
		
		return ResultJSON.getSuccess(ExcelUtilToParseAppUserConfigIndentInfo
				.parse(path,appId,allTerms,allPools));
		
	}

	/**
	 * 批量增加或修改用户的资源库物权
	 * 
	 * 
	 * @param request
	 * @throws CustomException 
	 */
	@RequestMapping(value="/v1.0/addAppUserPoolConfigBatch",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON addAppUserPoolConfigBatch(HttpServletRequest request) throws CustomException {
		
		//前段传递json的字符串 
		String indentInfo = request.getParameter("indentInfo");
		
		List<AppUserPoolConfigIndentInfo> list=  JSONObject.parseArray(indentInfo, AppUserPoolConfigIndentInfo.class);
		
		return appUserPoolConfigService.addAppUserPoolConfigBatch(list);
	}
	

   /** 删除用户的资源库授权
    * 
    * @param recordId 需要更新的记录的主键 */
   @RequestMapping(value="/v1.0/delAppUserPoolConfig",method=RequestMethod.POST) 
   @ResponseBody
   public ResultJSON delAppUserPoolConfig(Long recordId) throws CustomException{
      return appUserPoolConfigService.delAppUserPoolConfig(recordId);
   }
   
   /**
    * 获取全部的资源库
 * @throws Exception 
    * */
   @RequestMapping(value="/v1.0/getAllPools",method=RequestMethod.GET)
   @ResponseBody
   public ResultJSON getAllPools() throws Exception{
	   return ResultJSON.getSuccess(resPoolService.getAllPoolsWithAppend());
   }
   
   
   

}