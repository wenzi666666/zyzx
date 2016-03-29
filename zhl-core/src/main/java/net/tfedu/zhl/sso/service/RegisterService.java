package net.tfedu.zhl.sso.service;

import net.tfedu.zhl.sso.entity.SRegister;

public interface RegisterService {
	/**
	 * id获取注册用户
	 */
	public SRegister getRegister(Long id);
	/**
	 * name获取注册用户
	 */
	public SRegister getRegister(String userName);
	
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param password
	 */
	public void modifyRegisterPassword(Long userId,String password );
	
	
	
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public SRegister login(String userName,String password);
	
	
	
	
	

}
