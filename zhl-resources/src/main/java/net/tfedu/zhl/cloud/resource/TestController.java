package net.tfedu.zhl.cloud.resource;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import net.sf.ehcache.Ehcache;





/**
 
  
  @author wangwr
  @date 2016年12月6日
  @desc 
  
  copyRight@ 同方知好乐教育科技(北京)有限公司 

*/
public class TestController {

	@Autowired
	CacheManager cacheManager;

	public String cacheDetail(){
		
		
		EhCacheCacheManager _cacheManager = (EhCacheCacheManager)cacheManager;
		
		Collection<String> keys = _cacheManager.getCacheNames();
		
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			
			Cache _cache = _cacheManager.getCache(string);
			
			
			
			Ehcache cache  = (Ehcache)_cache.getNativeCache();
			
			List<String> list =  cache.getKeys();
			
			for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
				String key = (String) iterator2.next();
				System.out.println(key);
				System.out.println(cache.get(key));
			}
			
		}
		
		return  null ;
	}
	
	
}
