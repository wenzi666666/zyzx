package net.tfedu.zhl.cloud.resource.sysRes.typeAndFormats;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.controller.TypesAndFormatsController;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

public class TypeAndFormatControllerTest extends BaseControllerTestCase{

	@Resource TypesAndFormatsController typesAndFormatsController;
	
	/**
	 * 查询所有的资源库 controller单元测试
	 * @throws IOException
	 */
	@Test
    public void testGetAllTerms() throws IOException {
        request = newGet("/resRestAPI/v1.0/pools");
        ResultJSON resultJSON = typesAndFormatsController.getAllPools(request, response);

        Assert.isTrue(resultJSON != null);
        List<ResPool> pools = (List<ResPool>) resultJSON.getData();
        Assert.isTrue(pools.size() > 0);
        for (int i = 0; i < pools.size(); i++) {
            System.out.println(pools.get(i).getId() + ":" + pools.get(i).getName());
        }
    }
	
	
}
