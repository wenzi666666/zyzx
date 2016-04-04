package net.tfedu.zhl.sso.user.dao;


import java.util.HashMap;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.entity.UserSimple;

/**
 * 用户业务表
 * 
 * @author wangwr
 *
 */
public interface JUserMapper extends CoreMapper<JUser> {
	
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	public JUser getUserById(long id);
	
	/**
	 * 根据name获取用户
	 * @param id
	 * @return
	 */
	public JUser getUserByName(String name);
	
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	public UserSimple getUserSimpleById(long id);
	
	/**
	 * 根据name获取用户
	 * @param id
	 * @return
	 */
	public UserSimple getUserSimpleByName(String name);
	
	
	
	/**
	 * 修改用户头像信息
	 * @param userId
	 * @param userImage用户头像的相对路径
	 */
	public void updateUserImage(Long userId,String userImage);
	

	/**
	 * 获取用户地区信息
	 * @return
	 */
	public HashMap<String,Long> getUserAreaInfo(Long userId);
	
	

}