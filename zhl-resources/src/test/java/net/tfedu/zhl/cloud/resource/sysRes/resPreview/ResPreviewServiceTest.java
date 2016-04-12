package net.tfedu.zhl.cloud.resource.sysRes.resPreview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
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
	public void testResPreviewService()throws IOException{
		long resId = 164882428;
		int fromFlag = 1;
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
	 * 从资源预览页面返回时，查询对应的所有目录
	 * @throws IOException
	 *//*
	@Test
    public void testResCourseContents() throws IOException {
        String tfcode = "RJXX0101";
        JUserDefault content = resPreviewService.getPnodes(tfcode);
        Assert.isTrue(content != null);
        if (content != null) {
            System.out.println(content.getId() + ":" + content.getTfcode() + ":" + content.getTermId() + ":"
                    + content.getSubjectId() + ":" + content.getEditionId());
        }
    }*/
}
