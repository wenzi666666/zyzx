package net.tfedu.zhl.cloud.resources.navigation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resources.navigation.entity.TreeNode;
import net.tfedu.zhl.cloud.resources.navigation.service.TreeService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 目录树的controller
 * @author WeiCuicui
 * 查询目录树的所有结点
 */
@Controller
@RequestMapping("/resRestAPI")
public class TreeController {

	@Resource TreeService treeService;
	
	/**
	 * 返回json的结果对象
	 */
	private ResultJSON result = new ResultJSON();
	
	/**
	 * 异常
	 */
	private CustomException exception;
	
	/**
	 * 查询所有树结点
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/v1.0/contents",method = RequestMethod.GET)
	public ResultJSON getTreeNodes(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<TreeNode> resultNodes = new ArrayList<TreeNode>();
		try {
			//接收传递过来的父结点id
			Long pnodeId = Long.parseLong(request.getParameter("pnodeId").toString().trim());
			
			//查询父结点下的直接子结点
			List<TreeNode> topChildren = treeService.getTopChildren(pnodeId);
			
			//查询所有的子结点
			resultNodes = treeService.getAllChildren(topChildren, resultNodes);
			
			exception = CustomException.SUCCESS;
		
		} catch (Exception e) {
			// TODO: handle exception
			
			//补货异常信息
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
}
