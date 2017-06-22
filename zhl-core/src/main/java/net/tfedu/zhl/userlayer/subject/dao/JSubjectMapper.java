package net.tfedu.zhl.userlayer.subject.dao;


import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.userlayer.subject.entity.JSubject;

public interface JSubjectMapper extends CoreMapper<JSubject> {
	
	
	/**
	 * 根据名称获取学科
	 * @param name
	 * @return
	 */
	public JSubject getSubjectByName(@Param("name")String name);
	
	
}