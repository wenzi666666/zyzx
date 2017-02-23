package net.tfedu.zhl.cloud.resource.poolconfig.service;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.cloud.resource.poolconfig.entity.SAppUserPoolConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.service.BaseService;

/**
 
 	第三方用户与资源库的物权管理服务
  
    @author wangwr
    @date 2017年2月21日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public interface SAppUserPoolConfigService extends BaseService<SAppUserPoolConfig> {
	
	
	/**
	 * 查询用户有权限的资源库
	 * @param termId    学段
	 * @param subjectId 学科
	 * @param userName  用户
	 * @param appId     第三方appid
	 * @return 所有指定资源库以及用户是否有权限
	 * @throws CustomException
	 */
	public List<Map<String,Object>> getAppUserPoolConfig(Long termId, Long subjectId,String userName,String appId)
			throws CustomException;
	
	
	
	
}
