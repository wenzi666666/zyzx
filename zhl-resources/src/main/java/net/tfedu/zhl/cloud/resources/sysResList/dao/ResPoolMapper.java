package net.tfedu.zhl.cloud.resources.sysResList.dao;

import java.util.List;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.cloud.resources.sysResList.entity.ResPool;

/**
 * 查询所有资源库
 * @author WeiCuicui
 *
 */
public interface ResPoolMapper extends CoreMapper<ResPool> {
	
	//查询所有资源库
	public List<ResPool> getAllPools();
}