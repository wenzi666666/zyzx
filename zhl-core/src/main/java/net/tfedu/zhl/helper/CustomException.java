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
	UNCUSTOM("ERROR","系统异常",500),
	NOTOKEN("NoAccessToken","缺少token令牌",500),
	PARAMSERROR("ParamsERROR","参数异常",500),
	INVALIDACCESSTOKEN("InvalidAccessToken","提供的token无效",404);
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
	
	
	
	public static CustomException getCustomExceptionByCode(String code){
		CustomException e = code.equalsIgnoreCase(CustomException.OUTOFDATE.code)?CustomException.OUTOFDATE
				:code.equalsIgnoreCase(CustomException.UNUSUALERROR.code)?CustomException.UNUSUALERROR
				:code.equalsIgnoreCase(CustomException.WITHOUTUSER.code)?CustomException.WITHOUTUSER
				:code.equalsIgnoreCase(CustomException.WRONGPASSWORD.code)?CustomException.WRONGPASSWORD
				:CustomException.UNCUSTOM;
		return e;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
	

}
