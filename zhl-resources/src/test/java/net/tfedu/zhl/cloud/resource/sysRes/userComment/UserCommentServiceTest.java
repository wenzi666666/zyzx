package net.tfedu.zhl.cloud.resource.sysRes.userComment;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.userComment.entity.UserComment;
import net.tfedu.zhl.cloud.resource.userComment.service.UserCommentService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 用户评论的service测试
 * 
 * @author WeiCuicui
 *
 */
public class UserCommentServiceTest extends BaseServiceTestCase {

    @Resource
    UserCommentService userCommentService;

    /**
     * 用户评论测试
     * 
     * @throws IOException
     */
    @Test
    public void testUserComment() throws IOException {

        long commentId = 10600;

        String displayContent = "goodgoodgood";

        // 资源来源
        int fromFlag = 0;

        long resId = 31250;

        long userId = 699230735;

        int ascore = 5;
        int isScore = 0;

        System.out.println(fromFlag);

        // 查询我的评论
        List<UserComment> comments = userCommentService.getMyComments(fromFlag, resId, userId);
        Assert.isTrue(comments.size() > 0);
        for (int i = 0; i < comments.size(); i++)
            log.info(comments.get(i).toString());

        System.out.println("他人评论");

        /*
         * //查询其他人的评论 List<UserComment> otherComments =
         * userCommentService.getOtherComments(fromFlag, resId, userId);
         * Assert.isTrue(otherComments.size() > 0); for(int i = 0;i <
         * otherComments.size();i++) log.info(otherComments.get(i).toString());
         */
        /*
         * //增加 userCommentService.insertUserComment(resId, userId,
         * displayContent, ascore,fromFlag,isScore);
         * 
         * //修改 userCommentService.updateUserComment(displayContent, commentId);
         * 
         * //删除 userCommentService.deleteUserComment(commentId);
         */

    }
}
