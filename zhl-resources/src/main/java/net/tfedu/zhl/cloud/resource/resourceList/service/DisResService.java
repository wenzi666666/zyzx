package net.tfedu.zhl.cloud.resource.resourceList.service;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;


/**
 * 区本、校本资源的service
 * @author WeiCuicui
 *
 */
public interface DisResService {

	//获得区、校id
	public List<HashMap<?, ?>> getDisAndSchool(long userId);
	
	//查询区本、校本资源信息
	public Pagination<DisResourceEntity> selectDisRes(int fromFlag,List<String> formats, List<Long> typeIds, String tfcode,int orderBy,long schoolId,long districtId,int page,int perPage);
				
}
