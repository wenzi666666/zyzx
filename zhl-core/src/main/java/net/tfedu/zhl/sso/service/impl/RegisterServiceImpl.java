package net.tfedu.zhl.sso.service.impl;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.helper.CustomException;
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
	
	

	
	/**
	 * id获取注册用户
	 */
	public SRegister getRegister(Long id){
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
	public void modifyRegisterPassword(Long userId,String password ){
		SRegister r = new SRegister();
		r.setId(userId);
		byte[] pwd = PWDEncrypt.doEncryptByte(password);
		r.setPwd(pwd);
		rMapper.modifyPassword(userId, pwd);
	}
	
	
	
	
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public SRegister login(String userName,String password){
		
		byte[] pwd = PWDEncrypt.doEncryptByte(password);
		SRegister r = new SRegister();
		r.setName(userName);
		r = rMapper.selectOne(r);
		if(r!=null){
			//存在 检测是密码正确
			if(!Arrays.equals(pwd,r.getPwd())){
				throw new RuntimeException(CustomException.WRONGPASSWORD.getCode());
			}
			//存在 检测是否过期
			Date endTime =  r.getReendtime();
			Calendar c = Calendar.getInstance();
			//如果之前已经过期
			if(endTime.before(c.getTime())){
				throw new RuntimeException(CustomException.OUTOFDATE.getCode());
			}
		}else{
			//用户不存在
			throw new RuntimeException(CustomException.WITHOUTUSER.getCode());
		}
		return r;
	}
	
}
