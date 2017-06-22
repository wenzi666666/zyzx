package net.tfedu.zhl.cloud.back.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.back.user.entity.SBackUserScope;
import net.tfedu.zhl.sso.users.util.CardExcelForm;
import net.tfedu.zhl.userlayer.user.entity.UserEditForm;
import net.tfedu.zhl.userlayer.user.entity.UserQueryForm;

/**
 
  
    @author wangwr
    @date 2017年1月13日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Transactional
public class AccountRegisterControllerTest extends BaseControllerTestCase {

	
	
	@Resource AccountRegisterController controller; 
	
	ResultJSON result = null ;
	
	
	@Test
	public void testAddSRegister()throws Exception {
		
		
		CardExcelForm form = new CardExcelForm();
		form.setCardNumber("1929219676");
		form.setCardPwd("998234");
		form.setUserName("mytest_9676");
		form.setUserPwd("000000");
		form.setSchoolId(10105l);
		form.setTrueName("mytest_9676");
		form.setNickName("mytest_9676");
		form.setSexName("男");
		form.setTermId(2);
		form.setSubjectId(2);
		
		result =  controller.addSRegister(form, new BeanPropertyBindingResult(form, "form"), request);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		
		
	}

	@Test
	public void testUpdateUser()throws Exception {
		UserEditForm form = new UserEditForm();
		
		form.setId(390320126l);
		form.setMale(false);
		form.setNickName("csls01");
		form.setTrueName("csls01_update");
		form.setTermId(3l);
		form.setSubjectId(3l);
		
		result =  controller.updateUser(form, new BeanPropertyBindingResult(form, "form"), request);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		
	}

	@Test
	public void testDelUser()throws Exception {
		//390380126  csls07
		

		result =  controller.delUser(390380126l);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		
		
	}

	@Test
	public void testPageUser()throws Exception {
		
		
		
		List<SBackUserScope> list = new ArrayList<SBackUserScope>();
		SBackUserScope scope = new SBackUserScope();
		scope.setCityid(0l);
		scope.setDistrictid(0l);
		scope.setScope("1");
		list.add(scope);
		
		
		request.setAttribute("scopeList", list);
		
		
		UserQueryForm form = new UserQueryForm();
		form.setSchoolId(10105l);
		form.setKeyword("cs");
		
		result =  controller.pageUser(form, 1, 100, request, response);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));

	}

	@Test
	public void testDelayUser()throws Exception {
		
		result =  controller.delayUser(new String[]{"390380126"}, 1, request);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testActiveUser()throws Exception {
		result =  controller.activeUser(new String[]{"390380126"}, request);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testExportUser()throws Exception {
	}

}
