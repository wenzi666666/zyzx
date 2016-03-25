package net.tfedu.zhl.cloud.resources.poolTypeFormats.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resources.poolTypeFormats.dao.ResPoolMapper;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.ResPool;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.service.ResPoolService;

import org.springframework.stereotype.Service;

/**
 * 查询 资源库
 * @author WeiCuicui
 *
 */
@Service("resPoolService")
public class ResPoolServiceImpl implements ResPoolService{

	@Resource ResPoolMapper resPoolMapper;
	@Override
	//查询所有资源库
	public List<ResPool> getAllPools(){
		return resPoolMapper.getAllPools();
	}
}