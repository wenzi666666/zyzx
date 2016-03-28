package net.tfedu.zhl.helper.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {
	    "classpath:/applicationContext.xml",
	    "classpath*:/applicationContext-*.xml"
	})
public abstract class BaseServiceTestCase  extends AbstractJUnit4SpringContextTests {

	protected static Logger log = LoggerFactory.getLogger(BaseControllerTestCase.class);
    
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
}
