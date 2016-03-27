package net.tfedu.zhl.cloud.resource.resourceList.service.impl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.FileFormatMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResTypeMapper;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;

/**
 * 系统资源 serviceImpl
 * @author WeiCuicui
 *
 */
@Service("sysResourceService")
public class SysResourceServiceImpl implements SysResourceService{

	@Resource FileFormatMapper fileFormatMapper;
	
	@Resource ResTypeMapper resTypeMapper;
	
	//根据资源格式，查询所有后缀
	@Override
	public List<String> getFileExtsByFormat(String fileFormat){
		return fileFormatMapper.getExtsByFormat(fileFormat);
	}
	
	//查询当前结点下的所有资源id
	@Override
	public List<Long> getAllSysResourceIds(HashMap<String, Object> map){
		return resTypeMapper.getAllSysResIds(map);
	}
	
}
