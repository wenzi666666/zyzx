package net.tfedu.zhl.cloud.teaching.videoCourses.service;

import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoLevel;

import com.sun.tools.javac.util.List;

/**
 * 视频课程的service
 * @author WeiCuicui
 *
 */
public interface VideoCoursesService {

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
