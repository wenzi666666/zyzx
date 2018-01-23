package net.tfedu.zhl.cloud.back.controller;

import java.util.Calendar;

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
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.back.product.entity.SProduct;
import tk.mybatis.mapper.entity.Example;

/**
 
  
  	产品管理
  
    @author wangwr
    @date 2017年1月16日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Controller
@RequestMapping(value="/*BackAPI/v1.0/product")
public class ProductManagerController extends BaseController<SProduct> {

	/**
	 * 增加产品
	 * 
	 * @param product
	 *            產品對象，传递name，code
	 * @param bindingResult
	 *            校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addProduct(@Valid SProduct pro, BindingResult bindingResult, HttpServletRequest request)
			throws Exception {
		// 如果有异常
		if (bindingResult.hasErrors()) {
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		pro.setCreateman(ControllerHelper.getCurrentUserId(request));
		pro.setCreatetime(Calendar.getInstance().getTime());
		pro.setFlag(false);
		
		return super.insert(pro);
	}

	/**
	 * 修改产品
	 * 
	 * @param product
	 * 				產品對象，传递name，code	
	 * @param bindingResult
	 *            校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateProduct(@Valid SProduct product, BindingResult bindingResult) throws Exception {

		// 如果有异常
		if (bindingResult.hasErrors()) {
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}

		return super.update(product);
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
	@RequestMapping(value = "/delProduct", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delProduct(Long id) throws Exception {

		SProduct p = new SProduct();
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
	@RequestMapping(value = "/pageProduct", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageProduct(int pageNum, int pageSize, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Example example = new Example(SProduct.class);
		example.createCriteria().andCondition(" flag= ", false);
		
		return super.getPage(example, pageNum, pageSize);
	}
	
	
	
	
}
