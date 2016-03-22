package net.tfedu.zhl.cloud.resources.navigation.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tfedu.zhl.cloud.resources.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resources.navigation.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 根据版本，获得所有教材
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class BooksController {

	@Resource BookService bookService;
	
	/**
	 * 根据版本，获得所有教材
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/books")
	@ResponseBody
	public List<JSyscourse> getAllBooksByEdition(HttpServletRequest request,HttpServletResponse response) throws IOException{
		long pnodeId = request.getParameter("pnodeId") != null ? Long.parseLong(request.getParameter("pnodeId").toString().trim()) : 0;
		return bookService.getAllBooks(pnodeId);
	}
	
}
