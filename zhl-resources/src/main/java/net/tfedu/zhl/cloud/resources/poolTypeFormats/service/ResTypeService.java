package net.tfedu.zhl.cloud.resources.poolTypeFormats.service;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resources.resourceList.entity.SysResource;


/**
 * 资源类型 service
 * @author WeiCuicui
 *
 */
public interface ResTypeService {

	//获取当前节点下所有资源id，pTfcode为父结点的tfcode
	public HashMap<String, Object> getAllResourceIdsByPtfcode(int fromFlag,String pTfcode);
}
