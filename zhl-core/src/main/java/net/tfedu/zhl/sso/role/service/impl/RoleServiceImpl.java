package net.tfedu.zhl.sso.role.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.role.dao.JRoleMapper;
import net.tfedu.zhl.sso.role.dao.JUserRoleMapper;
import net.tfedu.zhl.sso.role.entity.JRole;
import net.tfedu.zhl.sso.role.entity.JUserRole;
import net.tfedu.zhl.sso.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<JRole> implements RoleService {
	
	@Resource
	JRoleMapper roleMapper;
	
	@Resource
	JUserRoleMapper userRoleMapper;
	
	

	@Override
	public ResultJSON queryRoleForTeachingResearch() throws Exception {
		// TODO Auto-generated method stub
		return  ResultJSON.getSuccess(roleMapper.queryRoleForTeachingResearch());
	}



	@Override
	public ResultJSON addUserRole(long userId, long roleId) throws Exception {
		if(roleId<=0){
			throw new ParamsException();
		}
		JUserRole obj = new JUserRole();
		obj.setUserid(userId);
		obj.setRoleid(roleId);
		obj.setFlag(false);
		
		int count = userRoleMapper.selectCount(obj);
		if(count==0){
			userRoleMapper.insert(obj);
		}
		return ResultJSON.getSuccess("");
	}
	

}
