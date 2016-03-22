package net.tfedu.zhl.cloud.resources.navigation.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.core.subject.entity.JSubject;
import net.tfedu.zhl.cloud.resources.navigation.service.TermSubjectService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询学段下的所有学科 controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("resRestAPI")
public class TermSubjectController {

	@Resource TermSubjectService termSubjectService;
	
	//查询学段下的所有学科
    @RequestMapping(value = "/v1.0/subjects")  
    @ResponseBody
	public List<JSubject> getAllSubjectsByTerm(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	//接收到的termId参数
    	long termId = request.getParameter("termId") != null ? Long.parseLong(request.getParameter("termId").toString().trim()) : 0;
    	
    	//返回查询结果
    	return termSubjectService.getAllSubjectsByTerm(termId);
    }
	
}
