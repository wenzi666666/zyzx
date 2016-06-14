package net.tfedu.zhl.cloud.teaching.discuss.controller;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.teaching.discuss.entity.TDiscussLog;
import net.tfedu.zhl.cloud.teaching.discuss.entity.TDiscussRecommend;
import net.tfedu.zhl.cloud.teaching.discuss.service.DiscussLogService;
import net.tfedu.zhl.cloud.teaching.discuss.service.DiscussRecommendService;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




/**
 * 推荐班级及其访问记录的相关接口
 * @author wangwr
 *
 */

@Controller
@RequestMapping("/teachingServiceRestAPI")
public class DiscussController {

	
	
	
	
	@Resource
	DiscussRecommendService discussService;
	

	@Resource
	DiscussLogService  discussLogService;
	
	
	/**
	 * 返回推荐班级列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="v1.0/discuss/recommend",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON getRecomended( int page,int perPage ) throws Exception{
		return discussService.getPage(page, perPage);
	}
	
	
	
	/**
	 * 增加访问记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="v1.0/discuss/readed",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON addReadRecord(@ModelAttribute("currentUserId") Long currentUserId,String classId) throws Exception{
		
		classId = ControllerHelper.checkEmpty(classId);
		
		TDiscussLog c = new TDiscussLog();
		c.setClassid(classId);
		c.setUserid(currentUserId);
		c.setCreatetime(Calendar.getInstance().getTime());
		
		return discussLogService.insert(c);
	}
	
	

	/**
	 * 返回最近访问列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="v1.0/discuss/readed",method=RequestMethod.GET)
	@ResponseBody	
	public ResultJSON getReaded( HttpServletRequest request,HttpServletResponse response ) throws Exception{
		
		Long currentUserId = (Long)request.getAttribute("currentUserId");	
		
		return  discussLogService.getReadLog(currentUserId) ;
	}
	
	
	
	
	
	/**
	 * 增加推荐班级列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="v1.0/discuss/recommend",method=RequestMethod.POST)
	@ResponseBody		
	public ResultJSON addRecomended( HttpServletRequest request,HttpServletResponse response ) throws Exception{
		Long currentUserId = (Long)request.getAttribute("currentUserId");	

		String className = ControllerHelper.getParameter(request, "className"); //	班级名称
		String classImage	 = ControllerHelper.getParameter(request, "classImage");	//班级图片路径
		String schoolName = ControllerHelper.getParameter(request, "schoolName");		//学校名称
		String classId	 = ControllerHelper.getParameter(request, "classId");	//班级id
		String note		 = ControllerHelper.getOptionalParameter(request, "note");//班级简介
		
		TDiscussRecommend record = new TDiscussRecommend();
		record.setClassid(classId);
		record.setClassname(className);
		record.setClassimage(classImage);
		record.setSchoolname(schoolName);
		record.setNote(note);
		record.setCreator(currentUserId);
		record.setCreatetime(Calendar.getInstance().getTime());
		record.setFlag(false);
		return discussService.insert(record);
	}
	
	

	/**
	 * 获取指定
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="v1.0/discuss/recommend/{id}",method=RequestMethod.GET)
	@ResponseBody		
	public ResultJSON getOne(@PathVariable Long id) throws Exception{
		
		return discussService.get(id) ;
	}
	
	
	/**
	 * 修改推荐班级列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="v1.0/discuss/recommend/{id}",method=RequestMethod.POST)
	@ResponseBody		
	public ResultJSON updateRecomended(HttpServletRequest request,@PathVariable Long id) throws Exception{
		
		
		
		String className = ControllerHelper.getParameter(request, "className"); //	班级名称
		String classImage	 = ControllerHelper.getParameter(request, "classImage");	//班级图片路径
		String schoolName = ControllerHelper.getParameter(request, "schoolName");		//学校名称
		String classId	 = ControllerHelper.getParameter(request, "classId");	//班级id
		String note		 = ControllerHelper.getOptionalParameter(request, "note");//班级简介
		TDiscussRecommend record = new TDiscussRecommend();
		record.setId(id);
		record.setClassid(classId);
		record.setClassname(className);
		record.setClassimage(classImage);
		record.setSchoolname(schoolName);
		record.setNote(note);
		
		return discussService.update(record) ;
	}
	
	
	
	
	/**
	 * 删除推荐班级列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ResultJSON delRecomended( String ids ) throws Exception{
		
		ControllerHelper.checkEmpty(ids);
		
		return discussService.removeRecommendRecords(ids);
	}
	
	
	
	
}
