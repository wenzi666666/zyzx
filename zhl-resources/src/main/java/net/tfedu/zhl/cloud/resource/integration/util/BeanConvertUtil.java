package net.tfedu.zhl.cloud.resource.integration.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;

/**
 * 
 * 类型转换工具类
 * 
 * @author wangwr
 * @date 2017年8月8日
 * @desc
 * 
 * 		copyRight@ 同方知好乐教育科技(北京)有限公司
 */
public class BeanConvertUtil {

	/**
	 * 将list中的Bean转换为Map,并要求所有的属性名小写
	 * 
	 * @param list
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException 
	 */
	public static <T> List<Map<String, Object>> toListMapLowerCase(List<T> list)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return toListMap(list, true);
	}

	/**
	 * 将list中的Bean转换为Map,并要求所有的属性名大写
	 * 
	 * @param list
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException 
	 */
	public static <T> List<Map<String, Object>> toListMapUpperCase(List<T> list)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return toListMap(list, false);
	}

	/**
	 * 将list中的Bean转换为Map,并要求所有的属性名与类声明中一致
	 * 
	 * @param list
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException 
	 */
	public static <T> List<Map<String, Object>> toListMap(List<T> list)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return toListMap(list, null);
	}

	/**
	 * 将list中的Bean转换为Map,并可定制属性名（isLowerCase 是否小写）
	 * 
	 * @param list
	 * @param isLowerCase
	 *            null:属性名与类声明中一致;true:属性名小写;false:属性名大写
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static <T> List<Map<String, Object>> toListMap(List<T> list, Boolean isLowerCase)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();

		if (null != list && list.size() > 0) {

			for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {

				T t = iterator.next();

				Map<String, Object> map = new BeanMap(t);

				

				if (null != isLowerCase) {
					map = updateMapKey(map, isLowerCase);
				}

				tempList.add(map);
			}

		}

		return tempList;
	}

	/**
	 * 将map中的key修改大小写
	 * 
	 * @param map
	 * @param isLowerCase
	 * @return
	 */
	private static Map<String, Object> updateMapKey(Map<String, Object> map, Boolean isLowerCase) {

		Map<String, Object> result = null;

		if (null != map) {

			result = new HashMap<String, Object>();

			Set<Entry<String, Object>> set = map.entrySet();
			Entry<String, Object> entry = null;
			for (Iterator<Entry<String, Object>> iterator = set.iterator(); iterator.hasNext();) {
				
				entry = iterator.next();

				String key = entry.getKey();
				result.put(isLowerCase ? key.toLowerCase() : key.toUpperCase(), entry.getValue());
			}

		}
		return result;
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		ResPool p1 = new ResPool();
		p1.setId(1L);
		p1.setName("K1");

		ResPool p2 = new ResPool();
		p2.setId(2L);
		p2.setName("K2");

		ResPool p3 = new ResPool();
		p3.setId(3L);
		p3.setName("K3");

		ResPool p4 = new ResPool();
		p4.setId(4L);
		p4.setName("K4");

		List<ResPool> list = new ArrayList<ResPool>();

		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);

//		System.out.println(JSONObject.toJSONString(toListMap(list)));
		System.out.println(JSONObject.toJSONString(toListMap(list,false)));
//		System.out.println(JSONObject.toJSONString(toListMap(list)));

	}

}
