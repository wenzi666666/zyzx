package net.tfedu.zhl.cloud.resource.poolTypeFormat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPoolType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统资源格式 controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class SysResFormatController {

	@Resource ResFormatService resFormatService;
	@Resource ResTypeService resTypeService;
	
	//封装的返回结果
	private ResultJSON resultJSON = new ResultJSON();
	
	
	//异常
	private CustomException exception;
	
	@RequestMapping(value = "/v1.0/sysResource/formats",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getSysResFormatsByMtype(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<String> formats = null;
		try {
			//传递的参数
			long poolId = Long.parseLong(request.getParameter("poolId").toString().trim());
			String pTfcode = request.getParameter("tfcode");
			int typeId = Integer.parseInt(request.getParameter("typeId").toString().trim());
			
			//根据poolId和typeId，查询父类型下所有子类型及其自身
			List<ResPoolType> poolTypes = resFormatService.getTypesByPMTypeAndPool(poolId, typeId);
			
			//提取出资源类型id
			List<Integer> typeIds = new ArrayList<Integer>();
			for (int i = 0; i < poolTypes.size(); i++) {
				typeIds.add(poolTypes.get(i).getTypes().getId());
			}
			
			//查询当前结点下的所有资源Id
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("pTfcode", pTfcode);
			map.put("sys_from", SysFrom.sys_from);
			List<Long> resourceIds = resTypeService.getAllSysResIds(map);
			
			//根据 resourceIds和typeIds，查询资源格式
			formats = resFormatService.getSysResFormatsByMType(resourceIds, typeIds);
		
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
