package net.tfedu.zhl.cloud.resource.sysRes.resList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.service.DisResService;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;


/**
 * 系统、校本、区本资源列表的controller单元测试
 * @author WeiCuicui
 *
 */
public class ResListServiceTest extends BaseServiceTestCase{

	@Resource
    SysResourceService sysResourceService;
	
    @Resource
    ResTypeService resTypeService;
    
    @Resource
    DisResService disResService;
   
    
    /**
     * 系统资源列表查询的Service单元测试
     * @throws IOException
     */
    @Test
    public void sysResourceServiceTest() throws IOException {
        // 查询结果，封装为pagination
        Pagination<SysResourceEntity> pagination = null;
        // 资源库id
        long poolId = 0;

        // 类型Id
        int mTypeId = 1;

        // 资源格式
        String fileFormat = "全部";

        // 课程tfcode
        String tfcode = "RJGZ040101";

        // 排序方式（综合排序；最多下载；最新发布）
        int orderBy = 0;

        // 页码
        int page = 1;

        // 每页的记录数
        int perPage = 10;
        
        //系统资源的来源
        List<Integer> sys_from = new ArrayList<Integer>();
        sys_from.add(0);
        sys_from.add(1);
        sys_from.add(2);

        // 查询出的系统资源信息
        pagination = sysResourceService.getAllSysRes(poolId, mTypeId, fileFormat, tfcode, orderBy, page, perPage,sys_from,30);
        if (pagination != null) {
            System.out.println(pagination.getPage());
            System.out.println(pagination.getPerPage());
            System.out.println(pagination.getTotal());
            System.out.println(pagination.getTotalLines());
            List<SysResourceEntity> list = pagination.getList();
            for (int i = 0; i < list.size() && i < 10; i++) {
                log.info(list.get(i).toString());
            }
        }
    }
    
    
    
    /**
     * 区本、校本资源列表查询的单元测试
     * @throws IOException
     */
    @Test
    public void disResourceServiceTest() throws IOException {
        Pagination<DisResourceEntity> pagination = null;
        long userId = 699230735;
        // 类型Id
        int mTypeId = 0;

        // 资源格式
        String fileFormat = "全部";

        // 课程tfcode
        String tfcode = "RJGZ040101";

        // 排序方式（综合排序；最多下载；最新发布）
        int orderBy = 0;

        // 页码
        int page = 1;

        // 每页的记录数
        int perPage = 10;

        // 资源来源 校本资源
        int fromFlag = 3;

        pagination = disResService.selectAllDisRes(userId, mTypeId, fileFormat, tfcode, orderBy, page, perPage,
                fromFlag,30);
        if (pagination != null) {
            System.out.println(pagination.getPage());
            System.out.println(pagination.getPerPage());
            System.out.println(pagination.getTotal());
            System.out.println(pagination.getTotalLines());
            List<DisResourceEntity> list = pagination.getList();
            for (int i = 0; i < list.size() && i < 10; i++) {
                log.info(list.get(i).toString());
            }
        }

    }
}
