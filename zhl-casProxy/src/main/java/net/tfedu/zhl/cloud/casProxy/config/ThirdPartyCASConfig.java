package net.tfedu.zhl.cloud.casProxy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 
 
      第三方CAS相关配置
  @author wangwr
  @date 2016年11月16日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
@Component("casConfig")
public class ThirdPartyCASConfig {
	
	
	/**
	 * 知好乐系统中的开发者
	 */
	@Value("#{configProperties['ZHL_DEVELOPER']}")
	public String ZHL_DEVELOPER;
	
	
	/**
	 * 知好乐系统中的应用id
	 */
	@Value("#{configProperties['ZHL_APPID']}")
	public String ZHL_APPID;
	
	
	/**
	 * 知好乐系统中的应用密钥
	 */
	@Value("#{configProperties['ZHL_APPKEY']}")
	public String ZHL_APPKEY;
	
	
	/**
	 * #第三方系统中的APPID
	 */
	@Value("#{configProperties['THIRDPARTY_APPID']}")
	public String THIRDPARTY_APPID;
	
	
	/**
	 * 第三方系统中的appkey密钥
	 */
	@Value("#{configProperties['THIRDPARTY_APPKEY']}")
	public String THIRDPARTY_APPKEY;

	
	
	/**
	 * 目标系统的(用来重定向)的url地址
	 */
	@Value("#{configProperties['TARGET_REDIRECT_URL']}")
	public String TARGET_REDIRECT_URL;
	
	/**
	 * 第三方API服务器
	 */
	@Value("#{configProperties['THIRDPARTY_API_SERVER']}")
	public String THIRDPARTY_API_SERVER;
	

	
	
	
	
	public String getTHIRDPARTY_API_SERVER() {
		return THIRDPARTY_API_SERVER;
	}


	public void setTHIRDPARTY_API_SERVER(String tHIRDPARTY_API_SERVER) {
		THIRDPARTY_API_SERVER = tHIRDPARTY_API_SERVER;
	}


	public String getZHL_DEVELOPER() {
		return ZHL_DEVELOPER;
	}


	public void setZHL_DEVELOPER(String zHL_DEVELOPER) {
		ZHL_DEVELOPER = zHL_DEVELOPER;
	}


	public String getZHL_APPID() {
		return ZHL_APPID;
	}


	public void setZHL_APPID(String zHL_APPID) {
		ZHL_APPID = zHL_APPID;
	}


	public String getZHL_APPKEY() {
		return ZHL_APPKEY;
	}


	public void setZHL_APPKEY(String zHL_APPKEY) {
		ZHL_APPKEY = zHL_APPKEY;
	}


	public String getTHIRDPARTY_APPID() {
		return THIRDPARTY_APPID;
	}


	public void setTHIRDPARTY_APPID(String tHIRDPARTY_APPID) {
		THIRDPARTY_APPID = tHIRDPARTY_APPID;
	}


	public String getTHIRDPARTY_APPKEY() {
		return THIRDPARTY_APPKEY;
	}


	public void setTHIRDPARTY_APPKEY(String tHIRDPARTY_APPKEY) {
		THIRDPARTY_APPKEY = tHIRDPARTY_APPKEY;
	}


	public String getTARGET_REDIRECT_URL() {
		return TARGET_REDIRECT_URL;
	}


	public void setTARGET_REDIRECT_URL(String tARGET_REDIRECT_URL) {
		TARGET_REDIRECT_URL = tARGET_REDIRECT_URL;
	}
	
	
	
	
	
	
}
