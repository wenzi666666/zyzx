package net.tfedu.zhl.cloud.teaching.videoCourses;

import javax.annotation.Resource;

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
