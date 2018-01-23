package net.tfedu.zhl.helper;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
/**
 * 缓存工具类对象
 * copyRight@知好乐教育技术北京有限公司
 * @author 	jiys
 * @date   	2016-9-13
 * @version	v1.0.0
 */
public class CacheUtil {

	protected static Logger log = LoggerFactory
			.getLogger("CacheUtil");
	/***
	 * 登录产品类型: web端资源中心
	 */
	public static final String LOGIN_PRODUCTION_TYPE_WEB = "RESOURCEWEB";
	
	/***
	 * 登录产品类型: e备课客户端
	 */
	public static final String LOGIN_PRODUCTION_TYPE_EPREPARE = "EPREPARE";
	
	
	
	/**
	 * 缓存用户信息的命名空间
	 */
	public static final String USERINFO_CACHE_NAMESPACE = "UserSimpleCache"; 
	
	/**
	 * 缓存用户token的命名空间
	 */
	public static final String Token_CACHE_NAMESPACE = "TokenCache"; 
	
	/**
	 * 缓存被踢出的token的命名空间
	 */
	public static final String kickOutTokenCache_NAMESPACE = "KickOutTokenCache";

	/**
	 * app应用信息缓存，空闲缓存1小时，不活跃缓存1小时
	 */
	public static final String CACHE_APP = "appCache";
	
	/**
	 * 用户token的缓存前缀
	 */
	public static final String USERTOKEN_CACHE_PREFIX = "USERTOKEN_CACHE_PREFIX_";

	
	/**
	 * 获取缓存
	 * @param cacheManager	缓存对象 
	 * @param cacheType		缓存类型
	 * @param cacheKey		缓存key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(CacheManager cacheManager,String cacheType,String cacheKey){
		if(cacheManager==null||StringUtils.isEmpty(cacheType)||StringUtils.isEmpty(cacheKey)){
			return null;
		}
		try {
			ParamsUtil.verifyNullParams(cacheManager,cacheType,cacheKey);
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			//如果参数为空时，返回空值
			return null;
		}
		ValueWrapper o = cacheManager.getCache(cacheType).get(cacheKey);
		if(o==null){
			return null;
		}
		return (T) o.get();
	}

	/**
	 * 添加缓存
	 * @param cacheManager	缓存对象 
	 * @param cacheType		缓存类型
	 * @param cacheKey		缓存key
	 * @param data			缓存value
	 * @return
	 */
	public static boolean put(CacheManager cacheManager,String cacheType,String cacheKey,Object data){
		try {
			ParamsUtil.verifyNullParams(cacheManager,cacheType,cacheKey,data);
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			//添加缓存失败
			return false;
		}
		cacheManager.getCache(cacheType).put(cacheKey,data);
		return true;
	}

	/**
	 * 清空缓存
	 * @param cacheManager	缓存对象 
	 * @param cacheType		缓存类型
	 * @param cacheKey		缓存key
	 * @return
	 */
	public static boolean evict(CacheManager cacheManager,String cacheType,String cacheKey){
		try {
			ParamsUtil.verifyNullParams(cacheManager,cacheType,cacheKey);
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			//添加缓存失败
			return false;
		}
		cacheManager.getCache(cacheType).evict(cacheKey);
		return true;
	}

	/**
	 * 生成缓存key
	 * @param cacheType
	 * @param keys
	 * @return
	 */
	public static String createCacheKey(String cacheType,Object... keys){
		if(keys==null||keys.length<=0){
			return null;
		}
		StringBuffer keyBuffer=new StringBuffer();
		for (Object key : keys) {
			keyBuffer.append(key).append("_");
		}
		keyBuffer.append(cacheType);
		return keyBuffer.toString();
	}

}
