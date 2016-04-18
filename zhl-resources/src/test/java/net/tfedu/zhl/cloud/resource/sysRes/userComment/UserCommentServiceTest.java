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
 * 用户评论相关service测试
 * 
 * @author WeiCuicui
 *
 */
public class UserCommentServiceTest extends BaseServiceTestCase {

    @Resource
    UserCommentService userCommentService;
    
    /**
     * 增加用户评论
     * @throws IOException
     */
    @Test
    public void testAddUserComments()throws IOException{

        String displayContent = "goodgoodgood";
        
        // 资源来源
        int fromFlag = 0;

        long resId = 31250;

        long userId = 699230735;
        
        int ascore = 5;
        int isScore = 0;
        
        userCommentService.insertUserComment(resId, userId,displayContent, ascore,fromFlag,isScore);

	}
    
    /**
     * 修改用户评论
     * @throws IOException
     */
    @Test
    public void testUpdateUserComments()throws IOException{

        String displayContent = "goodgoodgood";
        
        long commentId = 10600;
        
        userCommentService.updateUserComment(displayContent, commentId);

	}
    
   

    /**
     * 查询我的评论
     * 
     * @throws IOException
     */
    @Test
    public void testGetMyComments() throws IOException {

        // 资源来源
        int fromFlag = 0;

        long resId = 31250;

        long userId = 699230735;

        // 查询我的评论
        List<UserComment> comments = userCommentService.getMyComments(fromFlag, resId, userId);
        Assert.isTrue(comments.size() > 0);
        for (int i = 0; i < comments.size(); i++)
            log.info(comments.get(i).toString());
    }
    

    /**
     * 查询其他人的评论
     * 
     * @throws IOException
     */
    @Test
    public void testGetOtherComments() throws IOException {
    	// 资源来源
        int fromFlag = 0;

        long resId = 31250;

        long userId = 699230735;
        
        // 查询他人的评论
        List<UserComment> comments = userCommentService.getOtherComments(fromFlag, resId, userId);
        Assert.isTrue(comments.size() > 0);
        for (int i = 0; i < comments.size(); i++)
            log.info(comments.get(i).toString());
        
    }
    
    /**
     * 删除用户评论
     * @throws IOException
     */
    @Test
    public void testDeleteUserComments()throws IOException{

        long commentId = 10600;
        
        userCommentService.deleteUserComment(commentId);

	}
}
