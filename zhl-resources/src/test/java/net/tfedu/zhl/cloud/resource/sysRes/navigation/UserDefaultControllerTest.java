package net.tfedu.zhl.cloud.resource.sysRes.navigation;

import java.io.IOException;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.controller.NavigationController;
import net.tfedu.zhl.cloud.resource.navigation.entity.JUserDefault;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

public class UserDefaultControllerTest extends BaseControllerTestCase {

    @Resource NavigationController navigationController;

    /**
     * 查询用户历史记录
     * 
     * @throws IOException
     */
    @Test
    public void testGetUserDefaultController() throws IOException {
        request = newGet("/resRestAPI/v1.0/history");
        request.setParameter("type", "1");
        request.setParameter("userId", "699230735");
        ResultJSON resultJSON = navigationController.getUserDefault(request, response);
        Assert.isTrue(resultJSON != null);
        JUserDefault userDefault = (JUserDefault) resultJSON.getData();
        Assert.isTrue(userDefault != null);
        log.info(userDefault.toString());
    }

    /**
     * 修改用户历史记录
     * 
     * @throws IOException
     */
    @Test
    public void testUpdateUserDefaultController() throws IOException {
        request = newGet("/resRestAPI/v1.0/history");

        request.setParameter("type", "1");
        request.setParameter("userId", "8978979");
        request.setParameter("tfcode", "SHXX02010101");

        // 插入
        // request.setParameter("_method", "POST");

        // 修改
        request.setParameter("_method", "PATCH");

        ResultJSON resultJSON = navigationController.updateUserDefault(request, response);

        Assert.isTrue(resultJSON != null);
        String userDefault = (String) resultJSON.getData();
        Assert.isTrue(userDefault != null);
        log.info(userDefault.toString());

    }
}