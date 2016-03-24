package net.tfedu.zhl.cloud.resources.poolTypeFormats.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.ResPool;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.service.ResPoolService;
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
			
			//添加上 全部  库类型
			ResPool all = new ResPool();
			long id = 0;
			all.setId(id);
			all.setName("全部");
			
			//添加“全部”这个资源库类型
			pools.add(0, all);
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
