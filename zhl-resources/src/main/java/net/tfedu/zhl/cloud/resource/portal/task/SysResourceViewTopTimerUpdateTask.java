package net.tfedu.zhl.cloud.resource.portal.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.tfedu.zhl.cloud.resource.portal.controller.PortalController;
import net.tfedu.zhl.cloud.resource.portal.service.PortalService;
import net.tfedu.zhl.helper.ResultJSON;


/**
 
      系统资源点击排行榜 定时更新任务
  @author wangwr
  @date 2016年11月12日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
@Component
public class SysResourceViewTopTimerUpdateTask {
	
	@Resource
	PortalService portalService;


	@Resource
	CacheManager cacheManager;
	
	Logger log = LoggerFactory.getLogger(SysResourceViewTopTimerUpdateTask.class);
	
	
	
	/**
	 * 系统资源点击排行榜 定时更新
	 * 7200秒执行一次
	 * @throws Exception 
	 */
	@Scheduled(fixedRate = 7200000)
//	@Scheduled(fixedRate = 10000)
	public void viewTopTimerUpdate() throws Exception{
		log.info("----------------------系统资源点击排行榜 定时更新--------------start----");
		
		
				ResultJSON result = portalService.resourceViewTop();

				result.setMessage("");

				cacheManager.getCache(PortalController.CACHENAME)
				.put(PortalController.CACHEKEY_resourceViewTop, result);

		
		log.info("----------------------系统资源点击排行榜 定时更新--------------end----");
	}
	
	
	
	
}
