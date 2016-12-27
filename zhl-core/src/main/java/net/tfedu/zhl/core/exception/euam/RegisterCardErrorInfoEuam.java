package net.tfedu.zhl.core.exception.euam;
/**
 
	
	注册卡号的异常信息
	@author wangwr
	@date 2016年12月23日
	@desc 
	  
	copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public enum RegisterCardErrorInfoEuam {
	
	NOEXIST("RegisterCardNoExist","注册卡号不存在")
	,EXPIRE("RegisterCardExpirt","注册卡号已经过期")
	,NOPWD("RegisterCardNoPwd","注册卡号密码不正确")
	,BEUSED("RegisterCardBeUsed","注册卡号已经被使用")
	;

	
	private String code;
	
	private String message;
	
	
	private RegisterCardErrorInfoEuam(String code,String message){
		this.code = code ; 
		this.message = message;
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
	

}
