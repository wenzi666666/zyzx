package net.tfedu.zhl.cloud.resource.poolTypeFormat.dao;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

/**
 * 查询资源类型 mapper
 * 
 * @author WeiCuicui
 *
 */
public interface ResTypeMapper extends CoreMapper<ResType> {

    /**
     * 
     * 系统资源ids
     * 
     * @param map
     * @return
     */
    public List<Long> getAllSysResIds(HashMap<String, Object> map);

    /**
     * 区本、校本资源ids
     * 
     * @param map
     * @return
     */
    public List<Long> getAllDisResIds(HashMap<String, Object> map);

    
    public List<ResType> getSysFirstLevelType(@Param("poolId") long poolId,
            @Param("pTfcode") String pTfcode,@Param("sys_from")List<Integer> sys_from);
    
    public List<ResType> getSysFirstLevelType_ePrepare(@Param("poolId") long poolId,
            @Param("pTfcode") String pTfcode,@Param("sys_from")List<Integer> sys_from,@Param("removeTypes") List<Integer> removeTypes);

    /**
     * 系统资源：当资源库选择 “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型。当资源库为“理化生实验”时，只显示“全部”
     * 
     * @param resourceIds
     * @param typeIds
     * @return
     */
    public List<ResType> getSysSecondLevelType(@Param("poolId") long poolId,
            @Param("pTfcode") String pTfcode,@Param("sys_from")List<Integer> sys_from);
    
    public List<ResType> getSysSecondLevelType_ePrepare(@Param("poolId") long poolId,
            @Param("pTfcode") String pTfcode,@Param("sys_from")List<Integer> sys_from,@Param("removeTypes") List<Integer> removeTypes);

    /**
     * 系统资源：根据资源库id、父类型id，得到父类型的所有子类型及其自身
     * 
     * @param poolId
     * @param MType
     * @return
     */
    public List<Integer> getTypesByPMTypeAndPool(@Param("poolId") long poolId, @Param("MType") int MType);

    
    /**
     * 区本校本资源：查询资源类型
     * 
     * @return
     */
    public List<ResType> getDisResType(@Param("fromFlag") int fromFlag, @Param("pTfcode") String pTfcode,@Param("schoolId") long schoolId, @Param("districtId") long districtId);
    
    /**
     * 区本校本资源：查询资源类型，e备课
     * 
     * @return
     */
    public List<ResType> getDisResType_EPrepare(@Param("fromFlag") int fromFlag, @Param("pTfcode") String pTfcode,@Param("schoolId") long schoolId, @Param("districtId") long districtId,@Param("removeTypes")List<Integer> removeTypes);
    
    
    /**
     * 
     * 区本校本资源：查询父类型及其所有子类型
     */
    public List<Integer> getDisResTypesByPMType(@Param("MType") int MType);
    
    /**
     * 自建资源 ： 查询全部一级资源类型
     */
    public List<FirstLevelResType> getAllFirstLevelResType();
    
    /**
     * 自建资源 ： 查询全部类型
     */
    public List<FirstLevelResType> getAllResType();
    
    
    /**
     * 系统资源：根据资源库id，得到父类型的所有子类型及其自身，e备课
     * 
     * @param poolId
     * @param MType
     * @return
     */
    public List<Integer> getTypesByPMTypeAndPool_EPrepare(@Param("poolId") long poolId, @Param("MType") int MType,@Param("removeTypeIds")List<Integer> removeTypeIds);
    
    /**
     * 
     * 区本校本资源：查询父类型及其所有子类型，e备课
     */
    public List<Integer> getDisResTypesByPMType_EPrepare(@Param("MType") int MType,@Param("removeTypeIds")List<Integer> removeTypeIds);
}