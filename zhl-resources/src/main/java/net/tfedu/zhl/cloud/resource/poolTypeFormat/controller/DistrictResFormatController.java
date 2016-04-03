package net.tfedu.zhl.cloud.resource.poolTypeFormat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resRestAPI")
public class DistrictResFormatController {

    @Resource
    ResFormatService resFormatService;
    @Resource
    ResTypeService resTypeService;

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

                // 资源类型id
                String tfcode = request.getParameter("tfcode");
                int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));

                formats = resFormatService.getDisResFormats(tfcode, fromFlag);

                exception = CustomException.SUCCESS;
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
