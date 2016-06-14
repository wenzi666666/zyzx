package net.tfedu.zhl.cloud.teaching.videoCourses;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.teaching.videoCourses.controller.VideoCommentsController;
import net.tfedu.zhl.cloud.teaching.videoCourses.controller.VideoCoursesController;
import net.tfedu.zhl.cloud.teaching.videoCourses.controller.VideoTypesController;
import net.tfedu.zhl.cloud.utils.datatype.JsonUtil;
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
	
	@Resource VideoTypesController videoTypesController;
	@Resource VideoCoursesController videoCoursesController;
	@Resource VideoCommentsController videoCommentsController;
	
	ResultJSON result;

	//查询所有视频课程类型
	@Test
    public void testAllTypes()throws Exception{
    	result = videoTypesController.getAllTypes();
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
	//查询类型下的所属学科
	@Test
    public void testSubjectsByType()throws Exception{
		int typeId = 3;
    	result = videoTypesController.getSubjectsByType(typeId);
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
	//查询所有的视频课程
	@Test
	public void testGetAllVideoCourses()throws Exception{
		int fromFlag = 1;
		int typeId = 1;
		int subjectId = 1;
		int orderBy = 1;
		int page = 1;
		int perPage = 10;
		result = videoCoursesController.getAllVideoCourses(request, response, fromFlag, typeId, subjectId, orderBy, page, perPage);
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
	//预览一条视频课程信息
	@Test
	public void getOneVideoCourse()throws Exception{
		long id = 1;
		result = videoCoursesController.getOneVideoCourseInfo(request, id);
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
	//删除一个视频课程
	@Test
	public void deleteOneVideoCourse()throws Exception{
		String ids = "1,2,3";
		result = videoCoursesController.deleteVideoCourses(ids);
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
	//视频课程检索
	@Test
	public void searchVideoCourses()throws Exception{
		int fromFlag = 1;
		int page = 1;
		int perPage = 10;
		String keyWord = "haha";
		int orderBy = 1;
		result = videoCoursesController.searchVideoCourses(keyWord, page, perPage, fromFlag, orderBy);
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
	//查询一个视频课程的所有评论
	@Test
	public void getAllComments()throws Exception{
		long videoId = 1;
		int page = 1;
		int perPage = 10;
		result = videoCommentsController.getAllComments(videoId, page, perPage);
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
	//删除一个评论
	@Test
	public void deleteOneComment()throws Exception{
		long id = 1;
		result = videoCommentsController.deteleOneComment(id);
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
	//测试将json字符串转换为java对象
	public static void main(String[] args){
		User user = new User();
		user = JsonUtil.getInstance().fromJson("{name:'roma',age:10}",User.class);
		System.out.println(user.name + "; " + user.age);
	}
}

//自定义类型
class User{
	public String name;
	public int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
