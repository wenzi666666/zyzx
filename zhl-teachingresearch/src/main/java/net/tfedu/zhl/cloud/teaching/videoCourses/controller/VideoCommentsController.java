package net.tfedu.zhl.cloud.teaching.videoCourses.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoComments;
import net.tfedu.zhl.cloud.teaching.videoCourses.service.VideoCommentsService;
import net.tfedu.zhl.helper.PaginationHelper;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 视频课程的评分、评论相关的接口
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/teachingServiceRestAPI")
public class VideoCommentsController {
	
	@Resource VideoCommentsService videoCommentsService;
	
	/**
	 * 新建一条评论
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/comments", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON createNewComment(HttpServletRequest request,long videoId,String comment,int isScore)throws Exception{
		
		// 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        videoCommentsService.insertOneComment(videoId, comment, isScore, currentUserId);
		return ResultJSON.getSuccess(null);
	}
	
	/**
	 * 查询一个视频课程的所有评论
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/comments", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllComments(long videoId,int page,int perPage)throws Exception{
		
        PaginationHelper<TVideoComments> pagination = videoCommentsService.getAllComments(videoId, page, perPage);
        
		return ResultJSON.getSuccess(pagination);
	}

	
	/**
	 * 编辑一条评论
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/commentsEdit", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON editOneComment(int id,String content)throws Exception{
		
		videoCommentsService.editOneComment(content, id);
		return ResultJSON.getSuccess(null);
	}
	
	/**
	 * 删除一条评论
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/commentsDelete", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON deteleOneComment(int id)throws Exception{
		
		videoCommentsService.deleteOneComment(id);
		return ResultJSON.getSuccess(null);
	}
}
