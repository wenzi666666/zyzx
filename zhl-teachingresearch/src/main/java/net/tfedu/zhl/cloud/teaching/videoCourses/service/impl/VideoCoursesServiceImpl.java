package net.tfedu.zhl.cloud.teaching.videoCourses.service.impl;

import java.util.List;

import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoResources;
import net.tfedu.zhl.cloud.teaching.videoCourses.entity.VideoPreviewEntity;
import net.tfedu.zhl.cloud.teaching.videoCourses.service.VideoCoursesService;

import org.springframework.stereotype.Service;

/**
 * 视频课程的serviceImpl
 * @author WeiCuicui
 *
 */
@Service("videoCoursesService")
public class VideoCoursesServiceImpl implements VideoCoursesService {

	/**
	 * 分页查询视频课程资源
	 * @return
	 */
	public List<TVideoResources> getAllVideoCourses(){
		
		return null;
	}
	
	/**
	 * 一条视频课程的预览信息
	 * @param videoId
	 * @return
	 */
	public VideoPreviewEntity getOneVideoCourse(long videoId){
		
		return null;
	}
}
