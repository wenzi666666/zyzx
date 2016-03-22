package net.tfedu.zhl.cloud.resources.navigation.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.core.term.entity.JTerm;
import net.tfedu.zhl.cloud.resources.navigation.service.TermService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询所有学段的controller
 * @author WeiCuicui
 * date  2016.03.22
 */
@Controller
@RequestMapping("/resRestAPI")
public class TermController  {

	@Resource TermService termService;
	
	//查询所有学段
    @RequestMapping(value = "/v1.0/terms")  
    @ResponseBody
    public List<JTerm> selectAllTerms(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	return termService.selectAll();
    }
}
