package net.tfedu.zhl.cloud.resource.navigation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.navigation.entity.TreeNode;
import net.tfedu.zhl.cloud.resource.navigation.service.TreeService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 目录树的controller
 * 
 * @author WeiCuicui 查询目录树的所有结点
 */
@Controller
@RequestMapping("/resRestAPI")
public class TreeController {

    @Resource
    TreeService treeService;

    /**
     * 查询所有树结点
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
                Long pnodeId = Long.parseLong(request.getParameter("pnodeId").toString().trim());

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
}