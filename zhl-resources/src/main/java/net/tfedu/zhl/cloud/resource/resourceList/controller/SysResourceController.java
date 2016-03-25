package net.tfedu.zhl.cloud.resource.resourceList.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResource;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统资源 列表 controller
 * @author WeiCuicui
 *
 */

@Controller
@RequestMapping("resRestAPI")
public class SysResourceController {

	@Resource SysResourceService sysResourceService;
	
	//封装的返回结果
	private ResultJSON resultJSON = new ResultJSON();
	
	//异常
	private CustomException exception;
	
	@RequestMapping(value = "/v1.0/sysResource",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getSysResources(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<SysResource> sysRes = null;
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		
		return resultJSON;
	}
}
