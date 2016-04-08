package net.tfedu.zhl.cloud.resource.sysRes.controller;

import java.io.IOException;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.controller.SysResTypeController;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;

public class SysResTypeControllerTest extends BaseControllerTestCase {

    @Resource
    SysResTypeController sysResTypeController;

    @Test
    public void testSysTypeController() throws IOException {
        //这里不能新建newGet
//        request = newGet("/resRestAPI/v1.0/sysResource/types");

        request.setParameter("poolId", "0");
        request.setParameter("tfcode", "BJCZ0101");
        ResultJSON json = sysResTypeController.getSysResTypesByPool(request, response);
        log.debug(json.toString());
    }
}
