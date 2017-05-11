package net.tfedu.zhl.cloud.casProxy.service.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.fileservice.Base64;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;

/**
 
      莱西教育局实现方式
  @author wangwr
  @date 2016年11月16日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/  
@Service("proxyServiceWuChang")
public class ProxyServiceImplWuChang  implements ProxyService{
	
	
	Logger log = LoggerFactory.getLogger(ProxyServiceImplWuChang.class);

	@Override
	public RegisterAddForm parseAPI(HttpServletRequest request, ThirdPartyCASConfig config) throws CustomException{
		
		
		
		String provinceName = "湖北";
		String cityName = "武汉市";
		String districtName = "武昌区";
		String Default_schoolName = "武昌区教育局";
		String schoolName = "";
		Long roleId = 10005L;//莱西对接教师
		String default_subjcetName="语文";
		String default_termName="初中";
		
		
		//接收request中传递的用户uuid和真实姓名
		String uuid = ControllerHelper.getParameter(request, "uuid");
		String name = ControllerHelper.getParameter(request, "name");
		
		try {
			name = new String(Base64.decode(name));
		} catch (IOException e) {
			//如果报错  不做处理
		}
		
		schoolName = Default_schoolName;
		
		
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
		form.setNickName(name);
		form.setSex(false);
		form.setTrueName(name);
		form.setUserName(uuid);
		form.setTh_uuid(uuid);
		return form;
	}
	

}
