package net.tfedu.zhl.cloud.casProxy.model;

import java.io.Serializable;

/**
 * 返回值对象（接收第三方基础数据）
 * copyRight@知好乐教育技术北京有限公司
 * @author 	jiys
 * @date   	2016-11-23
 * @version	v1.0.0
 */
@SuppressWarnings("serial")
public class ResultDataTp implements Serializable {

	/**
	 * 基础信息
	 */
	private RegisterAddFormTp userinfo;
	
	/**
	 * 时间戳
	 */
	private String timestamp;

	/**
	 * 校验字符串
	 */
	private String sign;

	/**
	 * 状态码
	 */
	private String code;
	
	/**
	 * 状态信息
	 */
	private String message;

	public RegisterAddFormTp getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(RegisterAddFormTp userinfo) {
		this.userinfo = userinfo;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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
