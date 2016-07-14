package net.tfedu.zhl.cloud.resource.thirdparty.sichuan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 四川对接
 * @author wangwr
 *
 */
@Component("siChuanRelativeProperties")
public class SiChuanRelativeProperties {

	
	
	/**
	 * 验证接口
	 */
	@Value("#{configProperties['checkurl_for_realnameplatform']}")
	public String checkurl_for_realnameplatform;
	
	
	

	/**
	 *  用户信息接口
	 */
	@Value("#{configProperties['infourl_for_realnameplatform']}")
	public String infourl_for_realnameplatform;


	
	/**
	 *加密私钥
	 */
	@Value("#{configProperties['aes_key']}")
	public String aes_key ;
	
	/**
	 * 供应商许可码
	 */
	@Value("#{configProperties['p_key']}")
	public String p_key;
	/**
	 * 产品许可码
	 */
	@Value("#{configProperties['app_key']}")
	public String app_key;
	
	


	public String getCheckurl_for_realnameplatform() {
		return checkurl_for_realnameplatform;
	}




	public void setCheckurl_for_realnameplatform(String checkurl_for_realnameplatform) {
		this.checkurl_for_realnameplatform = checkurl_for_realnameplatform;
	}




	public String getInfourl_for_realnameplatform() {
		return infourl_for_realnameplatform;
	}




	public void setInfourl_for_realnameplatform(String infourl_for_realnameplatform) {
		this.infourl_for_realnameplatform = infourl_for_realnameplatform;
	}




	public String getAes_key() {
		return aes_key;
	}




	public void setAes_key(String aes_key) {
		this.aes_key = aes_key;
	}




	public String getP_key() {
		return p_key;
	}




	public void setP_key(String p_key) {
		this.p_key = p_key;
	}




	public String getApp_key() {
		return app_key;
	}




	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	
	
	
	
}
