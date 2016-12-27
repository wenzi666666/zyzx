package net.tfedu.zhl.helper.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.user.service.JUserService;

/**
 * Controller 单元测试基类
 * 
 * @author bruce
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml", "classpath*:/applicationContext-*.xml", "classpath:/spring-mvc.xml" })
public abstract class BaseControllerTestCase {

    protected static Logger log = LoggerFactory.getLogger(BaseControllerTestCase.class);
    
    /**
     * 注意，所有子类都不能新建request、response
     */
    protected MockHttpServletRequest request =new MockHttpServletRequest("GET", "/");
    protected MockHttpServletResponse response = new MockHttpServletResponse();

    protected long startTime;
    protected long endTime;

    @Autowired
    private JUserService userService;
    
    /**
     * 初始化
     */
    @Before
    public void onSetUp() {
        UserSimple us = userService.getUserSimpleById(390320126l," ",false);
        request.addHeader("Authorization", us.getToken());
        request.setAttribute("currentUserId", 390320126l); 
        
        
        
        
//        request.setAttribute("request_key_CustomException", CustomException.SUCCESS);
        startTime = System.currentTimeMillis();
    }

    /**
     * 结束
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        endTime = System.currentTimeMillis();
        log.info("执行时长：" + (endTime - startTime) + "ms");
    }

    /**
     * Convenience methods to make tests simpler
     *
     * @param url
     *            the URL to post to
     * @return a MockHttpServletRequest with a POST to the specified URL
     */
    public MockHttpServletRequest newPost(String url) {
        return new MockHttpServletRequest("POST", url);
    }

    /**
     * get请求
     * 
     * @param url
     * @return
     */
    public MockHttpServletRequest newGet(String url) {
        return new MockHttpServletRequest("GET", url);
    }


    /**
     * 断言并打印json结果
     * @param result
     */
	public void assertAndLog(ResultJSON result){
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
}
