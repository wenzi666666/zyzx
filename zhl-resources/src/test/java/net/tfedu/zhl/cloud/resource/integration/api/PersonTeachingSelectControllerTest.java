package net.tfedu.zhl.cloud.resource.integration.api;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;

import net.tfedu.zhl.cloud.resource.constant.ResourcePlatformWebConstant;
import net.tfedu.zhl.cloud.resource.integration.entity.ResultInfo;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 * 
 * 
 * @author wangwr
 * @date 2017年8月11日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Transactional
public class PersonTeachingSelectControllerTest extends BaseControllerTestCase {

	@Resource
	PersonTeachingSelectController controller;

	ResultInfo result = null;

	@Test
	public void testIsFirstSelect() throws Exception {

		request.setParameter("userName", "csls01");
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");

		result = controller.isFirstSelect(request);

		System.out.println(JSON.toJSONString(result));

		Assert.isTrue(null != result && ResourcePlatformWebConstant.SUCCESS.equals(result.getMessage()));

	}

	@Test
	public void testGetSubject() throws Exception {

		request.setParameter("userName", "csls01");
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");

		result = controller.getSubject(request);

		System.out.println(JSON.toJSONString(result));

		Assert.isTrue(null != result && ResourcePlatformWebConstant.SUCCESS.equals(result.getMessage()));

	}

	@Test
	public void testVersion() throws Exception {

		request.setParameter("termId", "3");
		request.setParameter("subjectId", "1");

		result = controller.version(request);

		System.out.println(JSON.toJSONString(result));

		Assert.isTrue(null != result && ResourcePlatformWebConstant.SUCCESS.equals(result.getMessage()));

	}

	@Test
	public void testBook() throws Exception {

		request.setParameter("versionId", "36196");

		result = controller.book(request);

		System.out.println(JSON.toJSONString(result));

		Assert.isTrue(null != result && ResourcePlatformWebConstant.SUCCESS.equals(result.getMessage()));

	}

	@Test
	public void testTeachingSelect() throws Exception {
		request.setParameter("userName", "csls01");
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");
		request.setParameter("termId", "3");
		request.setParameter("subjectId", "1");
		request.setParameter("syscourseId", "11643");

		result = controller.teachingSelect(request);

		System.out.println(JSON.toJSONString(result));

		Assert.isTrue(null != result && ResourcePlatformWebConstant.SUCCESS.equals(result.getMessage()));

	}

	@Test
	public void testTree() throws Exception {
		request.setParameter("userName", "csls01");
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");

		result = controller.tree(request);

		System.out.println(JSON.toJSONString(result));

		Assert.isTrue(null != result && ResourcePlatformWebConstant.SUCCESS.equals(result.getMessage()));

	}

	@Test
	public void testTree2() throws Exception {
		request.setParameter("userName", "csls01");
		request.setParameter("cloudPlatFormLocal", "http://192.168.111.8");
		request.setParameter("id", "11643");

		result = controller.tree(request);

		System.out.println(JSON.toJSONString(result));

		Assert.isTrue(null != result && ResourcePlatformWebConstant.SUCCESS.equals(result.getMessage()));

	}

	@Test
	public void testGetAllTree() throws Exception {

		request.setParameter("versionId", "11643");

		result = controller.getAllTree(request);

		System.out.println(JSON.toJSONString(result));

		Assert.isTrue(null != result && ResourcePlatformWebConstant.SUCCESS.equals(result.getMessage()));

	}

}
