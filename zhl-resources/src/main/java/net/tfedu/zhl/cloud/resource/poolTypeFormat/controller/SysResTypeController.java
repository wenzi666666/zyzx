package net.tfedu.zhl.cloud.resource.poolTypeFormat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统资源类型 controller
 * 
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class SysResTypeController {

    @Resource
    ResTypeService resTypeService;

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

                // 传递的三个参数
                long poolId = Long.parseLong(request.getParameter("poolId").toString().trim());

                // 当前课程结点的tfcode
                String pTfcode = request.getParameter("tfcode");
                
                if(request.getParameter("isEPrepare") != null){//而备课
                	//新的类型查询方法（去除一些类型）
                	
                } else {
                	types = resTypeService.getSysResTypes(poolId, pTfcode);
				}

               
                exception = CustomException.SUCCESS;

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