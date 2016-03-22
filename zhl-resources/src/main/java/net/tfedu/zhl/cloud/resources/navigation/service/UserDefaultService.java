package net.tfedu.zhl.cloud.resources.navigation.service;

import java.util.HashMap;

import net.tfedu.zhl.cloud.resources.navigation.entity.JUserDefault;

/**
 * 查询用户历史选择的学段、学科、版本、教材
 * @author WeiCuicui
 *
 */
public interface UserDefaultService {

	//查询用户历史选择的学段、学科、版本、教材
	public JUserDefault getUserHistoryDefault(HashMap<String, Object> map);
	
	//增加用户历史选择
	public void addUserHistoryDefault(HashMap<String, Object> map);
}
