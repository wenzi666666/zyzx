package net.tfedu.zhl.cloud.resource.portal.service;

import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.helper.ResultJSON;

/**
 
  
  @author wangwr
  @date 2016年11月11日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public interface PortalService {
	
	
	
	
	/**
	 * 获取学校的动态
	 * @param schoolId			 学校id
	 * @return
	 */
	public ResultJSON schoolDynamic(long schoolId)throws Exception;
	
	
	/**
	 * 获取地区的动态
	 * @param districtId		地区id
	 * @return
	 */
	public ResultJSON distrcitDynamic(long districtId)throws Exception;
	
	
	
	
	/**
	 * 资源统计（校级页面）
	 * @param schoolId  学校id
	 * @param expire    判断是否为最新资源的期限(天)
	 * @return
	 * @throws Exception
	 */
	public ResultJSON schoolResStatistics(long schoolId,int expire) throws Exception;
	
	
	
	
	

}
