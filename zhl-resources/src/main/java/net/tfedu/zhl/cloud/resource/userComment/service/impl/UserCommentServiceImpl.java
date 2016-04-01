package net.tfedu.zhl.cloud.resource.userComment.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.asset.entity.FromFlagForReview;
import net.tfedu.zhl.cloud.resource.userComment.dao.UserCommentMapper;
import net.tfedu.zhl.cloud.resource.userComment.entity.UserComment;
import net.tfedu.zhl.cloud.resource.userComment.service.UserCommentService;

import org.springframework.stereotype.Service;

/**
 * 用户评论service
 * 
 * @author WeiCuicui
 *
 */
@Service("userCommentService")
public class UserCommentServiceImpl implements UserCommentService {

    @Resource
    UserCommentMapper userCommentMapper;

    // 增加一条用户评论/评分
    @Override
    public void insertUserComment(long resId, long userId, String displayContent, int ascore, int fromFlag,
            int isScore) {
        // 转换fromFlag同资源评论表中一致
        fromFlag = FromFlagForReview.getAtypeByFromFlag(fromFlag);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("resId", resId);
        map.put("userId", userId);
        map.put("displayContent", displayContent);
        map.put("ascore", ascore);
        map.put("fromFlag", fromFlag);
        map.put("isScore", isScore);

        userCommentMapper.insertMyComment(map);
    }

    // 修改用户评论
    @Override
    public void updateUserComment(String displayContent, long commentId) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("displayContent", displayContent);
        map.put("commentId", commentId);
        userCommentMapper.updateComment(map);
    }

    // 删除用户评论
    @Override
    public void deleteUserComment(long commentId) {
        userCommentMapper.deleteComment(commentId);
    }

    // 查询我的评论
    @Override
    public List<UserComment> getMyComments(int fromFlag, long resId, long userId) {

        // 转换fromFlag同资源评论表中一致
        fromFlag = FromFlagForReview.getAtypeByFromFlag(fromFlag);
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("fromFlag", fromFlag);
        map.put("resId", resId);
        map.put("userId", userId);

        return userCommentMapper.getMyComments(map);
    }

    // 查询他人评论
    @Override
    public List<UserComment> getOtherComments(int fromFlag, long resId, long userId) {

        // 转换fromFlag同资源评论表中一致
        fromFlag = FromFlagForReview.getAtypeByFromFlag(fromFlag);
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("fromFlag", fromFlag);
        map.put("resId", resId);
        map.put("userId", userId);

        return userCommentMapper.getOtherComments(map);
    }

}
