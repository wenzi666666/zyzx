package net.tfedu.zhl.sso.service;

import java.util.List;

import net.tfedu.zhl.sso.entity.SysResource;
import net.tfedu.zhl.sso.entity.User;

public interface AccountService {

	public User getUserById(int userId);
	public User getUserByUserName(String username);
	public List<SysResource> selectAllPermission();
}
