package net.tfedu.zhl.cloud.casProxy.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.module.laixiapi.TokenInfo;
import net.tfedu.zhl.cloud.casProxy.module.laixiapi.UserShowInfo;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.httpclient.HttpClientUtils;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;

/**
 
      莱西教育局实现方式
  @author wangwr
  @date 2016年11月16日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/  
@Service("proxyService")
public class ProxyServiceImplLaixi  implements ProxyService{
	
	
	Logger log = LoggerFactory.getLogger(ProxyServiceImplLaixi.class);

	@Override
	public RegisterAddForm parseAPI(HttpServletRequest request, ThirdPartyCASConfig config) throws CustomException{
		
		//当前的访问地址，即CAS登录后的回调地址
		String URI = 
				request.getScheme()+"://"+
				request.getServerName()
				+(request.getServerPort() == 80 ?"":(":"+request.getServerPort()))
				+request.getRequestURI();
		
		
		
		String provinceName = "山东";
		String cityName = "青岛市";
		String districtName = "莱西区";
		String Default_schoolName = "莱西区测试学校";
		String schoolName = "";
		Long roleId = 10004l;//莱西对接教师
		String default_subjcetName="语文";
		String default_termName="初中";
		
		
		String code = ControllerHelper.getParameter(request, "code");
		
		String token = getToken(code, config.getTHIRDPARTY_API_SERVER(), URI,config);
		
		if(StringUtils.isEmpty(token )){
			log.error("parseAPI:获取第三方TOKEN失败;token="+token);
			throw new CustomException("parseAPI:获取第三方TOKEN失败");
		}
		
		
		String uid = getUid(config, token);
		if(StringUtils.isEmpty(uid)){
			log.error("parseAPI:获取第三方uid失败;uid="+uid);
			throw new CustomException("parseAPI:获取第三方uid");
		}
		
		UserShowInfo info = getUserinfo(config, token, uid);
		if(null == info || StringUtils.isEmpty(info.getUid())){
			log.error("parseAPI:用户信息失败;用戶信息爲空");
			throw new CustomException("parseAPI:获取用户信息失败");
		}
		
		schoolName = StringUtils.isEmpty(info.getUnit_name())?Default_schoolName:info.getUnit_name();
		
		
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
		form.setNickName(StringUtils.isEmpty(info.getNick_name())?"":info.getNick_name());
		form.setSex(info.getUser_type()==1?false:true);
		form.setTrueName(StringUtils.isEmpty(info.getReal_name())?"":info.getReal_name());
		form.setUserName(info.getUid());
		form.setTh_uuid(info.getUid());
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
