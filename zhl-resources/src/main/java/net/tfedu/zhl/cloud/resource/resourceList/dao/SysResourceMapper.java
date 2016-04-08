package net.tfedu.zhl.cloud.resource.resourceList.dao;

import java.util.List;

import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResource;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

/**
 * 系统资源 mapper
 * 
 * @author WeiCuicui
 *
 */
public interface SysResourceMapper extends CoreMapper<SysResource> {

    // 查询系统资源列表
    public List<SysResourceEntity> SelectSysResources(@Param("sys_from") List<Integer> sys_from,
            @Param("fileFormat") String fileFormat, @Param("resourceIds") List<Long> resourceIds,
            @Param("pTfcode") String tfcode, @Param("orderBy") int orderBy, @Param("typeIds") List<Integer> typeIds);

    // 获取一条系统资源的详细信息
    public ResPreviewInfo getSysResInfo(@Param("resId") long resId,@Param("userId")long userId);

    // 根据structCode，查询一个版本的目录
    public List<ResNavEntity> getSysNav(@Param("structCode") String structCode);

    // 根据资源id，获得所有版本的structCode
    public List<String> getAllRescodes(@Param("resId") long resId, @Param("curTfcode") String curTfcode);
    
    // 资源预览页面推荐列表  查询系统资源
    public List<SysResourceEntity> getAllSysRes_Preview(@Param("sys_from") List<Integer> sys_from,
            @Param("fileFormat") String fileFormat, @Param("resourceIds") List<Long> resourceIds,
            @Param("pTfcode") String tfcode, @Param("orderBy") int orderBy, @Param("typeIds") List<Integer> typeIds,@Param("resId")long resId);
    
    // e备课，查询系统资源（限制资源类型，资源title模糊查询）
    public List<SysResourceEntity> getAllSysRes_EPrepare(@Param("sys_from") List<Integer> sys_from,
            @Param("fileFormat") String fileFormat, @Param("resourceIds") List<Long> resourceIds,
            @Param("pTfcode") String tfcode, @Param("orderBy") int orderBy, @Param("typeIds") List<Integer> typeIds,@Param("searchWord")String searchWord);

}
