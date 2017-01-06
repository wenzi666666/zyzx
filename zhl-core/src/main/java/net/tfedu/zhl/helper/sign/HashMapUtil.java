package net.tfedu.zhl.helper.sign;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * HashMapUtil工具类
 * 重写HashMap
 * copyRight@知好乐教育技术北京有限公司
 * @author 	jiys
 * @date   	2016-9-13
 * @version	v1.0.0
 */
@SuppressWarnings("serial")
public class HashMapUtil<K,V> extends HashMap<K, V> implements Map<K, V> {

	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, String> sortMap = new TreeMap<String, String>(
				new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}

	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static Map<String, Object> sortMapByKeyValueObj(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, Object> sortMap = new TreeMap<String, Object>(
				new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}
	
	
	
}
