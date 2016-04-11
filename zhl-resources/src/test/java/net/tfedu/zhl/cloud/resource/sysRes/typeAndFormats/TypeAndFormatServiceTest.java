package net.tfedu.zhl.cloud.resource.sysRes.typeAndFormats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 资源类型、格式的service单元测试
 * @author WeiCuicui
 *
 */
public class TypeAndFormatServiceTest extends BaseServiceTestCase{

	@Resource
    ResTypeService resTypeService;
	
	@Resource
    ResFormatService resFormatService;

	/**
	 * 系统资源类型service的单元测试
	 * @throws IOException
	 */
    @Test
    public void testResTypeService() throws IOException {

        List<ResType> types = new ArrayList<ResType>();

        long poolId = 4;
        String pTfcode = "RJCZ01010501";

       // types = resTypeService.getSysResTypes(poolId, pTfcode);
        types = resTypeService.getSysResTypes_EPrepare(poolId, pTfcode, SysFrom.removeTypeIds);

        Assert.isTrue(types.size() > 0);

        for (int i = 0; i < types.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(types.get(i) + ",");
        }
    }
    
    /**
     * 系统资源格式service的单元测试
     * @throws IOException
     */
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
    
    /**
     * 校本、区本资源类型service的单元测试
     * @throws IOException
     */
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
    
    /**
     * 区本校本资源格式service的单元测试
     * @throws IOException
     */
    @Test
    public void testDisResFormatService() throws IOException {
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
