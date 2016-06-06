package net.tfedu.zhl.cloud.teaching.videoCourses.dao;

import org.apache.ibatis.annotations.Param;

import com.sun.tools.javac.util.List;

import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoLevel;
import net.tfedu.zhl.helper.CoreMapper;

public interface TVideoLevelMapper extends CoreMapper<TVideoLevel> {
	
	//查询特定等级下的所有学科
	public List<TSubject> getSubjectsByLevel(@Param("level")int level);
}