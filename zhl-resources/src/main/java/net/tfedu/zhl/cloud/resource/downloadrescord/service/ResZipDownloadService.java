package net.tfedu.zhl.cloud.resource.downloadrescord.service;

import net.tfedu.zhl.cloud.resource.downloadrescord.entity.ResZipDownRecord;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;



/**
 * 打包下载  记录表的service
 * @author wangwr
 *
 */
public interface ResZipDownloadService {
	
	
	
	/**
	 * 增加打包下载 的 记录
	 * @param obj
	 * @return
	 */
	public void addZipDownRecord(ResZipDownRecord obj);
	
	
	/**
	 * 更新打包下载的记录（的状态）
	 * @param obj
	 */
	public void updateZipDownRecord(ResZipDownRecord obj); 
	
	
	
	
	/**
	 * 获取打包下载的记录
	 * @param id
	 * @return
	 */
	public ResZipDownRecord getZipDownRecord(Long id);
	
	
	
	
	
	
	/**
	 * 分页获取我的下载
	 * @param userId
	 * @param unifyTypeId
	 * @param fileFormat
	 * @param page
	 * @param prePage
	 * @return
	 */
	public Pagination getMydownload(Long userId,Long unifyTypeId,String fileFormat ,Integer page,Integer prePage); 
	
	
	

}