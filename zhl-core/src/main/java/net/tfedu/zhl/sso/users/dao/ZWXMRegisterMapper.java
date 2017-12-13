package net.tfedu.zhl.sso.users.dao;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.users.entity.ZWXMRegister;

public interface ZWXMRegisterMapper extends CoreMapper<ZWXMRegister> {
	
	
	void updatePwd(@Param("id")long id,@Param("pwd")byte[] pwd);
	
	
}