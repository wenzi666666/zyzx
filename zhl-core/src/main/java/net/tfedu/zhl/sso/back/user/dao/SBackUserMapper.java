package net.tfedu.zhl.sso.back.user.dao;


import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.back.user.entity.SBackUser;

public interface SBackUserMapper extends CoreMapper<SBackUser> {
	
	
	/**
	 * 增加管理员
	 * @param user
	 */
	public void addManager(@Param("user")SBackUser user);
	
	
	/**
	 * 修改用戶的密碼 
	 * @param userId
	 * @param pwd
	 */
	public void updatePwd(@Param("userId")Long userId,@Param("pwd")byte[] pwd);
	
	
}