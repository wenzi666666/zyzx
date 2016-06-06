package net.tfedu.zhl.cloud.teaching.common.service.impl;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.teaching.common.service.BasicInfoService;
import net.tfedu.zhl.cloud.teaching.teachCases.dao.TSubjectMapper;
import net.tfedu.zhl.cloud.teaching.term.entity.Term;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Service;


@Service("basicInfoServiceImpl")
public class BasicInfoServiceImpl extends BaseServiceImpl<Term> implements BasicInfoService {

	
	@Resource
	TSubjectMapper subjectMapper;
	
	
	
	
	
	

	
	@Override
	public ResultJSON getAllTerm() throws Exception {
		return selectAll();
	}

	
	@Override
	public ResultJSON getTermSubject(int termId) throws Exception {
		
		return defaultSuccess(subjectMapper.getTermSubject(termId));
	}

	
	
	
	
	

}
