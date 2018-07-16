package net.tfedu.zhl.sso.term.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.subject.dao.JSubjectMapper;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.dao.JTermMapper;
import net.tfedu.zhl.sso.term.entity.JTerm;
import net.tfedu.zhl.sso.term.service.JTermService;

@Service("jTermService")
public class JTermServiceImpl extends BaseServiceImpl<JTerm> implements JTermService {


	
	@Autowired
	JSubjectMapper subjectMapper;
	
	@Autowired
	JTermMapper termMapper;

	
	
	
	@Override
	public JSubject getSubjectById(long id) {
		return subjectMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public ResultJSON getSubjectBySubjectId(long id) {
		return ResultJSON.getSuccess(subjectMapper.selectByPrimaryKey(id));
	}
	
	

	@Override
	public ResultJSON getSubjectByTermId(long termId) {
		
		
		return ResultJSON.getSuccess(termMapper.getSubjectsByTerm(termId));
	}


	@Override
	public ResultJSON queryAllTerm() {
		return ResultJSON.getSuccess(termMapper.selectAll());
	}
	
	
	
	
	
	

}
