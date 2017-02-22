package net.tfedu.zhl.cloud.resource.poolconfig.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.poolconfig.dao.ZAppUserPoolConfigMapper;
import net.tfedu.zhl.cloud.resource.poolconfig.entity.ZAppUserPoolConfig;
import net.tfedu.zhl.cloud.resource.poolconfig.service.ZAppUserPoolConfigService;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;

/**
 
 	第三方用户与资源库的物权管理服务
  
    @author wangwr
    @date 2017年2月21日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Service(value="appUserPoolConfigService")
public class ZAppUserPoolConfigServiceImpl extends BaseServiceImpl<ZAppUserPoolConfig> implements ZAppUserPoolConfigService {

	@Autowired
	ZAppUserPoolConfigMapper mapper;
	
	
	@Override
	public List<Map<String, Object>> getAppUserPoolConfig(Long termId, Long subjectId,String userName, String appId)
			throws CustomException {
		
		
		return mapper.getAppUserPoolConfig(termId, subjectId, userName, appId);
	}


	@Override
	public List<Map<String, Object>> getPoolsAndAppUserConfig(Long termId, Long subjectId, String userName,
			String appId) throws CustomException {
		return mapper.getPoolsAndAppUserConfig(termId, subjectId, userName, appId);
	}

	
	
	
	
}
