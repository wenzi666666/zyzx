package net.tfedu.zhl.cloud.resource.prepare.controller;

import javax.annotation.Resource;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


@Transactional
public class PrepareControllerTest extends BaseControllerTestCase {

    @Resource
    PrepareController controller;

    /**
     * 返回json的结果对象
     */
    private ResultJSON result = new ResultJSON();

    @Test
    public void testAddPrepare() {
        request.addParameter("tfcode", "RJCZ010109");
        request.addParameter("title", "title_RJCZ010109");

        result = controller.addPrepare(request, response);

        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

    }

    @Test
    public void testEditPrepare() {
        request.addParameter("id", "525");
        request.addParameter("title", "140252_title_RJCZ010109");
        request.addParameter("_method", "PATCH");

        result = controller.addPrepare(request, response);

        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

    }
    
    
    @Test
    @Rollback
    public void testAddPrepareContent() throws Exception {
        long prepareId = 525;
        request.addParameter("resIds", "4319500105,212992,212991");
        request.addParameter("fromFlags", "0,0,0");
        result = controller.addPrepareContent(prepareId, request, response);

    }
  
    
    @Test
    public void testGetlatestPrepare(){
    	
    	request.setAttribute("currentUserId", 390400126l);
    	result = controller.getLatestPrepare(request, response);
    	System.out.println(result.toString());
    	
    }
    
    
    
    @Test
    public void testGetPrepare4BookList() throws Exception {

    	request.setAttribute("currentUserId", 390400126l);
    	request.setParameter("termId", "3");
    	request.setParameter("subjectId", "2");
        result = controller.getPrepare4book(request, response);

        System.out.println(result.toString());
    }
    
    

    @Test
    public void testDEletePrepare() {
        request.addParameter("id", "140253");
        request.addParameter("_method", "DELETE");

        result = controller.addPrepare(request, response);

        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

    }

  

    @Test
    @Rollback
    public void testdelPrepareContent() throws Exception {
        request.addParameter("ids", "1365");
        result = controller.delPrepareContent(request, response);
        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

    }

    @Test
    public void testclearPrepareContent() throws Exception {
        request.addParameter("id", "140252");
        request.addParameter("_method", "CLEAR");
        result = controller.delPrepareContent(request, response);
        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

    }

    public void testFillREsource() throws Exception {

        String rescode = "newzy020101806612";
        request.setServerName("192.168.111.160");
        request.setServerPort(8080);
        request.setContextPath("/zhl-resources");
        request.setScheme("http");

        request.addParameter("rescode", rescode);
        request.addParameter("_method", "RESOURCEFILL");
        result = controller.fillPrepareContent(request, response);
        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));


    }

  
    
 
    @Test
    public void testgetViewUrl() throws Exception {
//        request.addParameter("resIds", "50892,212992,212991");
//        request.addParameter("fromFlags", "0,0,0");
        request.addParameter("resIds", "870142");
        request.addParameter("fromFlags", "4");
        request.setServerName("192.168.111.160");
        request.setServerPort(8080);
        request.setContextPath("/zhl-resources");
        request.setScheme("http");
        
        result = controller.getResViewUrl(request, response);
        
        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

    }

    @Test
    public void testGetPrepareList() throws Exception {

        request.addParameter("tfcode", "RJCZ010109");
    	request.setAttribute("currentUserId", 390400126l);

        result = controller.getPrepare(request, response);

        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

    }
    
    
    @Test
    public void testGetPreparePage() throws Exception {

    	request.setAttribute("currentUserId", 390400126l);
        request.addParameter("tfcode", "RJCZ010109");
        
        result = controller.getPreparePage(request, response);

        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

    }
    

    
    
    
    
    
    @Test
    public void testGetPrepareContent() throws Exception {
        long prepareId = 140249;
        result = controller.queryPrepareContent(prepareId, request, response);

        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

    }

    
    
    
    
    @Test
    public void testGetPrepareContentPage() throws Exception {
        long prepareId = 140249;
        result = controller.queryPrepareContentPage(prepareId, 2, 1, request);

        Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

    }

    
    
}
