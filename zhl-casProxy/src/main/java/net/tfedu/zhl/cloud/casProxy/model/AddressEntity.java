package net.tfedu.zhl.cloud.casProxy.model;

import java.io.Serializable;
/**
 * 
 *  {
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
 * 
 * copyRight@知好乐教育技术北京有限公司
 * @author 	jiys
 * @date   	2016-12-8
 * @version	v1.0.0
 */
@SuppressWarnings("serial")
public class AddressEntity implements Serializable {

	/**
	 * 
	 */
	private String country;

	/**
	 * 
	 */
	private String country_id;

	/**
	 * 
	 */
	private String area;

	/**
	 * 
	 */
	private String area_id;

	/**
	 * 
	 */
	private String region;

	/**
	 * 
	 */
	private String region_id;

	/**
	 * 
	 */
	private String city;

	/**
	 * 
	 */
	private String city_id;

	/**
	 * 
	 */
	private String county;

	/**
	 * 
	 */
	private String county_id;

	/**
	 * 
	 */
	private String isp;

	/**
	 * 
	 */
	private String isp_id;

	/**
	 * 
	 */
	private String ip;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCounty_id() {
		return county_id;
	}

	public void setCounty_id(String county_id) {
		this.county_id = county_id;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getIsp_id() {
		return isp_id;
	}

	public void setIsp_id(String isp_id) {
		this.isp_id = isp_id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
