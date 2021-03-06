package net.tfedu.zhl.cloud.resource.integration.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.integration.entity.CourseNode;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.exception.WithoutAuthorizationException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 
 * 工具类：访问云平台提供的HTTP接口
 * 
 * 
 * @author wangwr
 * @date 2017年8月8日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class CloudHttpInterfaceUtil {

	/**
	 * 访问云平台获取userId
	 * 
	 * @param userName
	 * @param cloudPath
	 * @return
	 */
	public static Long getUserIdFromCloud(String userName, String cloudPath) throws CustomException {

		String url = cloudPath + "/coludRssourseAjax_resourseCenterNew2.action?resPageForward=no&userName=" + userName;
		String result;
		try {
			result = HttpClientUtils.doGET(url);
			if (StringUtils.isNotEmpty(result)) {
				@SuppressWarnings("rawtypes")
				Map m = (Map) JSONObject.parseObject(result, Map.class);
				Object o = m.get("userId");
				return Long.parseLong(m.get("userId").toString());
			} else {
				throw new WithoutAuthorizationException();
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	
	/**
	 * 访问云平台 获取自建目录节点
	 * @param cloudPath
	 * @param userName
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	public static List<CourseNode> getCloudCourseNodes(String cloudPath, String userName, String id)
			throws CustomException {

		try {
			String url = cloudPath + "/coludRssourseAjax_sendTree.action?userName=" + userName + "&id=" + id;

			return JSONArray.parseArray(HttpClientUtils.doGET(url), CourseNode.class);

		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}
	
	/**
	 * 根据username获取userid(依赖参数userName、cloudPlatFormLocal)
	 * @param request
	 * @param registerService
	 * @return
	 * @throws Exception
	 */
	public static Long getUserId(HttpServletRequest request,RegisterService registerService) throws Exception {
		String userName = ControllerHelper.getParameter(request, "userName");

		SRegister reg = registerService.getRegister(userName);

		boolean isExist = reg != null;

		if (!isExist) {
			String cloudPlatFormLocal = ControllerHelper.getParameter(request, "cloudPlatFormLocal");

			if (StringUtils.isNotEmpty(cloudPlatFormLocal) && cloudPlatFormLocal.indexOf(",") >= 0) {
				cloudPlatFormLocal = cloudPlatFormLocal.split(",")[0];
			}
			return CloudHttpInterfaceUtil.getUserIdFromCloud(userName, cloudPlatFormLocal);

		} else {
			return reg.getId();
		}

	}

}
