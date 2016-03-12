package net.tfedu.zhl.sso.dao;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.entity.User;

public interface UserMapper extends CoreMapper<User> {
	public User getUserById(int userId);
	public User getUserByName(String username);
}