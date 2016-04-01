package net.tfedu.zhl.cloud.resource.prepare.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContent;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentView;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.helper.CoreMapper;

public interface JPrepareContentMapper extends CoreMapper<JPrepareContent> {

    /**
     * 分页获取我的备课资源
     * 
     * @param userId
     * @param unifyTypeId
     * @param fileFormat
     * @param page
     * @param prePage
     * @return
     */
    public List<JPrepareContentView> getPrepareContentListByUserId(@Param("userId") Long userId,
            @Param("unifyTypeId") Long unifyTypeId, @Param("fileFormat") String fileFormat);

    /**
     * 将指定资源从我的备课夹中清除
     * 
     * @param userId
     * @param resIds
     * @param fromFlags
     */
    public void removeMyPrepareContentResource(Long userId, Long contId, Integer contType);

}