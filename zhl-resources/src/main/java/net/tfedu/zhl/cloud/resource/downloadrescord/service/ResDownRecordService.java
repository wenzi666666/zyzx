package net.tfedu.zhl.cloud.resource.downloadrescord.service;

import net.tfedu.zhl.cloud.resource.downloadrescord.entity.ResDownRecord;
import net.tfedu.zhl.core.service.BaseService;

/**
 
  
    @author wangwr
    @date 2017年8月7日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public interface ResDownRecordService extends BaseService<ResDownRecord> {

	
	
	/**
	 * 增加资源的下载记录并更新下载次数
	 * @param userId
	 * @param resId 
	 * @param fromflag  下载资源的来源  0 系统资源 1 自建资源 2 共享资源 3校本资源,4区本资源
	 * @return
	 */
	Boolean addResourceDownloadRecordAndUpdateDownloadNumber(Long userId,Long resId,Integer fromflag);
	
	
	
}
