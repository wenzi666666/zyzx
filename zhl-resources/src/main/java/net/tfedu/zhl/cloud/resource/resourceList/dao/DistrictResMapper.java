package net.tfedu.zhl.cloud.resource.resourceList.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DistrictRes;

public interface DistrictResMapper extends CoreMapper<DistrictRes> {
	//获得区、校id
	public List<HashMap<?, ?>> getDisAndSchool(long userId);
	
	//查询区本、校本资源信息
	public List<DisResourceEntity> selectDisRes(@Param("fromFlag") int fromFlag,@Param("formats") List<String> formats,
			@Param("typeIds") List<Long> typeIds,@Param("tfcode") String tfcode,@Param("orderBy") int orderBy,@Param("schoolId") long schoolId,@Param("districtId") long districtId);
}