package net.tfedu.zhl.sso.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.online.dao.JOnlineUsersMapper;
import net.tfedu.zhl.cloud.online.entity.JOnlineUsers;
import net.tfedu.zhl.cloud.utils.datatype.IdUtil;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.UserTokenCacheUtil;
import net.tfedu.zhl.helper.ZhlOnlineUtil;
import net.tfedu.zhl.sso.back.user.entity.SBackUserScope;
import net.tfedu.zhl.sso.role.dao.JRoleMapper;
import net.tfedu.zhl.sso.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.sso.term.dao.JUserTermMapper;
import net.tfedu.zhl.sso.th_register.dao.SThirdRegisterRelativeMapper;
import net.tfedu.zhl.sso.user.dao.JUserMapper;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.entity.JUserTeachingQueryEntity;
import net.tfedu.zhl.sso.user.entity.UserAreaInfo;
import net.tfedu.zhl.sso.user.entity.UserEditForm;
import net.tfedu.zhl.sso.user.entity.UserQueryForm;
import net.tfedu.zhl.sso.user.entity.UserQueryResult;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.user.service.JUserService;
import net.tfedu.zhl.sso.users.dao.FuncListMapper;
import net.tfedu.zhl.sso.users.dao.SRegisterMapper;
import net.tfedu.zhl.sso.users.entity.FuncListSimple;

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
    
    
    @Override
	public UserSimple getUserSimpleByIdForThirdParty(long id, String model, boolean isRepeatLogin, String logoutUrl,
			String thirdPartyCode) {

    	UserSimple user = getUserSimpleById(id, "", isRepeatLogin,false);

		//设置退出url 和  对接产品的code
		user.setLogoutTarget(null==logoutUrl?"":logoutUrl);
		user.setThirdParyCode(thirdPartyCode);
		
		//写入缓存
        UserTokenCacheUtil.addUserInfoCache(model,cacheManager, user.getToken(), user, isRepeatLogin);

    	return user;
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
    
    
    @Override
	public void updateUserInfo(UserEditForm form) {
    	 JUser user = new JUser();
         user.setId(form.getId());
         user.setTruename(form.getTrueName());
         user.setNickname( StringUtils.isEmpty(form.getNickName())?form.getTrueName():form.getNickName());

         if(form.getMale()!=null){
        	 user.setMale(form.getMale());
         }
         if(form.getSchoolId()!=null && form.getSchoolId()>0){
             user.setSchoolid(form.getSchoolId());
         }
         
         mapper.updateByPrimaryKeySelective(user);
         if (form.getTermId() > 0) {
             termMapper.updateUserTerm(form.getId(), form.getTermId());
         }
         if (form.getSubjectId()>0){
             subjectMapper.udpateTeacherSubject(form.getId(), form.getSubjectId());
             subjectMapper.removeRepeatData(form.getId());
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

	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	@Override
	public PageInfo<UserQueryResult> queryUserByForm(List<SBackUserScope> scopeList,UserQueryForm form, int pageNum, int pageSize) throws Exception {
		
		
		//根据管理范围划分管理员有3种，全局的管理员、区级管理员、校级管理员。一个管理员只能是其中一种
		//1全国 2省 3市 4区 5校
		
		Long[] districtIds = new Long[]{};
		Long[] schoolIds = new Long[]{};
		
		Long[] cityIds = new Long[]{};
		Long[] provinceIds = new Long[]{};
		
		//整备查询范围的数组
		if(scopeList!=null){
			
			for (Iterator<SBackUserScope> iterator = scopeList.iterator(); iterator.hasNext();) {
				SBackUserScope sBackUserScope = (SBackUserScope) iterator.next();
				switch(Integer.parseInt(sBackUserScope.getScope())){
				case 5 : 
					ArrayUtils.add(schoolIds, sBackUserScope.getSchoolid());
					break;
				case 4 : 
					ArrayUtils.add(districtIds, sBackUserScope.getDistrictid());
					break;
				case 3 : 
					ArrayUtils.add(cityIds, sBackUserScope.getCityid());
					break;
				case 2 :
					ArrayUtils.add(provinceIds, sBackUserScope.getProvinceid());
					break;
				case 1 : 
				default: 
					break;
					
				}
				
			}
		}
		
		//处理查询关键字，当关键字不为空时，增加 ‘%%’
		if(null!=form.getKeyword() && StringUtils.isNotEmpty(form.getKeyword())){
			form.setKeyword("%"+form.getKeyword()+"%");
		}
		
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageNum, pageSize);
		List<UserQueryResult> list = mapper.queryUserByForm(form, provinceIds, cityIds, districtIds, schoolIds);
		return  new PageInfo((Page<UserQueryResult>)list) ;
	}

	
	


}
