package net.tfedu.zhl.cloud.resource.resSearch.service;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;

public interface ResSearchService {

    /**
     * 跨库检索资源
     * @param fromFlag
     * @param sys_from
     * @param searchKeyword
     * @param format
     * @param page
     * @param perPage
     * @return
     */

    public Pagination<ResSearchResultEntity> getResources(int fromFlag, List<Integer> sys_from, String searchKeyword,
            String format, int page, int perPage,long userId,int expire);
    
   
    /**
     * 查询资源格式
     * @param searchKeyword
     * @param fromFlag
     * @param sys_from
     * @return
     */
    public List<String> getFileFormats(String searchKeyword,int fromFlag,List<Integer> sys_from,long userId);
    
    
    
    /**
     * 分页查询指定资源库下的系统资源
     * @param respool
     * @param searchKeyword
     * @param page
     * @param perPage
     * @param expire
     * @return
     */
    public Pagination<ResSearchResultEntity> querySysResource(List<Integer> sys_from,int respool,String searchKeyword, int page, int perPage,int expire);
    
    
    
    /**
     * 批量查询 （不同类型的）资源的信息
     * @param assetIds         自建资源id集合
     * @param sysResourceIds   系统资源id集合
     * @param districtResIds   区校资源id集合
     * @return 返回的信息以3.0对接云平台的标准为准
     * 	fromflag, isfinished,'asset_res' as restype,rescode,assetid,assetname,fpath,assetpath,assetdesc,isdwj
     */
    public List<Map<String,Object>> queryBatchResourceInfo (Long[] assetIds,Long[] sysResourceIds,Long[] districtResIds);
    
    
    
    
    
    
    /**
     * 获取用户自建资源涉及的系统资源类型
     * @param userId		用户
     * @param isCollect     标签	1:自建	2:收藏	3我的共享
     * @param courseIds		自建目录的id
     * @return
     */
    public List<Map<String,Object>> getAssetResourceType(Long userId,Integer isCollect,List<Long> courseIds);
    
    
    
    /**
     * 获取用户自建资源涉及的文件格式
     * @param userId		用户
     * @param isCollect     标签	1:自建	2:收藏	3我的共享
     * @param courseIds		自建目录的id
     * @return
     */
    public List<Map<String,Object>> getAssetFileFormat(Long userId,Integer isCollect,List<Long> courseIds);
    
    
    /**
     * 获取系统资源涉及的系统资源类型
     * @param exceptPoolIds    系统要求排除的资源库的id的集合(对接接口中排除多媒体教辅库)
     * @param poolId		   查询的6大库的id，null或0时，查询全部库
     * @param syscourseCodes 查询的系统目录的范围
     * @param sysFrom		 系统资源表中的fromflag字段,区分系统资源的来源（知好乐、梯子网等）
     * @return
     */
    public List<Map<String,Object>> getSysResourceType(String exceptPoolIds,Integer poolId,List<String> syscourseCodes,String sysFrom);
    
    /**
     * 获取系统资源涉及的文件格式
     * @param exceptPoolIds	 系统要求排除的资源库的id的集合(对接接口中排除多媒体教辅库)
     * @param poolId		查询的6大库的id，null或0时，查询全部库
     * @param syscourseCodes查询的系统目录的范围
     * @param sysFrom		系统资源表中的fromflag字段,区分系统资源的来源（知好乐、梯子网等）
     * @return
     */
    public List<Map<String,Object>> getSysResourceFileFormat(String exceptPoolIds,Integer poolId,List<String> syscourseCodes,String sysFrom);
    
    
    
}
