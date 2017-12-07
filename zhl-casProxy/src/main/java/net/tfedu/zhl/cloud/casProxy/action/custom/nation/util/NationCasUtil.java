package net.tfedu.zhl.cloud.casProxy.action.custom.nation.util;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.casProxy.action.custom.nation.bean.NationResultJSON;
import net.tfedu.zhl.cloud.casProxy.action.custom.nation.bean.NationTokenInfo;
import net.tfedu.zhl.cloud.casProxy.action.custom.nation.bean.NationUserInfo;
import net.tfedu.zhl.cloud.casProxy.constant.ConstantForUserRole;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;

/**
 * 
 * 对接央馆的工具类
 * 
 * @author wangwr
 * @date 2017年9月18日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class NationCasUtil {

	public static final String Http_Encoding = "UTF-8";

	/**
	 * 获取加密后的keyinfo
	 * 
	 * @return
	 */
	public static String getKeyInfo_Sha1Hamc(String appId, String appKey, String timestamp) {

		String paramValues = appId + appKey + timestamp;
		byte[] hmacSHA = EncryptionUtils.getHmacSHA1(paramValues, appKey);
		return EncryptionUtils.bytesToHexString(hmacSHA);
	}

	
	/**
	 * 是否成功
	 * @param ret
	 * @throws CustomException
	 */
	public static void checkSuccess(NationResultJSON ret) throws CustomException {

		if (null == ret) {
			throw new CustomException("NationResultJSON返回null");
		} else if (!"000000".equals(ret.getRetCode())) {
			throw new CustomException(ret.getRetCode(), ret.getRetDesc());
		}
	}

	
	/**
	 * 获取用户的token信息
	 * @param appId
	 * @param appKey
	 * @param apiServer
	 * @param sysCode
	 * @return
	 * @throws Exception
	 */
	public static NationTokenInfo getToken(String appId, String appKey, String apiServer, String sysCode)
			throws Exception {

		String timeStamp = String.valueOf(System.currentTimeMillis());
		String keyInfo = getKeyInfo_Sha1Hamc(appId, appKey, timeStamp);
		String queryParam = "{\"appId\":\"" + appId + "\",\"timeStamp\":\"" + timeStamp + "\",\"keyInfo\":\"" + keyInfo
				+ "\",\"sysCode\":\"" + sysCode + "\"}";

		String tokenUrl = apiServer + "/apigateway/getAccessToken";
		
		
		System.out.println("----tokenUrl----"+tokenUrl);


		String result = HttpClientUtil.httpPost(tokenUrl, queryParam, Http_Encoding);

		NationResultJSON ret = JSONObject.parseObject(result, NationResultJSON.class);

		checkSuccess(ret);
		
		NationTokenInfo info = JSONObject.toJavaObject(ret.getData(), NationTokenInfo.class);

		return info;
	}

	/***
	   获取用户信息
	 * @param apiServer
	 * @param accessToken
	 * @param ticket
	 * @return
	 * @throws Exception
	 */
	public static NationUserInfo getUserInfoByCheckTicket(String apiServer, String accessToken,String ticket) throws Exception {

		String tokenUrl = apiServer + "/userSession/validaTicket?accessToken=" + accessToken;
		
		System.out.println("----tokenUrl----"+tokenUrl);
		
		String result = HttpClientUtil.httpPost(tokenUrl, "{\"ticket\":\""+ticket+"\"}", Http_Encoding);

		NationResultJSON ret = JSONObject.parseObject(result, NationResultJSON.class);

		checkSuccess(ret);

		return JSONObject.toJavaObject(ret.getData(), NationUserInfo.class);
	}
	
	/**
	 * 将央馆的信息格式化为注册form表单
	 * @param userInfo
	 * @return
	 */
	public static RegisterAddForm formatRegisterForm(NationUserInfo userInfo,String PROVINCE_NAME,
			String CITY_NAME,String DISTRICT_NAME,String SCHOOL_NAME) {
		RegisterAddForm form = new RegisterAddForm();
		form.setTh_uuid(userInfo.getUserId());
		form.setUserName(userInfo.getUserId());
		form.setNickName(userInfo.getName());
		form.setTrueName(userInfo.getName());
		// 性别：0-未知；1-男；2-女
		String gender = userInfo.getGender();
		form.setSex("2".equals(gender) ? true : false);

		form.setProvinceName(PROVINCE_NAME);
		form.setCityName(CITY_NAME);
		form.setArealName(DISTRICT_NAME);
		form.setSchoolName(SCHOOL_NAME);
		form.setRole(ConstantForUserRole.ROLE_SEX_POOL);
		form.setTermName(ConstantForUserRole.DEFAULT_TERMNAME);
		form.setSubjectName(ConstantForUserRole.DEFAULT_SUBJECTMNAME);
		form.setMotto("");
		return form;
	}
	
	/**
	 * 将央馆的信息格式化为注册form表单
	 * @param userInfo
	 * @return
	 */
	public static RegisterAddForm formatRegisterFormWithDefaultTeacherRole(NationUserInfo userInfo,String PROVINCE_NAME,
			String CITY_NAME,String DISTRICT_NAME,String SCHOOL_NAME) {
		RegisterAddForm form = new RegisterAddForm();
		form.setTh_uuid(userInfo.getUserId());
		form.setUserName(userInfo.getUserId());
		form.setNickName(userInfo.getName());
		form.setTrueName(userInfo.getName());
		// 性别：0-未知；1-男；2-女
		String gender = userInfo.getGender();
		form.setSex("2".equals(gender) ? true : false);
		
		form.setProvinceName(PROVINCE_NAME);
		form.setCityName(CITY_NAME);
		form.setArealName(DISTRICT_NAME);
		form.setSchoolName(SCHOOL_NAME);
		form.setRole(ConstantForUserRole.ROLE_TEACHER_DEFAULT);
		form.setTermName(ConstantForUserRole.DEFAULT_TERMNAME);
		form.setSubjectName(ConstantForUserRole.DEFAULT_SUBJECTMNAME);
		form.setMotto("");
		return form;
	}
	
	
	public static void main(String[] args) {
		
		String ticket = "ZEtEM2Y2MmIyMDctZmQ1Zi00NWE5LWEyYjItMDdmZDVmMTVhOWJkMTUwNTc5OTE0NjgxMQ==";
		
		String apiServer = "http://211.153.23.3:40015/";
		
		String accessToken = "";
		
		
		
	}

}
