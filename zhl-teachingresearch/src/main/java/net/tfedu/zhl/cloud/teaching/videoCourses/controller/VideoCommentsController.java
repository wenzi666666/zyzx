package net.tfedu.zhl.cloud.teaching.videoCourses.controller;

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
	
	
	/**
	 * 新建一条评论
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/comments", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON createNewComment(long videoId,String comment,long userId)throws Exception{
		
		return null;
	}
	
	/**
	 * 查询一个视频课程的所有评论
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/comments", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllComments(long videoId,int page,int perPage)throws Exception{
		
		return null;
	}

	
	/**
	 * 编辑一条评论
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/comments", method = RequestMethod.PATCH)
	@ResponseBody
	public ResultJSON editOneComment(int id,String comment)throws Exception{
		
		
		return null;
	}
	
	/**
	 * 删除一条评论
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/comments", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultJSON deteleOneComment(int id)throws Exception{
		
		
		return null;
	}
}
