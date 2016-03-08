package net.tfedu.zhl.sso;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.core.tests.BaseServiceTestCase;
import net.tfedu.zhl.sso.entity.User;
import net.tfedu.zhl.sso.service.UserService;


public class UserServiceTest extends BaseServiceTestCase {


	@Resource
	private UserService userService = null;
	
	@Test
	public void test1() {
		User user = userService.getUserById(1);
		// System.out.println(user.getUserName());
		// logger.info("值："+user.getUserName());
		Assert.notNull(user);
		log.info(JSON.toJSONString(user));
	}
	
	@Test
	public void testAdd() {
		User user = new User();
		user.setEmail("kk@qq.com");
		user.setUsername("1100");
		user.setPassword("ssss");
		int i = userService.insert(user);
		Assert.isTrue(i>0);
	}
	
	@Test
	public void testPage() {
		
		//获取第1页，10条内容，默认查询总数count
		PageHelper.startPage(1, 10);
		PageHelper.orderBy("userid desc");
		List<User> list = userService.selectAll();
		//用PageInfo对结果进行包装
		PageInfo<User> page = new PageInfo<User>(list);
		
		//测试PageInfo全部属性
		//PageInfo包含了非常全面的分页属性
		assertEquals(1, page.getPageNum());
		assertEquals(10, page.getPageSize());
		assertEquals(1, page.getStartRow());
		assertEquals(10, page.getEndRow());
		
		assertEquals(true, page.isIsFirstPage());
		assertEquals(false, page.isIsLastPage());
		assertEquals(false, page.isHasPreviousPage());
		assertEquals(true, page.isHasNextPage());
		
	}
	
}
