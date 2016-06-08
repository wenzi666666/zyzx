package net.tfedu.zhl.cloud.teaching.teachingdesign.controller;

import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 教学设计研究
 * @author wangwr
 *
 */
@Controller
@RequestMapping("/teachingServiceRestAPI")
public class TeachingDesignController {
	
	
	/**
	 * 返回 论坛3.0的访问url
	 * @return
	 */
	@RequestMapping(value="/v1.0/discuss",method= RequestMethod.GET)
	@ResponseBody
	public ResultJSON  discuss(){
		
		
		
		
		return null ;
	}
	
	
	
	
	
	
	
	
	
	

}
