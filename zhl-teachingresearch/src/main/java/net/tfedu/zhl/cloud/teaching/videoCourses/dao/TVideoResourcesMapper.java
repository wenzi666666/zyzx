package net.tfedu.zhl.cloud.teaching.videoCourses.dao;

import java.util.HashMap;

import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoResources;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

public interface TVideoResourcesMapper extends CoreMapper<TVideoResources> {
	
	/**
	 * 分页查询视频课程
	 * @return
	 */
	public HashMap<String,Object> getAllVideoCourses(@Param("userId")long userId,@Param("fromFlag")int fromFlag,
			@Param("typeId") int typeId,@Param("subjectId")int subjectId);
	
	/**
	 * 查询一个视频课程的信息
	 * @param userId
	 * @param videoId
	 * @return
	 */
	public HashMap<String,Object> getOneVideoCourse(@Param("userId")long userId,@Param("videoId")long videoId);
}