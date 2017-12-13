package net.tfedu.zhl.sso.users.service;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.ZWXMRegister;

/**
 
 	针对助我学对接增加的接口方法：解决助我学中m_register表的同步问题
 
  
    @author wangwr
    @date 2017年12月12日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public interface ZWXMRegisterService  extends BaseService<ZWXMRegister>{
	
	
	/**
	 * 同步用户信息到表：m_register
	 * @param zhlUserId
	 * @param userDefaultPwd
	 * @param form
	 */
	Long syncMRegister(Long zhlUserId,String userDefaultPwd,RegisterAddForm form);
	
	

}
