/**
 * 
 */
package net.tfedu.zhl.cloud.resource.user.service;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.user.entity.JUser;
import net.tfedu.zhl.cloud.resource.user.entity.UserSimple;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author wangwr
 *
 */
public class UserServiceTest extends BaseControllerTestCase {

    @Resource
    UserService userService;

    @Test
    public void test() {
        JUser user = userService.getUserById(1);

        Assert.notNull(user);

    }

    @Test
    public void test2() {
        UserSimple user = userService.getUserSimpleByName("admin");
        System.out.println(user.getMale());
        System.out.println(user.getTermName());
        System.out.println(user.getSubjectIds() + "----" + user.getSubjectNames());
        System.out.println(user.getUserImage());
        Assert.notNull(user);
    }

    @Test
    public void testUpdateUserInfo() {

        Long userId = 1l;
        String trueName = "管理员admin";
        boolean male = false;
        long termId = 1;
        long subjectId = 2;
        String userImage = "/person/head/Icon11.jpg";

        userService.updateUserInfo(userId, trueName, male, termId, subjectId);

    }

}
