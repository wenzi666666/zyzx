package net.tfedu.zhl.cloud.resource.resSearch.dao;

import java.util.List;

import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

public interface ResSearchMapper extends CoreMapper<ResSearchResultEntity> {

    /**
     * 当资源来源为全部时，查询所有满足条件的资源
     * 
     * @param searchKeyword
     * @param format
     * @param sys_from
     * @return
     */
    public List<ResSearchResultEntity> getAllResources(@Param("searchKeyword") String searchKeyword,
            @Param("format") String format, @Param("sys_from") List<Integer> sys_from);

    /**
     * 根据检索关键字查询所有符合条件的系统资源
     * 
     * @param searchKeyword
     * @param format
     * @param sys_from
     * @return
     */
    public List<ResSearchResultEntity> getAllSysResources(@Param("searchKeyword") String searchKeyword,
            @Param("format") String format, @Param("sys_from") List<Integer> sys_from);

    /**
     * 根据检索关键字查询所有符合条件的区本、校本资源
     * 
     * @param searchKeyword
     * @param fromFlag
     * @param format
     * @return
     */
    public List<ResSearchResultEntity> getAllDisResources(@Param("searchKeyword") String searchKeyword,
            @Param("fromFlag") int fromFlag, @Param("format") String format);

}
