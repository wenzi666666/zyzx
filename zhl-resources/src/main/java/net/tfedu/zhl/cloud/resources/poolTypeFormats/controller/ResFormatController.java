package net.tfedu.zhl.cloud.resources.poolTypeFormats.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.FileFormat;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.ResPoolType;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.service.ResFormatService;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.service.ResTypeService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资源格式 controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class ResFormatController {

	@Resource ResFormatService resFormatService;
	@Resource ResTypeService resTypeService;
	
	//封装的返回结果
	private ResultJSON resultJSON = new ResultJSON();
	
	
	//异常
	private CustomException exception;
	
	@RequestMapping(value = "/v1.0/formats",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getFormatsByMtype(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<FileFormat> formats = null;
		try {
			//传递的参数
			long poolId = Long.parseLong(request.getParameter("poolId").toString().trim());
			int fromFlag = Integer.getInteger(request.getParameter("fromFlag"));
			String pTfcode = request.getParameter("tfcode");
			long typeId = Long.parseLong(request.getParameter("typeId").toString().trim());
			
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
			map.put("fromFlag", fromFlag);
			List<Long> resourceIds = resTypeService.getAllResourceIdsByPtfcode(map);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
		} finally {
			
		}
		
		return resultJSON;
	}
}
