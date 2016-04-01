package net.tfedu.zhl.cloud.resource.userComment.service;

import java.util.List;

import net.tfedu.zhl.cloud.resource.userComment.entity.UserComment;

public interface UserCommentService {

	//增加一条用户评论
	public void insertUserComment(long resId,long userId,String displayContent,int score,int fromFlag,int isScore);
	
	//修改用户评论
	public void updateUserComment(String displayContent,long commentId);
	
	//删除用户评论
	public void deleteUserComment(long commentId);
	
	//查询我的评论
	public List<UserComment> getMyComments(int fromFlag,long resId,long userId);
	
	//查询他人评论
	public List<UserComment> getOtherComments(int fromFlag,long resId,long userId);

}
