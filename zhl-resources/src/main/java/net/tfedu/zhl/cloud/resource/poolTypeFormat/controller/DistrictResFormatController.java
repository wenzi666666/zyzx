package net.tfedu.zhl.cloud.resource.poolTypeFormat.controller;

import java.io.IOException;
import java.util.HashMap;
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

	@Resource ResFormatService resFormatService;
	@Resource ResTypeService resTypeService;
	

	//封装的返回结果
	private ResultJSON resultJSON = new ResultJSON();
	
	
	//异常
	private CustomException exception;
	
	@RequestMapping(value = "/v1.0/districtResource/formats",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getDisFormats(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<String> formats = null;
		try {
			
			//资源类型id
			String tfcode = request.getParameter("tfcode");
			int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));
			
			//获得资源id
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("pTfcode", tfcode);
			List<Long> resourceIds = resTypeService.getAllDisResIds(map);
			
			formats = resFormatService.getDisResFormatsByMType(resourceIds, fromFlag);
			
			//查询结果中增加一个 “全部”
			formats.add("全部");
			exception = CustomException.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			//捕获异常信息
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
