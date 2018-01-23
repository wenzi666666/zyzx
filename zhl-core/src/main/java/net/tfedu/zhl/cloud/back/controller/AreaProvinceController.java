package net.tfedu.zhl.cloud.back.controller;

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
import net.tfedu.zhl.userlayer.province.entity.Province;

/**
 * 地区管理之省管理
 * 
 * @author wangwr
 * @date 2017年1月6日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
@RequestMapping(value = "/*BackAPI/v1.0/area")
public class AreaProvinceController extends BaseController<Province> {

	
	/**
	 * 增加省
	 * @param province
	 * @param bindingResult 校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addProvince", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addProvince(@Valid Province province,BindingResult bindingResult)
			throws Exception {
		
		//如果有异常
		if(bindingResult.hasErrors()){
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		province.setFlag(false);
		
		return super.insert(province);
	}
	
	/**
	 * 修改省
	 * @param province
	 * @param bindingResult 校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateProvince", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateProvince(@Valid Province province,BindingResult bindingResult)
			throws Exception {
		
		//如果有异常
		if(bindingResult.hasErrors()){
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		return super.update(province);
	}

	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delProvince", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delProvince(Long id)
			throws Exception {
		
		Province p = new Province();
		p.setId(id);
		p.setFlag(true);
		
		return super.update(p);
	}

	/**
	 * 分页获取
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pageProvince", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageProvince(int pageNum, int pageSize,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return super.getPage(null, pageNum, pageSize);
	}

}
