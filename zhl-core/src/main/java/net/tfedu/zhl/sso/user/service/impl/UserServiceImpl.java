package net.tfedu.zhl.sso.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.sso.role.dao.JRoleMapper;
import net.tfedu.zhl.sso.role.entity.JRole;
import net.tfedu.zhl.sso.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.sso.term.dao.JUserTermMapper;
import net.tfedu.zhl.sso.user.dao.JUserMapper;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.user.service.UserService;
import net.tfedu.zhl.sso.users.dao.FuncListMapper;
import net.tfedu.zhl.sso.users.entity.FuncList;

/**
 * 用户业务接口
 * 
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

    @Autowired
    JRoleMapper roleMapper;

    @Autowired
    FuncListMapper funcListMapper;
    
    @Autowired
    CacheManager cacheManager;

    @Override
    public JUser getUserById(long id) {
        return mapper.getUserById(id);
    }

    @Override
    public JUser getUserByName(String name) {
        return mapper.getUserByName(name);
    }

    @Override
    public UserSimple getUserSimpleById(long id, String model) {

        // 1 获取UserSimple
        UserSimple us = mapper.getUserSimpleById(id);

        // 2 获取角色
        List<JRole> roles = roleMapper.getUserRoleByUserId(us.getUserId(), model);
        if (roles == null) {
            String key = String.valueOf(id) + model;
            cacheManager.getCache("UserSimpleCache").put(key, us);
            return us;
        }
        us.setRoles(roles);
        List<Long> roleIds = new ArrayList<Long>();
        for (JRole r : roles) {
            roleIds.add(r.getId());
        }
        // 本身缺省的角色
        roleIds.add(us.getRoleId());

        // 3 获取权限
        List<FuncList> funcs = funcListMapper.getRoleFuncByRoleIds(roleIds, model);
        us.setFuncs(funcs);
        
        //放入缓存
        String key = String.valueOf(id) + model;
        cacheManager.getCache("UserSimpleCache").put(key, us);
        
        return us;
    }

    @Override
    public UserSimple getUserSimpleByName(String name) {
        return mapper.getUserSimpleByName(name);
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
}
