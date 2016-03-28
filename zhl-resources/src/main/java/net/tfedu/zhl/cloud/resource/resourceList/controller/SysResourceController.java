package net.tfedu.zhl.cloud.resource.resourceList.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
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
	@Resource ResTypeService resTypeService;

	
	@RequestMapping(value = "/v1.0/sysResource",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getSysResources(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//封装的返回结果
		ResultJSON resultJSON = new ResultJSON();
		
		//异常
		CustomException exception = null;
		
		//查询结果，封装为pagination
		Pagination<SysResourceEntity> pagination = null;
		try {
			
			//资源库id
			long poolId = Long.parseLong(request.getParameter("poolId"));
			
			//类型Id
			int mTypeId = Integer.parseInt(request.getParameter("mTypeId"));
			
			//资源格式
			String fileFormat = request.getParameter("fileFormat");
			
			//课程tfcode
			String tfcode = request.getParameter("tfcode");
			
			//排序方式（综合排序；最多下载；最新发布）
			int orderBy = Integer.parseInt(request.getParameter("orderBy"));
			
			//页码
			int page = Integer.parseInt(request.getParameter("page"));
			
			//每页的记录数
			int perPage = Integer.parseInt(request.getParameter("perPage"));

			
			//根据fileFormat去查询该格式下的所有 后缀
			List<String> fileExts = sysResourceService.getFileExtsByFormat(fileFormat);
			
			//根据当前结点tfcode，以及sys_from，查询系统资源id
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sys_from", SysFrom.sys_from);
			map.put("pTfcode", tfcode);
			List<Long> resourceIds = resTypeService.getAllSysResIds(map);
			
			//根据资源库id和父类型id，得到父类型的所有子类型及其自身
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			map1.put("poolId", poolId);
			map1.put("MType", mTypeId);
			List<Integer> typeIds = resTypeService.getTypesByPMTypeAndPool(poolId, mTypeId);
			
			//查询出的系统资源信息
			pagination = sysResourceService.getSysResList(SysFrom.sys_from, fileExts, resourceIds, tfcode, orderBy, typeIds, page, perPage);
			
			exception = CustomException.SUCCESS;
			
 			
		} catch (Exception e) {
			// TODO: handle exception
			//捕获异常信息
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
			
		} finally {
			//封装结果集
			resultJSON.setCode(exception.getCode());
			resultJSON.setData(pagination);
			resultJSON.setMessage(exception.getMessage());
			resultJSON.setSign("");
		}
		
		return resultJSON;
	}
}
