package net.tfedu.zhl.cloud.teaching.videoCourses.controller;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.teaching.teachCases.entity.TSubject;
import net.tfedu.zhl.cloud.teaching.videoCourses.entity.TVideoLevel;
import net.tfedu.zhl.cloud.teaching.videoCourses.service.VideoLevelsService;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 视频课程等级及相应等级下的学科
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/teachingServiceRestAPI")
public class VideoLevelsController {

	@Resource VideoLevelsService videoLevelsService;
	/**
	 * 查询所有的视频课程等级
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/levels", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllLevels() throws Exception{
		
		List<TVideoLevel> list = videoLevelsService.getAllLevels();
		
		return ResultJSON.getSuccess(list);
	}
	
	/**
	 * 查询特定level下的学科
	 * @param level
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/videoCourses/subjects", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getSubjectsByLevel(int level) throws Exception{
		
		List<TSubject> list = videoLevelsService.getSubjectsByLevel(level);	
		return ResultJSON.getSuccess(list);
	}
}
