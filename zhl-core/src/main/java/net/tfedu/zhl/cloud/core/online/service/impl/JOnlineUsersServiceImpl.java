package net.tfedu.zhl.cloud.core.online.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.core.online.dao.JOnlineUsersMapper;
import net.tfedu.zhl.cloud.core.online.entity.JOnlineUsers;
import net.tfedu.zhl.cloud.core.online.service.JOnlineUsersService;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ZhlOnlineUtil;
import net.tfedu.zhl.sso.dao.SRegisterMapper;
import net.tfedu.zhl.sso.entity.SRegister;


@Service("jOnlineUsersService")
public class JOnlineUsersServiceImpl implements JOnlineUsersService {
	
	
	@Autowired
	JOnlineUsersMapper mapper;
	

	@Autowired
	SRegisterMapper registerMapper;
	
	
	@Override
	public JOnlineUsers getUserOnlines(Long userId,HttpServletRequest request) {
		//排除超时
		mapper.setTimeOut();
		
		//1 web 2 android 3 winpad 客户端类型
		int clientType = 1;
		//token的有效时间
		int validTime =ZhlOnlineUtil.getTokenValidTime(request) ;
		//查询已有的有效的token 
		JOnlineUsers user  = mapper.getOnlineByUserId(userId,validTime);
		//没有则新建并返回
		if(user==null){
			SRegister register =  registerMapper.selectByPrimaryKey(userId);
			//资源中心没有多级部署的情况： 使用注册站点作为登录站点
			long registerNodeId = register.getNodeid();
			//token 由UUID 生成
			String token = UUID.randomUUID().toString();
			//从工具类中获取ip
			String loginIP = ZhlOnlineUtil.getIpAddr(request);
			//获取设备信息
			String deviceInfo = ZhlOnlineUtil.getDeviceInfoWeb(request, userId, registerNodeId,clientType);
			
			Date currentDate = Calendar.getInstance().getTime();
			
			user = new JOnlineUsers();
			user.setUserid(userId);
			user.setClienttype(clientType);
			user.setClientversion(request.getHeader("user-agent"));
			user.setDeviceinfo(deviceInfo);
			user.setLoginip(loginIP);
			user.setLoginnodeid(registerNodeId);
			user.setLogintime(currentDate);
			user.setLastopertime(currentDate);
			user.setFlag(true);
			user.setStatus(ZhlOnlineUtil.ONLINE_STATUS_ONLINE);
			user.setToken(token);
			mapper.insert(user);
		}
		return user;
	}

	@Override
	public void addUserOnline(JOnlineUsers user) {
		mapper.insert(user);
	}

	@Override
	public void updateOnlineStatus(String token, Integer status) {
		mapper.updateOnlineStatus(token, status);
	}

	@Override
	public void updateLastOperTime(String token, Date date) {
		if(date == null){
			Calendar c = Calendar.getInstance();
			date = c.getTime();
		}
		mapper.updateLastOperTime(token, date);
	}
	
	
	@Override
	public void logout(String token){
		updateOnlineStatus(token, ZhlOnlineUtil.ONLINE_STATUS_LOGOUT);
	}

	@Override
	public JOnlineUsers getUserOnlinesByToken(String token) {

		JOnlineUsers obj = mapper.getOnlineByToken(token);
		if(obj==null){
			throw new RuntimeException(CustomException.INVALIDACCESSTOKEN.getCode());
		}
		return obj;
	}

}
