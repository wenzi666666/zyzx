package net.tfedu.zhl.sso.service.impl;

import net.tfedu.zhl.sso.dao.SCardMapper;
import net.tfedu.zhl.sso.dao.SRegisterMapper;
import net.tfedu.zhl.sso.entity.SRegister;
import net.tfedu.zhl.sso.service.RegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 注册表service
 * @author wangwr
 *
 */
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

	@Autowired 
	SRegisterMapper rMapper;
	
	@Autowired 
	SCardMapper cMapper;
	
	
	/**
	 * id获取注册用户
	 */
	public SRegister getRegister(long id){
		SRegister r = new SRegister();
		r.setId(id);
		return rMapper.selectOne(r);
	}
	/**
	 * name获取注册用户
	 */
	public SRegister getRegister(String userName){		
		SRegister r = new SRegister();
		r.setName(userName);
		return rMapper.selectOne(r);
	}
	
	public SRegister login(String userName,String password){
		
		
		
		
		return null;
	}
	
	
	
	
}
