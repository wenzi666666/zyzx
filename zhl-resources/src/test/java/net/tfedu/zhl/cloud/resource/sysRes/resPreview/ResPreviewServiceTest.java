package net.tfedu.zhl.cloud.resource.sysRes.resPreview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResRecommendationEntity;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 资源预览页面的service单元测试
 * @author WeiCuicui
 *
 */
public class ResPreviewServiceTest extends BaseServiceTestCase {

	@Resource ResPreviewService resPreviewService;
	
	/**
	 * 根据resId，查询一条资源的详细信息service单元测试
	 * @throws IOException
	 */
	@Test
	public void testGetOneResService()throws IOException{
		long resId = 12;
		int fromFlag = 0;
		long userId = 699230735;
		
		ResPreviewInfo info = resPreviewService.getResPreviewInfo(resId, userId, fromFlag);
		
		Assert.isTrue(info != null);
		
		log.info(info.toString());
	}
	
	/**
	 * 查询资源的所有版本service单元测试
	 * @throws IOException
	 */
	@Test
    public void testResPreview() throws IOException {

        // 0 系统资源；1 自建资源；2 共享资源；3 校本资源；4 区本资源
        int fromFlag = 0;

        // 资源id
        long resId = 1;

        // 当前所在结点
        String curTfcode = "RJXX0101";

        List<List<ResNavEntity>> result = new ArrayList<List<ResNavEntity>>();

        result = resPreviewService.getAllResNavs(resId, fromFlag, curTfcode);

        Assert.isTrue(result.size() > 0);

        for (int i = 0; i < result.size(); i++)
            for (int j = 0; j < result.get(i).size(); j++) {
                log.info(result.get(i).get(j).toString());
            }
    }
	
	/**
	 * 资源检索结果的资源推荐列表
	 * @throws IOException
	 */
	@Test
	public void testGetResRecommendation_search()throws IOException{
		
		int fromFlag = 0;
		long resId = 3;
		int page = 1;
		int perPage = 10;
		String searchKeyword = "数学";
		long userId = 699230735;
		
		Pagination<ResRecommendationEntity> list = resPreviewService.searchRecommendation(fromFlag, resId, userId, searchKeyword, SysFrom.sys_from, page, perPage);
		
		Assert.isTrue(list.getList().size() > 0);
		
	}
	
/*	@Test
	public void testGetSysResRecommendation()throws IOException{
		
	}*/
}
