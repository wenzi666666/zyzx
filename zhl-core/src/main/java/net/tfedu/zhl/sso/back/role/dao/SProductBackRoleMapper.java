package net.tfedu.zhl.sso.back.role.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.back.role.entity.SProductBackRole;

public interface SProductBackRoleMapper extends CoreMapper<SProductBackRole> {
	
	
	/**
	 * 获取管理员的角色
	 * @param userId
	 * @param productCode
	 * @return 
	 */
	public List<SProductBackRole> getManagerRole(@Param("userId") long userId,@Param("productCode")String productCode);
	
	
	
	
	
	
	
	
	
	
}