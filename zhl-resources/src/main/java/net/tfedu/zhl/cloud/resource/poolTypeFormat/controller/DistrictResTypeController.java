package net.tfedu.zhl.cloud.resource.poolTypeFormat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 区本校本资源的类型controller
 * 
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class DistrictResTypeController {

    @Resource
    ResTypeService resTypeService;

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

}
