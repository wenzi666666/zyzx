package net.tfedu.zhl.cloud.resource.portal.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.asset.dao.ZAssetMapper;
import net.tfedu.zhl.cloud.resource.portal.service.PortalService;
import net.tfedu.zhl.helper.ResultJSON;

/**
 
  
  @author wangwr
  @date 2016年11月11日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
@Service("portalService")
public class PortalServiceImpl implements PortalService {

	
	@Resource
	ZAssetMapper assetMapper;
	
	
	@Override
	public ResultJSON schoolDynamic(long schoolId) throws Exception {
	
		return ResultJSON.getSuccess(assetMapper.schoolDynamic(schoolId)) ;
	}

	@Override
	public ResultJSON distrcitDynamic(long districtId) throws Exception {

		return ResultJSON.getSuccess(assetMapper.distrcitDynamic(districtId)) ;
	}

	@Override
	public ResultJSON schoolResStatistics(long schoolId,int expire) throws Exception {
		
		//由日历类 生成 计算最新资源的起始日期
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 0-expire);
		
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.putAll(assetMapper.statisticsSchoolRes(schoolId));
		result.putAll(assetMapper.statisticsSchoolUserRes(schoolId));
		result.putAll(assetMapper.statisticsDailyAvg(schoolId));
		result.putAll(assetMapper.statisticsSysResourceUpdate(c.getTime()));

		
		return ResultJSON.getSuccess(result);
	}

}
