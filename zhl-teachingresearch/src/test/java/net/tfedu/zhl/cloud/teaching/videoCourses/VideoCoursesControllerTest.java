package net.tfedu.zhl.cloud.teaching.videoCourses;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.teaching.videoCourses.controller.VideoCoursesController;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

/**
 * 视频课程相关接口单元测试
 * @author WeiCuicui
 *
 */
public class VideoCoursesControllerTest extends BaseControllerTestCase{
	
	@Resource VideoCoursesController videoCoursesController;
	
	ResultJSON result ;

	//查询所有视频课程等级
	@Test
    public void testAllLevels()throws Exception{
    	result = videoCoursesController.getAllLevels();
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
	//查询等级下的所属学科
	@Test
    public void testSubjectsByLevel()throws Exception{
		int level = 3;
    	result = videoCoursesController.getSubjectsByLevel(level);
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
}
