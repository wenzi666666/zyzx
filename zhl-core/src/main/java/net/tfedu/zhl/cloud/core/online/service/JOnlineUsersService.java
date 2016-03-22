package net.tfedu.zhl.cloud.core.online.service;

import java.util.Date;

import net.tfedu.zhl.cloud.core.online.entity.JOnlineUsers;


public interface JOnlineUsersService {
	
	
	
	/**
	 * 获取用户的有效的在线信息
	 * @param userId
	 * @return
	 */
	public JOnlineUsers getUserOnlines(Long userId);
	
	
	
	/**
	 * 增加用户的在线信息
	 * @param user
	 */
	public void addUserOnline(JOnlineUsers user);
	
	
	/**
	 * 修改用户在线状态
	 * @param token
	 * @param status
	 */
	public void updateOnlineStatus(String token,Long status);
	
	
	
	/**
	 * 根据token设置用户的最后操作时间
	 * @param token
	 */
	public void updateLastOperTime(String token,Date date);
	
	
	
	
	
	

}
