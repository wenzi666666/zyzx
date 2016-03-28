package net.tfedu.zhl.sso.service;

import net.tfedu.zhl.sso.entity.User;

public interface AccountService {

	public User getUserById(int userId);
	public User getUserByUserName(String username);
	
}
