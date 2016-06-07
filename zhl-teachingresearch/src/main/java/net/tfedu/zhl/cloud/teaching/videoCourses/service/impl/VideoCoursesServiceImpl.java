package net.tfedu.zhl.cloud.teaching.videoCourses.service.impl;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.cloud.teaching.videoCourses.dao.TVideoLevelMapper;
import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoLevel;
import net.tfedu.zhl.cloud.teaching.videoCourses.service.VideoCoursesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频课程的serviceImpl
 * @author WeiCuicui
 *
 */
@Service("videoCoursesService")
public class VideoCoursesServiceImpl implements VideoCoursesService {

	@Resource TVideoLevelMapper tVideoLevelMapper;
	
	/**
	 * 查询所有的视频课程等级
	 * @return
	 */
	public List<TVideoLevel> getAllLevels(){
		return tVideoLevelMapper.getAllLevels();
	}
	
	/**
	 * 
	 */
	public List<TSubject> getSubjectsByLevel(int level){
		
		return tVideoLevelMapper.getSubjectsByLevel(level);
	}
}
