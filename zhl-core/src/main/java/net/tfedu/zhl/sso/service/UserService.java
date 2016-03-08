package net.tfedu.zhl.sso.service;

import java.util.List;

import net.tfedu.zhl.sso.entity.User;

public interface UserService {
	public User getUserById(int id); 
	int insert(User record);
	List<User> selectAll();
}
