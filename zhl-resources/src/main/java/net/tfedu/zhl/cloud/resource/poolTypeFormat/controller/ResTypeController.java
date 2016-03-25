package net.tfedu.zhl.cloud.resource.poolTypeFormat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPoolType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
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
			String pTfcode = request.getParameter("tfcode");
			
			//查询当前结点下的所有资源Id
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("pTfcode", pTfcode);
			map.put("fromFlag", fromFlag);
			List<Long> resourceIds = resTypeService.getAllResourceIdsByPtfcode(map);
			
			//根据资源库，查询所有一级、二级的资源类型id
			List<ResPoolType> poolTypeIds = resTypeService.getAllTypeIdsByPool(poolId);
			
			List<Integer> typeIds = new ArrayList<Integer>();
			
			for (int i = 0; i < poolTypeIds.size(); i++) {
				typeIds.add(poolTypeIds.get(i).getTypes().getId());
			}
			
			/**
			 * 当资源库选择  “全部” 或  “教学素材” 时
			 * 显示所有一级类型
			 */
			if(poolId == 0 || poolId == 4){
				
				types = resTypeService.getFirstLevelType(resourceIds, typeIds);
				
			} else {  //当资源库选择  “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型；当资源库为“理化生实验”时，只显示“全部”。
				types = resTypeService.getSecondLevelType(resourceIds, typeIds);
			}
			
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
