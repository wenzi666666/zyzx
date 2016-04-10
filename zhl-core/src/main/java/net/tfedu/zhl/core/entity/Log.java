package net.tfedu.zhl.core.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 日志entity
 * @author ty
 * @date 2015年1月13日
 */
//@Entity
//@Table(name = "sys_log")
public class Log implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Integer id;
	private String operationCode;
	private String creater;
	private Date createDate;
	private Integer type;
	private String os;
	private String browser;
	private String ip;
	private String mac;
	private Integer executeTime;
	private String description;
	private String requestParam;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** minimal constructor */
	public Log(String operationCode, Date createDate) {
		this.operationCode = operationCode;
		this.createDate = createDate;
	}

	/** full constructor */
	public Log(String operationCode, String creater, Timestamp createDate,
			Integer type, String os, String browser, String ip, String mac,
			Integer executeTime, String description, String requestParam) {
		this.operationCode = operationCode;
		this.creater = creater;
		this.createDate = createDate;
		this.type = type;
		this.os = os;
		this.browser = browser;
		this.ip = ip;
		this.mac = mac;
		this.executeTime = executeTime;
		this.description = description;
		this.requestParam = requestParam;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperationCode() {
		return this.operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowser() {
		return this.browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getExecuteTime() {
		return this.executeTime;
	}

	public void setExecuteTime(Integer executeTime) {
		this.executeTime = executeTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequestParam() {
		return this.requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}

}