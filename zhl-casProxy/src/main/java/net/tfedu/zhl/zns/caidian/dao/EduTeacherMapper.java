package net.tfedu.zhl.zns.caidian.dao;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.zns.caidian.entity.EduTeacher;
import net.tfedu.zhl.zns.caidian.model.UserInfo4Caidian;

public interface EduTeacherMapper extends CoreMapper<EduTeacher> {
	
	
	
	
	/**
	 * 根据登录用户名查询表中的用户属性
	 * @param loginName
	 * @return
	 */
	public UserInfo4Caidian getUserInfo(@Param("loginName") String loginName) ; 
	
}