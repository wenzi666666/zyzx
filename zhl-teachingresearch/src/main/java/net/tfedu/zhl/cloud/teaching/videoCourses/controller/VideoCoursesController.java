package net.tfedu.zhl.cloud.teaching.videoCourses.controller;

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
	public ResultJSON getAllVideoCourses(int fromFlag,int typeId,int subjectId,int orderBy,int page,int perPage)throws Exception{
		
		return null;
	}
	
	
	/**
	 * 新增一条视频课程资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON createOneVideoCourse(String video)throws Exception{
		
		return null;
	}
	
	/**
	 * 编辑一条视频课程资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses",method = RequestMethod.PATCH)
	@ResponseBody
	public ResultJSON editOneVideoCourse(String video)throws Exception{
		
		return null;
	}
	
	/**
	 * 批量删除视频资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses",method = RequestMethod.DELETE)
	@ResponseBody
	public ResultJSON deleteVideoCourses(String ids)throws Exception{
		
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
	
	/**
	 * 预览一条视频课程资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/previewInfo",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getOneVideoCourseInfo(long id)throws Exception{
		
		return null;
	}
	
	/**
	 * 用户浏览某个视频课程时，设置已看标记
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/visit", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON setViewState(long videoId,long userId)throws Exception{
	
		return null;
	}
	
}
