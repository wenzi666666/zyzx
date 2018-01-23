package net.tfedu.zhl.sso.skip;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.Assert;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 @author wangwr
 @date 2016年9月23日
 @desc 
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public class CartoonControllerTest extends BaseControllerTestCase {

	@Resource
	CartoonController controller ; 
	
	@Test
	public void testSkip() throws Exception{
		
		//一年级
		long grade = 1 ;
		
		
		ResultJSON json = controller.skip(request, response, grade);
		
		Assert.isTrue("OK".equalsIgnoreCase(json.getCode()));
	
	
	}

}
