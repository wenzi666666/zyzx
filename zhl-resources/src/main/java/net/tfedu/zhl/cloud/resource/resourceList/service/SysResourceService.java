package net.tfedu.zhl.cloud.resource.resourceList.service;

import java.util.List;

import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;


/**
 * 系统资源 service
 * @author WeiCuicui
 *
 */
public interface SysResourceService {


	//分页查询系统资源信息
	public Pagination<SysResourceEntity> getSysResList(List<Integer> sys_from,String fileFormat,List<Long> resourceIds,String tfcode,int orderBy,List<Integer> typeIds,int page,int perPage);
	
	//查询系统资源列表
	public Pagination<SysResourceEntity> getAllSysRes(long poolId,int mTypeId,String fileFormat,String tfcode,int orderBy,int page,int perPage);
}