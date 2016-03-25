package net.tfedu.zhl.helper.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	    "classpath:/applicationContext.xml",
	    "classpath*:/applicationContext-*.xml",
	    "classpath:/spring-mvc.xml"
})
public abstract class BaseControllerTestCase {
	
	protected static Logger log = LoggerFactory.getLogger(BaseControllerTestCase.class);
	protected MockHttpServletRequest request = new MockHttpServletRequest("GET", "/any");
	protected MockHttpServletResponse response =  new MockHttpServletResponse();
    
    protected long startTime,endTime;

    @Before
    public void onSetUp() {

        startTime=System.currentTimeMillis();
    }
    
	@After
	public void tearDown() throws Exception {
        endTime=System.currentTimeMillis();
        log.info("执行时长：" + (endTime - startTime) + "ms");
	}


    /**
     * Convenience methods to make tests simpler
     *
     * @param url the URL to post to
     * @return a MockHttpServletRequest with a POST to the specified URL
     */
    public MockHttpServletRequest newPost(String url) {
    	return new MockHttpServletRequest("POST", url);
    }

    public MockHttpServletRequest newGet(String url) {
    	return new MockHttpServletRequest("GET", url);
    }
    
}

