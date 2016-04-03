package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 资源类型 的 service测试
 * 
 * @author WeiCuicui
 *
 */
public class SysResTypeServiceTest extends BaseServiceTestCase {

    @Resource
    ResTypeService resTypeService;

    @Test
    public void testResTypeService() throws IOException {

        List<ResType> types = new ArrayList<ResType>();

        long poolId = 4;
        String pTfcode = "RJCZ01010501";

        types = resTypeService.getSysResTypes(poolId, pTfcode);

        Assert.isTrue(types.size() > 0);

        for (int i = 0; i < types.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(types.get(i) + ",");
        }
    }
}