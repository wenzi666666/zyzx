package net.tfedu.zhl.userlayer.term.service;

import java.util.List;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.userlayer.subject.entity.JTeacherSubject;
import net.tfedu.zhl.userlayer.term.entity.JUserTerm;

public interface JUserTermService extends BaseService<JUserTerm> {

	
	public void addTeacherSubject(List<JTeacherSubject> list);
	
}
