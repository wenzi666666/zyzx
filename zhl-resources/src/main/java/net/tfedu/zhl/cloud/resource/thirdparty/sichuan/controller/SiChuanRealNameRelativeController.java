package net.tfedu.zhl.cloud.resource.thirdparty.sichuan.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.filter.config.ConfigTools;

import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.config.SiChuanRelativeProperties;
import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.module.CheckResultContent;
import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.module.CheckResultJSON;
import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.module.CheckResultUser;
import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.module.ClassJSON;
import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.module.CourseDetail;
import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.module.InfoDetail;
import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.module.InfoResulltJSON;
import net.tfedu.zhl.cloud.resource.thirdparty.sichuan.util.RealNameAPIUtil;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.UserTokenCacheUtil;
import net.tfedu.zhl.sso.th_register.entity.SThirdRegisterRelative;
import net.tfedu.zhl.sso.th_register.service.SThirdRegisterService;
import net.tfedu.zhl.sso.user.UserImageCheckUtil;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.user.service.JUserService;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 四川省 实名平台 对接 
 * 
 * 
 * @author wangwr
 *
 */
@Controller
public class SiChuanRealNameRelativeController {
	
	@Resource
	private JUserService userService;

	@Resource
	private RegisterService registerService;

	@Resource
	private CommonWebConfig commonWebConfig;
	
	@Resource 
	SThirdRegisterService sThirdRegisterService;

	@Autowired
	CacheManager cacheManager;
	
	/**
	 * 自动注入配置信息
	 */
	@Resource
	SiChuanRelativeProperties siChuanRelativeProperties;
	
	
	Logger logger = LoggerFactory.getLogger(SiChuanRealNameRelativeController.class);
	
	
	private static final String config_error = "四川省实名平台对接配置信息错误，请检查checkUrl或infoUrl";
	
	private static final String check_fail = "四川省实名平台对接校验失败";
	
	private static final String ticket_invalid = "四川省实名平台对接校验,ticket失效";
	
	
	/**
	 * 四川实名平台的平台code
	 */
	private static final String platformCode = "sichuan_realname";
	

	
	/**
	 * 为避免重复对接，使用3.0中的对接url
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/realName_login.action")
	public Object login(HttpServletRequest request,HttpServletResponse response )throws Exception{
		String ticket = request.getParameter("Ticket");
		//获取web.xml中的(四川省 实名网络学习空间平台)认证接口配置
		String checkUrl = siChuanRelativeProperties.getCheckurl_for_realnameplatform();
		//获取web.xml中的(四川省 实名网络学习空间平台)用户信息接口配置
		String infoUrl =  siChuanRelativeProperties.getInfourl_for_realnameplatform();

		
		
		if(StringUtils.isEmpty(checkUrl) ||StringUtils.isEmpty(infoUrl)  ){
			//配置错误
			logger.info(config_error);
			throw new CustomException(config_error);
		}
		
		
		long zhl_userid = 0 ;
		String zhl_username = "" ;
		long roleId = 10001 ;
		boolean male = true ;
		String userName = "";
		String trueName = "";
		String nickName = "";
		String schoolName = "";
		String districtName = "";
		String cityName = "";
		String provinceName = "";
		String subjectNames = "";
		long nowTime = System.currentTimeMillis();
		long termId  =2 ;
		
		//认证接口
		CheckResultJSON json = RealNameAPIUtil.check(ticket, checkUrl,siChuanRelativeProperties);
		if(json!=null){
			
			
			CheckResultContent content = json.getResponse();
			//检查valid
			if(content.getValid()==-1){
				//ticket失效
				logger.info(ticket_invalid);			
				throw new CustomException(ticket_invalid);
			}
			
			CheckResultUser user = content.getContent();
			userName = user.getId();
			trueName = userName;
			nickName = userName;
//			roleId = "26".equals(user.getType())?roleId:"27".equals(user.getType())?roleId:roleId;
			
			
			//获取用户信息
			InfoResulltJSON info =  RealNameAPIUtil.info(ticket, infoUrl,siChuanRelativeProperties);
			if(info.getResponse().getValid()==-1){
				//ticket失效
				logger.info(ticket_invalid);			
				throw new CustomException(ticket_invalid);
			}
			
			
			InfoDetail  detail = info.getResponse().getContent();
			schoolName =  detail.getSchoolOrgName();
			districtName = detail.getDistrictOrgName();
			cityName = detail.getCityOrgName();
			provinceName = detail.getProvinceOrgName();
			

			ArrayList<ClassJSON>list_class = detail.getClassList();
			for (int i = 0 ;i< list_class.size();i++) {
				
				ClassJSON classJSON =list_class.get(i);
				ArrayList<CourseDetail> courseDetail = classJSON.getCourseList();
				for (int j = 0 ;j< courseDetail.size();j++) {
					CourseDetail courseDetail2 = courseDetail.get(j);
					if(null!=courseDetail2.getCoursename()){
						subjectNames +=  ( courseDetail2.getCoursename()+",");
					}
				}
				String section =  classJSON.getSection();//学段（1：小学、2：初中、3:高中
				if(section!=null){
					termId = Long.parseLong(section);
				}
			}
			
			
			//当前username是否已经映射过
			SThirdRegisterRelative  relative =  sThirdRegisterService.getThirdRelativeResult(userName, platformCode);
			//如果已经映射过，前往目标页面
			SRegister register =  null;
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
		    										.append(platformCode).append(i).toString();
		    			
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
		        
					RegisterAddForm form = new RegisterAddForm();
					form.setProvinceName(provinceName);
					form.setCityName(cityName);
					form.setArealName(districtName);
					form.setSchoolName(schoolName);
					form.setMotto("");
					form.setNickName(nickName);
					form.setRole(roleId);
					form.setSex(false);
					form.setSubjectName(subjectNames==null?"语文":subjectNames);
					form.setTermName(termId==1?"小学":termId==2?"初中":termId==3?"高中":"初中");
					form.setTrueName(trueName);
					form.setUserName(zhl_username);
					register =  registerService.addRegister(form, userName, platformCode);
					zhl_userid = register.getId();
				

			}
			// 获取用户信息
			String model= "";
			//获取用户基本信息但不缓存			
			UserSimple	userSimple = userService.getUserSimpleById(zhl_userid, model,commonWebConfig.getIsRepeatLogin(),false);
			//检测用户的头像
			UserImageCheckUtil.checkUserImage(userSimple, commonWebConfig, request);
//			userSimple.setLogoutTarget(JNZXRelativeUtil.url_logout);
			userSimple.setThirdParyCode(platformCode);
			//显示将用户信息写入缓存
			String _token = userSimple.getToken();
			UserTokenCacheUtil.addUserInfoCache(model,cacheManager, _token, userSimple, commonWebConfig.getIsRepeatLogin());
			

			
			
			String frontWebURL = commonWebConfig.getFrontWebURL();
			//组装跳转链接,跳转到资源平台的首页
			String url = new StringBuffer().append(frontWebURL)
					.append("/systemres").append("?tocken=").append(_token)
					.append("&userId=").append(userSimple.getUserId())
					.append("&iscoursewares=").append(platformCode)
					.toString();
			logger.info(platformCode+"---url:"+url);
			response.sendRedirect(url);
			
			
		}else{
			logger.info(config_error);
			logger.info("ticket:"+ticket);
			throw new CustomException(check_fail);
		}
		
		
		
		return null ;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {

		
		System.out.println(ConfigTools.encrypt("root"));
		System.out.println(ConfigTools.encrypt("tfedu123&*()"));
	}
	

}
