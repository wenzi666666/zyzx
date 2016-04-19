package net.tfedu.zhl.cloud.resource.navigation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.config.ResourceWebConfig;
import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resource.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;
import net.tfedu.zhl.cloud.resource.navigation.service.BookService;
import net.tfedu.zhl.cloud.resource.navigation.service.EditionService;
import net.tfedu.zhl.cloud.resource.navigation.service.TermService;
import net.tfedu.zhl.cloud.resource.navigation.service.TermSubjectService;
import net.tfedu.zhl.cloud.resource.navigation.service.TreeService;
import net.tfedu.zhl.cloud.resource.navigation.service.UserDefaultService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.entity.JTerm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 系统、区本、校本
 * 资源导航页面的controller
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class NavigationController {
	
	 @Resource
	 BookService bookService;
	 
	 @Resource
	 EditionService editionService;
	 
	 @Resource
	 TermService termService;
	 
	 @Resource
	 TermSubjectService termSubjectService;
	 
	 @Resource
	 TreeService treeService;
	 
	 @Resource
	 UserDefaultService userDefaultService;
	 
	 @Resource
	 private ResourceWebConfig resourceWebConfig;
	 
	/**
	 *  查询所有学段
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/terms", method = RequestMethod.GET)
	@ResponseBody
	public ResultJSON selectAllTerms(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON result = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        List<JTerm> terms = null;
        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {
                terms = termService.selectAll();
                exception = CustomException.SUCCESS;
            } else {
            	exception = CustomException.INVALIDACCESSTOKEN;
			}

        } catch (Exception e) {
            // TODO: handle exception
            // 获得异常信息并输出
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();
        } finally {
            result.setCode(exception.getCode());
            result.setData(terms);
            result.setMessage(exception.getMessage());
            result.setSign("");
        }

        return result;
	 }
	
	 /**
	  *  查询学段下的所有学科
	  * @param request
	  * @param response
	  * @return
	  * @throws IOException
	  */
    @RequestMapping(value = "/v1.0/subjects", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getAllSubjectsByTerm(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON result = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        List<JSubject> subjects = null;
        try {

            if (exception == null && currentUserId != null) {
                // 接收到的termId参数
            	long termId = 0;
            	if(StringUtils.isNotEmpty(request.getParameter("termId"))){
            		termId = Long.parseLong(request.getParameter("termId").toString().trim());
            	}
                subjects = termSubjectService.getAllSubjectsByTerm(termId);
                exception = CustomException.SUCCESS;
            } else {
            	exception = CustomException.INVALIDACCESSTOKEN;
			}

        } catch (Exception e) {
            // TODO: handle exception
            // 捕获异常信息
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();
        } finally {
            result.setCode(exception.getCode());
            result.setData(subjects);
            result.setMessage(exception.getMessage());
            result.setSign("");
        }
        return result;
    }
    
    /**
     *  根据学段、学科，查询所有版本教材
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1.0/editions", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getAllEditions(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON result = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        List<JSyscourse> editions = null;
        try {
            if (currentUserId != null && exception == null) {
            	 
            	long termId = 0;
            	long subjectId = 0;
            	if(StringUtils.isNotEmpty(request.getParameter("termId"))){
            		termId = Long.parseLong(request.getParameter("termId").toString().trim());
            	}
            	
            	if(StringUtils.isNotEmpty(request.getParameter("subjectId"))){
            		subjectId = Long.parseLong(request.getParameter("subjectId").toString().trim());
            	}

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("termId", termId);
                map.put("subjectId", subjectId);

                // 根据学段、学科，查询所有教材版本
                editions = editionService.getAllEditionsByTermAndSub(map);

                exception = CustomException.SUCCESS;
            } else {
            	exception = CustomException.INVALIDACCESSTOKEN;
			}

        } catch (Exception e) {
            // TODO: handle exception
            // 获得异常信息
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();

        } finally {
            result.setCode(exception.getCode());
            result.setData(editions);
            result.setMessage(exception.getMessage());
            result.setSign("");
        }

        return result;
    }


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
            	
            	String proCode = resourceWebConfig.getProCode();
            	
            	long pnodeId = 0;
            	
            	if(StringUtils.isNotEmpty(request.getParameter("pnodeId"))){
            		pnodeId = Long.parseLong(request.getParameter("pnodeId").toString().trim());
            	}

                // 根据所属版本和产品编码，查询所有的教材
                books = bookService.getAllBooks(pnodeId, proCode);

                exception = CustomException.SUCCESS;
            } else {
            	exception = CustomException.INVALIDACCESSTOKEN;
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
    
    /**
     * 查询系统课程目录结点
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1.0/contents", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getTreeNodes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON result = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        List<TreeNode> resultNodes = new ArrayList<TreeNode>();
        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {
                // 接收传递过来的父结点id
                long pnodeId = 0;
                if(StringUtils.isNotEmpty(request.getParameter("pnodeId"))){
                	 pnodeId = Long.parseLong(request.getParameter("pnodeId").toString().trim());
            	}

                // 加载父结点的所有子结点（递归）
                resultNodes = treeService.geTreeNodes(pnodeId);

                exception = CustomException.SUCCESS;
            } else {
            	exception = CustomException.INVALIDACCESSTOKEN;
			}

        } catch (Exception e) {
            // TODO: handle exception

            // 补货异常信息
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();

        } finally {
            result.setCode(exception.getCode());
            result.setData(resultNodes);
            result.setMessage(exception.getMessage());
            result.setSign("");
        }

        return result;
    }
    
    /**
     *  查询用户历史选择 使用GET方法
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1.0/history", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getUserDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /**
         * 返回json的结果对象
         */
        ResultJSON result = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        // 定义用户历史选择
        JUserDefault userDefault = null;
        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {
               
            	int type = resourceWebConfig.getType(request);
            	long userId = currentUserId;
               
                userDefault = userDefaultService.getUserHistoryDefault(userId,type);

                exception = CustomException.SUCCESS;
            } else {
            	exception = CustomException.INVALIDACCESSTOKEN;
			}

        } catch (Exception e) {
            // TODO: handle exception
            // 获得异常信息，并打印
            exception = CustomException.getCustomExceptionByCode(e.getMessage());

            e.printStackTrace();
        } finally {
            // 封装result
            result.setCode(exception.getCode());
            result.setData(userDefault);
            result.setMessage(exception.getMessage());
            result.setSign("");
        }

        return result;
    }

    /**
     *  修改、增加用户历史选择
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1.0/history", method = RequestMethod.POST)
    @ResponseBody
    public ResultJSON updateUserDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /**
         * 返回json的结果对象
         */
        ResultJSON result = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");

        try {

            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {
                // 用户id
                long userId = currentUserId;
                
                int type = resourceWebConfig.getType(request);

                // 结点tfcode
                String tfcode = request.getParameter("tfcode");
                           
                // 修改用户历史选择
                userDefaultService.updateUserHistoryDefault(userId,tfcode,type);
    
                exception = CustomException.SUCCESS;
            }

        } catch (Exception e) {
            // TODO: handle exception
            // 获得异常信息，并打印
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            e.printStackTrace();
        } finally {
            // 封装result
            result.setCode(exception.getCode());
            result.setData("");
            result.setMessage(exception.getMessage());
            result.setSign("");
        }

        return result;
    }

}
