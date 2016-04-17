package net.tfedu.zhl.cloud.resource.personal.controller;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonalControllerTest extends BaseControllerTestCase {

	
	
	@Autowired
	PersonalController controller;
	
	
    
    @Test
    public void testGetResTypeForPersonal(){
//    	request.setParameter("", "");
    	
    	request.setAttribute("currentUserId", 390320126l);
    	request.setAttribute("tabCode", "myPrepareRes");
    	
    	
    	ResultJSON json =  controller.getResTypeForPersonal(request, response);
    	System.out.println(json.toString());
    	
    	
    }
    @Test
    public void testGetResTypeForPersonal2(){
//    	request.setParameter("", "");
    	
    	request.setAttribute("currentUserId", 390320126l);
    	request.setAttribute("tabCode", "myUpload");
    	
    	
    	ResultJSON json =  controller.getResTypeForPersonal(request, response);
    	System.out.println(json.toString());
    	
    	
    }
    @Test
    public void testGetResTypeForPersonal3(){
//    	request.setParameter("", "");
    	
    	request.setAttribute("currentUserId", 390320126l);
    	request.setAttribute("tabCode", "myDownload");
    	
    	
    	ResultJSON json =  controller.getResTypeForPersonal(request, response);
    	System.out.println(json.toString());
    	
    	
    }
    @Test
    public void testGetResTypeForPersonal4(){
//    	request.setParameter("", "");
    	
    	request.setAttribute("currentUserId", 390320126l);
    	request.setAttribute("tabCode", "myView");
    	
    	
    	ResultJSON json =  controller.getResTypeForPersonal(request, response);
    	System.out.println(json.toString());
    	
    	
    }

    
    
    
    @Test
    public void testGetAutolearning(){
    	request.setAttribute("currentUserId", 1l);
    	ResultJSON json = this.controller.autoLearning(request, response);
    	System.out.println(json.toString());
    	
    }
    
    
    @Test
    public void testgetUnReview(){
        request.setServerName("192.168.111.160");
        request.setServerPort(8080);
        request.setContextPath("/zhl-resources");
        request.setScheme("http");
        request.setAttribute("currentUserId", 390320126l);
        
        ResultJSON result = controller.getUnReview(request, response);
    	
        System.out.println(result.toString());
        
    	
    }
    
    
    @Test
    public void testcustomizeRes(){
    	
        ResultJSON result = controller.getCustomizeRes(request, response);
        System.out.println(result.toString());
    	
    }
    
    
    
}
