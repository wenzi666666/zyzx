package net.tfedu.zhl.cloud.resource.sysRes.navigation;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resource.navigation.service.UserDefaultService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 用户历史记录service单元测试
 * @author WeiCuicui
 *
 */
public class UserDefaultServiceTest extends BaseServiceTestCase{

	@Resource UserDefaultService userDefaultService;
	
	/**
	 * 查询用户历史选择结点
	 * @throws IOException
	 */
	@Test
	public void testGetUserDefaultService() throws IOException{
		long userId = 699230735;
		int type = 1;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("type", type);
		
		JUserDefault userDefault = userDefaultService.getUserHistoryDefault(map);
		
		Assert.isTrue(userDefault != null);
       
        log.info(userDefault.toString());
	}
	
	/**
	 * 增加、修改用户历史选择
	 * @throws IOException
	 */
	@Test
	public void testUpdateUserDefaultService() throws IOException{
		String tfcode = "SHXX02010101";
		long userId = 8978979;
		int type = 1;
		String _method = "PATCH";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("type", type);
		map.put("tfcode", tfcode);
		
		//map.put("_method", _method); 修改
		
		userDefaultService.updateUserHistoryDefault(map);
	}
	
}