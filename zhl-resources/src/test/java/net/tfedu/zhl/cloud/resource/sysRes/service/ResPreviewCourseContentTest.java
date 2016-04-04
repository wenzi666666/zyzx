package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 从资源预览页面返回时，查询对应的所有目录
 * 
 * @author WeiCuicui
 *
 */
public class ResPreviewCourseContentTest extends BaseServiceTestCase {

    @Resource
    ResPreviewService resPreviewService;

    @Test
    public void testResCourseContents() throws IOException {
        String tfcode = "RJXX0101";
        JUserDefault content = resPreviewService.getPnodes(tfcode);
        Assert.isTrue(content != null);
        if (content != null) {
            System.out.println(content.getId() + ":" + content.getTfcode() + ":" + content.getTermId() + ":"
                    + content.getSubjectId() + ":" + content.getEditionId());
        }
    }
}
