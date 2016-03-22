package net.tfedu.zhl.cloud.core.online.dao;

import java.util.Date;

import net.tfedu.zhl.cloud.core.online.entity.JOnlineUsers;
import net.tfedu.zhl.helper.CoreMapper;

public interface JOnlineUsersMapper extends CoreMapper<JOnlineUsers> {
	
	
	/**
	 * 根据token获取online记录
	 * @param token
	 * @return
	 */
	public JOnlineUsers getOnlineByToken(String token);
	
	
	/**
	 * 根据userId 获取当前的登录用户的有效的在线信息
	 * @param id
	 * @return
	 */
	public JOnlineUsers getOnlineByUserId(Long userId);
	
	
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