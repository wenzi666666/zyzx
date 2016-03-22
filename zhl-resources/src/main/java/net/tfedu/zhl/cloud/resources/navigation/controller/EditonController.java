package net.tfedu.zhl.cloud.resources.navigation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tfedu.zhl.cloud.resources.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resources.navigation.service.EditionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resRestAPI")
public class EditonController {

	@Resource EditionService editionService;
	
	//根据学段、学科，查询所有版本教材
	@RequestMapping(value = "/v1.0/editions")  
	@ResponseBody
	public List<JSyscourse> getAllEditions(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		long termId = request.getParameter("termId") != null ? Long.parseLong(request.getParameter("termId").toString().trim()) : 0;
		long subjectId = request.getParameter("subjectId") != null ? Long.parseLong(request.getParameter("subjectId").toString().trim()) : 0;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("termId", termId);
		map.put("subjectId", subjectId);
		
		//根据学段、学科，查询所有教材版本
		return editionService.getAllEditionsByTermAndSub(map);
	}
	
}
