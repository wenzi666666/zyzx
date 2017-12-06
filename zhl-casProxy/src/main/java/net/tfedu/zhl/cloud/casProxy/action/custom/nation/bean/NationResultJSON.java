package net.tfedu.zhl.cloud.casProxy.action.custom.nation.bean;

import com.alibaba.fastjson.JSON;

/**
 
  
    @author wangwr
    @date 2017年9月18日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class NationResultJSON {
	
	
	/**
	 * 返回码
	 */
	String retDesc;
	
	/**
	 * 返回码
	 */
	String retCode;
	
	
	/**
	 * 返回的token 对象信息
	 */
	JSON data ;


	public String getRetDesc() {
		return retDesc;
	}


	public void setRetDesc(String retDesc) {
		this.retDesc = retDesc;
	}


	public String getRetCode() {
		return retCode;
	}


	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}


	public JSON getData() {
		return data;
	}


	public void setData(JSON data) {
		this.data = data;
	} 
	
	
	
	
	
	
	

}
