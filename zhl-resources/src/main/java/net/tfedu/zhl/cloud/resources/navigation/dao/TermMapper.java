package net.tfedu.zhl.cloud.resources.navigation.dao;

import java.util.List;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.cloud.core.subject.entity.JSubject;
import net.tfedu.zhl.cloud.core.term.entity.JTerm;


public interface TermMapper extends CoreMapper<JTerm> {
	
	//根据term查询所有学科
	public List<JSubject> getSubjectsByTerm(Long termId);
}