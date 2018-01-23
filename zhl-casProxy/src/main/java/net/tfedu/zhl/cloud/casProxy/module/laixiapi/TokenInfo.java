package net.tfedu.zhl.cloud.casProxy.module.laixiapi;

import java.io.Serializable;

/**
 
  
  @author wangwr
  @date 2016年11月17日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public class TokenInfo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String access_token ; 
	
	int  remind_in;
	
	//access_token的生命周期
	int  expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getRemind_in() {
		return remind_in;
	}

	public void setRemind_in(int remind_in) {
		this.remind_in = remind_in;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
	
	
	

}
