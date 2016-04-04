package net.tfedu.zhl.cloud.resource.prepare.controller;

import javax.annotation.Resource;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;

public class PrepareControllerTest extends BaseControllerTestCase {

    @Resource
    PrepareController controller;

    /**
     * 返回json的结果对象
     */
    private ResultJSON result = new ResultJSON();

    @Test
    public void testAddPrepare() {
        request = this.newPost("/resRestAPI/v1.0/prepare");
        request.addParameter("tfcode", "RJCZ010109");
        request.addParameter("title", "title_RJCZ010109");

        result = controller.addPrepare(request, response);

        System.out.println(result.getData().toString());

    }

    @Test
    public void testEditPrepare() {
        request = this.newPost("/resRestAPI/v1.0/prepare");
        request.addParameter("id", "140252");
        request.addParameter("title", "140252_title_RJCZ010109");
        request.addParameter("_method", "PATCH");

        result = controller.addPrepare(request, response);

        System.out.println(result.getData());

    }

    @Test
    public void testDEletePrepare() {
        request = this.newPost("/resRestAPI/v1.0/prepare");
        request.addParameter("id", "140253");
        request.addParameter("_method", "DELETE");

        result = controller.addPrepare(request, response);

        System.out.println(result.getData());

    }

    @Test
    public void testGetPrepareList() {
        request = this.newGet("/resRestAPI/v1.0/prepare");

        request.addParameter("tfcode", "RJCZ010109");

        result = controller.getPrepare(request, response);

        System.out.println(result.getData());

    }

    @Test
    public void testGetPrepare4BookList() {
        request = this.newGet("/resRestAPI/v1.0/prepare4book");

        request.addParameter("tfcode", "RJCZ010109");

        result = controller.getPrepare4book(request, response);

        System.out.println(result.getData());

    }

    @Test
    public void testGetPrepareContent() {
        long prepareId = 140249;
        request = this.newGet("/resRestAPI/v1.0/prepareContent/" + prepareId);

        result = controller.querPrepareContent(prepareId, request, response);

        System.out.println(result.getData());

    }

    @Test
    public void testAddPrepareContent() {
        long prepareId = 140252;
        request = this.newPost("/resRestAPI/v1.0/prepareContent/" + prepareId);
        request.addParameter("resIds", "4319500105,212992,212991");
        request.addParameter("fromFlags", "0,0,0");
        result = controller.addPrepareContent(prepareId, request, response);

    }

    @Test
    public void testdelPrepareContent() {
        long id = 560626;
        request = this.newPost("/resRestAPI/v1.0/prepareContent");
        request.addParameter("ids", "560626");
        result = controller.delPrepareContent(request, response);

    }

    @Test
    public void testclearPrepareContent() {
        long id = 140252;
        request = this.newPost("/resRestAPI/v1.0/prepareContent");
        request.addParameter("id", "140252");
        request.addParameter("_method", "CLEAR");
        result = controller.delPrepareContent(request, response);
        System.out.println(result.getCode());
        System.out.println(result.getData());
    }

    public void testFillREsource() {

        String rescode = "newzy020101806612";

        request = this.newPost("/resRestAPI/v1.0/prepareContentFill");
        request.addParameter("rescode", "rescode");
        request.addParameter("_method", "RESOURCEFILL");
        result = controller.fillPrepareContent(request, response);
        System.out.println(result.getCode());
        System.out.println(result.getData());

    }

    @Test
    public void testgetViewUrl() {
        long prepareId = 140252;
        String url = "http://192.168.111.22:8099/down";
        request = this.newPost("/resRestAPI/v1.0/resViewUrl");
        request.addParameter("resIds", "4319500105,212992,212991");
        request.addParameter("fromFlags", "0,0,0");
        request.setAttribute("resServiceLocal", url);
        request.setAttribute("currentResService", url);
        result = controller.getResViewUrl(request, response);
        request.getServletContext().setInitParameter("hostLocal", "http://192.168.111.160/resources/");
        System.out.println(result.getCode());
        System.out.println(result.getData());

    }

}