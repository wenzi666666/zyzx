package net.tfedu.zhl.cloud.resource.bookself.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.asset.entity.ZAssetView;
import net.tfedu.zhl.cloud.resource.bookself.bean.BookSelfImgPathConvert;
import net.tfedu.zhl.cloud.resource.bookself.bean.BookSelfView;
import net.tfedu.zhl.cloud.resource.bookself.service.BookSelfService;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentViewUtil;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 客户端（e备课）书架接口
 * @author wangwr
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class BookSelfController {
	
	
	@Resource
	BookSelfService bookSelfService;
	
	
	
	/**
	 * 获取我的全部书架
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/mybook",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON getMyBook(HttpServletRequest request, HttpServletResponse response){
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
				
				long userId = currentUserId;
				String title = request.getParameter("title");
				data =  bookSelfService.getAllMyBook(userId,title);
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
	 * 增加我的书架
	 * 删除我的书架
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/mybook",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON managerMyBook(HttpServletRequest request, HttpServletResponse response){
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
				long userId = currentUserId;
				String tfcode = request.getParameter("tfcode");
				String _method = request.getParameter("_method");
				
				if(StringUtils.isNotEmpty(_method)&&RequestMethod.DELETE.name().equalsIgnoreCase(_method)){
					//delete 
					bookSelfService.delMyBook(userId, tfcode);
				}else{
					//add
					bookSelfService.addMyBook(userId, tfcode);
				}
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
	 * 获取版本下的教材（判断是否在书架上）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/sysbook",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON getSysBook(HttpServletRequest request, HttpServletResponse response){
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
				String resServiceLocal = (String)request.getAttribute("resServiceLocal");
				String currentResPath = (String)request.getAttribute("currentResPath");

				
				long userId = currentUserId;
				String tfcode = request.getParameter("tfcode");
				List<BookSelfView> list = bookSelfService.queryBook(userId, tfcode);
				BookSelfImgPathConvert.convert(list, resServiceLocal, currentResPath);
				data = list ;
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
	 * 获取教程下的课件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/courseware",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON getUserCourseware(HttpServletRequest request, HttpServletResponse response){
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
				String resServiceLocal = (String)request.getAttribute("resServiceLocal");
				String currentResPath = (String)request.getAttribute("currentResPath");

				
				long userId = currentUserId;
				String tfcode = request.getParameter("tfcode");
				String title = request.getParameter("title");
				String orderby = request.getParameter("orderby");

				List<ZAssetView> list = bookSelfService.queryUserCourseware(userId, tfcode, title, orderby);
				JPrepareContentViewUtil.convertToPurpose_Asset(list, resServiceLocal, currentResPath);
				data = list ;
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
	 * 重命名课件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/courseware/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON getSysBook(@PathVariable Long id,HttpServletRequest request, HttpServletResponse response){
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
				String resServiceLocal = (String)request.getAttribute("resServiceLocal");
				String currentResPath = (String)request.getAttribute("currentResPath");
				String name = request.getParameter("name");
				if(StringUtils.isNotEmpty(name)){
					bookSelfService.renameAsset(id, name);
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

