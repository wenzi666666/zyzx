package net.tfedu.zhl.cloud.teaching.videoCourses.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoLevel;
import net.tfedu.zhl.helper.CoreMapper;

public interface TVideoLevelMapper extends CoreMapper<TVideoLevel> {
	
	/**
	 * 查询所有的视频课程等级
	 * @return
	 */
	public List<TVideoLevel> getAllLevels();
	
	/**
	 * 查询特定等级下的所有学科
	 * @param level
	 * @return
	 */
	public List<TSubject> getSubjectsByLevel(@Param("level")int level);
}