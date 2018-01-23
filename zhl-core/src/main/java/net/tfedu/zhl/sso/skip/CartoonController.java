package net.tfedu.zhl.sso.skip;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.soap.ZHLSOAPUtil;
import net.tfedu.zhl.userlayer.subject.entity.JTeacherSubject;
import net.tfedu.zhl.userlayer.subject.service.impl.JSubjectServiceImpl;
import net.tfedu.zhl.userlayer.user.entity.JUser;
import net.tfedu.zhl.userlayer.user.entity.UserAreaInfo;
import net.tfedu.zhl.userlayer.user.service.JUserService;

/**
 * 增加动漫练习的对接
 * 
 * @author wangwr
 * 
 * 
 * 增加 获取动漫练习链接地址的接口
 * 
 *
 */

@Controller
@RequestMapping("/*RestAPI/cartoon")
public class CartoonController {

	@Resource
	JUserService userService;

	@Resource
	JSubjectServiceImpl subjectService;

	@Resource
	CommonWebConfig config;

	/**
	 * 跳转链接
	 * 
	 * @param request
	 * @param response
	 * @return 返回跳转链接
	 * @throws CustomException 
	 */
	@RequestMapping("/skip")
	@ResponseBody
	public ResultJSON skip(HttpServletRequest request, HttpServletResponse response, long grade) throws CustomException {

		// 当前登录用户id
		Long currentUserId = (Long) request.getAttribute("currentUserId");

		// 获取用户地区信息 用于同步
		UserAreaInfo info = userService.getUserAreaInfo(currentUserId);

		// 获取用户信息 用于同步
		JUser user = userService.getUserById(currentUserId);

//		String result = synchronizationZhlToThirdpartSchool(info);
		synchronizationZhlToThirdpartSchool(info);

		long userId = currentUserId;
		// 用户名、昵称、学校id
		String nickName = user.getNickname();
		long schoolId = user.getSchoolid();
		String userName = user.getName();
		long roleId = Long.parseLong(user.getRoleid());

		// grade classname 没有班级，默认为空或0
		String className = "资源中心默认班级";

		// 获取学科
		long subjectId;
		String subject = "";
		String coursecode = "default";

		List<JTeacherSubject> list = subjectService.getUserSubject(userId);

		// list不为空 取多个学科的第一个
		if (list != null && list.size() > 0) {
			subjectId = list.get(0).getSubjectid();
			if (subjectId == 1) {
				subject += "yw,";
				coursecode = grade + "YWRJ02";
			} else if (subjectId == 2) {
				subject += "sx,";
				coursecode = grade + "SXRJ02";
			} else if (subjectId == 3) {
				subject += "yy,";
				coursecode = grade + "YYQD02";
			} else {
				coursecode = "default";
			}

			if (!StringUtils.isEmpty(subject) && subject.lastIndexOf(",") == (subject.length() - 1)) {
				subject = subject.substring(0, subject.lastIndexOf(","));
			}

		}

		synchronizationZhlToThirdpartUserInfo(userName, roleId, nickName, schoolId, grade,
				className/** 学生暂时取第一个行政班（待开发） */
				, subject/** 老师任教科目，如果是同时任教数学和英语时，默认选第一个（待开发） */
				, "1"/** avatarid 头像ID，预留接口，目前统一传值1（动漫练习平台在不支持） */
				, "20170101"/** expirationtime 验证字符串，暂略（动漫练习平台在不支持） */
		);

//		String result = synchronizationZhlToThirdpartUserInfo(userName, roleId, nickName, schoolId, grade,
//				className/** 学生暂时取第一个行政班（待开发） */
//				, subject/** 老师任教科目，如果是同时任教数学和英语时，默认选第一个（待开发） */
//				, "1"/** avatarid 头像ID，预留接口，目前统一传值1（动漫练习平台在不支持） */
//				, "20170101"/** expirationtime 验证字符串，暂略（动漫练习平台在不支持） */
//				);
//		
		// 生成动漫尚学登录链接
		// String createHyperlink =
		// "http://210.14.132.162:8011/zhihaole/user/index.php?w=1536&h=737";
		String createHyperlink = createHyperlink(userName, coursecode/** 暂时默认 */
				, System.currentTimeMillis(), "123456", request);

		return ResultJSON.getSuccess(createHyperlink);
	}

	/**
	 * 生成动漫尚学登录链接生成动漫尚学登录链接
	 * 
	 * @param userName
	 * @param coursecode
	 * @param timestamp
	 * @param key
	 * @param request
	 * @return
	 */
	private String createHyperlink(String userName, String coursecode, long timestamp, String key,
			HttpServletRequest request) {

		if (!StringUtils.isEmpty(coursecode)) {
			coursecode = coursecode.toLowerCase();
		}

		String dm5Begin = userName + "|" + coursecode + "|" + timestamp + "|" + key;
		String createHyperlink = this.config.getCurrentCartoonWeb(request) + "/log.php?&uid=" + userName
				+ "&coursecode=" + coursecode + "&time=" + timestamp + "&key=" + key + "&token="
				+ MD5.getMD5Str(dm5Begin).toLowerCase();

		return createHyperlink;
	}

	/**
	 * 向动漫尚学同步学校信息
	 * 
	 * @return
	 * @throws CustomException
	 */
	private String synchronizationZhlToThirdpartSchool(UserAreaInfo info) throws CustomException {

		String result = null;

		if (null != info) {

			String ProvinceName = info.getProvinceName();
			String CityName = info.getCityName();
			String DistrictName = info.getDistrictName();
			String schoolName = info.getSchoolName();
			long schoolId = info.getSchoolId();

			String namespace = config.getCartoonWebService() + "/soap/SchoolInfo";
			String name = "SchoolInfo";
			String url = config.getCartoonWebLocal() + "/nusoap/Sserver_SchoolInfo.php";
			Map<String, String> properties = new HashMap<String, String>();

			properties.put("ProvinceName", ProvinceName);
			properties.put("CityName", CityName);
			properties.put("DistrictName", DistrictName);
			properties.put("schoolName", schoolName);
			properties.put("schoolId", String.valueOf(schoolId));

			try {
				result = ZHLSOAPUtil.doneWebService(namespace, name, url, properties);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		if ("2".equals(result)) {
			System.err.println("************************* （动漫练习）学校名不合法 ***************************");
			throw new CustomException("（动漫练习）学校名不合法 ");
		} else if ("4".equals(result)) {
			System.err.println("************************* （动漫练习）学校ID不合法 ***************************");
			throw new CustomException("（动漫练习）学校ID不合法 ");

		} else if ("6".equals(result)) {
			System.err.println("************************* （动漫练习）市县ID不合法 ***************************");
			throw new CustomException("（动漫练习）市县ID不合法 ");
		} else if ("9".equals(result)) {
			System.err.println("************************* （动漫练习）其它情形 ***************************");
			throw new CustomException("（动漫练习）其它情形错误 ");
		}

		return result;
	}

	/**
	 * 向尚学动漫同步用户信息
	 * 
	 * @param uid
	 *            用户名
	 * @param utype
	 *            角色id
	 * @param nickname
	 * @param schoolid
	 * @param grade
	 * @param className
	 * @param subject
	 * @param avatarid
	 * @param expirationtime
	 * @return
	 * @throws CustomException
	 */
	private String synchronizationZhlToThirdpartUserInfo(String uid, long utype, String nickname, long schoolid,
			long grade, String className, String subject, String avatarid, String expirationtime)
			throws CustomException {
		String result = null;

		String namespace = config.getCartoonWebService() + "/soap/UserInfo";
		String name = "UserInfo";
		String url = config.getCartoonWebLocal() + "/nusoap/Sserver_UserInfo.php";
		Map<String, String> properties = new HashMap<String, String>();

		properties.put("uid", uid);
		properties.put("utype", String.valueOf(schoolid));
		properties.put("nickname", nickname);
		properties.put("schoolid", String.valueOf(schoolid));
		properties.put("grade", String.valueOf(grade));
		properties.put("uid", uid);
		properties.put("class", className);
		properties.put("subject", subject);
		properties.put("avatarid", avatarid);
		properties.put("expirationtime", expirationtime);

		try {
			result = ZHLSOAPUtil.doneWebService(namespace, name, url, properties);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if ("2".equals(result)) {
			System.err.println("************************* （动漫练习）uid不合法 ***************************");
			throw new CustomException("用户名不合法，请查看通知管理员");
		} else if ("3".equals(result)) {
			System.err.println("************************* （动漫练习）数据表更新失败 ***************************");
		} else if ("4".equals(result)) {
			System.err.println("************************* nickname不合法 ***************************");
			throw new CustomException("昵称不合法，请查看用户昵称");
		} else if ("5".equals(result)) {
			System.err.println("************************* （动漫练习）schoolid不合法 ***************************");
		} else if ("6".equals(result)) {
			System.out.println("************************* （动漫练习）avatarid不合法 ***************************");
		} else if ("7".equals(result)) {
			System.err.println("************************* （动漫练习）grade不合法 ***************************");
			throw new CustomException("年级不合法，请查看用户年级");
		} else if ("8".equals(result)) {
			System.err.println("************************* （动漫练习）class不合法 ***************************");
			throw new CustomException("班级不合法，请查看用户班级");
		} else if ("9".equals(result)) {
			System.err.println("************************* （动漫练习）其它情形 ***************************");
		}

		return result;

	}

}
