package net.tfedu.zhl.cloud.resource.poolTypeFormat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResPoolService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统、区本、校本资源的类型、格式查询
 * 
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class TypesAndFormatsController {

	@Resource
    ResTypeService resTypeService;
	
	@Resource
    ResFormatService resFormatService;
	
	@Resource
    ResPoolService resPoolService;
	
	/**
     * 查询所有的资源库
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1.0/pools", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getAllPools(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        List<ResPool> pools = new ArrayList<ResPool>();
        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {

                // 查询所有资源库
                pools = resPoolService.getAllPools();

                exception = CustomException.SUCCESS;
            } else {
            	exception = CustomException.INVALIDACCESSTOKEN;
			}

        } catch (Exception e) {
            // TODO: handle exception
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();
        } finally {
            resultJSON.setCode(exception.getCode());
            resultJSON.setData(pools);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign("");
        }

        return resultJSON;
    }
	
	
	/**
	 * 查询系统资源的类型
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/sysResource/types", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getSysResTypesByPool(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        List<ResType> types = new ArrayList<ResType>();
        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {

            	String pTfcode = "";
            	
            	long poolId = 0;
            	if(StringUtils.isNotEmpty(request.getParameter("pTfcode"))){
            		pTfcode = request.getParameter("pTfcode").toString().trim();
            	}
               
                if(StringUtils.isNotEmpty(request.getParameter("poolId"))){
                	poolId = Long.parseLong(request.getParameter("poolId").toString().trim());
                }
            	
                if(request.getParameter("isEPrepare") != null){//e备课
                	//新的类型查询方法（去除一些类型）
                	types = resTypeService.getSysResTypes_EPrepare(poolId, pTfcode, SysFrom.removeTypeIds);
                } else {
                	types = resTypeService.getSysResTypes(poolId, pTfcode);
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

            resultJSON.setCode(exception.getCode());
            resultJSON.setData(types);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign(" ");
        }

        return resultJSON;
    }
	
	/**
	 * 查询系统资源的格式
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/sysResource/formats", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getSysResFormatsByMtype(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        List<String> formats = new ArrayList<String>();
        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {

            	String pTfcode = "";
            	int typeId = 0;
            	long poolId = 0;
            	if(StringUtils.isNotEmpty(request.getParameter("pTfcode"))){
            		pTfcode = request.getParameter("pTfcode").toString().trim();
            	}
                if(StringUtils.isNotEmpty(request.getParameter("typeId"))){
                	typeId = Integer.parseInt(request.getParameter("typeId").toString().trim());
                }
                if(StringUtils.isNotEmpty(request.getParameter("poolId"))){
                	poolId = Long.parseLong(request.getParameter("poolId").toString().trim());
                }

                // 根据 resourceIds和typeIds，查询资源格式
                formats = resFormatService.getSysResFormats(poolId, pTfcode, typeId);

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
            resultJSON.setCode(exception.getCode());
            resultJSON.setData(formats);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign("");
        }

        return resultJSON;
    }
	
	/**
	 * 查询区本、校本资源的类型
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/districtResource/types", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getDisResTypesByPool(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        // 定义类型结果集
        List<ResType> types = new ArrayList<ResType>();
        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {

            	String tfcode = "";
            	int fromFlag = 3;
            	if(StringUtils.isNotEmpty(request.getParameter("tfcode"))){
            		tfcode = request.getParameter("tfcode").toString().trim();
            	}
                if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
                	fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
                }
                
                if(request.getParameter("isEPrepare") != null){//而备课
                	//新的类型查询方法（去除一些类型）
                	types = resTypeService.getDisResType_EPrepare(tfcode, fromFlag, SysFrom.removeTypeIds);
                	
                } else {
                	 types = resTypeService.getDisResTypes(tfcode, fromFlag);
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
            resultJSON.setCode(exception.getCode());
            resultJSON.setData(types);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign(" ");
        }

        return resultJSON;
    }
	
	/**
	 * 查询区本、校本资源的格式
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/districtResource/formats", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getDisFormats(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        // 格式
        List<String> formats = new ArrayList<String>();
        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {

            	String tfcode = "";
            	int fromFlag = 3;
            	if(StringUtils.isNotEmpty(request.getParameter("tfcode"))){
            		tfcode = request.getParameter("tfcode").toString().trim();
            	}
                if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
                	fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
                }

                formats = resFormatService.getDisResFormats(tfcode, fromFlag);

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
            resultJSON.setCode(exception.getCode());
            resultJSON.setData(formats);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign("");
        }

        return resultJSON;
    }
}
