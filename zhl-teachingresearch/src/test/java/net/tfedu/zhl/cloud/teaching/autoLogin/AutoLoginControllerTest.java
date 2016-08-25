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
	 * 
	 * args=
	 * 
	 * RS6XaCLv1TOS8tlcmM4ecQipNX%2FylE%2BuPeCqSh%2BM0EA%3D&sign=d2bf35fd94e830906ffdaa7f1e3f756f&platform=cloud&tfcode=
	 * 
	 * RS6XaCLv1TOS8tlcmM4ecQipNX%2FylE%2BuPeCqSh%2BM0EA%3D
	 * http://101.201.197.110:8080/teachingAutoLogin/v1.0/login?args=TqwcMLXZrjkJCaUv7OssvvT9VfYkyWCYVU975a7IGUuZy7Let5DbbHasKxfak6MjgwGwf%2FUFpm6WcZ6LVdgeGWt3neBGVuus
	 * &sign=d2bf35fd94e830906ffdaa7f1e3f756f
	 * &platform=cloud&tfcode=
	 */
	@Test
	public void testLogin() throws Exception  {
		String _args = "RS6XaCLv1TOS8tlcmM4ecQipNX%2FylE%2BuPeCqSh%2BM0EA%3D";
		String sign = "d2bf35fd94e830906ffdaa7f1e3f756f";
		request.setParameter("args", _args);
		request.setParameter("sign", sign);
		String s = controller.login(request, response);
		System.out.println("result:"+s);
	}

}
