package net.tfedu.zhl.cloud.resource.resourceList.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.resource.resourceList.entity.DisResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.resource.resourceList.entity.SysResourceEntity;
import net.tfedu.zhl.cloud.resource.resourceList.service.DisResService;
import net.tfedu.zhl.cloud.resource.resourceList.service.SysResourceService;
import net.tfedu.zhl.cloud.resource.resourceList.util.ResThumbnailPathUtil;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询系统、区本、校本资源列表
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class ResourceListController {

	@Resource
    SysResourceService sysResourceService;
	
    @Resource
    ResTypeService resTypeService;
    
    @Resource
    DisResService disResService;
    
    //写入日志
    Logger logger = LoggerFactory.getLogger(ResourceListController.class);

    /**
     * 查询系统资源列表
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
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
				long poolId = 0;
				// 类型Id
				int mTypeId = 0;
				 // 资源格式
				String fileFormat= "全部";
				 // 课程tfcode
				String tfcode = "";
				// 排序方式（综合排序；最多下载；最新发布）
				int orderBy = 0;
				// 页码
				int page = 1;
				 // 每页的记录数
				int perPage = 10;
				
				if(StringUtils.isNotEmpty(request.getParameter("poolId"))){
					poolId = Long.parseLong(request.getParameter("poolId").toString().trim());
				}
				if(StringUtils.isNotEmpty(request.getParameter("mTypeId"))){
					mTypeId = Integer.parseInt(request.getParameter("mTypeId").toString().trim());
				}
				if(StringUtils.isNotEmpty(request.getParameter("fileFormat"))){
					fileFormat = request.getParameter("fileFormat").toString().trim();
				}
				if(StringUtils.isNotEmpty(request.getParameter("tfcode"))){
					tfcode = request.getParameter("tfcode").toString().trim();
				}
				if(StringUtils.isNotEmpty(request.getParameter("orderBy"))){
					orderBy = Integer.parseInt(request.getParameter("orderBy").toString().trim());
					
				}
				if(StringUtils.isNotEmpty(request.getParameter("page"))){
					page = Integer.parseInt(request.getParameter("page").toString().trim());
				}
				if(StringUtils.isNotEmpty(request.getParameter("perPage"))){
					perPage = Integer.parseInt(request.getParameter("perPage").toString().trim());
				}
				
               
                if(request.getParameter("isPreview") != null){ //若当前是 预览页面的资源推荐列表（需要将当前预览的这条资源显示为第一个）
                	//要显示在查询结果第一个位置的资源id
                	long resId = 0;
                	if(StringUtils.isNotEmpty(request.getParameter("resId"))){
                		resId = Long.parseLong(request.getParameter("resId").toString().trim());
                	}
                	pagination = sysResourceService.getAllSysRes_Preview(poolId, mTypeId, fileFormat, tfcode, orderBy, page, perPage, resId);
                	
                }  else if(request.getParameter("isEPrepare") != null){//若当前访问的是 e备课
                	
                	//模糊查询的关键字
                	String searchWord = "";
                	if(StringUtils.isNotEmpty(request.getParameter("searchWord"))){
                		searchWord = request.getParameter("searchWord").toString().trim();
                	}
                
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
    
    /**
     * 查询区本、校本资源类型
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1.0/districtResource", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getDisResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 返回json的结果对象
         */
        ResultJSON resultJSON = new ResultJSON();

        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        Pagination<DisResourceEntity> pagination = null;
        try {
            // 当前用户已经登录系统
            if (exception == null && currentUserId != null) {
            	
            	//获取文件服务器的访问url 
				String resServiceLocal = (String)request.getAttribute("resServiceLocal");
				String currentResPath = (String)request.getAttribute("currentResPath");
				
                long userId = currentUserId; // 获得用户id
     
				// 类型Id
				int mTypeId = 0;
				 // 资源格式
				String fileFormat= "全部";
				 // 课程tfcode
				String tfcode = "";
				// 排序方式（综合排序；最多下载；最新发布）
				int orderBy = 0;
				// 页码
				int page = 1;
				 // 每页的记录数
				int perPage = 10;
				
				 // 资源来源
                int fromFlag = 3;
               
			
				if(StringUtils.isNotEmpty(request.getParameter("mTypeId"))){
					mTypeId = Integer.parseInt(request.getParameter("mTypeId").toString().trim());
				}
				if(StringUtils.isNotEmpty(request.getParameter("fileFormat"))){
					fileFormat = request.getParameter("fileFormat").toString().trim();
				}
				if(StringUtils.isNotEmpty(request.getParameter("tfcode"))){
					tfcode = request.getParameter("tfcode").toString().trim();
				}
				if(StringUtils.isNotEmpty(request.getParameter("orderBy"))){
					orderBy = Integer.parseInt(request.getParameter("orderBy").toString().trim());
					
				}
				if(StringUtils.isNotEmpty(request.getParameter("page"))){
					page = Integer.parseInt(request.getParameter("page").toString().trim());
				}
				if(StringUtils.isNotEmpty(request.getParameter("perPage"))){
					perPage = Integer.parseInt(request.getParameter("perPage").toString().trim());
				}
				if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
					fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
				}
                
               
                if(request.getParameter("isPreview") != null){ //若当前是 预览页面的资源推荐列表（需要将当前预览的这条资源显示为第一个）
                	
                	//要显示在查询结果第一个位置的资源id
                	long resId = 0;
                	if(StringUtils.isNotEmpty(request.getParameter("resId"))){
                		resId = Long.parseLong(request.getParameter("resId").toString().trim());
                	}
                	pagination = disResService.selectAllDisRes_Preview(userId, mTypeId, fileFormat, tfcode, orderBy, page, perPage, fromFlag, resId);
                	
                }  else if(request.getParameter("isEPrepare") != null){//若当前访问的是 e备课
                	
                	//e备课 模糊查询的关键字
                	String searchWord = "";
                	if(StringUtils.isNotEmpty(request.getParameter("searchWord"))){
                		searchWord = request.getParameter("searchWord").toString().trim();
                	}
                	pagination = disResService.selectAllDisRes_EPrepare(userId, mTypeId, fileFormat, tfcode, orderBy, page, perPage, fromFlag, searchWord, SysFrom.removeTypeIds);
                	
                } else { //普通区本校本资源接口
                	
                	 pagination = disResService.selectAllDisRes(userId, mTypeId, fileFormat, tfcode, orderBy, page, perPage,
                             fromFlag);
				}

               
                //生成文件的缩略图路径
                ResThumbnailPathUtil.convertToPurpos_dis(pagination.getList(), resServiceLocal, currentResPath);
               
                if(fromFlag == 3)
                	logger.debug(fromFlag + " : 校本资源");
                else if(fromFlag == 4)
                	logger.debug(fromFlag + " : 区本资源");
                
                logger.debug("校本 / 区本资源的资源格式：" + fileFormat);
                
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
