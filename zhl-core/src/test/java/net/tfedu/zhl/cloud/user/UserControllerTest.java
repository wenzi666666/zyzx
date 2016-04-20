package net.tfedu.zhl.cloud.user;

import javax.annotation.Resource;


import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.user.controller.UserController;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


@Transactional
public class UserControllerTest extends BaseControllerTestCase {

	@Resource
	UserController controller;

	ResultJSON result = null;

	@Test
	public void testGetUserInfo() throws Exception {

		result = controller.getUserInfo(1l, request, response);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

	}

	@Test
	public void testUpdateUserInfo() throws Exception {

		request.setParameter("trueName", "admin_trueName_trueName");
		request.setParameter("termId", "2");
		request.setParameter("subjectId", "3");
		request.setParameter("male", "true");
		request.setParameter("userImage", "/person/head/Icon10.jpg");
		request.setParameter("_method", "PATCH");

		result = controller.updateUserInfo(1l, request, response);

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

	}

	@Test
	public void testUpdatePwd() throws Exception {

		request.setParameter("oldPassword", "000000");
		request.setParameter("newPassword", "000000");
		request.setParameter("_method", "PATCH");

		result = controller.updateUserPwd(request, response);

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

	}

	@Test
	public void testLogin() throws Exception {
		request.setParameter("user_name", "csls10");
		request.setParameter("user_pwd", "000000");
		result = controller.Login(request, response);

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

	}

	@Test
	public void testLogout() throws Exception {
		request.setParameter("_method", "DELETE");
		result = controller.Login(request, response);

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

	@Test
	public void testUpdateImage() throws Exception {

		long userId = 390320126l;
		request.setAttribute("currentUserId", 390320126l);

		request.setParameter("userImage", "/person/head/Icon10.jpg");
		result = controller.updateUserImage(userId, request, response);

		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

	}

	@Test
	public void testGetUserImageUrl() {

		request.setAttribute("currentUserId", 390320126l);
		result = controller.getUserImageUrl(request, response);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

	}

	@Test
	public void testGetVerificationCode() {
		result = controller.getVerificationCode(request, response);
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

	}

}
