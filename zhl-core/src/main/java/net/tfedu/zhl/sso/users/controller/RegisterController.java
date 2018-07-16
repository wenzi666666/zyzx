package net.tfedu.zhl.sso.users.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.core.exception.AccountRegisterWebFormError;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.area.service.AreaService;
import net.tfedu.zhl.sso.term.service.JTermService;
import net.tfedu.zhl.sso.users.module.AccountRegisterWebForm;
import net.tfedu.zhl.sso.users.service.RegisterService;
import net.tfedu.zhl.sso.users.service.SCardService;

/**
 * 
 * 用户注册管理
 * 
 * @author wangwr
 * @date 2016年12月20日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */

@Controller
@RequestMapping("/*RestAPI/register/v1.0/")
public class RegisterController {
	@Resource
	private SCardService cardService;
	@Resource
	private AreaService areaService;
	@Resource
	private JTermService jTermService;
	@Resource
	private RegisterService registerService;

	/**
	 * 卡是否有效
	 * 
	 * @param cardNum
	 *            卡号
	 * @param cardPwd
	 *            卡密码
	 */
	@RequestMapping(value = "isCardAvailable")
	@ResponseBody
	public ResultJSON isCardAvailable(String cardNum, String cardPwd) throws Exception{
		
		ControllerHelper.checkEmpty(cardPwd);
		ControllerHelper.checkEmpty(cardNum);
		
		return cardService.isCardAvailable(cardNum, cardPwd);
	}

	/**
	 * 获取全部学段
	 * 
	 */
	@RequestMapping(value = "terms")
	@ResponseBody
	public ResultJSON allTerms() {
		return jTermService.selectAll();
	}

	/**
	 * 获取学段下的学科
	 * 
	 * @param termId
	 *            学段id
	 */
	@RequestMapping(value = "termSubject")
	@ResponseBody
	public ResultJSON termSubject(Long termId) throws Exception {
		return jTermService.getSubjectByTermId(termId);
	}

	/**
	 * 获取全部省
	 * 
	 */
	@RequestMapping(value = "provinces")
	@ResponseBody
	public ResultJSON provinces() throws Exception{
		return areaService.queryProvince();
	}

	/**
	 * 获取省下的市
	 * 
	 * @param provinceId
	 *            省Id
	 */
	@RequestMapping(value = "citys")
	@ResponseBody
	public ResultJSON citys(Long provinceId)throws Exception {
		return areaService.queryCityByProvinceId(provinceId);
	}

	/**
	 * 获取市下的区县
	 * 
	 * @param cityId
	 *            市id
	 */
	@RequestMapping(value = "districts")
	@ResponseBody
	public ResultJSON districts(Long cityId) throws Exception{
		return areaService.queryDistirctByCityId(cityId);
	}

	/**
	 * 获取学校
	 * 
	 * @param districtId
	 *            地区id
	 */
	@RequestMapping(value = "schools")
	@ResponseBody
	public ResultJSON schools(Long districtId)throws Exception {
		return areaService.querySchoolByDistrictId(districtId);
	}

	/**
	 * 获取班级
	 * 
	 * @param schoolId
	 *            学校id
	 */
	@RequestMapping(value = "classList")
	@ResponseBody
	public ResultJSON classList(Long schoolId) throws Exception{
		return areaService.queryClassBySchoolId(schoolId);
	}

	/**
	 * 用户注册
	 * 
	 * @param form
	 *            注册表单
	 * @param bindingResult
	 *            校验结果
	 */
	@RequestMapping(value = "register")
	@ResponseBody
	public ResultJSON register(@Valid AccountRegisterWebForm form, BindingResult bindingResult)throws Exception {
		//如果有异常
		if(bindingResult.hasErrors()){
			throw new AccountRegisterWebFormError(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		if(form.getUserName()==null || form.getUserPassword()==null){
			throw new AccountRegisterWebFormError();
		}
		
		return registerService.register(form);
	}

}