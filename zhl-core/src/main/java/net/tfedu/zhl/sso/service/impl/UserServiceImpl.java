package net.tfedu.zhl.sso.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.sso.dao.UserMapper;
import net.tfedu.zhl.sso.entity.User;
import net.tfedu.zhl.sso.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource  
    private UserMapper userMapper;

	public User getUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return userMapper.insert(record);
	}

	@Override
	public List<User> selectAll() {
		return userMapper.selectAll();
	}  
    

    
}
