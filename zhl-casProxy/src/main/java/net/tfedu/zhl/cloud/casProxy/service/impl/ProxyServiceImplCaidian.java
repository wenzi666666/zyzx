package net.tfedu.zhl.cloud.casProxy.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.zns.caidian.dao.EduTeacherMapper;
import net.tfedu.zhl.zns.caidian.model.UserInfo4Caidian;

/**
 
     武昌蔡甸教育局实现方式
  @author wangwr
  @date 2017年07月11日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/  
@Service("proxyCaidian")
public class ProxyServiceImplCaidian  implements ProxyService{
	
	
	Logger log = LoggerFactory.getLogger(ProxyServiceImplCaidian.class);
	

	@Autowired
	EduTeacherMapper mapper;
	
	
	

	@Override
	public RegisterAddForm parseAPI(HttpServletRequest request, ThirdPartyCASConfig config) throws CustomException{
		
		//当前的访问地址，即CAS登录后的回调地址
		String URI = 
				request.getScheme()+"://"+
				request.getServerName()
				+(request.getServerPort() == 80 ?"":(":"+request.getServerPort()))
				+request.getRequestURI();
		
		
		
		String provinceName = "湖北";
		String cityName = "武汉市";
		String districtName = "蔡甸区";
		String Default_schoolName = "蔡甸区测试学校";
		String schoolName = "";
		Long roleId = 10005l;//对接教师，仅6大库
		String default_subjcetName="语文";
		String default_termName="初中";
		
		
		
		System.out.println(request.getRemoteUser());
		System.out.println(request.getUserPrincipal());
		
		
		String loginName= request.getRemoteUser();
		
		UserInfo4Caidian info = mapper.getUserInfo(loginName);
		
		
		
		
		schoolName = StringUtils.isEmpty(info.getSchool_name())?Default_schoolName:info.getSchool_name();
		
		
		RegisterAddForm form = new RegisterAddForm();
		form.setArealName(districtName);
		form.setProvinceName(provinceName);
		form.setSchoolName(schoolName);
		form.setSubjectName(default_subjcetName);
		form.setTermName(info.getTermname()!=null ?info.getTermname():default_termName);
		form.setCityName(cityName);
		form.setBirthDate(null);
		form.setMotto("");
		form.setRole(roleId);
		
		form.setNickName(StringUtils.isEmpty(info.getTrue_name())?"":info.getTrue_name());
		form.setSex(info.getGender()==1?false:true);
		form.setTrueName(StringUtils.isEmpty(info.getTrue_name())?"":info.getTrue_name());
		form.setUserName(info.getUser_name());
		form.setTh_uuid(info.getUuid());
		return form;
	}
	
	

}
