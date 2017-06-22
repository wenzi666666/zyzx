package net.tfedu.zhl.cloud.back.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.core.controller.BaseController;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.excel.ExcelExportUtil;
import net.tfedu.zhl.sso.back.user.entity.SBackUserScope;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;
import net.tfedu.zhl.sso.users.util.CardExcelForm;
import net.tfedu.zhl.userlayer.user.entity.UserEditForm;
import net.tfedu.zhl.userlayer.user.entity.UserQueryForm;
import net.tfedu.zhl.userlayer.user.entity.UserQueryResult;
import net.tfedu.zhl.userlayer.user.service.JUserService;

/**
 * 
 * 前台用户注册之注册管理
 * 
 * @author wangwr
 * @date 2017年1月9日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
@RequestMapping(value = "/*BackAPI/v1.0/account")
public class AccountRegisterController extends BaseController<SRegister> {


	@Resource
	RegisterService registerService;
	@Resource
	JUserService userService;
	


	
	
	/**
	 * 用户注册
	 * @param form       前台用户注册  注冊時可以传递cardnumber，cardpwd 或 cardid
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addSRegister(@Valid CardExcelForm form, BindingResult bindingResult, HttpServletRequest request)
			throws Exception {

		// 如果有异常
		if (bindingResult.hasErrors()) {
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		

		List<CardExcelForm> list = new ArrayList<CardExcelForm>();
		list.add(form);

		return  ResultJSON.getSuccess(registerService.addRegister(list).get(0));
	}

	/**
	 * 修改用戶 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateUser(@Valid UserEditForm form ,BindingResult bindingResult, HttpServletRequest request) throws Exception {

		userService.updateUserInfo(form);
		
		return ResultJSON.getSuccess("");
	}

	/**
	 * 删除用戶（实为停用用户）
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delUser(Long id) throws Exception {

		SRegister p = new SRegister();
		p.setId(id);
		p.setStatus(1);

		return super.update(p);
	}

	/**
	 * 分页获取
	 * 
	 * @param form      用户查询条件表单
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pageUser", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageUser(UserQueryForm form ,int pageNum, int pageSize, HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		@SuppressWarnings("unchecked")
		List<SBackUserScope> scopeList = ((List<SBackUserScope>)request.getAttribute("scopeList"));
		
		
		return ResultJSON.getSuccess(userService.queryUserByForm(scopeList,form, pageNum, pageSize));
	}

	/**
	 * 账号延期
	 * 
	 * @param ids
	 * @param months
	 *            延期月份
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delayUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delayUser(String[] ids, int months, HttpServletRequest request) throws Exception {

		if (ids != null && ids.length > 0 && months > 0) {
			SRegister reg = null;
			for (int i = 0; i < ids.length; i++) {
				
				reg =  (SRegister)registerService.getByPrimaryKey(Long.parseLong(ids[i])).getData();
				Date endtime =reg.getReendtime() ; 
				Calendar c = Calendar.getInstance();
				c.setTime(endtime);
				c.add(Calendar.MONTH, months);
				
				
				//准备延期
				reg =new SRegister();
				reg.setId(Long.parseLong(ids[i]));
				reg.setReendtime(c.getTime());
				registerService.update(reg);

			}
			return ResultJSON.getSuccess("");
		} else {
			throw new ParamsException();
		}
	}

	/**
	 * 重新激活卡号
	 * 
	 * @param ids
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/activeUser", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON activeUser(String[] ids, HttpServletRequest request) throws Exception {

		if (ids != null && ids.length > 0) {
			SRegister reg = null;
			for (int i = 0; i < ids.length; i++) {
				
				//准备激活
				reg =new SRegister();
				reg.setId(Long.parseLong(ids[i]));
				reg.setStatus(0);
				registerService.update(reg);
			}

			return ResultJSON.getSuccess("");
		} else {
			throw new ParamsException();
		}

	}


	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportUser", method = RequestMethod.GET)
	public Object exportUser(UserQueryForm form ,HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<SBackUserScope> scopeList = ((List<SBackUserScope>)request.getAttribute("scopeList"));

		
		//为了避免内存溢出，最多1000条记录
		List<UserQueryResult> result = userService.queryUserByForm(scopeList, form, 1, 1000).getList();

		return ExcelExportUtil.getView("用户查询_1000", String.valueOf(Calendar.getInstance().getTime().getTime()), UserQueryResult.class,
				result);

		
	}

}
