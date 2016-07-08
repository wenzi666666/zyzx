package net.tfedu.zhl.sso.thirdpartyAPI.jnzx.util;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.sso.thirdpartyAPI.jnzx.entity.ZXCheckResult;
import net.tfedu.zhl.sso.thirdpartyAPI.jnzx.entity.ZXUserInfoResult;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 济宁中兴平台用户对接的工具类
 * 
 * @author wangwr
 *
 */
public class JNZXRelativeUtil {
	
	
	/**
	 * 中兴对接的教师用户角色（受限）
	 */
	public static final long roleId = 10002l;
	

	/**
	 * 中兴的api接口
	 */
	public static final String url = "http://edu.myjining.cn/serviceProxy/servlet/?json=";

	/**
	 * 退出登录后跳转的页面
	 */
	public static final String url_logout = "http://edu.myjining.cn/portal/login.html";

	/**
	 * 根据token 获取用户
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static ZXCheckResult checkToken(String token) throws Exception {

		// 接收参数json列表
		JSONObject json = new JSONObject();
		json.put("SERVICE_CODE", "zteict.proxy.user.LoginStatus");
		json.put("CONSUMER_ID", token);

		String result = HttpClientUtils.doGET(url + URLEncoder.encode(json.toJSONString(), "utf-8"));

		return JSONObject.parseObject(result, ZXCheckResult.class);
	}

	/**
	 * 获取用户的信息
	 * 
	 * @param userName
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static ZXUserInfoResult getUserInfo(String userName, String token) throws Exception {

		// 接收参数json列表
		JSONObject json = new JSONObject();
		json.put("SERVICE_CODE", "com.zte.space.homework.business.BusinessCenter4Homework.open__getUserInfo");
		json.put("CONSUMER_ID", token);
		json.put("USER_ID", userName);
		String result = HttpClientUtils.doGET(url + URLEncoder.encode(json.toJSONString(), "utf-8"));
		return JSONObject.parseObject(result, ZXUserInfoResult.class);

	}

	/**
	 * 增加注册并增加映射关系(指定roleId)
	 * @param info
	 * @param registerService
	 * @param userName
	 * @param platformcode
	 * @return
	 * @throws Exception
	 */
	public static SRegister registNewUserByZXAPI(ZXUserInfoResult info, RegisterService registerService, String userName, String platformcode)
			throws Exception {

		String _userName = info.getBODY().getUSER_ID();
		String _trueName = info.getBODY().getUSER_NAME();
		String _nickName = info.getBODY().getNICKNAME();
		String _sex = info.getBODY().getSEX();
		// USER_TYPE是用户类型，00代表学生，01代表老师，02代表家长，11代表管理员。
		String _userType = info.getBODY().getUSER_TYPE();
		String _motto = info.getBODY().getMOTTO();
		// 生日
		String _birthDate = info.getBODY().getBIRTH_DATE();
		// 省
		String provinceName = info.getBODY().getPROVINCE_NAME();
		// 市
		String cityName = info.getBODY().getCITY_NAME();
		// 区
		String arealName = info.getBODY().getAREAL_NAME();
		// 校
		String schoolName = info.getBODY().getSCHOOL_NAME();

		RegisterAddForm form = new RegisterAddForm();
		form.setProvinceName(provinceName);
		form.setCityName(cityName);
		form.setArealName(arealName);
		form.setSchoolName(schoolName);
		form.setMotto(_motto);
		form.setNickName(_nickName);
		form.setRole("00".equals(_userType) ? roleId : 2);
		form.setSex("0".equals(_sex) ? false : true);
		form.setSubjectName("语文");
		form.setTermName("初中");
		form.setTrueName(_userName);
		form.setUserName(_trueName);

		return registerService.addRegister(form, userName, platformcode);
	}
	
	
}
