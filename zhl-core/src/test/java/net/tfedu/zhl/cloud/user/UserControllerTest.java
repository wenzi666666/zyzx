package net.tfedu.zhl.cloud.user;

import javax.annotation.Resource;

import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.user.controller.UserController;

import org.junit.Test;

public class UserControllerTest extends BaseControllerTestCase {

    @Resource
    UserController controller;

    @Test
    public void testGetUserInfo() throws Exception {

        ResultJSON result = controller.getUserInfo(1l, request, response);
        System.out.println(result.toString());

    }

    @Test
    public void testUpdateUserInfo() throws Exception {


        request.setParameter("trueName", "admin_trueName_trueName");
        request.setParameter("termId", "2");
        request.setParameter("subjectId", "3");
        request.setParameter("male", "true");
        request.setParameter("userImage", "/person/head/Icon10.jpg");
        request.setParameter("_method", "PATCH");

        ResultJSON result = controller.updateUserInfo(1l, request, response);

        System.out.println(result.toString());

    }

    @Test
    public void testUpdatePwd() throws Exception {




        request.setParameter("oldPassword", "000000");
        request.setParameter("newPassword", "000000");
        request.setParameter("_method", "PATCH");

        ResultJSON result = controller.updateUserPwd(request, response);

        System.out.println(result.toString());

    }

    @Test
    public void testLogin() throws Exception {
        request.setParameter("user_name", "csls10");
        request.setParameter("user_pwd", "000000");
        ResultJSON result = controller.Login(request, response);

        System.out.println(result.toString());

    }

    @Test
    public void testLogout() throws Exception {
        request.setParameter("_method", "DELETE");
        ResultJSON result = controller.Login(request, response);

        System.out.println(result.toString());
    }

    @Test
    public void testUpdateImage() throws Exception {

        long userId = 390320126l;
    	request.setAttribute("currentUserId", 390320126l);

        request.setParameter("userImage", "/person/head/Icon10.jpg");
        ResultJSON result = controller.updateUserImage(userId, request, response);

        System.out.println(result.toString());

    }

}
