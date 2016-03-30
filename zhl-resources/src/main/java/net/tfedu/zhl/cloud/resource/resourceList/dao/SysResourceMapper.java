package net.tfedu.zhl.cloud.resource.resourceList.dao;


import java.util.List;

import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResource;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

/**
 * 系统资源 mapper
 * @author WeiCuicui
 *
 */
public interface SysResourceMapper extends CoreMapper<SysResource> {
	
	//查询系统资源，返回结果为List<HashMap<?, ?>>
	public List<SysResourceEntity> SelectSysResources
	(@Param("sys_from") List<Integer> sys_from,@Param("fileFormat") String fileFormat,@Param("resourceIds") List<Long> resourceIds,
		@Param("pTfcode") String tfcode,@Param("orderBy") int orderBy,@Param("typeIds") List<Integer> typeIds);	
			
}