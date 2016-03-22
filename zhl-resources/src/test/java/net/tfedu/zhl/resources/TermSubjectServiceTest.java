package net.tfedu.zhl.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.util.Assert;
import net.tfedu.zhl.cloud.core.subject.entity.JSubject;
import net.tfedu.zhl.cloud.resources.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resources.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resources.navigation.service.BookService;
import net.tfedu.zhl.cloud.resources.navigation.service.EditionService;
import net.tfedu.zhl.cloud.resources.navigation.service.TermSubjectService;
import net.tfedu.zhl.cloud.resources.navigation.service.UserDefaultService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

/**
 * 测试 学段下的学科 service
 * @author WeiCuicui
 *
 */
public class TermSubjectServiceTest extends BaseServiceTestCase{

	@Resource 
	private UserDefaultService userDefaultService = null;
	
	@Test
	public void testGetAllSubjectsByTerm() throws IOException{
		
		long userId = 699230735;
		int type = 1;
		String tfcode = "RJCZ010109";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("type", type);
		map.put("tfcode", tfcode);
		
		
		
		userDefaultService.addUserHistoryDefault(map);
	
		
		
	}
	
}
