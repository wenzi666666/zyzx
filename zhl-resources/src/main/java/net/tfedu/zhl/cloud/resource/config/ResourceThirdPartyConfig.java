package net.tfedu.zhl.cloud.resource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 资源中心web端的对接第三方的配置项
 * @author WeiCuicui
 *
 */
@Component("resourceThirdPartyConfig")
public class ResourceThirdPartyConfig {

	
	
	/**
	 * 济宁中兴对接的host地址 
	 */
	@Value("#{configProperties['jnzx_host']}")
	private String jnzx_host;

	
	/**
	 * 延庆对接的host地址 
	 */
	@Value("#{configProperties['yq_host']}")
	private String yq_host;
	
	
	/**
	 * 延庆对接的登录页面
	 */
	@Value("#{configProperties['yq_logWeb']}")
	private String yq_logWeb;
	
	
	public String getJnzx_host() {
		return jnzx_host;
	}

	public void setJnzx_host(String jnzx_host) {
		this.jnzx_host = jnzx_host;
	}

	public String getYq_host() {
		return yq_host;
	}

	public void setYq_host(String yq_host) {
		this.yq_host = yq_host;
	}

	public String getYq_logWeb() {
		return yq_logWeb;
	}

	public void setYq_logWeb(String yq_logWeb) {
		this.yq_logWeb = yq_logWeb;
	}
	
	
	
}
