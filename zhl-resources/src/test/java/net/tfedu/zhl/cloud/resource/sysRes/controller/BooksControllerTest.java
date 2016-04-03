package net.tfedu.zhl.cloud.resource.sysRes.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.controller.BooksController;
import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

public class BooksControllerTest extends BaseControllerTestCase {

    @Resource
    BooksController booksController;

    @Test
    public void testBooksController() throws IOException {
        request = newGet("/resRestAPI/v1.0/books");
        request.setParameter("pnodeId", "101140105");

        ResultJSON resultJSON = booksController.getAllBooksByEdition(request, response);
        Assert.isTrue(resultJSON != null);
        List<JSyscourse> books = (List<JSyscourse>) resultJSON.getData();
        Assert.isTrue(books.size() > 0);
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getId() + ":" + books.get(i).getName() + ":" + books.get(i).getTfcode());
        }

    }
}
