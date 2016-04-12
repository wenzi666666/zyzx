package net.tfedu.zhl.cloud.resource.resPreview.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResRecommendationEntity;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

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
	
	
	/**
	 * 根据资源id，fromFlag获取一条资源的详细信息
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/resPreviewInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getResPreviewInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);

        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        ResPreviewInfo previewInfo = null;

        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {

            	// 资源来源
            	int fromFlag = 0;
            	
            	// 资源id
            	long resId = 0;
            	
            	if(StringUtils.isNotEmpty(request.getParameter("resId"))){
            		resId = Long.parseLong(request.getParameter("resId").toString().trim());
            	}
            	if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
            		fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
            	}
            	 

                // 查询一条资源的详细信息
                previewInfo = resPreviewService.getResPreviewInfo(resId,currentUserId, fromFlag);

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
            resultJSON.setData(previewInfo);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign("");
        }

        return resultJSON;
    }
	
	/**
	 * 根据资源id，查询一条资源的所有版本目录（用于从资源检索结果页跳转到预览页面）
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/preview/lists", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getAllLists(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);

        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        // 各个版本的资源目录
        List<List<ResNavEntity>> result = new ArrayList<List<ResNavEntity>>();

        try {

            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {

            	// 资源来源
            	int fromFlag = 0;
            	
            	// 资源id
            	long resId = 0;
            	
            	 // 当前所在结点
            	String curTfcode = "";
            	
            	if(StringUtils.isNotEmpty(request.getParameter("resId"))){
            		resId = Long.parseLong(request.getParameter("resId").toString().trim());
            	}
            	if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
            		fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
            	}
            	 
            	if(StringUtils.isNotEmpty(request.getParameter("curTfcode"))){
            		curTfcode = request.getParameter("curTfcode").toString().trim();
            	}
            	 
                // 查询一条资源的所有版本目录
                result = resPreviewService.getAllResNavs(resId, fromFlag, curTfcode);

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
            resultJSON.setData(result);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign("");
        }

        return resultJSON;
    }
	
	/**
	 * 从资源预览页面跳转回到资源列表页时，加载资源目录
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 *//*
	@RequestMapping(value = "/v1.0/backCourseContent", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getCourseContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        *//**
         * 返回json的结果对象
         *//*
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);

        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        JUserDefault courseContent = null;
        try {

            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {
            	
            	String tfcode = "";
            	
            	if(StringUtils.isNotEmpty(request.getParameter("tfcode")))
                     tfcode = request.getParameter("tfcode").toString().trim();

                // 查询课程目录
                courseContent = resPreviewService.getPnodes(tfcode);
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
            resultJSON.setData(courseContent);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign("");
        }

        return resultJSON;
    }*/
	
	/**
	 * 资源推荐，将当前预览的资源显示在第一条的位置
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/resRecommendation", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getResRecommendation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);

        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
       
        //查询结果
        Pagination<ResRecommendationEntity> pagination = null;
        
        try {

            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {
            	
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
            	
            	List<Integer> sys_from = SysFrom.sys_from;
            	
            	if(StringUtils.isNotEmpty(request.getParameter("resId"))){
            		resId = Long.parseLong(request.getParameter("resId").toString().trim());
            	}
            	if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
            		fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
            	}
            	if(StringUtils.isNotEmpty(request.getParameter("tfcode"))){
            		tfcode = request.getParameter("tfcode").toString().trim();
            	}
            	
            	if(StringUtils.isNotEmpty(request.getParameter("page"))){
            		page = Integer.parseInt(request.getParameter("page").toString().trim());
            	}
            	if(StringUtils.isNotEmpty(request.getParameter("perPage"))){
            		perPage = Integer.parseInt(request.getParameter("perPage").toString().trim());
            	}
            	
            	
            	//是否我资源检索（0：否；1：是）默认为非资源检索页
            	int isSearch = 0; 
            	
            	if(StringUtils.isNotEmpty(request.getParameter("isSearch"))){ //从资源检索页面跳转到预览页面的
            		
            		if(StringUtils.isNotEmpty(request.getParameter("searchKeyword"))){
            			searchKeyword = request.getParameter("searchKeyword").toString().trim();
            		}            	
            		
            	} else { //从系统资源、区本资源、校本资源跳转过来的
            		
            		if(StringUtils.isNotEmpty(request.getParameter("poolId"))){ //系统资源
            			poolId = Integer.parseInt(request.getParameter("poolId").toString().trim());
            			
            			if(StringUtils.isNotEmpty(request.getParameter("typeId"))){
                    		typeId = Integer.parseInt(request.getParameter("typeId").toString().trim());
                    	}
            			
            			
            		} else { //区本、校本资源
						
					}
				}
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
}
