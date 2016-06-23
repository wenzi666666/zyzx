package net.tfedu.zhl.sso.role.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.role.service.RoleService;

/**
 * 角色管理、角色分配等
 * @author wangwr
 *
 */

@Controller
@RequestMapping("/*RestAPI")
public class RoleController {
	
	
	@Resource
	RoleService roleService;
	
	/**
	 *  查询（教研角色管理的）角色
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/v1.0/roles_teaching",method=RequestMethod.GET)
	public ResultJSON  queryRolesForTeachingResearch()throws Exception{
		return  roleService.queryRoleForTeachingResearch();
	}
	
	
	
	/**
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public ResultJSON  addUserRole(HttpServletRequest request,long roleId)throws Exception{
		// 当前登录用户id
		Long userId = (Long) request.getAttribute("currentUserId");
		return roleService.addUserRole(userId, roleId);
				
	}
	
	
	
	
	
	
	
	
	

}
