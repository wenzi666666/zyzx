package net.tfedu.zhl.helper;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.sso.user.entity.UserSimple;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache.ValueWrapper;


/**
 * 用户缓存工具类型
 * @author wangwr
 *
 */
public class UserTokenCacheUtil {
	
	/**
	 * 用户信息缓存的命名空间
	 */
	private static final String USERINFO_CACHE_NAMESPACE = "UserSimpleCache"; 
	
	/**
	 * 用户token的缓存前缀
	 */
	private static final String USERTOKEN_CACHE_PREFIX = "USERTOKEN_CACHE_PREFIX_";
	
	

	/**
	 * 获取用户token信息的缓存key
	 * @param userId
	 * @return
	 */
	private static String getUserTokenCacheKey(String userId){
		return USERTOKEN_CACHE_PREFIX+userId;
	}
	
	
	/**
	 * 获取缓存的value
	 * @param token
	 * @return
	 */
	public static ValueWrapper getValueWrapper(CacheManager cacheManager,String token){
		return cacheManager.getCache(USERINFO_CACHE_NAMESPACE).get(token);
	}
	
	
	
	/**
	 * 增加用户的缓存(isRepeatLogin是否允许重复登陆)
	 * @param cacheManager
	 * @param token
	 * @param us
	 * @param isRepeatLogin
	 */
	public static void addUserInfoCache(CacheManager cacheManager,String token,UserSimple us,Boolean isRepeatLogin ){
		
		String userId = us.getUserId().toString();	
		
		Cache cache = cacheManager.getCache(USERINFO_CACHE_NAMESPACE);
		/**
		 * 获取用户token信息的缓存key
		 */
		String user_token_key = getUserTokenCacheKey(userId);
		
		if(!isRepeatLogin){
			clearExistUserInfo(cache,user_token_key);
		}
		
		
		//增加用户缓存
		addUserInfoCache(cache, token, us, user_token_key,isRepeatLogin);
		
	}
	
	
	
	/**
	 * 清理用户的缓存
	 * @param cacheManager
	 * @param token
	 */
	public static void clearUserInfoCache(CacheManager cacheManager,String token){
		
		Cache cache = cacheManager.getCache(USERINFO_CACHE_NAMESPACE);
    	ValueWrapper o = cache.get(token);
        if(o!=null){
        	UserSimple us  = (UserSimple)(o.get());
        	if(us!=null){
                String userId = us.getUserId().toString();
        		String user_token_key = getUserTokenCacheKey(userId);
        		clearExistUserInfo(cache,user_token_key);
        	}
    	}		
	}
	
	
	
	/**
	 * 增加用户缓存
	 * @param cache
	 * @param token
	 * @param us
	 */
	private static void addUserInfoCache(Cache cache,String token,UserSimple us,String user_token_key,Boolean isRepeatLogin){

		//不允许重复登录的话，记录最新的token
		if(!isRepeatLogin){
			cache.put(user_token_key, token);
		}
		cache.put(token, us);
		
	}
	
	
	
	
	
	/**
	 * 如果存在历史纪录，清空
	 * @param user_token_key
	 */
	private static void clearExistUserInfo(Cache cache,String user_token_key){
		ValueWrapper o = cache.get(user_token_key);
		if(o!=null){
        	String token = (String)(o.get());
        	//如果历史存在token
        	if(StringUtils.isNotEmpty(token)){
        		cache.evict(token);
        		cache.evict(user_token_key);
        	}
    	}		
	}	
	
	
	
	
}
