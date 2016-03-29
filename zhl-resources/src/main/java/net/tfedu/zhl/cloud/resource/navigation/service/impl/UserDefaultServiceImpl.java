package net.tfedu.zhl.cloud.resource.navigation.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.dao.JUserDefaultMapper;
import net.tfedu.zhl.cloud.resource.navigation.entity.JUserDefault;
import net.tfedu.zhl.cloud.resource.navigation.service.UserDefaultService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 查询、增加、更新用户历史选择的学段、学科、版本、教材  的service
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
	
	//更新用户历史选择
	@Override
	public void updateUserHistoryDefault(HashMap<String, Object> map){
		jUserDefaultMapper.updateUserHistoryDefault(map);
	}
	
	//插入 或者  修改用户历史选择
	@Override
	public void updateUserDefault(long userId,int type,String tfcode,String _method){
		
		//封装参数
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("type", type);
		map.put("tfcode", tfcode);
		
		if (StringUtils.isNotEmpty(_method)&& RequestMethod.PATCH.name().equals(_method)) {
			
			// 修改用户历史选择
			updateUserHistoryDefault(map);
			
		} else if (StringUtils.isNotEmpty(_method)&& RequestMethod.POST.name().equals(_method)) {
				
			// 增加用户历史选择
			addUserHistoryDefault(map);
		}
	}
}
