package net.tfedu.zhl.cloud.casProxy.dao;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.cloud.casProxy.entity.AreaScopeLimitation;
import net.tfedu.zhl.helper.CoreMapper;

/**
 * 
 * copyRight@知好乐教育技术北京有限公司
 * 
 * @author jiys
 * @date 2016-12-8
 * @version v1.0.0
 */
public interface AreaScopeLimitationMapper extends
		CoreMapper<AreaScopeLimitation> {

	/**
	 * 该市或者该省是否允许使用
	 * 
	 * @param cityCode
	 * @param provinceCode
	 * @return
	 */
	public String isAllowToUse(@Param("cityCode") String cityCode,
			@Param("provinceCode") String provinceCode);

}