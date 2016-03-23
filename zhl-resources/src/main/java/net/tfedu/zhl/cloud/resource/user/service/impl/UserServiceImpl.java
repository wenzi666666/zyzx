package net.tfedu.zhl.cloud.resource.user.service.impl;

import net.tfedu.zhl.cloud.core.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.cloud.core.term.dao.JUserTermMapper;
import net.tfedu.zhl.cloud.resource.user.dao.JUserMapper;
import net.tfedu.zhl.cloud.resource.user.entity.JUser;
import net.tfedu.zhl.cloud.resource.user.entity.UserSimple;
import net.tfedu.zhl.cloud.resource.user.service.UserService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户业务接口
 * @author wangwr
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	
	
	@Autowired
	private JUserMapper mapper;
	
	
	@Autowired
	JUserTermMapper termMapper; 
	
	@Autowired
	JTeacherSubjectMapper subjectMapper;
	
	@Override
	public JUser getUserById(long id) {
		return mapper.getUserById(id);
	}

	@Override
	public JUser getUserByName(String name) {
		// TODO Auto-generated method stub
		return mapper.getUserByName(name);
	}
	
	
	
	@Override
	public UserSimple getUserSimpleById(long id){
		return mapper.getUserSimpleById(id);
	}
	
	@Override
	public UserSimple getUserSimpleByName(String name){
		return mapper.getUserSimpleByName(name);
	}
	
	
	@Override
	public void updateUserInfo(Long userId, String trueName, Boolean male,
			Long termId, Long subjectId) {
		
		JUser  user = new JUser();
		user.setId(userId);
		user.setTruename(trueName);
		user.setMale(male);
		mapper.updateByPrimaryKeySelective(user);
		if(termId>0){
			termMapper.updateUserTerm(userId, termId);
		}
		if(subjectId>0){
			subjectMapper.udpateTeacherSubject(userId, subjectId);
			subjectMapper.removeRepeatData(userId);
		}
		
	
	}
	
	/**
	 * 修改用户头像
	 * @param userId
	 * @param userImage
	 */
	public void updateUserImage(Long userId,String userImage){
		if(StringUtils.isNotEmpty(userImage)){
			mapper.updateUserImage(userId, userImage);
		}
	}
}
