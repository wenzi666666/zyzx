package net.tfedu.zhl.sso.role.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.role.entity.JRole;

public interface JRoleMapper extends CoreMapper<JRole> {
    /**
     * 根据用户编号，查询用户所拥有角色 这里能查用户角色对应表和用户组角色对应表
     * 
     * @param userId
     * @return
     */
    Set<Long> getUserRoleByUserId(@Param("userId")Long userId, @Param("model")String model);
}