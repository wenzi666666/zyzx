package net.tfedu.zhl.cloud.casProxy.service.impl;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.casProxy.dao.AreaScopeLimitationMapper;
import net.tfedu.zhl.cloud.casProxy.entity.AreaScopeLimitation;
import net.tfedu.zhl.cloud.casProxy.service.AreaScopeLimitationService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;
/**
 * 
 * 
 * copyRight@知好乐教育技术北京有限公司
 * @author 	jiys
 * @date   	2016-12-8
 * @version	v1.0.0
 */
@Service("areaScopeLimitationService")
public class AreaScopeLimitationImpl extends
		BaseServiceImpl<AreaScopeLimitation> implements
		AreaScopeLimitationService {

	/**
	 * 
	 */
    @Resource
    AreaScopeLimitationMapper mapper;

	@Override
	public boolean isAllowToUse(String cityCode, String provinceCode) {
		String isAllowToUse=mapper.isAllowToUse(cityCode, provinceCode);
		return StringUtils.isNotEmpty(isAllowToUse);
	}
}