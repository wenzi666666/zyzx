package net.tfedu.zhl.sso.users.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.sso.users.dao.ZWXMRegisterMapper;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.ZWXMRegister;
import net.tfedu.zhl.sso.users.service.ZWXMRegisterService;
import tk.mybatis.mapper.entity.Example;

/**
 
  
    @author wangwr
    @date 2017年12月12日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Service("mRegisterService")
public class ZWXMRegisterServiceImpl extends BaseServiceImpl<ZWXMRegister>
		implements ZWXMRegisterService {

	
	@Autowired
	ZWXMRegisterMapper mapper;
	
	
	@Override
	public Long syncMRegister(Long zhlUserId,String userDefaultPwd,RegisterAddForm form) {
		if(zhlUserId > 0 && null != form){
			
			Example example = new Example(ZWXMRegister.class);
			example.createCriteria().andCondition(" username = '"+form.getUserName()+"'");
			ZWXMRegister c  = null ; 
			List<ZWXMRegister> ls = mapper.selectByExample(example);
			if(null != ls && ls.size()> 0 ){
				c = ls.get(0);
			}
			
			if(null == c){
				
				c  = new ZWXMRegister();
				c.setBindphone("");
				c.setFlag(false);
				c.setFromflag(1);
				c.setFromproject("");
				c.setRegistertime(Calendar.getInstance().getTime());
				c.setThirdpartynumber("");
				c.setType(1);
				c.setUsername(form.getUserName());
				super.insertUseGeneratedKeys(c);
				
				c = mapper.selectByExample(example).get(0);
			}
				
			if(null == c.getUserpwd() || !userDefaultPwd.equals(c.getUserpwd())){
					mapper.updatePwd(c.getId(), userDefaultPwd.getBytes());
			}
			return  Long.valueOf(c.getId().longValue());
		}
		return null;
	}
}
