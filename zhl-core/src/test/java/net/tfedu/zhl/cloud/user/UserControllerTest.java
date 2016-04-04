package net.tfedu.zhl.cloud.user;

import javax.annotation.Resource;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.user.controller.UserController;

import org.junit.Test;

public class UserControllerTest extends BaseControllerTestCase {

    @Resource
    UserController controller;

    @Test
    public void testGetUserInfo() {

        request = newGet("/resRestAPI/v1.0/users/{id}");
        request.addHeader("Authorization", "f07b6038-f339-4ace-bf69-460e61d4e8e7");
        ResultJSON result = controller.getUserInfo(1l, request, response);
        System.out.println(result.toString());

    }

    @Test
    public void testUpdateUserInfo() {

        request = newPost("/resRestAPI/v1.0/users/{id}");

        request.addHeader("Authorization", "xxxxxxxtoken");

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
    public void testUpdatePwd() {

        String url = "/v1.0/users/password";

        request = newPost(url);
        request.addHeader("Authorization", "xxxxxxxtoken");

        request.setParameter("oldPassword", "123456");
        request.setParameter("newPassword", "666666");
        request.setParameter("_method", "PATCH");

        ResultJSON result = controller.updateUserPwd(request, response);

        System.out.println(result.toString());

    }

    @Test
    public void testLogin() {
        String url = "/v1.0/users/login";
        request = newPost(url);
        request.setParameter("user_name", "admin");
        request.setParameter("user_pwd", "666666");

        ResultJSON result = controller.Login(request, response);

        System.out.println(result.toString());

    }

    @Test
    public void testLogout() {
        String url = "/v1.0/users/login";
        request = newPost(url);
        request.setParameter("_method", "DELETE");
        request.addHeader("Authorization", "1e2c2b8f-8f77-4e60-80e6-41ddb89ea0e6");
        ResultJSON result = controller.Login(request, response);

        System.out.println(result.toString());
    }

    @Test
    public void testUpdateImage() {

        long userId = 1;
        String url = "/v1.0/users/userimage/" + userId;
        request = newPost(url);
        request.setParameter("userImage", "/person/head/Icon10.jpg");
        request.addHeader("Authorization", "57ce77b6-97a8-4874-837a-3356b8953534");
        ResultJSON result = controller.updateUserImage(userId, request, response);

        System.out.println(result.toString());

    }

}
