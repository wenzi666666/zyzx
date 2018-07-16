package net.tfedu.zhl.cloud.resource.integration.login;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.tfedu.zhl.cloud.resource.constant.ResourcePlatformConstant;
import net.tfedu.zhl.cloud.resource.constant.ResourcePlatformWebConstant;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.Custom500Exception;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.fileservice.xxtea;
import net.tfedu.zhl.sso.area.service.AreaService;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.user.service.JUserService;
import net.tfedu.zhl.sso.users.entity.CloudUserInfoForm;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 
 * 资源中心集成登录接口---集成公司教学平台
 * 
 * @author wangwr
 * @date 2017年8月2日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
@Controller
public class ResourceIntegrationLoginController {

	private static final String MD5_KEY = "9k8i78jug6hd93kjf84h";

	/**
	 * 演示平台来源标识
	 */
	private static final String YS_FROMFLAG = "ys";

	/**
	 * 对接有效的云平台起始版本
	 */
	private static final String CLOUD_VERSION_STARTED = "2.5";

	private static final String PARAM_USER = "user";
	private static final String PARAM_ROLEID = "roleId";
	private static final String PARAM_TRUENAME = "truename";
	private static final String PARAM_NICKNAME = "nickName";
	private static final String PARAM_TERMNAME = "termName";
	private static final String PARAM_TERMCODE = "termCode";
	private static final String PARAM_SUBJECTCODE = "subjectCodes";
	private static final String PARAM_MALE = "male";
	private static final String PARAM_SCHOOLNAME = "schoolName";
	private static final String PARAM_SCHOOLID = "schoolId";
	private static final String PARAM_PASS = "pass";
	private static final String PARAM_SIGN = "sign";
	private static final String PARAM_PAGE = "page";

	private static final String PARAM_DISTRICTID = "districtId";
	private static final String PARAM_DISTRICTNAME = "districtName";
	private static final String PARAM_CITYID = "cityId";
	private static final String PARAM_CITYNAME = "cityName";
	private static final String PARAM_PROVINCEID = "provinceId";
	private static final String PARAM_PROVINCENAME = "provinceName";
	private static final String PARAM_KEY = "key";

	@Resource
	AreaService areaServiceImpl;
	@Resource
	RegisterService registerService;
	@Resource
	private JUserService userService;
	@Resource
	private CommonWebConfig commonWebConfig;

	@Autowired
	CacheManager cacheManager;

	@RequestMapping(value = "loginRes_integrationNew.action")
	public String login(HttpServletRequest request, HttpServletResponse response, String s, String fromflag,
			Long userId, String target, String appendRoleIds, String resPageForward, String cloudPlatFormLocal,
			String currentCloudForm, String cloudplatFormVersion) throws CustomException {

		cloudplatFormVersion = cloudplatFormVersion == null ? "" : cloudplatFormVersion;
		cloudplatFormVersion = (!YS_FROMFLAG.equals(fromflag) && cloudplatFormVersion != null) ? CLOUD_VERSION_STARTED
				: cloudplatFormVersion.trim();
		if (s == null) {
			throw new Custom500Exception("缺少对接字符串");
		}

		Long schoolId_final = 0L;
		UserSimple userSimple = null;

		try {
			// 解密字符串

			s = s.replace(" ", "+");

			String p = xxtea.decryptstring(s, MD5_KEY);

			// 将字符串中的参数按照key、value，放到map中
			String param[] = p.split("&");
			HashMap<String, String> paramMap = new HashMap<String, String>();
			for (int i = 0; i < param.length; i++) {
				String[] temp = param[i].split("=");
				paramMap.put(temp[0], temp.length == 2 ? temp[1] : "");
			}

			String userName = paramMap.get(PARAM_USER);
			String roleIdParam = paramMap.get(PARAM_ROLEID);
			String truename = paramMap.get(PARAM_TRUENAME);
			String nickName = paramMap.get(PARAM_NICKNAME);
			String termName = paramMap.get(PARAM_TERMNAME);
			String termCode = paramMap.get(PARAM_TERMCODE);
			String subjectCodes = paramMap.get(PARAM_SUBJECTCODE);
			String male = paramMap.get(PARAM_MALE);
			String schoolName = paramMap.get(PARAM_SCHOOLNAME);
			String schoolId = paramMap.get(PARAM_SCHOOLID);
			String pass = paramMap.get(PARAM_PASS);
			String sign = paramMap.get(PARAM_SIGN);
			String page = paramMap.get(PARAM_PAGE);

			// 为了补充完整地区信息增加省市区的id、name的参数传递
			String districtId = paramMap.get(PARAM_DISTRICTID);
			String districtName = paramMap.get(PARAM_DISTRICTNAME);
			String cityId = paramMap.get(PARAM_CITYID);
			String cityName = paramMap.get(PARAM_CITYNAME);
			String provinceId = paramMap.get(PARAM_PROVINCEID);
			String provinceName = paramMap.get(PARAM_PROVINCENAME);

			String trueName = truename;

			String params = PARAM_USER + "=" + userName + "&" + PARAM_PASS + "=" + pass + "&" + PARAM_ROLEID + "="
					+ roleIdParam + "&" + PARAM_TRUENAME + "=" + truename + "&" + PARAM_NICKNAME + "=" + nickName + "&"
					+ PARAM_MALE + "=" + male + "&" + PARAM_SCHOOLNAME + "=" + schoolName + "&" + PARAM_SCHOOLID + "="
					+ schoolId + "&" + PARAM_TERMNAME + "=" + termName + "&" + PARAM_TERMCODE + "=" + termCode + "&"
					+ PARAM_SUBJECTCODE + "=" + subjectCodes;
			// 已经增加省区校
			if (StringUtils.isNotEmpty(districtId) && StringUtils.isNotEmpty(districtName)) {
				params += "&" + PARAM_DISTRICTID + "=" + districtId + "&" + PARAM_DISTRICTNAME + "=" + districtName
						+ "&" + PARAM_CITYID + "=" + cityId + "&" + PARAM_CITYNAME + "=" + cityName + "&"
						+ PARAM_PROVINCEID + "=" + provinceId + "&" + PARAM_PROVINCENAME + "=" + provinceName;
			}

			String _sign = MD5.MD5(params + "&" + PARAM_KEY + "=" + MD5_KEY);
			if (!sign.equals(_sign)) {
				throw new Custom500Exception("接口传递参数校验失败");
			}

			// 先获取学校id
			schoolId_final = areaServiceImpl.syncCloudSchoolInfo(provinceId, provinceName, cityId, cityName, districtId,
					districtName, schoolId, schoolName);

			CloudUserInfoForm form = new CloudUserInfoForm();
			form.setNickName(nickName);
			form.setTrueName(trueName);
			form.setRole(Long.parseLong(roleIdParam));
			form.setSchoolId(schoolId_final);
			form.setSex(Boolean.parseBoolean(male));
			form.setSubjectCodes(subjectCodes);
			form.setTermCode(termCode);
			form.setUserId(userId);
			form.setUserName(userName);
			form.setPassword(pass);
			// 同步用户信息
			SRegister reg = registerService.syncCloudUser(form);

			// 获取用户信息
			userSimple = userService.getUserSimpleById(reg.getId(), ResourcePlatformConstant.MODEL_DEFAULT,
					commonWebConfig.getIsRepeatLogin());

			// 记录用户的登录状态
			userService.addUserLoginStatusWeb(reg.getId(), reg.getNodeid(), userSimple.getToken(), request);

			// 重定向到指定的页面
			String url = ResourcePlatformWebConstant.getWEbURL(commonWebConfig.getFrontWebURL(), userId.toString(),
					userSimple.getToken());

			response.sendRedirect(url);

		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new Custom500Exception(e.getMessage());
		}

		return null;
	}

}
