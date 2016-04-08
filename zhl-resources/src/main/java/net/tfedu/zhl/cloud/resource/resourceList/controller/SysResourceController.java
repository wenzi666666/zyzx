package net.tfedu.zhl.cloud.resource.resourceList.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.cloud.resource.resourceList.util.ResThumbnailPathUtil;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

/**
 * 系统资源 列表 controller
 * 
 * @author WeiCuicui
 *
 */

@Controller
@RequestMapping("resRestAPI")
public class SysResourceController {

    @Resource
    SysResourceService sysResourceService;
    @Resource
    ResTypeService resTypeService;
    
    //写入日志
    Logger logger = LoggerFactory.getLogger(SysResourceController.class);

    @RequestMapping(value = "/v1.0/sysResource", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getSysResources(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        

        // 查询结果，封装为pagination
        Pagination<SysResourceEntity> pagination = null;
        try {

            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {
            	
            	//获取文件服务器的访问url 
				String resServiceLocal = (String)request.getAttribute("resServiceLocal");
				String currentResPath = (String)request.getAttribute("currentResPath");

                // 资源库id
                long poolId = Long.parseLong(request.getParameter("poolId"));

                // 类型Id
                int mTypeId = Integer.parseInt(request.getParameter("mTypeId"));

                // 资源格式
                String fileFormat = request.getParameter("fileFormat");

                // 课程tfcode
                String tfcode = request.getParameter("tfcode");

                // 排序方式（综合排序；最多下载；最新发布）
                int orderBy = Integer.parseInt(request.getParameter("orderBy"));

                // 页码
                int page = Integer.parseInt(request.getParameter("page"));

                // 每页的记录数
                int perPage = Integer.parseInt(request.getParameter("perPage"));
                
               
                if(request.getParameter("isPreview") != null){ //若当前是 预览页面的资源推荐列表（需要将当前预览的这条资源显示为第一个）
                	//要显示在查询结果第一个位置的资源id
                	long resId = Long.parseLong(request.getParameter("resId"));
                	pagination = sysResourceService.getAllSysRes_Preview(poolId, mTypeId, fileFormat, tfcode, orderBy, page, perPage, resId);
                	
                }  else if(request.getParameter("isEPrepare") != null){//若当前访问的是 e备课
                	
                	//模糊查询的关键字
                	String searchWord = request.getParameter("searchWord");
                
                	//e备课查询系统资源
                	pagination = sysResourceService.getAllSysRes_EPrepare(poolId, mTypeId, fileFormat, tfcode, orderBy, page, perPage, searchWord, SysFrom.removeTypeIds);
                	
                } else {
                	 // 查询出的系统资源信息
                    pagination = sysResourceService.getAllSysRes(poolId, mTypeId, fileFormat, tfcode, orderBy, page,
                            perPage);
				}
                
                //生成文件的缩略图路径
                ResThumbnailPathUtil.convertToPurpos_sys(pagination.getList(), resServiceLocal, currentResPath);
                
                logger.debug("系统资源的课程id：" + tfcode);
                
                logger.debug("系统资源的资源格式：" + fileFormat);
              
                logger.debug("查询结果的当前页：" + pagination.getPage());
                logger.debug("查询结果每页资源数目：" + pagination.getPerPage());
                logger.debug("查询到的资源总页：" + pagination.getTotal());
                logger.debug("查询到的资源总数：" + pagination.getTotalLines());

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
            // 封装结果集
            resultJSON.setCode(exception.getCode());
            resultJSON.setData(pagination);
            resultJSON.setMessage(exception.getMessage());
            resultJSON.setSign("");
        }

        return resultJSON;
    }
}
