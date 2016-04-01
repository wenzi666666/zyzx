package net.tfedu.zhl.cloud.resource.userComment.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.userComment.entity.UserComment;
import net.tfedu.zhl.cloud.resource.userComment.service.UserCommentService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户评论controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class UserCommentController {

	@Resource UserCommentService userCommentService;
	
	/**
	 * 添加、修改、删除用户评论
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/myComments",method = RequestMethod.POST)
	@ResponseBody
	public ResultJSON updateUserComment(HttpServletRequest request,HttpServletResponse response)throws IOException{
		/**
		 * 返回json的结果对象
		 */
		ResultJSON resultJSON = new ResultJSON();
		
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		try {
			//当前用户已经登录系统
    		if(exception == null && currentUserId != null){
    			
    			//方法
    			String _method = request.getParameter("_method");
    			
    			// 修改用户评论
    			if (StringUtils.isNotEmpty(_method)&& RequestMethod.PATCH.name().equals(_method)) {// 修改用户评论
    				
    				
    				long commentId = Long.parseLong(request.getParameter("commentId"));
        			String displayContent = request.getParameter("displayContent");
        			
        			userCommentService.updateUserComment(displayContent, commentId);
    				
    			} else if (StringUtils.isNotEmpty(_method)&& RequestMethod.DELETE.name().equals(_method)) {// 删除用户评论
    					
    				
    				long commentId = Long.parseLong(request.getParameter("commentId"));
    				
    				userCommentService.deleteUserComment(commentId);
    				
    			} else { //新建用户评论
    				
    				long userId = currentUserId;
        			long resId = Long.parseLong(request.getParameter("resId"));
        			String displayContent = request.getParameter("displayContent");
        			int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));
        			int ascore = Integer.parseInt(request.getParameter("ascore"));
        			int isScore = Integer.parseInt(request.getParameter("isScore"));
        			
        			userCommentService.insertUserComment(resId, userId, displayContent, ascore,fromFlag,isScore);
				}
    			
    			exception = CustomException.SUCCESS;
    		}
		} catch (Exception e) {
			// TODO: handle exception
			//捕获异常信息
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
		} finally {
			resultJSON.setCode(exception.getCode());
			resultJSON.setData("");
			resultJSON.setMessage(exception.getMessage());
			resultJSON.setSign("");
		}
		
		return resultJSON;
	}
	
	/**
	 * 查询我的评论
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/myComments",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getMycomments(HttpServletRequest request,HttpServletResponse response)throws IOException{
		/**
		 * 返回json的结果对象
		 */
		ResultJSON resultJSON = new ResultJSON();
		
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		
		List<UserComment> myComments = null;
		
		try {
			//当前用户已经登录系统
    		if(exception == null && currentUserId != null){
    			long userId = currentUserId;
    			long resId = Long.parseLong(request.getParameter("resId"));
    			int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));
    			
    			myComments = userCommentService.getMyComments(fromFlag,resId,userId );
    			
    			exception = CustomException.SUCCESS;
    		}
		} catch (Exception e) {
			// TODO: handle exception
			//捕获异常信息
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
		} finally {
			resultJSON.setCode(exception.getCode());
			resultJSON.setData(myComments);
			resultJSON.setMessage(exception.getMessage());
			resultJSON.setSign("");
		}
		
		return resultJSON;
	}
	
	/**
	 * 查询其他人的评论
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/otherComments",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getOthercomments(HttpServletRequest request,HttpServletResponse response)throws IOException{
		/**
		 * 返回json的结果对象
		 */
		ResultJSON resultJSON = new ResultJSON();
		
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		
		List<UserComment> otherComments = null;
		
		try {
			//当前用户已经登录系统
    		if(exception == null && currentUserId != null){
    			long userId = currentUserId;
    			long resId = Long.parseLong(request.getParameter("resId"));
    			int fromFlag = Integer.parseInt(request.getParameter("fromFlag"));
    			
    			otherComments = userCommentService.getOtherComments(fromFlag,resId,userId);
    			
    			exception = CustomException.SUCCESS;
    		}
		} catch (Exception e) {
			// TODO: handle exception
			//捕获异常信息
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			e.printStackTrace();
		} finally {
			resultJSON.setCode(exception.getCode());
			resultJSON.setData(otherComments);
			resultJSON.setMessage(exception.getMessage());
			resultJSON.setSign("");
		}
		
		return resultJSON;
	}
	
}
