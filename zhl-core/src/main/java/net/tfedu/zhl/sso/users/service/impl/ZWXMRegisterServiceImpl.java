package net.tfedu.zhl.sso.users.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.users.dao.ZWXMRegisterMapper;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.ZWXMRegister;
import net.tfedu.zhl.sso.users.service.ZWXMRegisterService;
import net.tfedu.zhl.userlayer.subject.dao.JSubjectMapper;
import net.tfedu.zhl.userlayer.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.userlayer.subject.entity.JSubject;
import net.tfedu.zhl.userlayer.subject.entity.JTeacherSubject;
import net.tfedu.zhl.userlayer.term.dao.JTermMapper;
import net.tfedu.zhl.userlayer.term.dao.JUserTermMapper;
import net.tfedu.zhl.userlayer.term.entity.JTerm;
import net.tfedu.zhl.userlayer.term.entity.JUserTerm;
import net.tfedu.zhl.userlayer.user.dao.JUserMapper;
import net.tfedu.zhl.userlayer.user.entity.JUser;
import net.tfedu.zhl.userlayer.userinfo.dao.JUserInfoMapper;
import net.tfedu.zhl.userlayer.userinfo.entity.JUserInfo;
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
	
	@Autowired
	JUserMapper userMapper;

	@Autowired
	JUserTermMapper userTermMapper;

	@Autowired
	JTeacherSubjectMapper teachSubjectMapper;

	@Autowired
	JUserInfoMapper userInfoMapper;
	

	@Autowired
	JTermMapper termMapper;

	@Autowired
	JSubjectMapper subjectMapper;
	

	@Override
	public Long syncMRegister(RegisterAddForm form, SApp app) throws ParseException {
		// 第三方编码为app中的前缀
		String thirdCode = app.getPrefix();
		// 注册时，增加前缀
		String zhl_username = thirdCode + "_" + form.getUserName();
		form.setUserName(zhl_username);
		long schoolId = 0 ;
		//更新用户真实姓名等信息
		JUser user = userMapper.getUserByName(zhl_username);
		
		//如果没有用户记录表
		if(null == user ){
			
			user = new JUser();
			user.setName(zhl_username);
			user.setTruename(form.getTrueName());
			user.setNickname(form.getNickName());
			user.setFlag(false);
			user.setIsfirstlogin(false);
			user.setMale(form.isSex());
			user.setRoleid(String.valueOf(form.getRole()));
			user.setStatus(0);
			user.setSchoolid(schoolId);

			userMapper.insertSelective(user);

			// userInfoMapper
			JUserInfo userinfo = new JUserInfo();
			userinfo.setUserid(user.getId());
			if (StringUtils.isNotEmpty(form.getBirthDate())) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				userinfo.setBirthdate(format.parse(form.getBirthDate()));
			}
			userinfo.setResume(form.getMotto());
			userinfo.setFlag(false);
			userInfoMapper.insertSelective(userinfo);

			// 学段 学科
			JTerm term = new JTerm();
			term.setName(form.getTermName());
			term = termMapper.selectOne(term);

			if (term != null) {
				JUserTerm userTerm = new JUserTerm();
				userTerm.setUserid(user.getId());
				userTerm.setTermid(term.getId());
				userTermMapper.insertSelective(userTerm);
			}

			JSubject subject = new JSubject();
			subject.setName(form.getSubjectName());
			subject = subjectMapper.selectOne(subject);

			if (subject != null) {
				JTeacherSubject ts = new JTeacherSubject();
				ts.setUserid(user.getId());
				ts.setSubjectid(subject.getId());
				teachSubjectMapper.insertSelective(ts);
			}

		}else if(!form.getTrueName().equals(user.getTruename())
				|| !form.getNickName().equals(user.getNickname())
				|| !user.getRoleid().equals(String.valueOf(form.getRole()))
				|| form.isSex() == user.getMale()
				|| schoolId!= user.getSchoolid()
				){
			long userId =user.getId();
			user = new JUser();
			user.setId(userId);
			user.setTruename(form.getTrueName());
			user.setNickname(form.getNickName());
			user.setMale(form.isSex());
			user.setRoleid(String.valueOf(form.getRole()));
			user.setSchoolid(schoolId);
			userMapper.updateByPrimaryKeySelective(user);
		}
		
		
		return  syncMRegister(user.getId(), app.getUserdefaultpwd(), form);
	}

	

	
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
				c.setFromflag(0);
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
			
			mapper.updateUserVIPEnd(c.getUsername(), c.getRegistertime());
			
			return  Long.valueOf(c.getId().longValue());
		}
		return null;
	}


}
