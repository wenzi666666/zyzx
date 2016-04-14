package net.tfedu.zhl.cloud.resource.sysRes.navigation;

import java.io.IOException;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.controller.NavigationController;
import net.tfedu.zhl.cloud.utils.datatype.JsonUtil;
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
       
        ResultJSON json = navigationController.getUserDefault(request, response);
        JsonUtil.toJsonString(json);
        Assert.isTrue("OK".equals(json.getCode()));
    }

    /**
     * 修改用户历史记录
     * 
     * @throws IOException
     */
    @Test
    public void testUpdateUserDefaultController() throws IOException {
       
        request.setParameter("tfcode", "SHXX02010101");

        ResultJSON json = navigationController.updateUserDefault(request, response);

        JsonUtil.toJsonString(json);
        Assert.isTrue("OK".equals(json.getCode()));

    }
}