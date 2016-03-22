package net.tfedu.zhl.cloud.resources.navigation.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resources.navigation.dao.JUserDefaultMapper;
import net.tfedu.zhl.cloud.resources.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resources.navigation.service.UserDefaultService;

/**
 * 查询用户历史选择的学段、学科、版本、教材  的service
 * @author WeiCuicui
 *
 */
@Service("userDefaultService")
public class UserDefaultServiceImpl implements UserDefaultService{

	@Resource JUserDefaultMapper jUserDefaultMapper;
    
	@Override
	//查询用户历史选择的学段、学科、版本、教材
	public JUserDefault getUserHistoryDefault(HashMap<String, Object> map){
		return jUserDefaultMapper.getUserHistoryDefault(map);
	}
	
	//增加用户历史选择
	@Override
	public void addUserHistoryDefault(HashMap<String, Object> map){
		jUserDefaultMapper.addUserHistoryDefault(map);
	}
}
