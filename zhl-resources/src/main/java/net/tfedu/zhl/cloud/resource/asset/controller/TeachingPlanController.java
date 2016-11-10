package net.tfedu.zhl.cloud.resource.asset.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.resource.asset.entity.TeachingPlan;
import net.tfedu.zhl.cloud.resource.asset.service.TeachingPlanService;
import net.tfedu.zhl.cloud.resource.lessonrecord.entity.ZLessonRecord;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;

/**
 
 
 @author wangwr
 @date 2016年11月10日
 @desc   教案（特殊的自建資源 ）的专用controller
  
 copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
@Controller
@RequestMapping("resRestAPI")
public class TeachingPlanController {
	
	
	@Resource
	TeachingPlanService  teachingPlanService; 
	
	
	
	
	
	/**
	 * 增加新的教案
	 * @param request
	 * @param obj   教案對象 
	 * @return    新增的id
	 * @throws Exception
	 */
	@RequestMapping(value="/v1.0/teachingPlan",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON addTeachingPlan(HttpServletRequest request,TeachingPlan obj) throws Exception{
		
		obj.setUserId(ControllerHelper.getCurrentUserId(request));
		
		return teachingPlanService.addRecord(obj) ;
	}
	
	
	

	
	/**
	 * 编辑
	 * @param request
	 * @param obj
	 * @param id
	 * @return  成功或失败信息
	 * @throws Exception
	 */
	@RequestMapping(value="/v1.0/teachingPlan/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON editTeachingPlan(HttpServletRequest request,TeachingPlan obj
			,@PathVariable long id
			) throws Exception{
		
		return teachingPlanService.editRecord(obj) ;
	}
	
	
	
	

	/**
	 * 按当前用户分页查询
	 * @param request
	 * @param page
	 * @param perPage
	 * @return  分页信息
	 * @throws Exception
	 */
	@RequestMapping(value="/v1.0/teachingPlanList",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON queryPage(HttpServletRequest request,int page,int perPage
			) throws Exception{
		
		return teachingPlanService.queryRecord(page, perPage, 
				ControllerHelper.getCurrentUserId(request)
				) ;
	}
	
	
	/**
	 * 获取指定记录
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="v1.0/teachingPlan/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON getTeachingPlan(HttpServletRequest request
			,@PathVariable Long id)throws Exception{
		
		return teachingPlanService.getRecord(id);
	}
	
	
	/**
	 * 逻辑删除
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="v1.0/teachingPlanDel/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON delTeachingPlan(HttpServletRequest request
			,@PathVariable Long id)throws Exception{
		
		return teachingPlanService.delRecord(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
