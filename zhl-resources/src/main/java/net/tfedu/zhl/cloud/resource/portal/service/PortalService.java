package net.tfedu.zhl.cloud.resource.portal.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	/**
	 * 
	 * 上传排行榜（学校资源上传量）
	 * @param districtId    地区id
	 * @return 				学校上传信息集合
	 * @throws Exception
	 */
	public ResultJSON schoolUploadTop(long districtId) throws Exception;
	
	
	

	/**
	 * 
	 * 共享资源排行榜（共享资源浏览次数的排行榜）
	 * @param districtId    地区id
	 * @return 				共享资源信息集合
	 * @throws Exception
	 */
	public ResultJSON sharedResTop(long districtId) throws Exception;
	
	
	
	/**
	 * 
	 * 系统资源排行榜（本地区用户浏览次数的排行榜）
	 * @param districtId    地区id
	 * @return 				学科系统资源排行信息集合
	 * @throws Exception
	 */
	public ResultJSON resourceViewTop() throws Exception;
	
	
	
	

}
