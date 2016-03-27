package net.tfedu.zhl.cloud.resource.resourceList.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
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
			//资源来源
			int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));
			
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
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sys_from", SysFrom.sys_from);
			map.put("pTfcode", tfcode);
			List<Long> resourceIds = sysResourceService.getAllSysResourceIds(map);
			
			
 			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		
		return resultJSON;
	}
}
