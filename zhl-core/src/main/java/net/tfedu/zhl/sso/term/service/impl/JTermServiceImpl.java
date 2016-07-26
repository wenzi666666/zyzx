package net.tfedu.zhl.sso.term.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.sso.subject.dao.JSubjectMapper;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.entity.JTerm;
import net.tfedu.zhl.sso.term.service.JTermService;

@Service("jTermService")
public class JTermServiceImpl extends BaseServiceImpl<JTerm> implements JTermService {

	@Resource
	JSubjectMapper subjectMapper;

	@Override
	public JSubject getSubjectById(long id) {
		// TODO Auto-generated method stub
		return subjectMapper.selectByPrimaryKey(id);
	}
	
	
	
	
	
	

}
