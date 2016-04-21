package net.tfedu.zhl.cloud.resource.userComment.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.userComment.entity.UserComment;
import net.tfedu.zhl.cloud.resource.userComment.service.UserCommentService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户评论controller
 * 
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class UserCommentController {

    @Resource
    UserCommentService userCommentService;

    /**
     * 添加、修改、删除用户评论
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/v1.0/myComments", method = RequestMethod.POST)
    @ResponseBody
    public ResultJSON updateUserComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	// 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        
    	// 方法
        String _method = request.getParameter("_method");

        // 修改用户评论
        if (StringUtils.isNotEmpty(_method) && RequestMethod.PATCH.name().equals(_method)) {// 修改用户评论

            long commentId = 0;
            String displayContent = "";
            if(StringUtils.isNotEmpty(request.getParameter("commentId"))){
            	commentId = Long.parseLong(request.getParameter("commentId").toString().trim());
            } else {
				throw new ParamsException();
			}
            
            if(StringUtils.isNotEmpty(request.getParameter("displayContent"))){
            	displayContent = request.getParameter("displayContent").toString().trim();
            }  else {
				throw new ParamsException();
			}
            
            userCommentService.updateUserComment(displayContent, commentId);

        } else if (StringUtils.isNotEmpty(_method) && RequestMethod.DELETE.name().equals(_method)) {// 删除用户评论

        	 long commentId = 0;
        	 if(StringUtils.isNotEmpty(request.getParameter("commentId"))){
             	commentId = Long.parseLong(request.getParameter("commentId").toString().trim());
             } else {
				throw new ParamsException();
			 }
          
            userCommentService.deleteUserComment(commentId);

        } else { // 新建用户评论

            long userId = currentUserId;
            long resId = 0;
            String displayContent = "";
            int fromFlag = 0;
            int ascore = 0; //默认评分为0
            int isScore = 0; //0：评分，1：评论
            if(StringUtils.isNotEmpty(request.getParameter("resId"))){
            	resId = Long.parseLong(request.getParameter("resId").toString().trim());
            } else {
				throw new ParamsException();
			}
            
            if(StringUtils.isNotEmpty(request.getParameter("displayContent"))){
            	displayContent = request.getParameter("displayContent").toString().trim();
            } else {
				throw new ParamsException();
			}
            
            if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
            	fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
            } else {
				throw new ParamsException();
			}
            
            if(StringUtils.isNotEmpty(request.getParameter("ascore"))){
            	ascore = Integer.parseInt(request.getParameter("ascore").toString().trim());
            } else {
				throw new ParamsException();
			}
            
            if(StringUtils.isNotEmpty(request.getParameter("isScore"))){
            	isScore = Integer.parseInt(request.getParameter("isScore").toString().trim());
            } else {
				throw new ParamsException();
			}

            userCommentService.insertUserComment(resId, userId, displayContent, ascore, fromFlag, isScore);
        }
        
        return ResultJSON.getSuccess("");
    }

    /**
     * 查询我的评论
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/v1.0/myComments", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getMycomments(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        List<UserComment> myComments = null;

        long userId = currentUserId;

        long resId = 0;
      
        int fromFlag = 0;
        
        if(StringUtils.isNotEmpty(request.getParameter("resId"))){
        	resId = Long.parseLong(request.getParameter("resId").toString().trim());
        } else {
			throw new ParamsException();
		}
       
        if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
        	fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
        }  else {
			throw new ParamsException();
		}
        
        myComments = userCommentService.getMyComments(fromFlag, resId, userId);
        
        return ResultJSON.getSuccess(myComments);
    }

    /**
     * 查询其他人的评论
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/v1.0/otherComments", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getOthercomments(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        List<UserComment> otherComments = null;

        long userId = currentUserId;

        long resId = 0;
      
        int fromFlag = 0;
        
        if(StringUtils.isNotEmpty(request.getParameter("resId"))){
        	resId = Long.parseLong(request.getParameter("resId").toString().trim());
        } else {
        	throw new ParamsException();
        }
       
        if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
        	fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
        } else {
			throw new ParamsException();
		}

        otherComments = userCommentService.getOtherComments(fromFlag, resId, userId);
        
        return ResultJSON.getSuccess(otherComments);
    }
}
