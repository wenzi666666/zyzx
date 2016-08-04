package net.tfedu.zhl.cloud.teaching.discuss.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

public class DiscussControllerTest extends BaseControllerTestCase {

	@Resource
	DiscussController controller;

	ResultJSON result;

	@Test
	public void testGetRecomended() throws Exception {
		result = controller.getRecomended(request,1, 10);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));

	}
	@Test
	public void testGetRecomendedBack() throws Exception {
		result = controller.getRecomendedBack(request,1, 10,null);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));

	}
	@Test
	public void testAddReadRecord() throws Exception {
		String classId = "20105";
		result = controller.addReadRecord(request, classId);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testGetReaded() throws Exception {
		result = controller.getReaded(request, response);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testAddRecomended() throws Exception {
		request.setParameter( "className","test测试班级"); //	班级名称
		request.setParameter( "classImage","1.png");	//班级图片路径
		request.setParameter( "schoolName","测试学校");		//学校名称
		request.setParameter( "classId","20105");	//班级id
		request.setParameter( "note","note");//班级简介
		request.setParameter("visit_name", "csls10");
		request.setParameter("visit_pwd", "111111");
		
		
		result = controller.addRecomended(request, response);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testGetOne() throws Exception {
		result = controller.getOne(1l);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testUpdateRecomended() throws Exception {
		request.setParameter( "className","到很晚的我觉得"); //	班级名称
		request.setParameter( "classImage","http://ac-LPYK8MCl.clouddn.com/83e27cf07368290fde32.jpg");	//班级图片路径
		request.setParameter( "schoolName","测试学校");		//学校名称
		request.setParameter("visit_name", "csls10");
		request.setParameter("visit_pwd", "111111");
		request.setParameter("classurl"
, "http://192.168.111.8/net_jyForum.action?args=Y3N4czA1OjAwMDAwMA==&targetPage=http%3A%2F%2F192.168.111.8%2Fnet_jyForum.action%3Fargs%3DY3N4czA1OjAwMDAwMA%3D%3D%26targetPage%3Dhttp%253A%252F%252F192.168.111.8%252F%252FmyForum%252FforumGrade_forumGradeState.action%253FclassNo%253D5%2526partId%253D0%2523%2521partId50247");
		request.setParameter( "note","note");//班级简介		
		result = controller.updateRecomended(request, 73l);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testDelRecomended() throws Exception {
		result = controller.delRecomended("1,2,3");
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
		
	}

}
