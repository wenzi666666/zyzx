package net.tfedu.zhl.cloud.core.online.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.core.online.dao.JOnlineUsersMapper;
import net.tfedu.zhl.cloud.core.online.entity.JOnlineUsers;
import net.tfedu.zhl.cloud.core.online.service.JOnlineUsersService;


@Service("jOnlineUsersService")
public class JOnlineUsersServiceImpl implements JOnlineUsersService {
	
	
	@Autowired
	JOnlineUsersMapper mapper;
	

	@Override
	public JOnlineUsers getUserOnlines(Long userId) {
		// TODO Auto-generated method stub
		return mapper.getOnlineByUserId(userId);
	}

	@Override
	public void addUserOnline(JOnlineUsers user) {
		mapper.insert(user);
	}

	@Override
	public void updateOnlineStatus(String token, Long status) {
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

}
