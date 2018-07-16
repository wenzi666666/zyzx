package net.tfedu.zhl.sso.subject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.sso.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.sso.subject.entity.JTeacherSubject;
import net.tfedu.zhl.sso.subject.service.TeacherSubjectService;

/**
 @author wangwr
 @date 2016年9月23日
 @desc 
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
@Service("teacherSubjectService")
public class TeacherSubjectServiceImpl extends BaseServiceImpl<JTeacherSubject> implements TeacherSubjectService {

	@Autowired
	JTeacherSubjectMapper mapper;
	
	
	@Override
	public List<Map<String,Object>> getUserSubjects(Long userId) {

		
		
		return null;
	}

}
