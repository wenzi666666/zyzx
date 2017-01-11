package net.tfedu.zhl.cloud.back.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.tfedu.zhl.core.controller.BaseController;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.excel.ExcelExportUtil;
import net.tfedu.zhl.sso.users.entity.SBatch;
import net.tfedu.zhl.sso.users.entity.SCard;
import net.tfedu.zhl.sso.users.service.SCardService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
 * 前台用户注册之卡号管理
 * 
 * @author wangwr
 * @date 2017年1月9日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
@RequestMapping(value = "/*BackAPI/v1.0/account")
public class AccountCardController extends BaseController<SCard> {

	@Resource
	SCardService sCardService;

	/**
	 * 增加卡号
	 * 
	 * @param card
	 *            用於收集卡號注冊的信息
	 * @param bindingResult
	 *            校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addCard", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addSCard(@Valid SCard card, BindingResult bindingResult,HttpServletRequest request) throws Exception {

		// 如果有异常
		if (bindingResult.hasErrors()) {
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}

		card.setFlag(false);
		card.setCreateman(ControllerHelper.getCurrentUserId(request).toString());
		
		
		// 根据注册信息，生成批次号，生成密码

		return sCardService.createCardByCardModule(card);
	}

	/**
	 * 修改卡号
	 * 
	 * @param card
	 * @param bindingResult
	 *            校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCard", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateSCard(@Valid SCard card, BindingResult bindingResult) throws Exception {

		// 如果有异常
		if (bindingResult.hasErrors()) {
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}

		return super.update(card);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delCard", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delSCard(Long id) throws Exception {

		SCard p = new SCard();
		p.setId(id);
		p.setFlag(true);

		return super.update(p);
	}

	/**
	 * 分页获取
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pageCard", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageScard(int pageNum, int pageSize, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 角色id 为 0,1,2
		Long roleid = ControllerHelper.getOptionalLongParameter(request, "roleid");
		// 卡状态 未注册 0 已经注册 1 全部为-1
		Integer state = ControllerHelper.getOptionalIntegerParameter(request, "state");
		// 卡号
		String cardnumber = ControllerHelper.getOptionalParameter(request, "cardnumber");
		// 卡号
		String batchname = ControllerHelper.getOptionalParameter(request, "batchname");
		// 省
		Long provinceid = ControllerHelper.getOptionalLongParameter(request, "provinceid");
		// 市
		Long cityid = ControllerHelper.getOptionalLongParameter(request, "cityid");
		// 区
		Long districtid = ControllerHelper.getOptionalLongParameter(request, "districtid");
		// 校
		Long schoolid = ControllerHelper.getOptionalLongParameter(request, "schoolid");

		Example example = new Example(SCard.class);
		Criteria cr = example.createCriteria();
		
		if(null!=roleid && roleid>0){
			cr.andCondition(" roleid =", roleid);
		}
		
		if(null!=state && roleid>-1){
			cr.andCondition(" state =", state);
		}
		
		if(null!=provinceid && provinceid>0){
			cr.andCondition(" provinceid =", provinceid);
		}
		
		if(null!=cityid && cityid>0){
			cr.andCondition(" cityid =", cityid);
		}
		
		if(null!=districtid && districtid>0){
			cr.andCondition(" dispositionid =", districtid);
		}
		
		if(null!=schoolid && schoolid>0){
			cr.andCondition(" positionid =", schoolid);
		}
		
		if(StringUtils.isNotEmpty(cardnumber)){
			cr.andCondition(" cardnumber =", cardnumber);
		}

		if(StringUtils.isNotEmpty(batchname)){
			SBatch batch = sCardService.getBatchByName(batchname);
			cr.andCondition(" batchid= ",batch.getId());
		}
		
		
		return super.getPage(example, pageNum, pageSize);
	}
	
	
	/**
	 * 账号延期
	 * @param ids
	 * @param months  延期月份
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delayCard", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delayCard(String[] ids,int months,HttpServletRequest request) throws Exception {
		
		if(ids!=null && ids.length>0 && months>0){
			SCard card =  null;
			for (int i = 0; i < ids.length; i++) {
				card =  (SCard)sCardService.get(Long.parseLong(ids[i])).getData();
				int expnum =  card.getExpNum();
				Date endtime =card.getEndtime();
				
				//延期
				expnum += months;
				Calendar c = Calendar.getInstance();
				c.setTime(endtime);
				c.add(Calendar.MONTH, months);
				endtime = c.getTime();
				
				card.setEndtime(endtime);
				card.setExpNum(expnum);
				//更新數據庫
				super.update(card);
				
			}
			
			return ResultJSON.getSuccess("");
		}else{
			throw new ParamsException();
		}
	}
	
	
	/**
	 * 重新激活卡号
	 * @param ids
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/activeCard", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON activeCard(String[] ids,HttpServletRequest request) throws Exception {
	
		if(ids!=null && ids.length>0){
			SCard card =  null;
			for (int i = 0; i < ids.length; i++) {
				card =  (SCard)sCardService.get(Long.parseLong(ids[i])).getData();
			
				card.setState(0);
				//更新數據庫
				super.update(card);
				
			}
			
			return ResultJSON.getSuccess("");
		}else{
			throw new ParamsException();
		}
		
	}
	
	

	
	/**
	 * 文件(批量注册模板文件)上传
	 * @param file        文件对象
	 * @param request
	 * @param response
	 * @return 返回文件保存的相对路径
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadCard", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON uploadFile(@RequestParam(value = "file", required = false)MultipartFile file
				, HttpServletRequest request,HttpServletResponse response)throws Exception {
		
		if(file==null || file.getSize()==0){
			throw new CustomException("未指定上传文件或上传文件大小为0");
		}

		
		Calendar c = Calendar.getInstance();
		//相对路径
		String _path = File.separatorChar+"upload"+File.separatorChar+"excel"+File.separatorChar
				+c.get(Calendar.YEAR)+File.separatorChar+c.get(Calendar.MONTH)+File.separatorChar;
		
		//文件名
		String filename = c.getTimeInMillis()+"_"+ file.getOriginalFilename();
		
		
		String finalPath = _path+filename;


		File targetFile = new File(request.getServletContext().getRealPath("/")+finalPath);  
		if(!targetFile.exists()){
			if(!targetFile.getParentFile().exists()){
				targetFile.getParentFile().mkdirs();
			}
			targetFile.createNewFile();
		}
		
		
		file.transferTo(targetFile);
		
		
		return ResultJSON.getSuccess(finalPath) ;
	}
	
	
	
	
	
	
	
	/**
	 * 批量注冊
	 * registerCardBatch
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registerCardBatch", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON registerCardBatch(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return null ;
	}
	
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportCard", method = RequestMethod.GET)
	public Object exportCard(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		// 角色id 为 0,1,2
		Long roleid = ControllerHelper.getOptionalLongParameter(request, "roleid");
		// 卡状态 未注册 0 已经注册 1 全部为-1
		Integer state = ControllerHelper.getOptionalIntegerParameter(request, "state");
		// 卡号
		String cardnumber = ControllerHelper.getOptionalParameter(request, "cardnumber");
		// 卡号
		String batchname = ControllerHelper.getOptionalParameter(request, "batchname");
		// 省
		Long provinceid = ControllerHelper.getOptionalLongParameter(request, "provinceid");
		// 市
		Long cityid = ControllerHelper.getOptionalLongParameter(request, "cityid");
		// 区
		Long districtid = ControllerHelper.getOptionalLongParameter(request, "districtid");
		// 校
		Long schoolid = ControllerHelper.getOptionalLongParameter(request, "schoolid");

		Example example = new Example(SCard.class);
		Criteria cr = example.createCriteria();
		
		if(null!=roleid && roleid>0){
			cr.andCondition(" roleid =", roleid);
		}
		
		if(null!=state && roleid>-1){
			cr.andCondition(" state =", state);
		}
		
		if(null!=provinceid && provinceid>0){
			cr.andCondition(" provinceid =", provinceid);
		}
		
		if(null!=cityid && cityid>0){
			cr.andCondition(" cityid =", cityid);
		}
		
		if(null!=districtid && districtid>0){
			cr.andCondition(" dispositionid =", districtid);
		}
		
		if(null!=schoolid && schoolid>0){
			cr.andCondition(" positionid =", schoolid);
		}
		
		if(StringUtils.isNotEmpty(cardnumber)){
			cr.andCondition(" cardnumber =", cardnumber);
		}

		if(StringUtils.isNotEmpty(batchname)){
			SBatch batch = sCardService.getBatchByName(batchname);
			cr.andCondition(" batchid= ",batch.getId());
		}
		
		
		List<SCard> result = sCardService.queryCardByExampe(example);
		
		
		
		return ExcelExportUtil.getView("卡号查询",String.valueOf(Calendar.getInstance().getTime().getTime()), 
					SCard.class, result);
		
	}
	

}
