package net.tfedu.zhl.cloud.resource.navigation.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.navigation.service.TermSubjectService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.subject.entity.JSubject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询学段下的所有学科 controller
 * 
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("resRestAPI")
public class TermSubjectController {

    @Resource
    TermSubjectService termSubjectService;

    // 查询学段下的所有学科
    @RequestMapping(value = "/v1.0/subjects", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getAllSubjectsByTerm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON result = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        List<JSubject> subjects = null;
        try {

            if (exception == null && currentUserId != null) {
                // 接收到的termId参数
            	long termId = 0;
            	if(StringUtils.isNotEmpty(request.getParameter("termId"))){
            		termId = Long.parseLong(request.getParameter("termId").toString().trim());
            	}
                subjects = termSubjectService.getAllSubjectsByTerm(termId);
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
            result.setCode(exception.getCode());
            result.setData(subjects);
            result.setMessage(exception.getMessage());
            result.setSign("");
        }
        return result;
    }

}
