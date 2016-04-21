package net.tfedu.zhl.cloud.resource.resPreview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.config.ResourceWebConfig;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResRecommendationEntity;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.util.ResThumbnailPathUtil;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ResultJSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资源预览页面的Controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class ResPreviewController {

	@Resource
    ResPreviewService resPreviewService;
	
	@Resource
    private CommonWebConfig commonWebConfig;
	
	@Resource
    private ResourceWebConfig resourceWebConfig;
	
	 //写入日志
    Logger logger = LoggerFactory.getLogger(ResPreviewController.class);
	
	
	/**
	 * 根据资源id，fromFlag获取一条资源的详细信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/resPreviewInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getResPreviewInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        ResPreviewInfo previewInfo = null;

        //获取文件服务器的访问url 
    	String resServiceLocal = commonWebConfig.getResServiceLocal();
		String currentResPath = commonWebConfig.getCurrentResPath(request);

		// 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
    	// 资源来源
    	int fromFlag = 0;
    	
    	// 资源id
    	long resId = 0;
    	
    	if(StringUtils.isNotEmpty(request.getParameter("resId"))){
    		resId = Long.parseLong(request.getParameter("resId").toString().trim());
    	} else {
			throw new ParamsException();
		}
    	
    	if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
    		fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
    	}  else {
			throw new ParamsException();
		}
    	 

        // 查询一条资源的详细信息
        previewInfo = resPreviewService.getResPreviewInfo(resId,currentUserId, fromFlag);
        
        //生成缩略图
        ResThumbnailPathUtil.convertToPurpos_resPreview(previewInfo, resServiceLocal, currentResPath);

        return ResultJSON.getSuccess(previewInfo);
    }
	
	/**
	 * 根据资源id，查询所在目录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/preview/lists", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getAllLists(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        // 各个版本的资源目录
        List<List<ResNavEntity>> result = new ArrayList<List<ResNavEntity>>();

        // 资源来源
    	int fromFlag = 0;
    	
    	// 资源id
    	long resId = 0;
    	
    	 // 当前所在结点
    	String curTfcode = "";
    	
    	if(StringUtils.isNotEmpty(request.getParameter("resId"))){
    		resId = Long.parseLong(request.getParameter("resId").toString().trim());
    	} else {
			throw new ParamsException();
		}
    	if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
    		fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
    	} else {
			throw new ParamsException();
		}
    	 
    	if(StringUtils.isNotEmpty(request.getParameter("curTfcode"))){
    		curTfcode = request.getParameter("curTfcode").toString().trim();
    	} else {
			throw new ParamsException();
		}
    	 
        // 查询一条资源的所有版本目录
        result = resPreviewService.getAllResNavs(resId, fromFlag, curTfcode);
        
        return ResultJSON.getSuccess(result);
    }
	
	/**
	 * 资源推荐，将当前预览的资源显示在第一条的位置
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/resRecommendation", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getResRecommendation(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        //查询结果
        Pagination<ResRecommendationEntity> pagination = null;
        
        //获取文件服务器的访问url 
    	String resServiceLocal = commonWebConfig.getResServiceLocal();
		String currentResPath = commonWebConfig.getCurrentResPath(request);
		List<Integer> sys_from = resourceWebConfig.getSys_from(request);
		
		// 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
    	
    	//当前预览的资源id
    	long resId = 0;
    	
    	//资源来源
    	int fromFlag = 0;
    	
    	//资源的tfcode
    	String tfcode = "";
    	
    	//资源类型id，默认为全部
    	int typeId = 0;
    	
    	//资源库id，默认为全部
    	long poolId = 0;
    	
    	//检索关键字
    	String searchKeyword = "";
    	
    	//当前页码
    	int page = 1;
    	
    	//每页多少条记录
    	int perPage = 10;
    	
    	//排序方式
    	int orderBy = 0;
    	
    	if(StringUtils.isNotEmpty(request.getParameter("resId"))){
    		resId = Long.parseLong(request.getParameter("resId").toString().trim());
    	} else {
			throw new ParamsException();
		}
    	
    	if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
    		fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
    	} else {
			throw new ParamsException();
		}
    	
    	if(StringUtils.isNotEmpty(request.getParameter("page"))){
    		page = Integer.parseInt(request.getParameter("page").toString().trim());
    	} else {
			throw new ParamsException();
		}
    	
    	if(StringUtils.isNotEmpty(request.getParameter("perPage"))){
    		perPage = Integer.parseInt(request.getParameter("perPage").toString().trim());
    	} else {
			throw new ParamsException();
		}
    	
    	if(StringUtils.isNotEmpty(request.getParameter("isSearch"))){ //从资源检索页面跳转到预览页面的
    		
    		if(StringUtils.isNotEmpty(request.getParameter("searchKeyword"))){
    			searchKeyword = request.getParameter("searchKeyword").toString().trim();
    		}   
    		
    		pagination = resPreviewService.searchRecommendation(fromFlag, resId, currentUserId, searchKeyword, sys_from, page, perPage);
    		
    	} else { //从自建资源、系统资源、区本资源、校本资源跳转过来的
    		
    		if(fromFlag == 1){ //为当前预览的自建资源推荐相同结点下的系统、区本、校本资源
    			pagination = resPreviewService.myResByUploadRecommendation(currentUserId, resId, sys_from, page, perPage);
    			
    		} else { //系统、区本、校本
    			
    			if(StringUtils.isNotEmpty(request.getParameter("orderBy"))){
    				orderBy = Integer.parseInt(request.getParameter("orderBy").toString().trim());
    			}
    			
    			if(StringUtils.isNotEmpty(request.getParameter("tfcode"))){
            		tfcode = request.getParameter("tfcode").toString().trim();
            	}
        		if(StringUtils.isNotEmpty(request.getParameter("typeId"))){
            		typeId = Integer.parseInt(request.getParameter("typeId").toString().trim());
            	}
            	
        		if(StringUtils.isNotEmpty(request.getParameter("poolId"))){ //系统资源
        			poolId = Integer.parseInt(request.getParameter("poolId").toString().trim());
        			
        			pagination = resPreviewService.sysRecommendation(tfcode, typeId, resId, poolId, page, perPage, sys_from,orderBy);
        			
        		} else { //区本、校本资源
        			
					pagination = resPreviewService.disRecommendation(tfcode, typeId, fromFlag, resId, currentUserId, page, perPage,orderBy);
				}
			}
		}
    	
    	//生成文件的缩略图路径
        ResThumbnailPathUtil.convertToPurpos_recommendation(pagination.getList(), resServiceLocal, currentResPath);
        
        logger.debug("课程id：" + tfcode);

        logger.debug("查询结果的当前页：" + pagination.getPage());
        logger.debug("查询结果每页资源数目：" + pagination.getPerPage());
        logger.debug("查询到的资源总页：" + pagination.getTotal());
        logger.debug("查询到的资源总数：" + pagination.getTotalLines());
        
        return ResultJSON.getSuccess(pagination);
    }
}
