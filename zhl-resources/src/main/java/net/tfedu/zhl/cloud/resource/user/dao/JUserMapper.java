package net.tfedu.zhl.cloud.resource.user.dao;


import net.tfedu.zhl.cloud.resource.user.entity.JUser;
import net.tfedu.zhl.cloud.resource.user.entity.UserSimple;
import net.tfedu.zhl.helper.CoreMapper;
/**
 * 用户业务表
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
	
	
}