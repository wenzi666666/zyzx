package net.tfedu.zhl.sso.th_register.service;


import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.sso.th_register.entity.SThirdRegisterRelative;

/**
 * 第三方对接（映射）的接口
 * @author wangwr
 *
 */
public interface SThirdRegisterService extends BaseService<SThirdRegisterRelative> {

	
	
	/**
	 * 
	 * @param userName
	 * @param thirdCode
	 * @return
	 * @throws Exception
	 */
	public SThirdRegisterRelative getThirdRelativeResult(String userName,String thirdCode) throws Exception;
	
	
	
	
	
	
	
	
}
