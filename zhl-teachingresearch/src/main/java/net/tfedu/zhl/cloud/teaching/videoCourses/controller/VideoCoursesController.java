package net.tfedu.zhl.cloud.teaching.videoCourses.controller;

import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoResources;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 视频课程相关的接口
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/teachingServiceRestAPI")
public class VideoCoursesController {
	
	
	/**
	 * 查询所有的视频课程资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllVideoCourses()throws Exception{
		
		return null;
	}
	
	/**
	 * 查询一条视频课程资源的详细信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/previewInfo",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getOneVideoCourseInfo(long id)throws Exception{
		
		return null;
	}
	
	/**
	 * 新增一条视频课程资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON createOneVideoCourse(TVideoResources video)throws Exception{
		
		return null;
	}
	
	/**
	 * 编辑一条视频课程资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses",method = RequestMethod.PUT)
	@ResponseBody
	public ResultJSON editOneVideoCourse(TVideoResources video)throws Exception{
		
		return null;
	}
	
	/**
	 * 批量删除视频资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses",method = RequestMethod.PATCH)
	@ResponseBody
	public ResultJSON editOneVideoCourse(long[] ids)throws Exception{
		
		return null;
	}
	
	/**
	 * 视频课程检索
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/search",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON searchVideoCourses(String keyWord,int page,int perPage,int fromFlag)throws Exception{
		
		return null;
	}
	
	
	
}
