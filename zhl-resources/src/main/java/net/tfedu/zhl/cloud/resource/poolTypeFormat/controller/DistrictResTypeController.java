package net.tfedu.zhl.cloud.resource.poolTypeFormat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 区本校本资源的类型controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class DistrictResTypeController {

	@Resource ResTypeService resTypeService;
	
	//封装返回结果
	private ResultJSON resultJSON = new ResultJSON();
	
	//异常
	private CustomException exception;
	
	@RequestMapping(value = "/v1.0/districtResource/types",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getDisResTypesByPool(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<ResType> types = null;
		try {
			
			//传递参数
			String tfcode = request.getParameter("tfcode");
			//3校本 4区本
			int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));
		
			
			//根据tfcode获得区本校本资源ids
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("pTfcode", tfcode);
			List<Long> resourceIds = resTypeService.getAllDisResIds(map);
			
			types = resTypeService.getDisResType(resourceIds, fromFlag);
			
			//资源类型中增加一个“全部”
			ResType all = new ResType();
			all.setId(0);
			all.setMtype("全部");
			types.add(all);
			
			exception = CustomException.SUCCESS;
			
		} catch (Exception e) {
			// TODO: handle exception
			//捕获异常信息
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
		} finally {
			resultJSON.setCode(exception.getCode());
			resultJSON.setData(types);
			resultJSON.setMessage(exception.getMessage());
			resultJSON.setSign(" ");
		}
		
		return resultJSON;
	}
	
}
