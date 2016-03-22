package net.tfedu.zhl.cloud.resource.user.service;

import net.tfedu.zhl.cloud.resource.user.entity.JUser;
import net.tfedu.zhl.cloud.resource.user.entity.UserSimple;

/**
 * 用户相关接口
 * @author wangwr
 *
 */
public interface UserService {
	
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	

}
