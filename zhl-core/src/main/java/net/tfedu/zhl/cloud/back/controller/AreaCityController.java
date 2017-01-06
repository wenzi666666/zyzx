package net.tfedu.zhl.cloud.back.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.core.controller.BaseController;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.city.entity.City;

/**
 * 地区管理之市管理
 * 
 * @author wangwr
 * @date 2017年1月6日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
@RequestMapping(value = "/*BackAPI/v1.0")
public class AreaCityController extends BaseController<City> {

	
	/**
	 * 增加省
	 * @param City
	 * @param bindingResult 校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addCity", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addCity(@Valid City city,BindingResult bindingResult)
			throws Exception {
		
		//如果有异常
		if(bindingResult.hasErrors()){
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		city.setFlag(false);
		
		return super.insert(city);
	}
	
	/**
	 * 修改省
	 * @param City
	 * @param bindingResult 校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCity", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateCity(@Valid City city,BindingResult bindingResult)
			throws Exception {
		
		//如果有异常
		if(bindingResult.hasErrors()){
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		return super.update(city);
	}

	/**
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delCity", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delCity(Integer id)
			throws Exception {
		
		City p = new City();
		p.setId(id);
		p.setFlag(true);
		
		return super.update(p);
		
	}

	/**
	 * 分页获取
	 * @param provinceId  省id
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pageCity", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageCity(long provinceId,int pageNum, int pageSize,HttpServletRequest request) throws Exception {
		return super.getPage(null, pageNum, pageSize);
	}

}
