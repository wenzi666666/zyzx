package net.tfedu.zhl.cloud.resources.poolTypeFormats.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resources.poolTypeFormats.service.ResTypeService;
import net.tfedu.zhl.cloud.resources.resourceList.dao.SysResourceMapper;
import net.tfedu.zhl.cloud.resources.resourceList.entity.SysResource;

import org.springframework.stereotype.Service;

/**
 * 资源类型的serviceImpl
 * @author WeiCuicui
 *
 */
@Service("resTypeService")
public class ResTypeServiceImpl implements ResTypeService{
    
	//引用系统资源的mapper
	@Resource SysResourceMapper sysResourceMapper;
	
	//获取当前节点下所有资源id，pTfcode为父结点的tfcode
	@Override
	public HashMap<String, Object> getAllResourceIdsByPtfcode(int fromFlag,String pTfcode){
		return sysResourceMapper.getAllResourceIdsByPtfcode(fromFlag, pTfcode);
	}

}
