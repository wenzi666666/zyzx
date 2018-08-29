package net.tfedu.zhl.sso.subject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.sso.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.sso.subject.entity.JTeacherSubject;
import net.tfedu.zhl.sso.subject.service.JSubjectService;

@Service( value="subjectService")
public class JSubjectServiceImpl implements JSubjectService {

	@Autowired
	JTeacherSubjectMapper subjectMapper;

	public List<JTeacherSubject> getUserSubject(long userId) {
		JTeacherSubject obj = new JTeacherSubject();
		obj.setUserid(userId);
		obj.setFlag(false);
		return subjectMapper.select(obj);

	}

}
