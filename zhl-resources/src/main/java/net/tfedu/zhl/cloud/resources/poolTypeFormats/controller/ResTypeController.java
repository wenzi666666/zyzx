package net.tfedu.zhl.cloud.resources.poolTypeFormats.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.ResType;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.service.ResTypeService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资源类型controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class ResTypeController {

	@Resource ResTypeService resTypeService;
	
	//封装的返回结果
	private ResultJSON resultJSON = new ResultJSON();
	
	//异常
	private CustomException exception;
	
	@RequestMapping(value = "/v1.0/types",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getTypesByPool(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<ResType> types = null;
		try {
			//传递的三个参数
			long poolId = Long.parseLong(request.getParameter("poolId").toString().trim());
			int fromFlag = Integer.getInteger(request.getParameter("fromFlag"));
			String tfcode = request.getParameter("tfcode");
			
			//查询当前结点下的所有资源Id
			List<>
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		
		return resultJSON;
	}
	
}
