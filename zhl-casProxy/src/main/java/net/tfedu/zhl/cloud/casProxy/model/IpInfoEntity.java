package net.tfedu.zhl.cloud.casProxy.model;

import java.io.Serializable;
/**
 * IP地址信息信息
 * 
 * 			{
				    "code": 0,
				    "data": {
				        "country": "中国",
				        "country_id": "CN",
				        "area": "华北",
				        "area_id": "100000",
				        "region": "北京市",
				        "region_id": "110000",
				        "city": "北京市",
				        "city_id": "110100",
				        "county": "",
				        "county_id": "-1",
				        "isp": "鹏博士",
				        "isp_id": "1000143",
				        "ip": "219.239.146.20"
				    }
				}
 * copyRight@知好乐教育技术北京有限公司
 * @author 	jiys
 * @date   	2016-12-8
 * @version	v1.0.0
 */
@SuppressWarnings("serial")
public class IpInfoEntity implements Serializable {

	/**
	 * 
	 */
	private String code;
	
	/**
	 * 地域信息
	 */
	private AddressEntity data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public AddressEntity getData() {
		return data;
	}

	public void setData(AddressEntity data) {
		this.data = data;
	}
}
