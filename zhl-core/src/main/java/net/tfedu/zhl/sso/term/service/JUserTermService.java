package net.tfedu.zhl.sso.term.service;

import java.util.List;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.sso.subject.entity.JTeacherSubject;
import net.tfedu.zhl.sso.term.entity.JUserTerm;

public interface JUserTermService extends BaseService<JUserTerm> {

	
	public void addTeacherSubject(List<JTeacherSubject> list);
	
}
