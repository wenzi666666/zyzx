package net.tfedu.zhl.sso.service;

import javax.annotation.Resource;

import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

public class RegisterServiceTest extends BaseControllerTestCase {

    @Resource
    RegisterService registerService;

    @Test
    public void testGetRegister() {

        SRegister r = registerService.getRegister(1l);
        System.out.println(r.getName());

    }

    @Test
    public void testGetRegisterName() {
        SRegister r = registerService.getRegister("admin");
        System.out.println(r.getName());

    }

    @Test
    public void testModifyRegisterPassword() {
        long id = 1;
        String pwd = "123456";
        registerService.modifyRegisterPassword(id, pwd);

    }

    @Test
    public void testLogin() {
        String userName = "admin";
        String password = "000000";
        try {
            registerService.login(userName, password);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    @Test
    public void testAddRegister() throws Exception{

    	
    	RegisterAddForm form = new RegisterAddForm();
    	
    	form.setProvinceName("北京");
    	form.setCityName("北京市");
    	form.setArealName("延庆县");
    	form.setSchoolName("延庆县测试学校1");
    	form.setMotto("xxx");
    	form.setNickName("myTestUser2");
    	form.setRole(2);
    	form.setSex(false);
    	form.setSubjectName("数学");
    	form.setTermName("高中");
    	form.setTrueName("测试用户");
    	form.setUserName("myTestUser");
    	registerService.addRegister(form);
    }
    
    @Test
    public void testregisterOrUpdateUserWithThirdPartyApp()throws Exception{
    	
    	RegisterAddForm form = new RegisterAddForm();
    	
    	form.setProvinceName("山东");
    	form.setCityName("青岛市");
    	form.setArealName("莱西区");
    	form.setSchoolName("莱西区测试学校");
    	form.setMotto("xxx");
    	form.setNickName("xx测试用户");
    	form.setRole(2);
    	form.setSex(false);
    	form.setSubjectName("数学");
    	form.setTermName("高中");
    	form.setTrueName("测试用户_edit");
    	form.setUserName("myuser");
    	form.setTh_uuid("xxxxxxuuid"+1);
    	
    	SApp app = new SApp();
    	
    	app.setAppid(963683);
    	app.setAppkey("45a672470c69");
    	app.setBasedataurl("");
    	app.setDeveloperid(3);
    	app.setIsloginautosynbasedata(1);
    	app.setPrefix("zjqxres");
    	app.setUserdefaultpwd("000000");
    	app.setUsefullife(36);
    	
    	registerService.registerOrUpdateUserWithThirdPartyApp(form, app);
    	
    	
    }
    
    
}
