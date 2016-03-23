package net.tfedu.zhl.cloud.resources.sysResList.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resources.sysResList.entity.ResPool;
import net.tfedu.zhl.cloud.resources.sysResList.service.ResPoolService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资源库 controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class ResPoolController {

	@Resource ResPoolService resPoolService;
	
	//封装返回结果
	private ResultJSON resultJSON = new ResultJSON();
	
	//异常
	private CustomException exception;
	
	/**
	 * 查询所有的资源库
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/pools",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllPools(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<ResPool> pools = null;
		try {
			pools = resPoolService.getAllPools();
			exception = CustomException.SUCCESS;
		} catch (Exception e) {
			// TODO: handle exception
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
		} finally {
			resultJSON.setCode(exception.getCode());
			resultJSON.setData(pools);
			resultJSON.setMessage(exception.getMessage());
			resultJSON.setSign("");
		}
		
		return resultJSON;
	}
}
