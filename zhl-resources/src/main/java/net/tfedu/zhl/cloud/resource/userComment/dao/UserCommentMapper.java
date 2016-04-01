package net.tfedu.zhl.cloud.resource.userComment.dao;

import java.util.List;

import net.tfedu.zhl.cloud.resource.userComment.entity.UserComment;
import net.tfedu.zhl.helper.CoreMapper;

import org.apache.ibatis.annotations.Param;

public interface UserCommentMapper extends CoreMapper<UserComment> {
	
	//插入一条记录
	public void insertMyComment(@Param("resId")long resId,@Param("userId")long userId,@Param("displayContent")String displayContent,@Param("fromFlag")int fromFlag);
	
	//修改我的评论
	public void updateComment(@Param("displayContent")String displayContent,@Param("commentId") long commentId);
		
	
	//删除我的评论
	public void deleteComment(@Param("commentId") long commentId);
		
	
	//查询我的评论
	public List<UserComment> getMyComments(@Param("fromFlag")int fromFlag,@Param("resId")long resId,@Param("userId") long userId);
	
	//查询我的评论
	public List<UserComment> getOtherComments(@Param("fromFlag")int fromFlag,@Param("resId")long resId,@Param("userId") long userId);
	
}