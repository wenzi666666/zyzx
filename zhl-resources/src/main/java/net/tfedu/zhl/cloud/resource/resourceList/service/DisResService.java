package net.tfedu.zhl.cloud.resource.resourceList.service;

import java.util.List;

import net.tfedu.zhl.cloud.resource.resourceList.entity.DisAndSchoolEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;

/**
 * 区本、校本资源的service
 * 
 * @author WeiCuicui
 *
 */
public interface DisResService {

    // 获得区、校id
    public DisAndSchoolEntity getDisAndSchool(long userId);

    // 查询区本、校本资源信息
    public Pagination<DisResourceEntity> selectDisRes(int fromFlag, String fileFormat, List<Integer> typeIds,
            String tfcode, int orderBy, long schoolId, long districtId, int page, int perPage);

    // 查询区本、校本资源信息
    public Pagination<DisResourceEntity> selectAllDisRes(long userId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage, int fromFlag);
    
    
    // 查询区本、校本资源信息，e备课
    public Pagination<DisResourceEntity> selectDisRes_EPrepare(int fromFlag, String fileFormat, List<Integer> typeIds,
            String tfcode, int orderBy, long schoolId, long districtId, int page, int perPage,String searchWord);

    // 查询区本、校本资源信息，e备课
    public Pagination<DisResourceEntity> selectAllDisRes_EPrepare(long userId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage, int fromFlag,String searchWord,List<Integer> removeTypeIds);

}
