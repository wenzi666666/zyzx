package net.tfedu.zhl.cloud.teaching.teachCases.controller;

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

	/**
	 * 查询所有的教学案例
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllTeachCases(int fromFlag,int termId,int subjectId,int page,int perPage)throws Exception{
		
		return null;
	}
	
	/**
	 * 新建一个教学案例
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases", method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON createOneTeachCase(String teachCase)throws Exception{
		
		return null;
	}
	
	/**
	 * 编辑一个教学案例
	 * @param teachCase
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases", method = RequestMethod.PATCH)
	@ResponseBody
	public ResultJSON editOneTeachCase(String teachCase)throws Exception{
		
		return null;
	}
	
	/**
	 * 删除一条教学案例
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/teachCases",method = RequestMethod.DELETE)
	@ResponseBody
	public ResultJSON deleteOneTeachCase(long id)throws Exception{
		
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
	@RequestMapping(value = "/v1.0/teachCases/contents",method = RequestMethod.DELETE)
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
