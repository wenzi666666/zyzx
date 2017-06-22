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
import net.tfedu.zhl.userlayer.district.entity.District;

/**
 * 地区管理之区县管理
 * 
 * @author wangwr
 * @date 2017年1月6日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
@RequestMapping(value = "/*BackAPI/v1.0/area")
public class AreaDistrictController extends BaseController<District> {

	
	/**
	 * 增加省
	 * @param District
	 * @param bindingResult 校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addDistrict", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addDistrict(@Valid District district,BindingResult bindingResult)
			throws Exception {
		
		//如果有异常
		if(bindingResult.hasErrors()){
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		district.setFlag(false);
		
		return super.insert(district);
	}
	
	/**
	 * 修改省
	 * @param District
	 * @param bindingResult 校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateDistrict", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateDistrict(@Valid District district,BindingResult bindingResult)
			throws Exception {
		
		//如果有异常
		if(bindingResult.hasErrors()){
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		return super.update(district);
	}

	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delDistrict", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delDistrict(Integer id)
			throws Exception {
		

		District p = new District();
		p.setId(id);
		p.setFlag(true);
		
		return super.delete(id);
	}

	/**
	 * 分页获取
	 * @param cityId   市id
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pageDistrict", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageDistrict(long cityId, int pageNum, int pageSize,HttpServletRequest request, HttpServletResponse response) throws Exception {
		return super.getPage(null, pageNum, pageSize);
	}

}
