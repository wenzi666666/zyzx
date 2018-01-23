package net.tfedu.zhl.cloud.resource.portal.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.resource.config.ResourceWebConfig;
import net.tfedu.zhl.cloud.resource.portal.module.SubjectResourceUpdateResult;
import net.tfedu.zhl.cloud.resource.portal.service.PortalService;
import net.tfedu.zhl.cloud.resource.resourceList.util.ResThumbnailPathUtil;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ResultJSON;

/**
 * 
 * 区本、校本页面 相关接口 （门户页）
 * 
 * 
 * @author wangwr
 * @date 2016年11月11日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 * 
 */
@Controller
@RequestMapping("resPortalAPI/portal/")
public class PortalController {

	@Resource
	PortalService portalService;

	@Resource
	ResourceWebConfig resourceWebConfig;

	@Resource
	CommonWebConfig commonWebConfig;

	
	@Autowired
	CacheManager cacheManager;

	
	/**
	 * 
	 * 教师动态（校级页面、区级页面通用）
	 * 
	 * @param request
	 * @param tab
	 *            页面类型：1 校级页面 2区级页面
	 * @param queryId
	 *            查询条件id， tab 为 1 时，为学校id；tab为2时，是地区id
	 * @return 返回最新的10个用户的上传记录
	 * @throws Exception
	 */
	@RequestMapping(value = "v1.0/dynamic", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON dynamic(HttpServletRequest request, int tab, long queryId) throws Exception {

		switch (tab) {
		case 1:
			return portalService.schoolDynamic(queryId);
		case 2:
			return portalService.distrcitDynamic(queryId);
		default:
			throw new ParamsException();
		}

	}

	/**
	 * 
	 * 资源统计（校级页面）
	 * 
	 * @param request
	 * @param schoolId
	 *            学校id
	 * @return 统计信息对象
	 * @throws Exception
	 */
	@RequestMapping(value = "v1.0/schoolResStatistics", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON schoolResStatistics(HttpServletRequest request, long schoolId) throws Exception {

		// 判断是否为最新资源的期限(天)
		int expire = resourceWebConfig.getExpire(request);

		return portalService.schoolResStatistics(schoolId, expire);

	}

	/**
	 * 
	 * 上传排行榜（学校资源上传量）
	 * 
	 * @param request
	 * @param districtId
	 *            地区id
	 * @return 学校上传信息集合
	 * @throws Exception
	 */
	@RequestMapping(value = "v1.0/schoolUploadTop", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON schoolUploadTop(HttpServletRequest request, long districtId) throws Exception {

		return portalService.schoolUploadTop(districtId);

	}

	/**
	 * 
	 * 共享资源排行榜（共享资源浏览次数的排行榜）
	 * 
	 * @param request
	 * @param districtId
	 *            地区id
	 * @return 共享资源信息集合
	 * @throws Exception
	 */
	@RequestMapping(value = "v1.0/sharedResTop", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON sharedResTop(HttpServletRequest request, long districtId) throws Exception {

		return portalService.sharedResTop(districtId);

	}

	
	
	
	
	/**
	 * 系统资源排行榜（用户浏览次数的排行榜）的缓存key,和缓存空间
	 */
	public static final String CACHEKEY_resourceViewTop = "port_resourceViewTop";
	public static final String CACHENAME = "usernamesCache";
	
	//ResultJSON 中的message 等于  FINALWEBRESULT时，直接返回页面，否则处理浏览和缩略图路径
	public static final String FINALWEBRESULT = "FINALWEBRESULT";
	
	
	/**
	 * 
	 * 系统资源排行榜（用户浏览次数的排行榜）
	 * 
	 * @param request
	 * @return 学科系统资源排行信息集合
	 * @throws Exception
	 */
	@RequestMapping(value = "v1.0/resourceViewTop", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON resourceViewTop(HttpServletRequest request) throws Exception {

		ResultJSON result = null;
		//先从缓存获取
		ValueWrapper value =  cacheManager.getCache(CACHENAME).get(CACHEKEY_resourceViewTop);
		if(null!=value && value instanceof ResultJSON){
			result = (ResultJSON)value;
			if(FINALWEBRESULT.equals(result.getMessage())){
				return result;
			}
		}else{
			result = portalService.resourceViewTop();
		}
		
		
		// 获取文件服务器的访问url
		String resServiceLocal = commonWebConfig.getResServiceLocal();
		String currentResPath = commonWebConfig.getCurrentResPath(request);


		ResThumbnailPathUtil.convertToPurpos_resPreview((List<SubjectResourceUpdateResult>) result.getData(),
				resServiceLocal, currentResPath);

		result.setMessage(FINALWEBRESULT);
		
		cacheManager.getCache(CACHENAME).put(CACHEKEY_resourceViewTop, result);
		
		return result;

	}

}
