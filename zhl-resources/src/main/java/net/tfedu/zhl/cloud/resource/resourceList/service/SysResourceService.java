package net.tfedu.zhl.cloud.resource.resourceList.service;

import java.util.HashMap;
import java.util.List;

/**
 * 系统资源 service
 * @author WeiCuicui
 *
 */
public interface SysResourceService {

	//根据资源格式，查询所有后缀
	public List<String> getFileExtsByFormat(String fileFormat);
	
	//查询当前结点下的所有资源id
	public List<Long> getAllSysResourceIds(HashMap<String, Object> map);
}
