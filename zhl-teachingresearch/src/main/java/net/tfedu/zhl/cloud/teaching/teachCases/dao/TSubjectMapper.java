package net.tfedu.zhl.cloud.teaching.teachCases.dao;

import java.util.List;

import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.helper.CoreMapper;

public interface TSubjectMapper extends CoreMapper<TSubject> {
	
	
	
	/**
	 * 获取学段下的学科
	 * @param termId
	 * @return
	 */
	public List<TSubject> getTermSubject(int termId);
	
	
	
	
	
	
}