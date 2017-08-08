package net.tfedu.zhl.cloud.resource.statistics.taishan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.statistics.taishan.dao.ResourceStatisticsMapper;
import net.tfedu.zhl.cloud.resource.statistics.taishan.service.ResourceStatisticsService;
import net.tfedu.zhl.helper.ResultJSON;

@Service("resourceStatisticsService")
public class ResourceStatisticsImpl implements ResourceStatisticsService {

	@Autowired
	ResourceStatisticsMapper resourceStatisticsMapper;

	@Override
	public List<Map<String, Object>> getResDynamic(String scope, Long scopeId, Integer number) {
		List<Map<String, Object>> list = resourceStatisticsMapper.getResDynamic(scope, scopeId, number);
		return list;
	}

	@Override
	public List<Map<String, Object>> getBastSysRes(Integer number) {
		List<Map<String, Object>> list = resourceStatisticsMapper.getBastSysRes(number);
		return list;
	}

	@Override
	public List<Map<String, Object>> getBastScopeRes(String scope, Long scopeId, Integer number) {
		List<Map<String, Object>> list = resourceStatisticsMapper.getBastScopeRes(scope, scopeId, number);
		return list;
	}

	@Override
	public List<Map<String, Object>> getLastAsset(String scope, Long scopeId, Integer number) {
		List<Map<String, Object>> list = resourceStatisticsMapper.getLastAsset(scope, scopeId, number);
		return list;
	}

	@Override
	public List<Map<String, Object>> getSchoolAssetStatistics(String scope, Long scopeId) {
		List<Map<String, Object>> list = resourceStatisticsMapper.getSchoolAssetStatistics(scope, scopeId);
		return list;
	}
	
}
