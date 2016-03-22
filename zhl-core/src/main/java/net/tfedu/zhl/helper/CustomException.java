package net.tfedu.zhl.helper;


/**
 * 自定义的异常信息
 * @author wangwr
 *
 */
public enum CustomException {
	
	
	
	SUCCESS("OK","",200),	
	WITHOUTUSER("WithoutUser","用户不存在",401),	
	WRONGPASSWORD("WrongPassWord","密码错误",401),
	OUTOFDATE("OutOfDate","卡号过期",401),
	UNUSUALERROR("UnusualError","用户信息异常",401),
	;

	
	/**
	 * 异常编码
	 */
	private String code ;
	
	/**
	 * 异常信息
	 */
	private String message;
	
	/**
	 * http状态码
	 */
	private Integer status;
	
	
	private CustomException(String code,String message,Integer status){
		this.code = code;
		this.message = message;
		this.status = status ;
	}
	
	
	
	
	
	
	
	
	

}
