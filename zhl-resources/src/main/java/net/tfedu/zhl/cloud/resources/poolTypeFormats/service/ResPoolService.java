package net.tfedu.zhl.cloud.resources.poolTypeFormats.service;

import java.util.List;

import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.ResPool;

/**
 * 查询所有资源库 service
 * @author WeiCuicui
 *
 */
public interface ResPoolService {

    //查询所有资源库
	public List<ResPool> getAllPools();
}
