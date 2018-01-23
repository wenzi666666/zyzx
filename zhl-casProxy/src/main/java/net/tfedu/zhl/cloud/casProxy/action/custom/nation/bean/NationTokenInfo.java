package net.tfedu.zhl.cloud.casProxy.action.custom.nation.bean;

/**
 * 
 * 
 * 
 * 获取token接口返回的token的信息对象
 * 
 * @author wangwr
 * @date 2017年9月18日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class NationTokenInfo {

	/**
	 * 返回token 有效期，默认2 小时后的
时间戳，
	 */
	String validTime;
	

	String appKey;
	
	/**
	 * 用户ID
	 */
	String userId;
	
	
	String appId;
	
	
	/**
	 * 访问令牌
	 */
	String accessToken;
	
	/**
	 * 应用名称
	 */
	String appName;

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Override
	public String toString() {
		return "NationTokenInfo [validTime=" + validTime + ", appKey=" + appKey + ", userId=" + userId + ", appId="
				+ appId + ", accessToken=" + accessToken + ", appName=" + appName + "]";
	}
	
	

}
