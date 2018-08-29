package net.tfedu.zhl.core.exception;


/**
 * 缺乏校验信息或校验失败异常
 * @author wangwr
 *
 */
public class WithoutSignError extends CustomException{

	public WithoutSignError(){
		
		super("WithoutSign", "缺乏校验信息或校验失败");
	}
	
	
	
}
