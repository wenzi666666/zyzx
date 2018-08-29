package net.tfedu.zhl.sso.term.service;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.entity.JTerm;

public interface JTermService extends BaseService<JTerm> {

	
	public JSubject getSubjectById(long id);
	
	
}
