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
 * 区本校本资源类型测试
 * 
 * @author WeiCuicui
 *
 */
public class DistrictResTypeServiceTest extends BaseServiceTestCase {

    @Resource
    ResTypeService resTypeService;

    @Test
    public void testDisResTypeService() throws IOException {

        // 传递参数
        String tfcode = "RJGZ040101";
        // 3校本 4区本
        int fromFlag = 3;

        // 定义类型结果集
        List<ResType> types = new ArrayList<ResType>();

        // 查询类型id
        types = resTypeService.getDisResTypes(tfcode, fromFlag);

        Assert.isTrue(types.size() > 0);
        System.out.println(types.size());
        for (int i = 0; i < types.size(); i++)
            System.out.print(types.get(i) + ",");
    }

}