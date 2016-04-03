package net.tfedu.zhl.cloud.resource.sysRes.service;

import java.io.IOException;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;

public class ResPreviewServiceTest extends BaseServiceTestCase {

    @Resource
    ResPreviewService resPreviewService;

    @Test
    public void testResPreview() throws IOException {
        // 0 系统资源；1 自建资源；2 共享资源；3 校本资源；4 区本资源
        int fromFlag = 0;

        // 资源id
        long resId = 1;

        ResPreviewInfo previewInfo = null;

        // 查询一条资源的详细信息
        previewInfo = resPreviewService.getResPreviewInfo(resId, fromFlag);

        if (previewInfo != null) {
            log.info(previewInfo.toString());
        }
    }
}
