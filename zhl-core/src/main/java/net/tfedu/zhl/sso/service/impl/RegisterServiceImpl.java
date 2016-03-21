package net.tfedu.zhl.sso.service.impl;

import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
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
	
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param password
	 */
	public void modifyRegisterPassword(long userId,String password ){
		SRegister r = new SRegister();
		r.setId(userId);
		r.setPwd(PWDEncrypt.doEncryptByte(password));
		rMapper.updateByPrimaryKey(r);
	}
	
	
	
	
}
