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

	public String getJnzx_host() {
		return jnzx_host;
	}

	public void setJnzx_host(String jnzx_host) {
		this.jnzx_host = jnzx_host;
	}
	
	
	
	
	
	
	
	
	
}
