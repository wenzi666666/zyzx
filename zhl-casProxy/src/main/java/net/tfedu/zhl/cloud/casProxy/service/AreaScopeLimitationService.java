package net.tfedu.zhl.cloud.casProxy.service;

import net.tfedu.zhl.cloud.casProxy.entity.AreaScopeLimitation;
import net.tfedu.zhl.core.service.BaseService;

/**
 * 用户信息业务逻辑接口 copyRight@知好乐教育技术北京有限公司
 * 
 * @author jiys
 * @date 2016-9-7
 * @version v1.0.0
 */
public interface AreaScopeLimitationService extends
		BaseService<AreaScopeLimitation> {

	/**
	 * 该市或者该省是否允许使用
	 * 
	 * @param cityCode
	 * @param provinceCode
	 * @return
	 */
	public boolean isAllowToUse(String cityCode, String provinceCode);

}