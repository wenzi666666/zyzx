package net.tfedu.zhl.cloud.resource.user.service.impl;

import net.tfedu.zhl.cloud.resource.user.dao.JUserMapper;
import net.tfedu.zhl.cloud.resource.user.entity.JUser;
import net.tfedu.zhl.cloud.resource.user.entity.UserSimple;
import net.tfedu.zhl.cloud.resource.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户业务接口
 * @author wangwr
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	
	
	@Autowired
	private JUserMapper mapper;
	
	
	
	@Override
	public JUser getUserById(long id) {
		return mapper.getUserById(id);
	}

	@Override
	public JUser getUserByName(String name) {
		// TODO Auto-generated method stub
		return mapper.getUserByName(name);
	}
	
	
	
	@Override
	public UserSimple getUserSimpleById(long id){
		return mapper.getUserSimpleById(id);
	}
	
	@Override
	public UserSimple getUserSimpleByName(String name){
		return mapper.getUserSimpleByName(name);
	}
	

}
