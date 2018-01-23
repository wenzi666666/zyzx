package net.tfedu.zhl.cloud.casProxy.service;

import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;

/**
 
 
    对接服务 
  
  @author wangwr
  @date 2016年11月16日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public interface ProxyService {
	
	
	/**
	 * 解析出用户的信息，用于注册
	 * @param request
	 * @param config
	 * @return
	 */
	public RegisterAddForm parseAPI(HttpServletRequest request,ThirdPartyCASConfig config)throws CustomException;
	
	
	

}
