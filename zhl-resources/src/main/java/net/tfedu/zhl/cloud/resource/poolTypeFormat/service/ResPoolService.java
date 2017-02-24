package net.tfedu.zhl.cloud.resource.poolTypeFormat.service;

import java.util.List;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;

/**
 * 查询所有资源库 service
 * 
 * @author WeiCuicui
 *
 */
public interface ResPoolService {

    /**
     * 查询所有资源库
     * @return
     */
    public List<ResPool> getAllPools();
    
    /**
     * 查询所有资源库
     * @return
     */
    public List<ResPool> getExistPools();
    
    
    

    /**
     * 查询所有资源库，包括SSO中扩展的资源库(情景英语、题库)
     * @return
     */
    public List<ResPool> getAllPoolsWithAppend()throws Exception;
    
    
    
}
