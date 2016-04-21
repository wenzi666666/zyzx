package net.tfedu.zhl.cloud.resource.resourceList.service;

import java.util.List;

import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;

/**
 * 区本、校本资源的service
 * 
 * @author WeiCuicui
 *
 */
public interface DisResService {


    /**
     * 根据类型ids，所在区id / 校id，查询区本、校本资源信息
     * @param fromFlag
     * @param fileFormat
     * @param typeIds
     * @param tfcode
     * @param orderBy
     * @param schoolId
     * @param districtId
     * @param page
     * @param perPage
     * @param expire
     * @return
     */
    public Pagination<DisResourceEntity> selectDisRes(int fromFlag, String fileFormat, List<Integer> typeIds,
            String tfcode, int orderBy, long schoolId, long districtId, int page, int perPage,int expire);

    /**
     * 查询区本、校本资源信息
     * @param userId
     * @param mTypeId
     * @param fileFormat
     * @param tfcode
     * @param orderBy
     * @param page
     * @param perPage
     * @param fromFlag
     * @param expire
     * @return
     */
    public Pagination<DisResourceEntity> selectAllDisRes(long userId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage, int fromFlag,int expire);
    
    
    /**
     * 根据类型ids，所在区id / 校id，查询区本、校本资源信息，e备课
     * @param fromFlag
     * @param fileFormat
     * @param typeIds
     * @param tfcode
     * @param orderBy
     * @param schoolId
     * @param districtId
     * @param page
     * @param perPage
     * @param searchWord
     * @param expire
     * @return
     */
    public Pagination<DisResourceEntity> selectDisRes_EPrepare(int fromFlag, String fileFormat, List<Integer> typeIds,
            String tfcode, int orderBy, long schoolId, long districtId, int page, int perPage,String searchWord,int expire);

    /**
     * 查询区本、校本资源信息，e备课
     * @param userId
     * @param mTypeId
     * @param fileFormat
     * @param tfcode
     * @param orderBy
     * @param page
     * @param perPage
     * @param fromFlag
     * @param searchWord
     * @param removeTypeIds
     * @param expire
     * @return
     */
    public Pagination<DisResourceEntity> selectAllDisRes_EPrepare(long userId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage, int fromFlag,String searchWord,List<Integer> removeTypeIds,int expire);

}
