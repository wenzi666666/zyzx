package net.tfedu.zhl.cloud.teaching.videoCourses.service;

import java.util.List;

import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoResources;
import net.tfedu.zhl.cloud.teaching.videoCourses.entity.VideoPreviewEntity;



/**
 * 视频课程的service
 * @author WeiCuicui
 *
 */
public interface VideoCoursesService {

	/**
	 * 分页查询视频课程资源
	 * @return
	 */
	public List<TVideoResources> getAllVideoCourses();
	
	/**
	 * 一条视频课程的预览信息
	 * @param videoId
	 * @return
	 */
	public VideoPreviewEntity getOneVideoCourse(long videoId);
}
