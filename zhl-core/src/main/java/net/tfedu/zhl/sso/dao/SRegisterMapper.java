package net.tfedu.zhl.sso.dao;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.entity.SRegister;

/**
 * 注册表接口
 * @author wangwr
 *
 */
public interface SRegisterMapper extends CoreMapper<SRegister> {
	
	
	/**
	 * 修改密码
	 * @param pwd
	 * @param id
	 */
	public void modifyPassword(Long id,byte[]pwd);
	
	
	
	
}