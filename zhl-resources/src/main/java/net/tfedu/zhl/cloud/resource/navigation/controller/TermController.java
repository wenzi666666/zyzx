package net.tfedu.zhl.cloud.resource.navigation.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.navigation.service.TermService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.term.entity.JTerm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询所有学段的controller
 * 
 * @author WeiCuicui date 2016.03.22
 */
@Controller
@RequestMapping("/resRestAPI")
public class TermController {

    @Resource
    TermService termService;

    // 查询所有学段
    @RequestMapping(value = "/v1.0/terms", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON selectAllTerms(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON result = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        List<JTerm> terms = null;
        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {
                terms = termService.selectAll();
                exception = CustomException.SUCCESS;
            }

        } catch (Exception e) {
            // TODO: handle exception
            // 获得异常信息并输出
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();
        } finally {
            result.setCode(exception.getCode());
            result.setData(terms);
            result.setMessage(exception.getMessage());
            result.setSign("");
        }

        return result;
    }
}
