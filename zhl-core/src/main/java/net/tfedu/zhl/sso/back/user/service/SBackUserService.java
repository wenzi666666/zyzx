package net.tfedu.zhl.sso.back.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.CacheManager;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.UserTokenCacheUtil;
import net.tfedu.zhl.sso.back.user.bean.ManagerSimple;
import net.tfedu.zhl.sso.back.user.entity.SBackUser;

/**
 * 管理員管理
 * @author wangwr
 * @date 2017年1月5日
 * @desc 管理员业务
 * 
 *       copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public interface SBackUserService extends BaseService<SBackUser> {

	
	/**
	 * 后台登录
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param productCode
	 *            当前登录的产品编码
	 * @param CacheManager
	 *            缓存管理
	 *            
	 */
	public ResultJSON login(String username, String password,String productCode) throws Exception;
	
	
	/**
	 * 退出登录
	 * @param token
	 * @param userId
	 * @param isRepeatLogin
	 * @throws Exception
	 */
	public void logout(String token,long userId,boolean isRepeatLogin)throws Exception;
	
	
	
	/**
	 * 增加 管理员的登录状态
	 * @param userId
	 * @param registerNodeid
	 * @param token
	 * @param request
	 * @throws Exception
	 */
	public void addUserLoginStatusWeb(long userId,long registerNodeid, String token, HttpServletRequest request) throws Exception;

	
	/**
	 * 根据id获取用户登录信息权限信息
	 * @param id     用户id
	 * @param model 
	 * @param isRepeatLogin  是否重复登录
	 * @param productCode    登录产品
	 * @param cacheManager  
	 * @return
	 * @throws Exception
	 */
    public ManagerSimple getManagerSimpleById(long id, String model,boolean isRepeatLogin,String productCode)throws Exception;
	
	
}
