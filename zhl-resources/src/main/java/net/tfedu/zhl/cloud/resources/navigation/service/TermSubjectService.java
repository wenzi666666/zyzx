package net.tfedu.zhl.cloud.resources.navigation.service;

import java.util.List;

import net.tfedu.zhl.cloud.core.subject.entity.JSubject;
import net.tfedu.zhl.cloud.resources.navigation.entity.Term;

/**
 * 查询学段下的所有学科
 * @author WeiCuicui
 *
 */
public interface TermSubjectService {

	//查询学段下的所有学科
	public List<JSubject> getAllSubjectsByTerm(Long termId);
	
	//自定义（测试用）
	public Term getSubjectsByTermId(Long termId);
}
