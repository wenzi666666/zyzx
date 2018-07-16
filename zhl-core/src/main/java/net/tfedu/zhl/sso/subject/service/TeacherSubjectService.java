package net.tfedu.zhl.sso.subject.service;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.sso.subject.entity.JTeacherSubject;

/**
 @author wangwr
 @date 2016年9月23日
 @desc 
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public interface TeacherSubjectService extends BaseService<JTeacherSubject>{

	
	
	public List<Map<String,Object>> getUserSubjects(Long userId);
	
	
}
