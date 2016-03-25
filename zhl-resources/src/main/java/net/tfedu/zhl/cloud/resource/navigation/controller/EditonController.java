package net.tfedu.zhl.cloud.resource.navigation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resource.navigation.service.EditionService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 根据学段、学科，选择教材版本信息
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class EditonController {

	@Resource EditionService editionService;
	
	//封装传递的参数
	private HashMap<String, Object> map = new HashMap<String, Object>();
	
	//返回结果
	private ResultJSON result = new ResultJSON();
	
	//异常信息
	private CustomException exception;
	
	//根据学段、学科，查询所有版本教材
	@RequestMapping(value = "/v1.0/editions",method = RequestMethod.GET)  
	@ResponseBody
	public ResultJSON getAllEditions(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<JSyscourse> editions = null;
		try {
			
			long termId = Long.parseLong(request.getParameter("termId").toString().trim());
			long subjectId = Long.parseLong(request.getParameter("subjectId").toString().trim());
			
			map.put("termId", termId);
			map.put("subjectId", subjectId);
			
			//根据学段、学科，查询所有教材版本
			editions = editionService.getAllEditionsByTermAndSub(map);
			
			exception = CustomException.SUCCESS;
			
		} catch (Exception e) {
			// TODO: handle exception
			//获得异常信息
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
			
		} finally {
			result.setCode(exception.getCode());
			result.setData(editions);
			result.setMessage(exception.getMessage());
			result.setSign("");
		}
		
		return result;
	}
	
}
