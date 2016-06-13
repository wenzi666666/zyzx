package net.tfedu.zhl.cloud.teaching.teachCases.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.teaching.teachCases.entity.TeachCases;
import net.tfedu.zhl.cloud.teaching.teachCases.service.TeachCasesService;
import net.tfedu.zhl.helper.PaginationHelper;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 教学案例相关的接口
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/teachingServiceRestAPI")
public class TeachCasesController {

	@Resource TeachCasesService teachCasesService;
	
	/**
	 * 查询所有的教学案例
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllTeachCases(int fromFlag,int termId,int subjectId,int page,int perPage)throws Exception{
		
		PaginationHelper<TeachCases> pagination = teachCasesService.selectAllCases(fromFlag, termId, subjectId, page, perPage);
		
		return ResultJSON.getSuccess(pagination);
	}
	
	/**
	 * 新建一个教学案例
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON createOneTeachCase(HttpServletRequest request,String teachCase)throws Exception{
		//当前用户
		Long userId = (Long) request.getAttribute("currentUserId");
		
		teachCasesService.createOneTeachCase(teachCase, userId);
		
		return null;
	}
	
	/**
	 * 编辑一个教学案例
	 * @param teachCase
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCasesEdit", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON editOneTeachCase(HttpServletRequest request,String teachCase)throws Exception{
		//当前用户
	    Long userId = (Long) request.getAttribute("currentUserId");
		teachCasesService.editOneTeachCase(teachCase, userId);	
		return null;
	}
	
	/**
	 * 删除一条教学案例
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCasesDelete",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON deleteOneTeachCase(long id)throws Exception{
		teachCasesService.deleteOneTeachCase(id);
		return null;
	}
	
	
	
	
	
	
	/**
	 * 预览一条教学案例
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases/previewInfo",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getOneTeachCasePreview(long id)throws Exception{
		
		return null;
	}
	
	/**
	 * 查询一个教学案例下的所有内容
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases/contents", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getContentsInOneTeachCase(long id)throws Exception{
		
		return null;
	}

	
	/**
	 * 增加一个内容
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases/contents",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON createOneContent(long caseId,int typeId,String fname)throws Exception{
		
		return null;
	}
	
	/**
	 * 删除一个内容
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases/contentsDelete",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON deleteOneContent(long id)throws Exception{
		
		return null;
	}
	
	/**
	 * 预览一个内容
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases/contents/previewInfo",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getOneContentPreview(long id)throws Exception{
		
		return null;
	}
}
