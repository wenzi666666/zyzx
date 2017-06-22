package net.tfedu.zhl.userlayer.subject.service;


import java.util.List;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.userlayer.subject.entity.JSubject;
import net.tfedu.zhl.userlayer.subject.entity.JTeacherSubject;

public interface JSubjectService extends BaseService<JSubject>{
	
	/**
	 * 获取用户的学科
	 * @param userId
	 * @return
	 */
	public List<JTeacherSubject> getUserSubject(long userId);


}
