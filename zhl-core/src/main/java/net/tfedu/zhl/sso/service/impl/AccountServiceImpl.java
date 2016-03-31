package net.tfedu.zhl.sso.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.sso.dao.SysReourceMapper;
import net.tfedu.zhl.sso.dao.UserMapper;
import net.tfedu.zhl.sso.dao.UserRoleMapper;
import net.tfedu.zhl.sso.entity.SysResource;
import net.tfedu.zhl.sso.entity.User;
import net.tfedu.zhl.sso.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource
    UserMapper userMapper;
    @Resource
    UserRoleMapper userRoleMapper;
    @Resource
    CacheManager cacheManager;
    @Autowired
    private SysReourceMapper sysReourceMapper;

    @Cacheable(value = "usernamesCache", key = "#username")
    @Override
    public User getUserByUserName(String username) {
        // 查用户
        User user = userMapper.getUserByName(username);
        return user;
    }

    /**
     * 业务类常用。
     */
    @Override
    public User getUserById(int userId) {
        Object object = cacheManager.getCache("userIdsCache").get(userId);
        if (object != null) {
            return getUserByUserName(object.toString());
        } else {
            User simple = userMapper.selectByPrimaryKey(userId);
            if (simple == null) {
                return null;
            }
            // 手动缓存，只缓存id，name，节约内存
            cacheManager.getCache("userIdsCache").put(userId, simple.getUsername());
            return getUserByUserName(simple.getUsername());
        }
    }

}
