package net.tfedu.zhl.cloud.resource.lessonrecord.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.resource.lessonrecord.entity.ZLessonRecord;
import net.tfedu.zhl.cloud.resource.lessonrecord.service.LessonRecordService;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import tk.mybatis.mapper.entity.Example;

/**
 @author wangwr
 @date 2016年11月10日
 @desc 
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
@Controller
@RequestMapping("resRestAPI")
public class LessonRecordController {

	
	
	@Resource
	LessonRecordService lessonRecordService;
	
	
	
	/**
	 * 新增听课记录
	 * @param request
	 * @param response
	 * @return
	 * @throws ParamsException 
	 */
	@RequestMapping(value="v1.0/lessonRecord",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON addLessonRecord(HttpServletRequest request,ZLessonRecord record) throws ParamsException{
		
		
		record.setUserId((Long) request.getAttribute("currentUserId"));
		record.setFlag(false);
		
		return lessonRecordService.insert(record);
	}
	
	
	/**
	 * 编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="v1.0/lessonRecord/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON editLessonRecord(HttpServletRequest request,ZLessonRecord record
			, @PathVariable Long id
			){
		
		record.setId(id);
		
		return lessonRecordService.update(record);
	}
	
	
	/**
	 * 分页查询
	 * @param request
	 * @param page
	 * @param perPage
	 * @return
	 */
	@RequestMapping(value="v1.0/lessonRecordList",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON queryPage(HttpServletRequest request,int page,int perPage){
		
		
		Example example = new Example(ZLessonRecord.class);
		example.createCriteria()
		.andCondition(" user_id =", (Long) request.getAttribute("currentUserId"))
		.andCondition(" flag = false  ")
				;
		
		return lessonRecordService.getPageByExample(example, page, perPage);
	}
	
	
	/**
	 * 获取指定听课记录
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="v1.0/lessonRecord/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON getLessonRecord(HttpServletRequest request,HttpServletResponse response
			,@PathVariable Long id){
		
		return lessonRecordService.get(id);
	}
	
	
	/**
	 * 逻辑删除
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value="v1.0/lessonRecordDel/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON delLessonRecord(HttpServletRequest request,HttpServletResponse response
			,@PathVariable Long id){
		
		ZLessonRecord record = new ZLessonRecord();
		record.setId(id);
		record.setFlag(true); //删除
		
		return lessonRecordService.update(record);
	}
	
	
	
	
}
