package net.tfedu.zhl.cloud.resources.resourceList.dao;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.cloud.resources.resourceList.entity.SysResource;

/**
 * 系统资源 mapper
 * @author WeiCuicui
 *
 */
public interface SysResourceMapper extends CoreMapper<SysResource> {
	
	//获取当前节点下所有资源id，pTfcode为父结点的tfcode
	public HashMap<String, Object> getAllResourceIdsByPtfcode(int fromFlag,String pTfcode);
}