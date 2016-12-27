package net.tfedu.zhl.core.exception;

import net.tfedu.zhl.core.exception.euam.RegisterCardErrorInfoEuam;

/**
 
  	注册卡号的异常
  
    @author wangwr
    @date 2016年12月23日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public class RegisterCardError extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RegisterCardError() {
	        super("RegisterCardError", "注册卡号异常");
	}
	
	
	/**
	 * 通过注册卡号的异常信息枚举类 构造
	 * @param euam
	 */
	public RegisterCardError(RegisterCardErrorInfoEuam euam) {
        super(euam.getCode(), euam.getMessage());
	}
}
