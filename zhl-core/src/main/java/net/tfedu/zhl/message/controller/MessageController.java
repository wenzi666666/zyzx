package net.tfedu.zhl.message.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.message.service.JMessageService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

/**
 * 消息
 * @author wangwr
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class MessageController {

	@Resource
	JMessageService jMessageService;
	
	
	
	/**
	 * 获取用户新消息的个数
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/message/new",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON  getNewMessageNum(HttpServletRequest request, HttpServletResponse response){
		//返回json的结果对象
		ResultJSON result = new ResultJSON();
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		//返回
		Object data = null;
		
		try{
			if(currentUserId!=null && exception==null){	
				//获取文件服务器的访问url 

				data =  jMessageService.getUserNewMessageNumber(currentUserId);
				
				exception = CustomException.SUCCESS;
			}
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;		
	}
	
	
	
	/**
	 * 分页获取用户新信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/message",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON  getUserNewMessage(HttpServletRequest request, HttpServletResponse response){
		//返回json的结果对象
		ResultJSON result = new ResultJSON();
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		//返回
		Object data = null;
		
		try{
			if(currentUserId!=null && exception==null){	
				//获取文件服务器的访问url 
				int page = 1; 
				int perPage = 10;
				String _page = request.getParameter("page");
				String _prePage = request.getParameter("perpage");
			
				if(StringUtils.isNotEmpty(_page)){
					page  = Integer.parseInt(_page);
				}
				if(StringUtils.isNotEmpty(_prePage)){
					perPage  = Integer.parseInt(_prePage); 
				}
				PageInfo  page_result = jMessageService.queryMessage(currentUserId, page, perPage);

				
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("page", page);
				map.put("perpage", perPage);
				map.put("total", page_result.getTotal());
				map.put("totallines", page_result.getSize());
				map.put("list", page_result.getList());
				data = map;
				exception = CustomException.SUCCESS;
			}
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;		
	}
	
	/**
	 * 设置新信息为已读状态
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/message/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON  getUserNewMessage(@PathVariable("id")Long id,HttpServletRequest request, HttpServletResponse response){
		//返回json的结果对象
		ResultJSON result = new ResultJSON();
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		//返回
		Object data = null;
		
		try{
			if(currentUserId!=null && exception==null){	
				String _method = request.getParameter("_method");
				
				if(StringUtils.isNotEmpty(_method)&& RequestMethod.PATCH.name().equalsIgnoreCase(_method)){
					jMessageService.updateMessageReaded(id);
					exception = CustomException.SUCCESS;
				}else{
					exception = CustomException.PARAMSERROR;
				}
			}
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;		
	}
	
}
