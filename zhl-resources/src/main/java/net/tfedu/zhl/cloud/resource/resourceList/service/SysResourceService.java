package net.tfedu.zhl.cloud.resource.resourceList.service;

import java.util.List;

import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
import net.tfedu.zhl.core.exception.CustomException;

/**
 * 系统资源 service
 * 
 * @author WeiCuicui
 *
 */
public interface SysResourceService {

	
    /**
     * 查询系统资源列表
     * @param poolId
     * @param mTypeId
     * @param fileFormat
     * @param tfcode
     * @param orderBy
     * @param page
     * @param perPage
     * @param sys_from
     * @param expire
     * @return
     */
    public Pagination<SysResourceEntity> getAllSysRes(long poolId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage,List<Integer> sys_from,int expire);
    
    
    /**
     * 查询系统资源（限制资源类型，资源title模糊查询），e备课
     * @param poolId
     * @param mTypeId
     * @param fileFormat
     * @param tfcode
     * @param orderBy
     * @param page
     * @param perPage
     * @param searchWord
     * @param removeTypeIds
     * @param sys_from
     * @param expire
     * @return
     */
    public Pagination<SysResourceEntity> getAllSysRes_EPrepare(long poolId, int mTypeId, String fileFormat, String tfcode,
            int orderBy, int page, int perPage,String searchWord,List<Integer> removeTypeIds,List<Integer> sys_from,int expire);
    
    
    
    
    /**
	 * 第三方用戶是否有权限下载浏览
	 * @param userName
	 * @param appId
	 * @param resId
	 * @return
	 */
	public boolean hasPermissionToViewAndDown(String userName,String appId,Long resId)throws CustomException;
	

    
    
}