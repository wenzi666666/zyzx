package net.tfedu.zhl.cloud.core.online.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.core.online.entity.JOnlineUsers;


public interface JOnlineUsersService {
	
	
	
	/**
	 * 获取用户的有效的在线信息
	 * @param userId
	 * @return
	 */
	public JOnlineUsers getUserOnlines(Long userId,HttpServletRequest request);
	
	
	/**
	 * 根据token获取用户的在线信息
	 * @param token
	 * @return
	 */
	public JOnlineUsers  getUserOnlinesByToken(String token);
	
	
	
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
	public void updateOnlineStatus(String token,Integer status);
	
	
	
	/**
	 * 根据token设置用户的最后操作时间
	 * @param token
	 */
	public void updateLastOperTime(String token,Date date);
	
	
	
	
	/**
	 * 设置用户退出
	 * @param token
	 */
	public void logout(String token);
	
	
	
	
	

}
