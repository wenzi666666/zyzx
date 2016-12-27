package net.tfedu.zhl.core.exception;


/**
 
  	账号注册前端表单信息异常
  
    @author wangwr
    @date 2016年12月26日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class AccountRegisterWebFormError extends CustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public AccountRegisterWebFormError(){
		super("AccountRegisterWebFormError", "账号注册前端表单信息异常");
	}
	
	public AccountRegisterWebFormError(String message){
		super("AccountRegisterWebFormError", message);
	}
	
}
