package net.tfedu.zhl.cloud.resource.resSearch.dao;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.cloud.resource.resPreview.entity.ResRecommendationEntity;
import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

public interface ResSearchMapper extends CoreMapper<ResSearchResultEntity> {

	/**
     * 当资源来源为全部时，查询所有满足条件的资源
     */
    public List<ResSearchResultEntity> getAllResources(@Param("searchKeyword") String searchKeyword,
            @Param("format") String format, @Param("sys_from") List<Integer> sys_from,@Param("schoolId") long schoolId,@Param("districtId") long districtId);

    /**
     * 根据检索关键字查询所有符合条件的系统资源
     */
    public List<ResSearchResultEntity> getAllSysResources(@Param("searchKeyword") String searchKeyword,
            @Param("format") String format, @Param("sys_from") List<Integer> sys_from);

    /**
     * 根据检索关键字查询所有符合条件的区本、校本资源
     */
    public List<ResSearchResultEntity> getAllDisResources(@Param("searchKeyword") String searchKeyword,
            @Param("fromFlag") int fromFlag, @Param("format") String format,@Param("schoolId") long schoolId,@Param("districtId") long districtId);
    
    /**
     * 根据关键字，查询所有资源查询结果的格式
     */
    public List<String> getAllFileFormats(@Param("searchKeyword") String searchKeyword,@Param("sys_from") List<Integer> sys_from,@Param("schoolId") long schoolId,@Param("districtId") long districtId);
    
    /**
     * 根据关键字，fromFlag，查询区本、校本资源的格式
     */
    public List<String> getDisFileFormats(@Param("searchKeyword") String searchKeyword,@Param("fromFlag") int fromFlag,@Param("schoolId") long schoolId,@Param("districtId") long districtId);
    
    /**
     * 根据关键字，查询系统资源的格式
     */
    public List<String> getSysFileFormats(@Param("searchKeyword") String searchKeyword,@Param("sys_from") List<Integer> sys_from);
    
   
    /**
     * 检索范围为全部时，查询推荐列表
     */
    public List<ResRecommendationEntity> getAllResources_preview(@Param("searchKeyword") String searchKeyword,
             @Param("sys_from") List<Integer> sys_from,@Param("schoolId") long schoolId,@Param("districtId") long districtId,@Param("resId")long resId,
             @Param("fileFormat") String format);

    /**
     * 检索范围为系统资源时，查询推荐列表
     */
    public List<ResRecommendationEntity> getAllSysResources_preview(@Param("searchKeyword") String searchKeyword,@Param("sys_from") List<Integer> sys_from,@Param("resId")long resId,
            @Param("fileFormat") String format);
            

    /**
     * 检索范围为区本、校本资源时，查询推荐列表
     */
    public List<ResRecommendationEntity> getAllDisResources_preview(@Param("searchKeyword") String searchKeyword,
            @Param("fromFlag") int fromFlag,@Param("schoolId") long schoolId,@Param("districtId") long districtId,@Param("resId")long resId,
            @Param("fileFormat") String format);
    
    

    /**
     * 查询指定资源库下的系统资源
     * @param searchKeyword
     * @param respool
     * @return
     */
	public List<ResSearchResultEntity> querySysResource(@Param("sys_from") List<Integer> sys_from,@Param("searchKeyword")String searchKeyword, @Param("respool")int respool);
	
	
	

    /**
     * 批量查询自建资源的信息
     * @param assetIds         自建资源id集合
     * @return 返回的信息以3.0对接云平台的标准为准
     * 	fromflag, isfinished,'asset_res' as restype,rescode,assetid,assetname,fpath,assetpath,assetdesc,isdwj
     */
    public List<Map<String,Object>> queryAssets (@Param("assetIds")Long[] assetIds);
    
    
    
    /**
     * 批量查询 系统资源的信息
     * @param sysResourceIds   系统资源id集合
     * @return 返回的信息以3.0对接云平台的标准为准
     * 	fromflag, isfinished,'asset_res' as restype,rescode,assetid,assetname,fpath,assetpath,assetdesc,isdwj
     */
    public List<Map<String,Object>> querySysResources (
    		@Param("sysResourceIds")Long[] sysResourceIds);
    
    
    
    /**
     * 批量查询  区校资源的信息
     * @param districtResIds   区校资源id集合
     * @return 返回的信息以3.0对接云平台的标准为准
     * 	fromflag, isfinished,'asset_res' as restype,rescode,assetid,assetname,fpath,assetpath,assetdesc,isdwj
     */
    public List<Map<String,Object>> queryDistrcitResources (@Param("districtResIds")Long[] districtResIds);
    
    
    
	
}
