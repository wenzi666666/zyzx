package net.tfedu.zhl.cloud.resource.resourceList.dao;

import java.util.List;

import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisAndSchoolEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DistrictRes;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

public interface DistrictResMapper extends CoreMapper<DistrictRes> {
    // 获得区、校id
    public DisAndSchoolEntity getDisAndSchool(long userId);

    // 查询区本、校本资源信息
    public List<DisResourceEntity> selectDisRes(@Param("fromFlag") int fromFlag, @Param("fileFormat") String fileFormat,
            @Param("typeIds") List<Integer> typeIds, @Param("tfcode") String tfcode, @Param("orderBy") int orderBy,
            @Param("schoolId") long schoolId, @Param("districtId") long districtId);

    // 查询一条区本、校本资源的详细信息
    public ResPreviewInfo getDisResInfo(@Param("fromFlag") int fromFlag, @Param("resId") long resId);

    // 根据资源id，获得所有版本的structCode
    public List<String> getAllDisRescodes(@Param("resId") long resId, @Param("curTfcode") String curTfcode);
}