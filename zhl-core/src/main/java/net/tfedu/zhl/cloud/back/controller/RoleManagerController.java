package net.tfedu.zhl.cloud.back.controller;

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
import net.tfedu.zhl.sso.role.entity.JRole;
import tk.mybatis.mapper.entity.Example;

/**
 
    	
         （前台）角色的管理类
  
    @author wangwr
    @date 2017年1月17日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/

@Controller
@RequestMapping("/*BackAPI/v1.0/role")
public class RoleManagerController extends BaseController<JRole> {


	/**
	 * 增加前台角色
	 * @param name    名称
	 * @param note    备注
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON addRole(String name,String note, HttpServletRequest request)
			throws Exception {

		ControllerHelper.checkEmpty(name);
		note = StringUtils.isEmpty(note)?"":note;
		
		JRole role = new JRole();
		role.setName(name);
		role.setNote(note);
		role.setFlag(false);
		
		return super.insertSelective(role) ;
	}

	
	/***
	 * 修改角色
	 * @param id     角色id
	 * @param name   角色名称
	 * @param note   角色说明
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateRole(Long id,String name,String note) throws Exception {

		
		ControllerHelper.checkEmpty(name);
		
		JRole role = new JRole();
		role.setId(id);
		role.setName(name);
		if(StringUtils.isNotEmpty(note)){
			role.setNote(note);
		}
		
		
		return super.update(role);
	}

	/**
	 * (逻辑)删除角色
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delRole", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON delRole(Long id) throws Exception {

		JRole role = new JRole();
		role.setId(id);
		role.setFlag(true);
		
		return super.update(role);
	}

	@RequestMapping(value = "/pageRole", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON pageRole(int pageNum, int pageSize, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Example example = new Example(JRole.class);
		
		example.createCriteria().andCondition(" flag= ", false);
		
		return super.getPage(example, pageNum, pageSize);
	}
	
	
	
}
