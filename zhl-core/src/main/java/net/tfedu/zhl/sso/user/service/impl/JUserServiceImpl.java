package net.tfedu.zhl.sso.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.utils.datatype.IdUtil;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.UserTokenCacheUtil;
import net.tfedu.zhl.sso.app.entity.SApp;
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


}
