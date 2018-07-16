package net.tfedu.zhl.cloud.resource.integration.entity;

import net.tfedu.zhl.cloud.resource.constant.ResourcePlatformWebConstant;

/**
 
 	
          与云平台对接的接口的返回信息 的对象定义
 
  
    @author wangwr
    @date 2017年8月4日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class ResultInfo {
	
	
	/***
	 * 返回成功失败信息
	 */
	private String message ; 
	
	/**
	 * 返回成功时的查询结果
	 */
	private Object result ;
	
	
	
	private ResultInfo(String message ,Object result){
		this.message = message ; 
		this.result = result ; 
	}
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	} 
	
	/**
	 * 返回成功时的构造实体
	 * @param result
	 * @return
	 */
	public static ResultInfo success(){
		return new ResultInfo(ResourcePlatformWebConstant.SUCCESS, "");
	}
	/**
	 * 返回成功时的构造实体
	 * @param result
	 * @return
	 */
	public static ResultInfo success(Object result){
		return new ResultInfo(ResourcePlatformWebConstant.SUCCESS, result);
	}
	
	/**
	 * 返回失败的构造实体
	 * @return
	 */
	public static ResultInfo error(){
		return new ResultInfo(ResourcePlatformWebConstant.ERROR, null);
	}

}
