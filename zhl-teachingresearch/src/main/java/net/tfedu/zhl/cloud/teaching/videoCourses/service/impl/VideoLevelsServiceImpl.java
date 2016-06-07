package net.tfedu.zhl.cloud.teaching.videoCourses.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.cloud.teaching.videoCourses.dao.TVideoLevelMapper;
import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoLevel;
import net.tfedu.zhl.cloud.teaching.videoCourses.service.VideoLevelsService;

import org.springframework.stereotype.Service;

/**
 * 视频课程等级 及 等级下的相关课程
 * @author WeiCuicui
 *
 */
@Service("videoLevelsService")
public class VideoLevelsServiceImpl implements VideoLevelsService{

    @Resource TVideoLevelMapper tVideoLevelMapper;
	
	/**
	 * 查询所有的视频课程等级
	 * @return
	 */
    @Override
	public List<TVideoLevel> getAllLevels(){
		return tVideoLevelMapper.getAllLevels();
	}
	
	/**
	 * 查询特定等级下的视频课程
	 */
    @Override
	public List<TSubject> getSubjectsByLevel(int level){
		
		return tVideoLevelMapper.getSubjectsByLevel(level);
	}
}
