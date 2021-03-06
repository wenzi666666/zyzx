package net.tfedu.zhl.cloud.resource.productUpdate.service;

import net.tfedu.zhl.helper.ResultJSON;

public interface ProductUpdateService {

	/**
	 * 根据产品编码、版本编码，查询升级文件及升级说明
	 * @param productCode
	 * @param versionCode
	 * @param productType
	 * @return
	 */
	public ResultJSON selectVersionsByCode(String productCode,long versionCode,int productType);
	
	/**
	 * 根据产品编码、版本名称，查询升级文件及升级说明
	 * @param productName
	 * @param versionCode
	 * @param productType
	 * @return
	 */
	public ResultJSON selectVersionsByName(String productName,String versionName,int productType);
	
}
