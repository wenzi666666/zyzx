package net.tfedu.zhl.cloud.back.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.core.controller.BaseController;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclist;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclistDetail;
import net.tfedu.zhl.sso.back.func.service.SProductBackFuncListService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 
 
 	产品后台功能及其功能点管理
  
    @author wangwr
    @date 2017年1月16日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/

@Controller
@RequestMapping(value="/*BackAPI/v1.0/func")
public class ProductBackFunlistController extends BaseController<SProductBackFunclist> {

	@Resource
	SProductBackFuncListService funcService;
	
	
	
	/**
	 * 增加产品后台功能
	 * 
	 * @param card
	 *            用於收集卡號注冊的信息
	 * @param bindingResult
	 *            校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addFunc", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addFunc(@Valid SProductBackFunclist func, BindingResult bindingResult, HttpServletRequest request)
			throws Exception {

		// 如果有异常
		if (bindingResult.hasErrors()) {
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}

		func.setFlag(false);
		return super.insertSelective(func);
	}

	/**
	 * 修改产品后台功能
	 * 
	 * @param card
	 * @param bindingResult
	 *            校验结果
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateFunc", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateFunc(@Valid SProductBackFunclist func, BindingResult bindingResult) throws Exception {

		// 如果有异常
		if (bindingResult.hasErrors()) {
			throw new ParamsException(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}

		return super.update(func);
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
	@RequestMapping(value = "/delFunc", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delFunc(Long id) throws Exception {

		SProductBackFunclist p = new SProductBackFunclist();
		p.setId(id);
		p.setFlag(true);
		return super.update(p);
	}

	/**
	 * 分页获取(根据产品code)
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pageFunc", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageFunc(int pageNum, int pageSize, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Example example = new Example(SProductBackFunclist.class);
		
		Criteria c = example.createCriteria().andCondition(" flag= ", false);
		
		String productCode = ControllerHelper.getOptionalParameter(request, "productCode");
		
		if(StringUtils.isNotEmpty(productCode)){
			c.andCondition(" product_code =",productCode);
		}
		
		return super.getPage(example, pageNum, pageSize);
	}
	
	/**
	 * 查看功能点
	 * @param funcId   功能id
	 * @param request
	 * @param response
	 * @return  返回成功或失敗信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON queryDetail(Long funcId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return funcService.getFuncAllDetail(funcId);
	}
	
	
	/**
	 * 刪除功能点
	 * @param id   功能点id
	 * @return   返回成功或失敗信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/delDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON delDetail(Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return funcService.delFuncDetail(id);
	}
	
	
	
	/**
	 * 修改功能点
	 * @param id   功能点id
	 * @return   返回成功或失敗信息
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateDetail(Long id,String name,String code,String path,String funcDesc)
			throws Exception {
		
		ControllerHelper.checkEmpty(name);
		ControllerHelper.checkEmpty(code);
		ControllerHelper.checkEmpty(path);
		
		if(StringUtils.isEmpty(funcDesc)){
			funcDesc= name ;
		}
		
		SProductBackFunclistDetail detail = new SProductBackFunclistDetail();
		
		
		detail.setId(id);
		detail.setName(name);
		detail.setPath(path);
		detail.setCode(code);
		detail.setFlag(false);
		detail.setFuncdesc(funcDesc);
		
		
		return funcService.updateFuncDetail(detail);
	}
	
	
	
	/**
	 * 添加功能点
	 * @param name   功能点名称
	 * @param code   功能点的编码
	 * @param path   功能点的访问路径
	 * @param funcDesc   功能点的描述
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addDetail", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addDetail(String name,String code,String path,String funcDesc,Long funcId)
			throws Exception {
		
		ControllerHelper.checkEmpty(name);
		ControllerHelper.checkEmpty(code);
		ControllerHelper.checkEmpty(path);
		ControllerHelper.checkLongEmpty(funcId);
		
		
		if(StringUtils.isEmpty(funcDesc)){
			funcDesc= name ;
		}
		
		SProductBackFunclistDetail detail = new SProductBackFunclistDetail();
		
		
		detail.setName(name);
		detail.setPath(path);
		detail.setCode(code);
		detail.setFlag(false);
		detail.setFuncdesc(funcDesc);
		detail.setFuncid(funcId);
		
		return funcService.addFuncDetail(detail);
	}
	
	
	/**
	 * 分配多个功能点的权限到指定的产品的角色
	 * 
	 * @param funcId
	 * @param detailIds
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addDetailRoleConfig", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addDetailRoleConfig (Long funcId, Long[] detailIds, Long roleId) throws Exception {
		
		ControllerHelper.checkLongEmpty(funcId);
		ControllerHelper.checkLongEmpty(roleId);
		ControllerHelper.checkArrayEmpty(detailIds);
		
		return funcService.addDetailRoleConfig(funcId, detailIds, roleId);
	}
	
	
	
	
	
	
	
	
	
	
}
