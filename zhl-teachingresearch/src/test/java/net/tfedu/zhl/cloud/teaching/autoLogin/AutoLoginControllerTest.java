package net.tfedu.zhl.cloud.teaching.autoLogin;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

@Transactional
public class AutoLoginControllerTest extends BaseControllerTestCase {

	@Resource 
	AutoLoginController controller ;
	
	
	
	/**
	 * ?args=YCRYAmjxmlXvuNHTA1gC9hlyLRB%2FG0YHh5BzNJrUyBc%3D&sign=4b2d7d9602e7a3310b5828423423de04
	 * @throws Exception 
	 */
	@Test
	public void testLogin() throws Exception  {
		String _args = "YCRYAmjxmlXvuNHTA1gC9hlyLRB%2FG0YHh5BzNJrUyBc%3D";
		String sign = "4b2d7d9602e7a3310b5828423423de04";
		request.setParameter("args", _args);
		request.setParameter("sign", sign);
		String s = controller.login(request, response);
		System.out.println("result:"+s);
	}

}
