package net.tfedu.zhl.sso.app.service.impl;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.CacheUtil;
import net.tfedu.zhl.sso.app.dao.SAppMapper;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.app.service.SAppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author wangwr
 * @date 2016年11月18日
 * @desc
 * 
 *       copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Service("sAppService")
public class SAppServiceImpl extends BaseServiceImpl<SApp> implements
		SAppService {

	@Autowired
	CacheManager cacheManager;

	@Autowired
	SAppMapper sAppMapper;

	@Override
	public SApp getSApp(String appId) throws ParamsException {
		if (StringUtils.isEmpty(appId)) {
			return null;
		}
		SApp sApp = CacheUtil.get(cacheManager, CacheUtil.CACHE_APP, appId);
		if (sApp == null) {
			Integer appIdInt = Integer.parseInt(appId);
			sApp = sAppMapper.selectByPrimaryKey(appIdInt);
			if(null == sApp){
				throw new ParamsException("NO THIS APP");
			}
			CacheUtil.put(cacheManager, CacheUtil.CACHE_APP, appId, sApp);
		}
		return sApp;
	}

}
