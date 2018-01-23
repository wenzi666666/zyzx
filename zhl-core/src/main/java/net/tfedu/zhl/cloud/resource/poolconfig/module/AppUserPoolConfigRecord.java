package net.tfedu.zhl.cloud.resource.poolconfig.module;

/**
copyRight@ 同方知好乐教育科技(北京)有限公司
@author　wangwr
@date 　
@desc 　　AppUserPoolConfigRecord.java
*/
import java.util.Date;

/** 第三方用户资源库物权记录信息--后台前端展示使用 */
public class AppUserPoolConfigRecord {

	/** 物权记录id */
	private Long id;
	/** 第三方应用的注册主键 */
	private String appId;
	/** 第三方用户用户名 */
	private String userName;
	/** 本次订货购买或续期的资源库 */
	private Long poolId;
	/** 学段id，0为全部 */
	private Long termId;
	/** 学科id.0为全部 */
	private Long subjectId;
	/** 记录增加时间 */
	private Date addTime;
	/** 记录生效的起始时间 */
	private Date startDate;
	/** 记录有效期的截止时间 */
	private Date expireDate;

	/** 学段名称 */
	private String termName;
	/** 学科名称 */
	private String subjectName;
	/** 资源库名称 */
	private String poolName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getPoolId() {
		return poolId;
	}

	public void setPoolId(Long poolId) {
		this.poolId = poolId;
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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

}