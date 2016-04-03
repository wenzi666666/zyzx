package net.tfedu.zhl.cloud.resource.navigation.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resource.navigation.service.BookService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 根据版本，获得所有教材
 * 
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class BooksController {

    @Resource
    BookService bookService;

    /**
     * 根据版本，获得所有教材
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1.0/books", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getAllBooksByEdition(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // 定义这里的产品编码是资源中心
        String proCode = "zy";

        /**
         * 返回json的结果对象
         */
        ResultJSON result = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        List<JSyscourse> books = null;
        try {

            if (currentUserId != null && exception == null) {
                long pnodeId = Long.parseLong(request.getParameter("pnodeId").toString().trim());

                // 根据所属版本和产品编码，查询所有的教材
                books = bookService.getAllBooks(pnodeId, proCode);

                exception = CustomException.SUCCESS;
            }

        } catch (Exception e) {
            // TODO: handle exception

            // 记录异常信息
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();
        } finally {
            result.setCode(exception.getCode());
            result.setData(books);
            result.setMessage(exception.getMessage());
            result.setSign("");
        }

        return result;
    }

}
