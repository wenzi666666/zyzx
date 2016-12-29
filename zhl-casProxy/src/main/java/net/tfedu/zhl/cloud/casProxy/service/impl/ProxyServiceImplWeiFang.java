package net.tfedu.zhl.cloud.casProxy.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.module.laixiapi.TokenInfo;
import net.tfedu.zhl.cloud.casProxy.module.laixiapi.UserShowInfo;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.cloud.casProxy.service.impl.util.DesZhuCheng;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;

/**
 
     潍坊教育局实现方式
  @author wangwr
  @date 2016年11月16日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/  
@Component
public class ProxyServiceImplWeiFang  implements ProxyService{
	
	
	Logger log = LoggerFactory.getLogger(ProxyServiceImplWeiFang.class);

	@Override
	public RegisterAddForm parseAPI(HttpServletRequest request, ThirdPartyCASConfig config) throws CustomException{
		
		//当前的访问地址，即CAS登录后的回调地址
		String URI = 
				request.getScheme()+"://"+
				request.getServerName()
				+(request.getServerPort() == 80 ?"":(":"+request.getServerPort()))
				+request.getRequestURI();
		
		
		
		String provinceName = "山东";
		String cityName = "潍坊市";
		String districtName = "奎文区";
		String Default_schoolName = "潍坊市测试学校";
		String schoolName = "";
		Long roleId = 10006l;//仅动画教具库
		String default_subjcetName="语文";
		String default_termName="初中";
		
		
		
		//获得url中的加密参数
		String encInfo = request.getParameter("EncInfo"); 
		if(encInfo==null){
			throw new CustomException("加密参数EncInfo缺失");
		}
		
		//解密
		String decryptInfo;
		try {
			decryptInfo = DesZhuCheng.decrypt(encInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("解析加密参数失败");
		} 
		
		
		//拆分参数
		String[] info = decryptInfo.split("\\|"); 
		
		if(StringUtils.isEmpty(info[0])||StringUtils.isEmpty(info[1])||StringUtils.isEmpty(info[2])){
			throw new CustomException("加密参数中存在空值");
		}
		
		
		schoolName = StringUtils.isEmpty(info[0])?Default_schoolName:info[0];  //学校名称
		String userName = info[1];    //用户名
		String trueName = info[2];    //真实姓名
		String nickName = trueName;   //默认：truename
		String gender  = "1";         //默认：男,和资源平台相反
		Boolean sex = ! Boolean.parseBoolean(gender);

		
		String termName = "";         //根据schoolname判断term
		if(schoolName.indexOf("小学") >= 0)
			termName = "小学";
		else if(schoolName.indexOf("初中") >= 0 || schoolName.indexOf("初级中学") >= 0)
			termName = "初中";
		else {
			termName = "高中";
		}
		
		
	
		
		
		RegisterAddForm form = new RegisterAddForm();
		form.setArealName(districtName);
		form.setProvinceName(provinceName);
		form.setSchoolName(schoolName);
		form.setSubjectName(default_subjcetName);
		form.setTermName(default_termName);
		form.setCityName(cityName);
		form.setBirthDate(null);
		form.setMotto("");
		form.setRole(roleId);
		form.setNickName(nickName);
		form.setSex(sex);
		form.setTrueName(trueName);
		form.setUserName(userName);
		form.setTh_uuid(userName);
		return form;
	}
	
	/**
	 * 
	 * 返回获取token的url 
	 * @param code     登录后回调传递的code
	 * @param server   API服务器的前缀
	 * @param URI      当前的回调的URI
	 * @return
	 * @throws CustomException 
	 */
	public String getToken(String code,String server,String URI,ThirdPartyCASConfig config) throws CustomException{
		
		String url = server+"/api/oauth2/access_token?client_id="+config.getTHIRDPARTY_APPID()
					+"&client_secret="+config.getTHIRDPARTY_APPKEY()
					+"&grant_type=authorization_code"
					+"&redirect_uri="+URI+"&code="+code;
		try {
			
			String result =  HttpClientUtils.doGET(url);
			
			TokenInfo info = JSONObject.parseObject(result, TokenInfo.class);
			
			if(null!=info && info.getExpires_in()>0){
				return info.getAccess_token();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * 通过token 获取用户的uid
	 * @param config
	 * @param token  
	 * @return
	 * @throws CustomException 
	 */
	public String getUid(ThirdPartyCASConfig config,String token) throws CustomException{
		
		String url = config.getTHIRDPARTY_API_SERVER()
				+"/api/account/get_uid.json?access_token="+token;
		String result;
		try {
			result = HttpClientUtils.doGET(url);
			Map<String,Object> map = JSONObject.parseObject(result, HashMap.class);

			if(map!=null){
				return  map.get("uid").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}
		
		return null ;
		
	}
	
	/**
	 * 访问api获取用户信息
	 * @param config
	 * @param token
	 * @param uid
	 * @return
	 * @throws CustomException
	 */
	public UserShowInfo getUserinfo(ThirdPartyCASConfig config,String token,String uid)throws CustomException{
		UserShowInfo info = null;
		
		String url = config.getTHIRDPARTY_API_SERVER()
				+"/api/users/show.json?access_token="+token+"&uid="+uid;

		String result;
		try {
			result = HttpClientUtils.doGET(url);
			System.out.println("----getUserinfo------result---"+result);
			info = JSONObject.parseObject(result, UserShowInfo.class);
			if(null!=info){
				return info ;
			}else{
				throw new CustomException("parseAPI:获取用户信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}
		
	}
	
	
	

}
