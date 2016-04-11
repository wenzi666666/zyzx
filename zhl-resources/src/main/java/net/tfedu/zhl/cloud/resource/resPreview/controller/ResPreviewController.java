package net.tfedu.zhl.cloud.resource.resPreview.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResNavEntity;
import net.tfedu.zhl.cloud.resource.resPreview.entity.ResPreviewInfo;
import net.tfedu.zhl.cloud.resource.resPreview.service.ResPreviewService;
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
	 */
	@RequestMapping(value = "/v1.0/backCourseContent", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getCourseContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
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
    }
}
