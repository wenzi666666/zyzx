package net.tfedu.zhl.sso.back.online.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.back.online.entity.SOnlineManager;

public interface SOnlineManagerMapper extends CoreMapper<SOnlineManager> {
	
    /**
     * 根据token获取online记录
     * 
     * @param token
     * @param validTime
     * @return
     */
    public SOnlineManager getOnlineByToken(@Param("token")String token, @Param("validTime")Integer validTime);

    /**
     * 踢出相同方式的用户登录((同一个用户、同一种登录client、同一个站点))
     * 
     * @param userId
     * @param clientType
     * @param nodeId
     */
    public void clearRepeatUserLogin(Long userId, Integer clientType, Long nodeId);

    /**
     * 根据userId 获取当前的登录用户的有效的在线信息
     * 
     * @param id
     * @return
     */
    public SOnlineManager getOnlineByUserId(Long userId, Integer validTime);

    /**
     * 修改用户在线状态
     * 
     * @param token
     * @param status
     */
    public void updateOnlineStatus(String token, Integer status);

    /**
     * 根据token设置用户的最后操作时间
     * 
     * @param token
     */
    public void updateLastOperTime(String token, Date date);

    /**
     * 将超时的用户在线记录的状态设为超时
     */
    public void setTimeOut();

	
}