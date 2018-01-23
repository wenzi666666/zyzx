package net.tfedu.zhl.userlayer.subject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.userlayer.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.userlayer.subject.entity.JSubject;
import net.tfedu.zhl.userlayer.subject.entity.JTeacherSubject;
import net.tfedu.zhl.userlayer.subject.service.JSubjectService;

@Service( value="subjectService")
public class JSubjectServiceImpl   extends BaseServiceImpl<JSubject>  implements JSubjectService {

	@Autowired
	JTeacherSubjectMapper subjectMapper;

	public List<JTeacherSubject> getUserSubject(long userId) {
		JTeacherSubject obj = new JTeacherSubject();
		obj.setUserid(userId);
		obj.setFlag(false);
		return subjectMapper.select(obj);

	}

}
