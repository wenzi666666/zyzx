package net.tfedu.zhl.userlayer.term.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.userlayer.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.userlayer.subject.entity.JTeacherSubject;
import net.tfedu.zhl.userlayer.term.entity.JUserTerm;
import net.tfedu.zhl.userlayer.term.service.JUserTermService;

@Service("userTermService")
public class JUserTermServiceImpl extends BaseServiceImpl<JUserTerm> implements JUserTermService {

	@Resource
	JTeacherSubjectMapper tsMapper;
	
	
	
	@Override
	public void addTeacherSubject(List<JTeacherSubject> list) {
		tsMapper.insertList(list);
		
	}

	

}
