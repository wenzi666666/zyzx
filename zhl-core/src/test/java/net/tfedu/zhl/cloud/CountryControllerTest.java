package net.tfedu.zhl.cloud;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import net.tfedu.zhl.cloud.demo.controller.CountryController;
import net.tfedu.zhl.cloud.demo.entity.Country;
import net.tfedu.zhl.cloud.utils.datatype.JsonUtil;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 * @Transactional 注释标签是表明此测试类的事务启用，这样所有的测试方案都会自动的 rollback， 即您不用自己清除自己所做的任何对数据库的变更了
 * @author bruce
 *
 */
@Transactional
public class CountryControllerTest extends BaseControllerTestCase {

    @Resource
    CountryController countryController;

    @Test
    // @Rollback(false)
    public void testAdd() throws Exception {
        Country c = new Country();
        c.setCname("china");
        c.setCcode("1000");
        Map<?, ?> map = countryController.create(request, response, c);
        JsonUtil.toJsonString(map);
        Assert.isTrue(map.containsKey("message"));
    }

    @Test
    // @Rollback(false)
    public void testAddArray() throws Exception {
        Country[] list = new Country[10];
        for (int i = 0; i < 10; i++) {
            Country c = new Country();
            c.setCname("china");
            c.setCcode("1000" + i);
            list[i] = c;
        }

        Map<?, ?> map = countryController.addArray(request, response, list);
        JsonUtil.toJsonString(map);
        Assert.isTrue(map.containsKey("message"));
    }

    @Test
    // @Rollback(false)
    public void testUpdate() throws Exception {
        Country entity = new Country();
        entity.setCname("china");
        Map<?, ?> map = countryController.update(request, response, entity);
        JsonUtil.toJsonString(map);
        Assert.isTrue(map.containsKey("message"));
    }

    @Test
    // @Rollback(false)
    public void testDelete() throws Exception {
        Map<?, ?> map = countryController.delete(request, 6);
        JsonUtil.toJsonString(map);
        Assert.isTrue(map.containsKey("message"));
    }

    @Test
    public void testGetPage() {
        request.addParameter("pageNum", "1");
        request.addParameter("pageSize", "10");
        Map<?, ?> map = countryController.getPage(request, response);
        JsonUtil.toJsonString(map);
        Assert.isTrue(map.containsKey("message"));
    }

    @Test
    public void testGetSelective() {
        Map<?, ?> map = countryController.queryMaps(request, response);
        JsonUtil.toJsonString(map);
        Assert.isTrue(map.containsKey("message"));
    }

    @Test
    public void testGetByProperty() {
        Map<?, ?> map = countryController.queryIds(request, response);
        JsonUtil.toJsonString(map);
        Assert.isTrue(map.containsKey("message"));
    }
}
