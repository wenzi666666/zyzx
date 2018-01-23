package net.tfedu.zhl.cloud.casProxy.data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import net.tfedu.zhl.cloud.casProxy.model.RegisterAddFormTp;
import net.tfedu.zhl.cloud.casProxy.model.ResultDataTp;
import net.tfedu.zhl.cloud.casProxy.util.SignVerify;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.MD5;
import net.tfedu.zhl.helper.httpclient.APIForm;
import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.area.dao.AreaMapper;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 获取第三方基础数据 copyRight@知好乐教育技术北京有限公司
 * 
 * @author jiys
 * @date 2016-11-23
 * @version v1.0.0
 */
public class BaseData {

	/**
	 * 用户对象转换器
	 * 
	 * @param userinfo
	 * @param areaMapper
	 * @return
	 */
	public RegisterAddForm RegisterAddFormConversion(RegisterAddFormTp userinfo,AreaMapper areaMapper) {
		RegisterAddForm form = new RegisterAddForm();
		form.setArealName(userinfo.getAreaname());
		form.setBirthDate(userinfo.getBirthdate());
		String code=userinfo.getAreacode();
		String cityName="";
		String provinceName="";
		if(StringUtils.isNotEmpty(code)){
			Map<String, String> cityMap=areaMapper.queryAreaParent(code);
			cityName=cityMap!=null?cityMap.get("name"):cityName;
			String cityCode=cityMap!=null?cityMap.get("code"):"";
			if(StringUtils.isNotEmpty(cityCode)){
				Map<String, String> provinceMap=areaMapper.queryAreaParent(cityCode);
				provinceName=provinceMap!=null?provinceMap.get("name"):provinceName;
			}
		}
		
		form.setCityName(cityName);
		form.setProvinceName(provinceName);
		form.setMotto(StringUtils.isEmpty(userinfo.getMotto())?"":userinfo.getMotto());
		form.setNickName(StringUtils.isEmpty(userinfo.getNickname())?"":userinfo.getNickname());
		String rolename = userinfo.getRolename();
		// 根据角色名称转换成角色id
		long roleId = roleConversion(rolename);
		form.setRole(roleId);
		form.setSchoolName(userinfo.getSchoolname());
		// 性别
		String sexStr = userinfo.getSex();
		form.setSex(sexConversion(sexStr));
		String subjectname=StringUtils.isEmpty(userinfo.getSubjectname())?"":userinfo.getSubjectname();
		String termname=StringUtils.isEmpty(userinfo.getTermname())?"":userinfo.getTermname();
		if(StringUtils.isEmpty(subjectname)){
			subjectname="语文";
		}
		if(StringUtils.isEmpty(termname)){
			termname="初中";
		}
		form.setSubjectName(subjectname);
		form.setTermName(termname);
		form.setTh_uuid(userinfo.getUuid());
		form.setTrueName(userinfo.getTruename());
		form.setUserName(userinfo.getUsername());
		return form;
	}

	/**
	 * 根据角色名称转换成角色id
	 * 
	 * @param rolename
	 * @return
	 */
	public long roleConversion(String rolename) {
		long roleId = 0;
		if (StringUtils.equalsIgnoreCase(rolename, "老师")
				|| StringUtils.equalsIgnoreCase(rolename, "教师")) {
			roleId = 2;
		} else if (StringUtils.equalsIgnoreCase(rolename, "学生")) {
			roleId = 1;
		}
		return roleId;
	}

	/**
	 * 性别转换器
	 * 
	 * @param sexStr
	 * @return
	 */
	public boolean sexConversion(String sexStr) {
		boolean sex = false;
		if (StringUtils.equalsIgnoreCase(sexStr, "女")
				|| StringUtils.equalsIgnoreCase(sexStr, "女生")) {
			sex = true;
		}
		return sex;
	}

	/**
	 * 获取第三方基础数据
	 * 
	 * @param app
	 * @param userid
	 * @return
	 * @throws CustomException
	 */
	public RegisterAddFormTp getBaseDataTp(SApp app, String userid)
			throws CustomException {
		Integer appid = app.getAppid();
		String appkey = app.getAppkey();
		String sign = SignVerify.createSignVerify(userid, appid, appkey);
		String thirdpartybasedataurl = app.getThirdpartybasedataurl();
		APIForm form = new APIForm();
		form.setUrl(thirdpartybasedataurl);
		HashMap<String, String> request_param1 = new HashMap<String, String>();
		request_param1.put("id", userid);
		request_param1.put("uid", userid);
		request_param1.put("appid", appid + "");
		request_param1.put("sign", sign);
		form.getRequest_params().add(request_param1);
		String result;
		try {
			result = HttpClientUtils.doGet(form);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new CustomException("ClientProtocolException", "获取基础信息链接中断");
		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomException("IOException", "获取基础信息网络异常");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new CustomException("IOException", "获取基础信息网络异常");
		}
		
		if(result.indexOf("\\\"")>0){
			result = result.replace("\\\"", "\"");
		}
		if(result.startsWith("\"")){
			result = result.substring(1, result.length()-1);
		}
		
		
		ResultDataTp resultDataTp = JSONObject.parseObject(result,
				ResultDataTp.class);
		// 如果获取信息为空或者获取状态失败，返回
		if (resultDataTp == null) {
			throw new CustomException("NotGetToBaseData", "没有获取到用户基础信息");
		}
		if (!StringUtils.equalsIgnoreCase(resultDataTp.getCode(), "ok")) {
			throw new CustomException(resultDataTp.getCode(),
					resultDataTp.getMessage());
		}
		RegisterAddFormTp userinfo = resultDataTp.getUserinfo();
		String signResult=resultDataTp.getSign();
		String uuid=userinfo.getUuid();
		String timestamp=resultDataTp.getTimestamp();
		String signResultLocal=MD5.getMD5Str(uuid+ SignVerify.separator+ timestamp + SignVerify.separator+appid+ SignVerify.separator+appkey);
		if (!StringUtils.equalsIgnoreCase(signResult, signResultLocal)) {
			throw new CustomException("BaseDataIllegal","基础数据返回信息不合法");
		}
		
		return userinfo;
	}
	
	
	public static void main(String[] args) {
		String result = "{\"userinfo\":{\"uuid\":\"33031\",\"username\":\"test001\",\"truename\":\"test001\",\"sex\":\"女\",\"rolename\":\"教师\",\"termname\":\"\",\"areacode\":\"610103\",\"areaname\":\"碑林区\",\"schoolname\":\"\"},\"timestamp\":\"1493114548\",\"sign\":\"57b69f71ec1d19dcad6f5fca5cb445e9\",\"code\":\"ok\",\"message\":\"成功\"}";
		
		
//		result = JSONUtils.parse(result);
		
		
		ResultDataTp resultDataTp = JSONObject.parseObject(result,
				ResultDataTp.class);
		
		
		System.out.println(resultDataTp!=null && resultDataTp.getCode().equalsIgnoreCase("ok"));
		System.out.println(resultDataTp);
		
	}

}
