package net.tfedu.zhl.sso.app.service;

import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.sso.app.entity.SApp;

/**
 
  
  @author wangwr
  @date 2016年11月18日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public interface SAppService  extends BaseService<SApp>{

	/**
	 * 获取APP信息
	 * @param appId
	 * @return
	 */
	public SApp getSApp(String appId)throws ParamsException;
	/**
	 * 获取APP信息
	 * @param appCode  第三方编码（用户名前缀）
	 * @return
	 */
	public SApp getSAppByCode(String appCode)throws ParamsException;
}
