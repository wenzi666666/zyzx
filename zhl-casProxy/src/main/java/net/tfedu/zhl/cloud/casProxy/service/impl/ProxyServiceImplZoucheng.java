package net.tfedu.zhl.cloud.casProxy.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.casProxy.config.ThirdPartyCASConfig;
import net.tfedu.zhl.cloud.casProxy.service.ProxyService;
import net.tfedu.zhl.cloud.userdata.dao.ZUserdataThirdpartyImportedMapper;
import net.tfedu.zhl.cloud.userdata.entity.ZUserdataThirdpartyImported;
import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.sso.app.dao.SAppMapper;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import tk.mybatis.mapper.entity.Example;

/**
 
  
  	邹城对接
  
  
    @author wangwr
    @date 2017年3月21日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Service("proxyServiceZoucheng")
public class ProxyServiceImplZoucheng implements ProxyService {
	
	
	@Resource
	SAppMapper appMapper;
	
	
	@Resource
	ZUserdataThirdpartyImportedMapper mapper;
	
	
	/**
	 * 默认6大库权限
	 */
	private static long  roleId = 10005l;
	
	
	public RegisterAddForm getRegisterByAppIdAndUserId(String userid, Integer appid) {
		
		Example example = new Example(ZUserdataThirdpartyImported.class);
		
		example.createCriteria().andCondition("appid="+appid).andCondition("username="+userid);
		
		return createReigterAddForm(mapper.selectByExample(example));
	}

	private RegisterAddForm createReigterAddForm(List<ZUserdataThirdpartyImported> selectByExample) {
		ZUserdataThirdpartyImported obj = selectByExample.get(0);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		RegisterAddForm form = new RegisterAddForm();
		form.setArealName(obj.getAreaname());
		form.setBirthDate(obj.getBirthdate()==null?"":format.format(obj.getBirthdate()));
		form.setCityName(obj.getCityname());
		form.setMotto(obj.getMotto());
		form.setNickName(obj.getNickname());
		form.setProvinceName(obj.getProvincename());
		form.setRole(roleId);
		form.setSchoolName(obj.getSchoolname());
		form.setSex(obj.getSex());
		form.setSubjectName(obj.getSubjectname());
		form.setTermName(obj.getTermname());
		form.setTh_uuid(obj.getUuid());
		form.setTrueName(obj.getTruename());
		form.setUserName(obj.getUsername());
		
		return form;
	}

	@Override
	public RegisterAddForm parseAPI(HttpServletRequest request, ThirdPartyCASConfig casConfig) throws CustomException {
		
		String userid = ControllerHelper.getParameter(request, "userid");
		
		if (null == casConfig || StringUtils.isEmpty(casConfig.getZHL_APPID())
				|| StringUtils.isEmpty(casConfig.getTHIRDPARTY_APPID())) {
			throw new CustomException("APP配置信息异常");
		}

		
		return getRegisterByAppIdAndUserId(userid, Integer.parseInt(casConfig.getZHL_APPID()));
	}
	

}
