package net.tfedu.zhl.cloud.teaching.videoCourses.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoResources;
import net.tfedu.zhl.cloud.teaching.videoCourses.entity.VideoPreviewEntity;
import net.tfedu.zhl.cloud.teaching.videoCourses.service.VideoCoursesService;
import net.tfedu.zhl.helper.PaginationHelper;
import net.tfedu.zhl.helper.ResultJSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	//日志
    Logger logger = LoggerFactory.getLogger(VideoCoursesController.class);
    
    @Resource VideoCoursesService videoCoursesService;
	
	/**
	 * 分页查询视频课程资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllVideoCourses(HttpServletRequest request,HttpServletResponse response,int fromFlag,int typeId,int subjectId,int orderBy,int page,int perPage)throws Exception{
		
		// 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
		
		PaginationHelper<TVideoResources> pagination = videoCoursesService.getAllVideoCourses(fromFlag, typeId, subjectId, orderBy, page, perPage, currentUserId);
	    
		return ResultJSON.getSuccess(pagination);
	}
	
	
	/**
	 * 新增一条视频课程资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON createOneVideoCourse(HttpServletRequest request,String video)throws Exception{
		
		// 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
		
        videoCoursesService.insertOneVideoCourse(video, currentUserId);
        
		return ResultJSON.getSuccess(null);
	}
	
	/**
	 * 编辑一条视频课程资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCoursesEdit",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON editOneVideoCourse(HttpServletRequest request,String video)throws Exception{
		
		// 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        
        videoCoursesService.editOneVideoCourse(video, currentUserId);
		
		return ResultJSON.getSuccess(null);
	}
	
	/**
	 * 批量删除视频资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCoursesDelete",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON deleteVideoCourses(String ids)throws Exception{
		
		
		
		videoCoursesService.delVideoCourses(ids);
		
		return ResultJSON.getSuccess(null);
	}
	
	/**
	 * 视频课程检索
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/search",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON searchVideoCourses(String keyWord,int page,int perPage,int fromFlag,int orderBy)throws Exception{
		
		PaginationHelper<TVideoResources> pagination = videoCoursesService.getVideoSearchResults(keyWord, fromFlag, orderBy, page, perPage);
		
		return ResultJSON.getSuccess(pagination);
	}
	
	/**
	 * 预览一条视频课程资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/previewInfo",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getOneVideoCourseInfo(HttpServletRequest request,long id)throws Exception{
		
		// 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
		
		VideoPreviewEntity videoResource = videoCoursesService.getOneVideoCourse(id, currentUserId);
		
		return ResultJSON.getSuccess(videoResource);
	}
	
	/**
	 * 用户浏览某个视频课程时，设置已看标记
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/visit", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON setViewState(HttpServletRequest request,long videoId)throws Exception{
	
		// 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        
        videoCoursesService.addOneVisitItem(currentUserId, videoId);
        
		return ResultJSON.getSuccess(null);
	}
	
}
