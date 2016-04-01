package net.tfedu.zhl.cloud.resource.asset.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.asset.service.ZAssetService;
import net.tfedu.zhl.cloud.resource.user.entity.JUser;
import net.tfedu.zhl.cloud.resource.user.service.UserService;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;
import net.tfedu.zhl.fileservice.zhldowncenter;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resRestAPI")
public class AssetController {

    /**
     * 用户service
     */
    @Resource
    private UserService userService;

    /**
     * 自建资源service
     */
    @Resource
    ZAssetService assetService;

    /**
     * 前端web端获取上传文件路径（支持格式转换）
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/v1.0/resource/upload", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getUploadUrl(HttpServletRequest request, HttpServletResponse response) {
        // 返回json的结果对象
        ResultJSON result = new ResultJSON();
        // 异常
        CustomException exception = (CustomException) request.getAttribute(CustomException.request_key);
        // 当前登录用户id
        Long currentUserId = (Long) request.getAttribute("currentUserId");
        // 返回
        Object data = null;

        try {
            if (currentUserId != null && exception == null) {
                // 获取文件服务器的访问url
                String resServiceLocal = (String) request.getAttribute("resServiceLocal");
                String currentResPath = (String) request.getAttribute("currentResPath");
                String hostLocal = (String) request.getAttribute("hostLocal");

                long userId = currentUserId;
                JUser user = userService.getUserById(userId);
                long schoolId = user.getSchoolid();
                // 组装上传路径
                String uploadPath = ZhlResourceCenterWrap.getUserUploadPath(userId, schoolId);
                // 获取上传文件路径
                String uploadUrl = ZhlResourceCenterWrap.getUploadUrlConvert(uploadPath, resServiceLocal,
                        currentResPath, hostLocal, userId);

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("uploadUrl", uploadUrl);
                data = map;
                exception = CustomException.SUCCESS;
            }
        } catch (Exception e) {
            exception = CustomException.getCustomExceptionByCode(e.getMessage());
            // 如果是普通的异常
            if (exception.getStatus() == 500) {
                e.printStackTrace();
            }
        } finally {
            result.setCode(exception.getCode());
            result.setMessage(exception.getMessage());
            result.setData(data == null ? "" : data);
            result.setSign("");
        }
        return result;

    }

    /**
     * 文件服务格式转换后的回调
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/v1.0/resource/uploadConvertCallBack", method = RequestMethod.GET)
    public void uploadConvertCallBack(HttpServletRequest request, HttpServletResponse response) {
        // 获得文件服务器返回的extend参数
        String ext = zhldowncenter.GetOriginQueryString(request.getParameter("ext"));
        // 从extend参数中获得userId
        int index = ext.indexOf("=");
        long userId = Long.parseLong(ext.substring(index + 1, ext.length()));
        // 获得文件服务器返回的file参数
        String resPath = request.getParameter("file");

        assetService.setTypeConvertSucceed(userId, resPath);

    }

}
