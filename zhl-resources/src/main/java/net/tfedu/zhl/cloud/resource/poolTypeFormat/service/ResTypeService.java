package net.tfedu.zhl.cloud.resource.poolTypeFormat.service;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.FirstLevelResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;

/**
 * 资源类型 service
 * 
 * @author WeiCuicui
 *
 */
public interface ResTypeService {
	
	
	/**
     * 系统资源：查询资源类型
     */
    public List<ResType> getSysResTypes(long poolId, String pTfcode,List<Integer> sys_from);
    
    /**
     * 系统资源：查询资源类型，e备课
     */
    public List<ResType> getSysResTypes_EPrepare(long poolId, String pTfcode,List<Integer> removeTypeIds,List<Integer> sys_from); 
    
    /**
     * 区本校本资源：查询资源类型
     * 
     * @return
     */
    public List<ResType> getDisResTypes(String tfcode, int fromFlag,long userId);

    /**
     * 区本校本资源：查询资源类型，e备课
     * 
     * @return
     */
    public List<ResType> getDisResType_EPrepare(String tfcode, int fromFlag,long userId,List<Integer> removeTypeIds);
    
    
    /**
     * 区本校本资源： 获取当前节点下所有资源id
     * 
     * @param map
     * @return
     */
    public List<Long> getAllDisResIds(HashMap<String, Object> map);

  
    /**
     * 系统资源：根据资源库id、父类型id，得到父类型的所有子类型及其自身
     * 
     * @param map
     * @return
     */
    public List<Integer> getTypesByPMTypeAndPool(long poolId, int MType);

    /**
     * 区本校本资源：根据父类型id，查询父类型及其所有子类型
     * 
     * @param MType
     * @return
     */
    public List<Integer> getDisResTypesByPMType(int MType);

    /**
     * 自建资源 ： 查询全部一级资源类型
     */
    public List<FirstLevelResType> getAllFirstLevelResType();

}