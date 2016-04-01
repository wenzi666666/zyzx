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

/**
 * 系统资源格式测试
 * 
 * @author WeiCuicui
 *
 */
public class SysResFormatServiceTest extends BaseServiceTestCase {

    @Resource
    ResFormatService resFormatService;
    @Resource
    ResTypeService resTypeService;

    @Test
    public void testSysFormatService() throws IOException {

        List<String> formats = new ArrayList<String>();

        long poolId = 6;
        String pTfcode = "RJCZ01010501";
        int typeId = 1;

        // 根据 resourceIds和typeIds，查询资源格式
        formats = resFormatService.getSysResFormats(poolId, pTfcode, typeId);

        Assert.isTrue(formats.size() > 0);
        System.out.println(formats.size());
        for (int i = 0; i < formats.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(formats.get(i) + ",");
        }

    }

}
