package net.tfedu.zhl.cloud.resource.statistics.taishan.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

	@Override
	public ResultJSON getSchoolStatistics(Long schoolId) {
		List<Map<String, Object>> listResCount = resourceStatisticsMapper.getAllSchoolResCount(schoolId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = getFirstDayForMonth();
		String timeDesc = format.format(start);
		List<Map<String, Object>> listForTime = resourceStatisticsMapper.getSchoolUpdateCountForTime(schoolId,
				timeDesc);

		int allSchoolResCount = 0;

		int updateNum = 0;

		if (listResCount != null && listResCount.size() > 0) {
			allSchoolResCount = Integer.parseInt(listResCount.get(0).get("allSchoolResCount").toString());
		}
		if (listForTime != null && listForTime.size() > 0) {
			updateNum = Integer.parseInt(listForTime.get(0).get("updatenum").toString());
		}

		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("allSchoolResCount", allSchoolResCount);
		result.put("updateNum", updateNum);
		return ResultJSON.getSuccess(result);
	}

	@Override
	public List<Map<String, Object>> getSchoolUploadTop(Long schoolId, Integer number) {
		List<Map<String, Object>> list = resourceStatisticsMapper.getSchoolUploadTop(schoolId, number);
		return list;
	}

	@Override
	public List<Map<String, Object>> getGradeClickTop(String userIds, String startTime, String endTime) {
		List<Map<String, Object>> list = resourceStatisticsMapper.getGradeClickTop(userIds, startTime, endTime);
		return list;
	}

	@Override
	public List<Map<String, Object>> getGradeClickLog(String userIds, Integer number) {
		List<Map<String, Object>> list = resourceStatisticsMapper.getGradeClickLog(userIds, number);
		return list;
	}
	
	/**
	 * 获取本月的第一天
	 * 
	 * 2016-06-19
	 * 
	 * @return
	 */
	private static Date getFirstDayForMonth() {

		Calendar c = Calendar.getInstance();

		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);

		return c.getTime();
	}

}
