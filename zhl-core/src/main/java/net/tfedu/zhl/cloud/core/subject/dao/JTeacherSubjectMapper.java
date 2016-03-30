package net.tfedu.zhl.cloud.core.subject.dao;

import net.tfedu.zhl.cloud.core.subject.entity.JTeacherSubject;
import net.tfedu.zhl.helper.CoreMapper;

public interface JTeacherSubjectMapper extends CoreMapper<JTeacherSubject> {


	/**
	 * 更新用户的学科（教师）
	 * @param userId
	 * @param subjectId
	 */
	public void udpateTeacherSubject(Long userId,Long subjectId);
	
	/**
	 * 清理（用户的学科）重复记录
	 * @param userId
	 */
	public void removeRepeatData(Long userId);
	
}