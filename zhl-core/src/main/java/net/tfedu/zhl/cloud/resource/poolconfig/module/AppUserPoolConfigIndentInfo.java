package net.tfedu.zhl.cloud.resource.poolconfig.module;

/**
copyRight@ 同方知好乐教育科技(北京)有限公司
@author　wangwr
@date 　
@desc 　　AppUserPoolConfigIndentInfo.java
*/
import java.util.List;

/** 第三方用户资源库物权订单信息类 */
public class AppUserPoolConfigIndentInfo {
	/** 第三方应用的注册主键 */
	private String appId;
	/** 第三方用户用户名 */
	private String userName;
	/** 本次订货购买或续期的资源 */
	private List<Long> pools;
	/** 本次订货购买或续期的月份数 */
	private Integer month;
	/** 学段id，0为全部 */
	private Long termId;
	/** 学科id.0为全部 */
	private Long subjectId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Long> getPools() {
		return pools;
	}

	public void setPools(List<Long> pools) {
		this.pools = pools;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getTermId() {
		return termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

}