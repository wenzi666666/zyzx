package net.tfedu.zhl.cloud.teaching.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.teaching.common.service.BasicInfoService;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/teaching")
public class BasicInfoController {
	
	

	@Resource 
	BasicInfoService basicInfoServiceImpl ;
	
	
	
	
	/**
	 * 获取全部的学段
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/terms",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllTerm() throws  Exception{
		
		return basicInfoServiceImpl.selectAll() ;
	}
	
	
	

}
