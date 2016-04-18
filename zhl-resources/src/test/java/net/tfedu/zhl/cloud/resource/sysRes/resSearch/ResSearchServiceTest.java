package net.tfedu.zhl.cloud.resource.sysRes.resSearch;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.cloud.resource.resSearch.service.ResSearchService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 资源检索页面service单元测试
 * 
 * @author WeiCuicui
 *
 */
public class ResSearchServiceTest extends BaseServiceTestCase {

    @Resource
    ResSearchService resSearchService;

    /**
     * 资源检索结果的列表
     * @throws IOException
     */
    @Test
    public void sysResourceServiceTest() throws IOException {

        // 查询结果，封装为pagination
        Pagination<ResSearchResultEntity> pagination = null;

        // 检索范围 0 全部资源 1 系统资源 3 校本资源 4 区本资源
        int fromFlag = 0;

        String format = "全部";

        String searchKeyword = "乐";
        
        long userId = 699230735;

        // 页码
        int page = 1;

        // 每页记录数目
        int perPage = 10;

        pagination = resSearchService.getResources(fromFlag, SysFrom.sys_from, searchKeyword, format, page, perPage,userId);

        if (pagination != null) {
            System.out.println(pagination.getPage());
            System.out.println(pagination.getPerPage());
            System.out.println(pagination.getTotal());
            System.out.println(pagination.getTotalLines());
            List<ResSearchResultEntity> list = pagination.getList();
            for (int i = 0; i < list.size() && i < 10; i++) {
                log.info(list.get(i).toString());
            }
        }
    }
    
    /**
     * 资源检索结果的格式 
     * @throws IOException
     */
    @Test
	public void testResSearchFormat()throws IOException{
		String searchKeyword = "荷塘";
		int fromFlag = 1; //系统资源
		long userId = 699230735;
		
		List<String> list = resSearchService.getFileFormats(searchKeyword, fromFlag, SysFrom.sys_from,userId);
		
		Assert.isTrue(list.size() > 0);
		
		for(int i = 0; i < list.size(); i++){
			System.out.print(list.get(i) + ", ");
		}
	}
}
