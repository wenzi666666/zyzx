package net.tfedu.zhl.cloud.resources.navigation.service.impl;

import java.util.List;
import javax.annotation.Resource;

import net.tfedu.zhl.cloud.core.subject.entity.JSubject;
import net.tfedu.zhl.cloud.resources.navigation.dao.TermMapper;
import net.tfedu.zhl.cloud.resources.navigation.entity.Term;
import net.tfedu.zhl.cloud.resources.navigation.service.TermSubjectService;

import org.springframework.stereotype.Service;
/*
 * 查询学段下的所有学科 serviceImpl
 * @author WeiCuicui
 *
 */
@Service("termSubjectService")
public class TermSubjectServiceImpl implements TermSubjectService{
	
	@Resource TermMapper termMapper;

	//查询学段下的所有学科
	@Override
	public List<JSubject> getAllSubjectsByTerm(Long termId){
		return termMapper.getSubjectsByTerm(termId);
	}
	
	//（测试用）
    @Override
	public Term getSubjectsByTermId(Long termId){
		return termMapper.getSubjectsByTermId(termId);
	}
}
