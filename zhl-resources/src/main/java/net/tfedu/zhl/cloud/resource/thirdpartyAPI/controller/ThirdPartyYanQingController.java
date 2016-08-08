package net.tfedu.zhl.cloud.resource.thirdpartyAPI.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.config.ResourceThirdPartyConfig;
import net.tfedu.zhl.cloud.resource.thirdpartyAPI.jnzx.entity.ZXUserInfoResult;
import net.tfedu.zhl.cloud.resource.thirdpartyAPI.jnzx.util.JNZXRelativeUtil;
import net.tfedu.zhl.cloud.resource.thirdpartyAPI.util.YanQingUtil;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.UserTokenCacheUtil;
import net.tfedu.zhl.sso.th_register.entity.SThirdRegisterRelative;
import net.tfedu.zhl.sso.th_register.service.SThirdRegisterService;
import net.tfedu.zhl.sso.user.UserImageCheckUtil;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.user.service.UserService;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 延庆信息中心对接
 * @author wangwr
 *
 *
 *
 *
 *
 */
@Controller
@RequestMapping("/*RestAPI/thirdparty")
public class ThirdPartyYanQingController {
	
	
	Logger log = LoggerFactory.getLogger(ThirdPartyYanQingController.class);
	
	@Resource
	private UserService userService;

	@Resource
	private RegisterService registerService;

	@Resource
	private CommonWebConfig commonWebConfig;
	
	@Resource 
	SThirdRegisterService sThirdRegisterService;

	@Autowired
	CacheManager cacheManager;
	
	
	@Resource
	ResourceThirdPartyConfig resourceThirdPartyConfig;
	
	
	/**
	 * 延庆资源中心对接时的默认的有效用户的角色
	 */
	private static final long default_teacher_roleId = 2;
	/**
	 * 对接第三方平台的标示
	 */
	private static final String platformcode = "yanqing";
	/**
	 * 延庆平台不合法用户过来时的错误提示
	 */
	private static final String default_role_error = "本应用暂时只支持教师用户访问"; 
	
	/**
	 *用户已经退出登录的提示
	 */
	private static final String logout_error = "当前用户已经退出登录的"; 

	/**
	 * 默认北京
	 */
	private static final String default_province = "北京";
	/**
	 * 默认北京市
	 */
	private static final String default_city = "北京市";
	/**
	 * 默认延庆县
	 */
	private static final String default_district = "延庆县";
	/**
	 * 默认延庆信息中心
	 */
	private static final String default_school = "延庆信息中心";
	/**
	 * 默认学段
	 */
	private static final String default_term = "初中";
	/**
	 * 默认学科
	 */
	private static final String default_subject = "语文";
	
	
	
	@RequestMapping(value="/v1.0/login/yq/")	
	public Object login(HttpServletRequest request ,HttpServletResponse response
			) throws ClientProtocolException, IOException,Exception{
		
		//第三方系统中的用户名
		String userName = null;
		//zhl系统中的用户名
		String zhl_username = null ;
		//zhl系统中的用户id
		long zhl_userid = 0 ;
		//
		long roleId = 0;

		
		String sessid = ControllerHelper.getOptionalParameter(request, YanQingUtil.USER_SESSID);
		//判断是否为空
		if(!StringUtils.isEmpty(sessid)){
			
			String user_id = YanQingUtil.getUserId(resourceThirdPartyConfig, sessid);
			
			log.debug("--sessid--"+sessid);
			HashMap<String,Object> userInfo = YanQingUtil.getUserInfo(resourceThirdPartyConfig, user_id);
			if(null == userInfo){
				response.getWriter().println(logout_error);
				return null ;
			}

			log.debug("--host--"+resourceThirdPartyConfig.getYq_host());
			log.debug("--userInfo--"+userInfo.toString());

			userName = user_id;
			//分组
			Integer group_id = (Integer)userInfo.get("group_id");
			//用户姓名
			String user_name = (String)userInfo.get("user_name");
			//昵称
			String nickname= (String)userInfo.get("nickname");
			//生日，格式为：yyyy-mm-dd
			String birthday= (String)userInfo.get("birthday");
			if(!StringUtils.isEmpty(birthday)){
				birthday += " 00:00:00";
			}
			
			//性别 值为“男”或“女”
			String gender= (String)userInfo.get("gender");
			//邮箱
			String email= (String)userInfo.get("email");
			//用户类型c用户身份代码：有 5 位，从左往右，每一位代表一种身份，分别表示“一般注册用户”、“行政管理人员”、“教职员工”、“学生”、“家长”，对应位值为 1 时表示用户具有该身份
			String user_type= (String)userInfo.get("user_type");
			String extend_info= (String)userInfo.get("extend_info");
			
			//判断是否为  教职员工
			if("1".equals(String.valueOf(user_type.charAt(2)))){
				
				roleId = default_teacher_roleId;
				
				HashMap extend_temp = JSONObject.parseObject(extend_info, HashMap.class);
				String school =  (String)extend_temp.get("k12_school");
				
				
				//当前username是否已经映射过
				SThirdRegisterRelative  relative =  sThirdRegisterService.getThirdRelativeResult(userName, platformcode);
				SRegister register =  null;
				//如果已经注册过
				if(relative!=null && relative.getId()>0){
					
					zhl_userid = Long.parseLong(relative.getZhlUserid());
				}else{
			        register =  registerService.getRegister(userName);
			        if(register !=null && register.getId()>0){
			    		//如果用户存在 ，修正用户名(在原有的用户名后面增加 “_”和platformcode和序号),如果还有重复，增加序号即：username_jnzx1、username_jnzx2
			    		int i=0;
			    		boolean breakFlag = true ;
			    		while(breakFlag){
			    			zhl_username = new StringBuffer().append(userName).append("_")
			    										.append(platformcode).append(i).toString();
			    			
			    			register =  registerService.getRegister(zhl_username);
			    			//可用的用户名
			    			if(register ==null || register.getId()<1){
			    				breakFlag = false ;
			    			}
			    			i++;
			    		}
			    	}else{
			    		zhl_username = userName;
			    	}
			        
			        //组装注册表单
			        RegisterAddForm form = new RegisterAddForm();
			        form.setUserName(zhl_username);
			        form.setNickName(nickname);
			        form.setTrueName(user_name);
			        form.setSex("男".equals(gender)?false:true);

			        form.setBirthDate(birthday);
			        form.setMotto("");
			        form.setRole(roleId);
			        
			        form.setTermName(default_term);
			        form.setSubjectName(default_subject);
			        
			        //地区信息
			        form.setProvinceName(default_province);
			        form.setCityName(default_city);
			        form.setArealName(default_district);
			        form.setSchoolName(StringUtils.isEmpty(school)?default_school:school);
			        
		    		register =  YanQingUtil.registNewUserByForm(form, registerService,userName, platformcode);
		    		zhl_userid =  register.getId();
				}
				
				// 获取用户信息
				String model= "";
				//获取用户基本信息但不缓存			
				UserSimple	user = userService.getUserSimpleById(zhl_userid, model,commonWebConfig.getIsRepeatLogin(),false);
				//检测用户的头像
				UserImageCheckUtil.checkUserImage(user, commonWebConfig, request);
				//退出时 退到第三方的登录页面
				user.setLogoutTarget(resourceThirdPartyConfig.getYq_logWeb());
				user.setThirdParyCode(platformcode);
				//显示将用户信息写入缓存
				String _token = user.getToken();
				UserTokenCacheUtil.addUserInfoCache(model,cacheManager, _token, user, commonWebConfig.getIsRepeatLogin());

				String frontWebURL = commonWebConfig.getFrontWebURL();
				//组装跳转链接
				String url = new StringBuffer().append(frontWebURL)
						.append("/systemres").append("?tocken=").append(_token)
						.append("&userId=").append(user.getUserId())
						.append("&iscoursewares=").append(platformcode)
						.toString();
				log.info("yanqingRes---url:"+url);
				response.sendRedirect(url);
				return null ;
			}
		}
		response.getWriter().println(default_role_error);
		return null ;
		
	}
	

	
	public static void main(String[] args) throws ParseException {
//		String type = "10100";
//		System.out.println(type.substring(2, 3));
//		System.out.println("1".equals(String.valueOf(type.charAt(2))));
		
		
		String time = "1996-02-01";
		SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = formt.parse(time);

		
		
		
		System.out.println(date.getTime());
		System.out.println(formt.format(date));
	}
}
