package net.tfedu.zhl.cloud.resource.userComment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.userComment.dao.UserCommentMapper;
import net.tfedu.zhl.cloud.resource.userComment.entity.UserComment;
import net.tfedu.zhl.cloud.resource.userComment.service.UserCommentService;

import org.springframework.stereotype.Service;
/**
 * 用户评论service
 * @author WeiCuicui
 *
 */
@Service("userCommentService")
public class UserCommentServiceImpl implements UserCommentService{

	@Resource UserCommentMapper userCommentMapper;
	
	//增加一条用户评论
	@Override
	public void insertUserComment(long resId,long userId,String displayContent,int fromFlag){
		userCommentMapper.insertMyComment(resId, userId, displayContent, fromFlag);
	}
	
	//修改用户评论
	@Override
	public void updateUserComment(String displayContent,long commentId){
		userCommentMapper.updateComment(displayContent,commentId);
	}
	
	//删除用户评论
	@Override
	public void deleteUserComment(long commentId){
		userCommentMapper.deleteComment(commentId);
	}
	
	//查询我的评论
	@Override
	public List<UserComment> getMyComments(int fromFlag,long userId,long resId){
		return userCommentMapper.getMyComments(fromFlag, resId, userId);
	}
	
	//查询他人评论
	@Override
	public List<UserComment> getOtherComments(int fromFlag,long userId,long resId){
		return userCommentMapper.getOtherComments(fromFlag, resId, userId);
	}
}
