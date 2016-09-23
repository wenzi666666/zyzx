package net.tfedu.zhl.sso.subject.service;

import java.util.List;

import net.tfedu.zhl.sso.subject.entity.JTeacherSubject;

public interface JSubjectService {
	
	/**
	 * 获取用户的学科
	 * @param userId
	 * @return
	 */
	public List<JTeacherSubject> getUserSubject(long userId);


}
