package net.tfedu.zhl.cloud.teaching.videoCourses.service;

import java.util.List;

import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoLevel;

public interface VideoLevelsService {

	/**
	 * 查询所有的视频课程等级
	 * @return
	 */
	public List<TVideoLevel> getAllLevels();
	
	/**
	 * 查询特定视频课程等级下的学科
	 * @param level
	 * @return
	 */
	public List<TSubject> getSubjectsByLevel(int level);
}
