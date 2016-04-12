package net.tfedu.zhl.cloud.resource.resSearch.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resSearch.entity.ResSearchResultEntity;
import net.tfedu.zhl.cloud.resource.resSearch.service.ResSearchService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.util.ResThumbnailPathUtil;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资源跨库检索的controller
 * 
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class ResSearchController {

    @Resource
    ResSearchService resSearchService;
    
    //写入日志
    Logger logger = LoggerFactory.getLogger(ResSearchController.class);

    /**
     * 根据检索内容，跨库检索所有资源
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/v1.0/resSearchResults")
    @ResponseBody
    public ResultJSON getAllResources(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        // 分页查询结果
        Pagination<ResSearchResultEntity> pagination = null;

        try {

            // 若当前用户已经登录系统
            if (exception == null && currentUserId != null) {
            	
            	//获取文件服务器的访问url 
				String resServiceLocal = (String)request.getAttribute("resServiceLocal");
				String currentResPath = (String)request.getAttribute("currentResPath");
				
				// 检索范围 0 全部资源 1 系统资源 3 校本资源 4 区本资源
				int fromFlag = 0;
				
				 // 检索的关键词
                String searchKeyword = "";
				
                // 资源格式：全部，文本，图片......
                String format = "全部";
                
                // 页码
                int page = 1;
                
                // 每页记录数目
                int perPage = 10;
                
                if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
                	fromFlag =  Integer.parseInt(request.getParameter("fromFlag").toString().trim());
                }
                if(StringUtils.isNotEmpty(request.getParameter("searchKeyword"))){
                	searchKeyword =  request.getParameter("searchKeyword").toString().trim();
                }
                
                if(StringUtils.isNotEmpty(request.getParameter("format"))){
                	format =  request.getParameter("format").toString().trim();
                }
                
                if(StringUtils.isNotEmpty(request.getParameter("page"))){
                	page =  Integer.parseInt(request.getParameter("page").toString().trim());
                }
                
                if(StringUtils.isNotEmpty(request.getParameter("perPage"))){
                	perPage =  Integer.parseInt(request.getParameter("perPage").toString().trim());
                }
                

                pagination = resSearchService.getResources(fromFlag, SysFrom.sys_from, searchKeyword, format, page,
                        perPage,currentUserId);
                
                //生成文件的缩略图路径
                ResThumbnailPathUtil.convertToPurpos_resSearch(pagination.getList(), resServiceLocal, currentResPath);
                
                logger.debug("检索关键字：" + searchKeyword);
                logger.debug("资源格式：" + format);
                logger.debug("资源来源fromFlag：" + fromFlag);
                logger.debug("检索结果的当前页：" + pagination.getPage());
                logger.debug("检索结果每页资源数目：" + pagination.getPerPage());
                logger.debug("检索到的资源总页：" + pagination.getTotal());
                logger.debug("检索到的资源总数：" + pagination.getTotalLines());

                exception = CustomException.SUCCESS;

            } else {
            	exception = CustomException.INVALIDACCESSTOKEN;
			}

        } catch (Exception e) {
            // TODO: handle exception
            // 捕获异常信息
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();
        } finally {
            // 封装结果集
            resultJSON.setCode(exception.getCode());
            resultJSON.setData(pagination);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign("");
        }

        return resultJSON;
    }
    
    /**
     * 查询资源检索结果中的格式
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/v1.0/resSearchResults/formats")
    @ResponseBody
    public ResultJSON getResFormats(HttpServletRequest request,HttpServletResponse response)throws IOException{
    	 /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        
        List<String> resultList = new ArrayList<String>();
    	try {
    		
    		// 若当前用户已经登录系统
            if (exception == null && currentUserId != null) {
            	// 检索范围 -1 全部  0 全部资源 1 系统资源 3 校本资源 4 区本资源
            	int fromFlag = -1; //默认为全部
            	
            	if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
            		fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
            	}

                // 检索的关键词
                String searchKeyword = "";
                if(StringUtils.isNotEmpty(request.getParameter("searchKeyword"))){
                	searchKeyword = request.getParameter("searchKeyword").toString().trim();
                }
                
                resultList = resSearchService.getFileFormats(searchKeyword, fromFlag, SysFrom.sys_from,currentUserId);
                
                exception = CustomException.SUCCESS;
            }
			
		} catch (Exception e) {
			// TODO: handle exception
			// 捕获异常信息
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();
		} finally {
			
			// 封装结果集
            resultJSON.setCode(exception.getCode());
            resultJSON.setData(resultList);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign("");
		}
    	
    	return resultJSON;
    }

}
