package net.tfedu.zhl.cloud.resources.navigation.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tfedu.zhl.cloud.resources.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resources.navigation.service.BookService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 * 返回json的结果对象
	 */
	private ResultJSON result = new ResultJSON();
	
	/**
	 * 异常
	 */
	private CustomException exception;
	
	/**
	 * 根据版本，获得所有教材
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/books",method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON getAllBooksByEdition(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<JSyscourse> books = null;
		try {
			
			long pnodeId = Long.parseLong(request.getParameter("pnodeId").toString().trim());
			books = bookService.getAllBooks(pnodeId);
			
			exception = CustomException.SUCCESS;
			
		} catch (Exception e) {
			// TODO: handle exception
			
			//记录异常信息
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
