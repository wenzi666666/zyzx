package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

public class DistrictResFormatServiceTest extends BaseServiceTestCase {

    @Resource
    ResTypeService resTypeService;
    @Resource
    ResFormatService resFormatService;

    @Test
    public void testDisResTypeService() throws IOException {
        // 格式
        List<String> formats = new ArrayList<String>();

        // 资源类型id
        String tfcode = "RJGZ040101";
        int fromFlag = 3;

        formats = resFormatService.getDisResFormats(tfcode, fromFlag);

        Assert.isTrue(formats.size() > 0);
        System.out.println(formats.size());
        for (int i = 0; i < formats.size(); i++)
            System.out.print(formats.get(i) + ",");
    }
}