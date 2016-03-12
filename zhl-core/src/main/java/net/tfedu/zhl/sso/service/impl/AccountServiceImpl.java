package net.tfedu.zhl.sso.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.sso.dao.PermissionMapper;
import net.tfedu.zhl.sso.dao.UserMapper;
import net.tfedu.zhl.sso.dao.UserRoleMapper;
import net.tfedu.zhl.sso.entity.Permission;
import net.tfedu.zhl.sso.entity.User;
import net.tfedu.zhl.sso.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Resource UserMapper userMapper;
	@Resource UserRoleMapper userRoleMapper;
	@Resource PermissionMapper permissionMapper;
	
	@Override
	public User getUserByUserName(String username) {
		//查用户
		User user = userMapper.getUserByName(username);
		return user;
	}

	@Override
	public List<Permission> getPermissionsByUserName(String username) {
		User user = getUserByUserName(username);  
        if (user == null) {  
            return null;  
        }          
        return user.getUserPermissions(); 
	}

}
