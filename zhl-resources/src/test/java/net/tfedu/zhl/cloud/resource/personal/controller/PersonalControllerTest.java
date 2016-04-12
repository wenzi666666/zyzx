package net.tfedu.zhl.cloud.resource.personal.controller;

import static org.junit.Assert.*;

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

}
