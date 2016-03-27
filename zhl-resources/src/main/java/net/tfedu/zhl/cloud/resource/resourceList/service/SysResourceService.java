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

	//根据资源格式，查询所有后缀
	public List<String> getFileExtsByFormat(String fileFormat);

	//分页查询系统资源信息
	public Pagination<SysResourceEntity> getSysResList(List<Integer> sys_from,List<String> formats,List<Long> resourceIds,String tfcode,int orderBy,List<Long> typeIds,int page,int perPage);
}
