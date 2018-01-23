package net.tfedu.zhl.cloud.casProxy.data.service;

import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;

public interface BaseDataCommonService {

	/**
	 * 解析出用户的信息，用于注册
	 * 
	 * @param userid
	 * @param config
	 * @param app
	 * @return
	 */
	public abstract RegisterAddForm parseAPI(String userid,
			ThirdPartyCASConfig config, SApp app) throws CustomException;

}
