package net.tfedu.zhl.cloud.back.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.core.controller.BaseController;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.back.role.entity.SProductBackRole;
import net.tfedu.zhl.sso.back.role.service.SProductBackRoleService;
import net.tfedu.zhl.userlayer.role.entity.JRole;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 
    	
         （前台）角色的管理类
  
    @author wangwr
    @date 2017年1月17日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/

@Controller
@RequestMapping("/*BackAPI/v1.0/backRole")
public class BackRoleManagerController extends BaseController<SProductBackRole> {
	
	@Resource
	SProductBackRoleService backRoleService;


	/**
	 * 增加后台角色
	 * @param name                   角色名称
	 * @param productCode            所在产品
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addBackRole", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addBackRole(String name,String productCode, HttpServletRequest request)
			throws Exception {

		ControllerHelper.checkEmpty(name);
		ControllerHelper.checkEmpty(productCode);
		
		SProductBackRole role = new SProductBackRole();
		role.setName(name);
		role.setFlag(false);
		role.setProductCode(productCode);
		
		
		return super.insertSelective(role) ;
	}

	
	/***
	 * 修改角色
	 * @param id     角色id
	 * @param name   角色名称
	 * @param productCode   所在产品(可选)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateBackRole", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateBackRole(Long id,String name,String productCode) throws Exception {

		
		ControllerHelper.checkEmpty(name);
		ControllerHelper.checkLongEmpty(id);
		
		
		SProductBackRole role = new SProductBackRole();
		role.setName(name);
		role.setId(id);
		
		if(StringUtils.isNotEmpty(productCode)){
			role.setProductCode(productCode);
		}
		
		
		return super.update(role);
	}

	/**
	 * (逻辑)删除角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delBackRole", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delBackRole(Long id) throws Exception {

		SProductBackRole role = new SProductBackRole();
		role.setId(id);
		role.setFlag(true);
		
		return super.update(role);
	}

	/**
	 * 分页查询后台角色
	 * 可选参数 productCode
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pageBackRole", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageBackRole(int pageNum, int pageSize, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String productCode = ControllerHelper.getOptionalParameter(request, "productCode");
		
		Example example = new Example(JRole.class);
		
		Criteria c = example.createCriteria().andCondition(" flag= ", false);
		
		if(StringUtils.isNotEmpty(productCode)){
			c.andCondition(" product_code =  ",productCode);
		}
		
		return super.getPage(example, pageNum, pageSize);
	}
	
	
	
	/**
	 * 查询指定角色(在指定的产品功能下)的权限分配
	 * @param roleId
	 * @param productCode
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryRoleConfig", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON queryRoleConfig(Long roleId,String productCode,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return backRoleService.queryRoleConfig(roleId, productCode) ;
	}
	
	
	
	
	/**
	 * 增加(角色与功能的)分配记录
	 * @param roleId
	 * @param funcIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRoleConfig", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addRoleConfig(Long roleId,Long[] funcIds)
			throws Exception {
		
		ControllerHelper.checkLongEmpty(roleId);
		ControllerHelper.checkArrayEmpty(funcIds);
		
		
		return backRoleService.addRoleConfig(roleId, funcIds) ;
	}
	
	
	
	
	/**
	 * （物理）删除指定的(角色与功能的)分配记录
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delRoleConfig", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delRoleConfig(Long roleId,Long[] funcIds)
			throws Exception {
		
		
		return backRoleService.delRoleConfig(roleId,funcIds) ;
	}
	
	

	/**
	 * 查询指定角色(在指定功能下)的权限分配
	 * @param funcId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryRoleConfigDetail", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON queryRoleConfigDetail(Long funcId,Long roleId)
			throws Exception {
		
		
		return backRoleService.queryRoleConfigDetail(funcId, roleId) ;
	}
	
	
	
	
	/**
	 * 增加(角色与功能点的)分配记录
	 * @param roleId
	 * @param detailIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRoleConfigDetail", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addRoleConfigDetail(Long roleId,Long[] detailIds)
			throws Exception {
		
		ControllerHelper.checkLongEmpty(roleId);
		ControllerHelper.checkArrayEmpty(detailIds);
		
				
		
		return backRoleService.addRoleConfigDetail(roleId, detailIds) ;
	}
	
	
	
	
	/**
	 * （物理）删除指定的(角色与功能点的)分配记录
	 * @param roleId
	 * @param detailIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delRoleConfigDetail", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delRoleConfigDetail(Long roleId,Long[] detailIds)
			throws Exception {
		
		return backRoleService.delRoleConfigDetail(roleId, detailIds);
	}
	
	
	
	
	
}
