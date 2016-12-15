package net.tfedu.zhl.sso.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.utils.datatype.IdUtil;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.UserTokenCacheUtil;
import net.tfedu.zhl.helper.ZhlOnlineUtil;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.online.dao.JOnlineUsersMapper;
import net.tfedu.zhl.sso.online.entity.JOnlineUsers;
import net.tfedu.zhl.sso.role.dao.JRoleMapper;
import net.tfedu.zhl.sso.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.sso.term.dao.JUserTermMapper;
import net.tfedu.zhl.sso.th_register.dao.SThirdRegisterRelativeMapper;
import net.tfedu.zhl.sso.th_register.entity.SThirdRegisterRelative;
import net.tfedu.zhl.sso.user.dao.JUserMapper;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.entity.JUserTeachingQueryEntity;
import net.tfedu.zhl.sso.user.entity.UserAreaInfo;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.user.service.JUserService;
import net.tfedu.zhl.sso.users.dao.FuncListMapper;
import net.tfedu.zhl.sso.users.dao.SRegisterMapper;
import net.tfedu.zhl.sso.users.entity.FuncListSimple;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.SRegister;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户业务接口
 * 
 * @author wangwr
 *
 */
@Service("userService")
public class JUserServiceImpl extends BaseServiceImpl<JUser> implements JUserService{

    @Autowired
    private JUserMapper mapper;

    @Autowired
    JUserTermMapper termMapper;

    @Autowired
    SRegisterMapper registerMapper;
    
    
    @Autowired
    JTeacherSubjectMapper subjectMapper;

    @Autowired
    JRoleMapper roleMapper;

    @Autowired
    FuncListMapper funcListMapper;
    
    @Autowired
    CacheManager cacheManager;
    
    //在线状态
    @Autowired
    JOnlineUsersMapper onlineMapper;
    
    /**
     * 第三方对照
     */
	@Autowired
	SThirdRegisterRelativeMapper thRegistermapper;

    
    @Override
    public JUser getUserById(long id) {
        return mapper.getUserById(id);
    }

    @Override
    public JUser getUserByName(String name) {
        return mapper.getUserByName(name);
    }

    @Override
    public UserSimple getUserSimpleById(long id, String model,boolean isRepeatLogin) {

        
        return getUserSimpleById(id, model, isRepeatLogin, true);
    }
    
    
    @Override
	public UserSimple getUserSimpleById(long id, String model, boolean isRepeatLogin, boolean isCache) {
    	 // 1 获取UserSimple
        UserSimple us = getUserSimpleById(id);
        
        //设置从什么产品登录
        us.setModel(model);
        
        // 2 获取角色
        Set<Long> roleIds = roleMapper.getUserRoleByUserId(us.getUserId(), model);
        us.setRoleIds(roleIds);        
        
        // 本身缺省的角色
        roleIds.add(us.getRoleId());

        // 3 获取权限
        List<FuncListSimple> funcs = funcListMapper.getRoleFuncByRoleIds(roleIds, model);

        us.setFuncList(funcs);
        
        //记录状态
        String token = IdUtil.getUUID();
        us.setLogintime(new Date());
        us.setToken(token);

        if(isCache){
            //放入缓存
            UserTokenCacheUtil.addUserInfoCache(model,cacheManager, token, us, isRepeatLogin);
        }
        
        
        //记录登录状态(online、loginTime)
        
        
        
        
        return us;
	}
    
    
    private UserSimple getUserSimpleById(long id){
        // 1 获取UserSimple
        UserSimple us = mapper.getUserSimpleById(id);
        return reSetUserSimpleMale(us) ;
    }
    
    private UserSimple reSetUserSimpleMale(UserSimple us){
    	if(us==null){
    		return us ;
    	}
    	 if("1".equals(us.getMale())){
         	us.setMale("女");
         }else if("0".equals(us.getMale())){
         	us.setMale("男");
         }
    	 return us ;
    }
    
    @Override
    public UserSimple getUserSimpleById(long id, String model) {

        // 1 获取UserSimple
        UserSimple us = getUserSimpleById(id);
        
        //设置从什么产品登录
        //us.setModel(model);

        // 2 获取角色
        Set<Long> roleIds = roleMapper.getUserRoleByUserId(us.getUserId(), model);
        us.setRoleIds(roleIds);        
        
        // 本身缺省的角色
        roleIds.add(us.getRoleId());

        // 3 获取权限
        List<FuncListSimple> funcList = funcListMapper.getRoleFuncByRoleIds(roleIds, model);
        us.setFuncList(funcList);
        return us;
    }
    @Override
    public void logout(String token,boolean isRepeatLogin){
    	UserTokenCacheUtil.clearUserInfoCache(cacheManager, token,isRepeatLogin);
    }

    @Override
    public UserSimple getUserSimpleByName(String name) {
    	
        return  reSetUserSimpleMale(mapper.getUserSimpleByName(name));
    }

    @Override
    public void updateUserInfo(Long userId, String trueName, Boolean male, Long termId, Long subjectId) {

        JUser user = new JUser();
        user.setId(userId);
        user.setTruename(trueName);
        user.setMale(male);
        mapper.updateByPrimaryKeySelective(user);
        if (termId > 0) {
            termMapper.updateUserTerm(userId, termId);
        }
        if (subjectId > 0) {
            subjectMapper.udpateTeacherSubject(userId, subjectId);
            subjectMapper.removeRepeatData(userId);
        }
        
    }

    /**
     * 修改用户头像
     * 
     * @param userId
     * @param userImage
     */
    public void updateUserImage(Long userId, String userImage) {
        if (StringUtils.isNotEmpty(userImage)) {
            mapper.updateUserImage(userId, userImage);
        }
    }

	@Override
	public HashMap<String, String> getUserTermAndSubject(Long userId) {
		return mapper.getUserTermAndSubject(userId);
	}

	@Override
	public ResultJSON queryUserWithRoleAndName(long roleId, String name, String model, int page, int perPage)
			throws Exception {
		PageHelper.startPage(page, perPage);
		
		List<JUserTeachingQueryEntity> list  = mapper.queryUserWithRoleAndName(roleId
										,(StringUtils.isEmpty(name)?null:("%"+name.trim()+"%")), model);
        PageInfo<JUserTeachingQueryEntity> data = new PageInfo<JUserTeachingQueryEntity>(list);
		
        return ResultJSON.getSuccess(data);
	}

	@Override
	public List<Long> getMissUserBetweenJXAndSSO() throws Exception {
		
		return mapper.getMissUserBetweenJXAndSSO();
	}

	@Override
	public void addUser(JUser user) {
		mapper.insertSelective(user);
	}

	@Override
	public UserAreaInfo getUserAreaInfo(long userId) {
		
//		HashMap<String, String>  map = mapper.getUserAreaALLInfo(userId);
		
		return mapper.getUserAreaALLInfo(userId); 
	}

	@Override
	public void addUserLoginStatusWeb(long userId,long registerNodeid, String token, HttpServletRequest request) {
		
		int clientType = ZhlOnlineUtil.ONLINE_CLIENTTYPE_BROWSER;
		//当前时间
		Date currentDate = ControllerHelper.getCurrentDate();
		//ip
		String ip =  ZhlOnlineUtil.getIpAddr(request);
		//设备信息
		String deviceInfo  =ZhlOnlineUtil.getDeviceInfoWeb(request, userId,registerNodeid, clientType);
		// 客户端的版本
		String clientVersion = ZhlOnlineUtil.getClientVersion(request);

		
		//将之前的登录置为 “踢出”状态
		onlineMapper.clearRepeatUserLogin(userId, clientType, registerNodeid);
		
		
		JOnlineUsers record = new JOnlineUsers();
		
		record.setClienttype(clientType);
		record.setClientversion(clientVersion);
		record.setDeviceinfo(deviceInfo);
		record.setFlag(false);
		record.setLastopertime(currentDate);
		record.setLoginip(ip);
		record.setLoginnodeid(1l);
		record.setLogintime(currentDate);
		record.setStatus(ZhlOnlineUtil.ONLINE_STATUS_ONLINE);
		record.setToken(token);
		record.setUserid(userId);
		
		//增加在线记录
		onlineMapper.insertUseGeneratedKeys(record);
		
		//修改user表中的web登录时间
		JUser _record = new JUser();
		_record.setId(userId);
		_record.setWeblogintime(currentDate);
		mapper.updateByPrimaryKeySelective(_record);
		
		
	}

	@Override
	public void updateUserStatutToLogout(long userId, String token) {
		
		log.debug("--service---用户退出-----userId="+userId+"-----token="+token);
		
		//修改在线记录
		onlineMapper.updateOnlineStatus(token, ZhlOnlineUtil.ONLINE_STATUS_LOGOUT);
		
		
		//修改user表中的web退出时间
		JUser _record = new JUser();
		_record.setId(userId);
		_record.setWeblogouttime(ControllerHelper.getCurrentDate());
		mapper.updateByPrimaryKeySelective(_record);
		
	}


}
