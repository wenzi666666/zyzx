package net.tfedu.zhl.cloud.resource.resourceList.dao;

import java.util.List;

import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResRecommendationEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisAndSchoolEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DistrictRes;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

public interface DistrictResMapper extends CoreMapper<DistrictRes> {
    /**
     *  获得区、校id
     * @param userId
     * @return
     */
    public DisAndSchoolEntity getDisAndSchool(long userId);

    /**
     * 查询区本、校本资源信息
     * @param fromFlag
     * @param fileFormat
     * @param typeIds
     * @param tfcode
     * @param orderBy
     * @param schoolId
     * @param districtId
     * @return
     */
    public List<DisResourceEntity> selectDisRes(@Param("fromFlag") int fromFlag, @Param("fileFormat") String fileFormat,
            @Param("typeIds") List<Integer> typeIds, @Param("tfcode") String tfcode, @Param("orderBy") int orderBy,
            @Param("schoolId") long schoolId, @Param("districtId") long districtId);

    /**
     *  查询一条区本、校本资源的详细信息
     * @param fromFlag
     * @param resId
     * @param userId
     * @return
     */
    public ResPreviewInfo getDisResInfo(@Param("fromFlag") int fromFlag, @Param("resId") long resId,@Param("userId")long userId);

    /**
     *  根据资源id，获得所有版本的structCode
     * @param resId
     * @param curTfcode
     * @return
     */
    public List<String> getAllDisRescodes(@Param("resId") long resId, @Param("curTfcode") String curTfcode);
    
    /**
     *  查询区本、校本资源信息，用于资源预览页面的推荐
     * @param fromFlag
     * @param typeIds
     * @param tfcode
     * @param schoolId
     * @param districtId
     * @return
     */
    public List<ResRecommendationEntity> selectDisRes_Preview(@Param("fromFlag") int fromFlag,
            @Param("typeIds") List<Integer> typeIds, @Param("tfcode") String tfcode, 
            @Param("schoolId") long schoolId, @Param("districtId") long districtId,@Param("resId")long resId,@Param("orderBy")int orderBy);
    
    /**
     *  查询区本、校本资源信息，用于e备课
     * @param fromFlag
     * @param fileFormat
     * @param typeIds
     * @param tfcode
     * @param orderBy
     * @param schoolId
     * @param districtId
     * @param searchWord
     * @return
     */
    public List<DisResourceEntity> selectDisRes_EPrepare(@Param("fromFlag") int fromFlag, @Param("fileFormat") String fileFormat,
            @Param("typeIds") List<Integer> typeIds, @Param("tfcode") String tfcode, @Param("orderBy") int orderBy,
            @Param("schoolId") long schoolId, @Param("districtId") long districtId,@Param("searchWord") String searchWord);
    
    /**
     * 更新点击次数（+1）
     * @param rescode
     */
    public void updateClickTime(String rescode);
    
    
    /**
     * 更新下载次数（+1）
     * @param rescode
     */
    public void updateDownloadTime(String rescode);
    
}
