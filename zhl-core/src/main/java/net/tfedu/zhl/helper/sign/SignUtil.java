package net.tfedu.zhl.helper.sign;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.fileservice.MD5;
/**
 * 校验工具对象
 * copyRight@知好乐教育技术北京有限公司
 * @author 	jiys
 * @date   	2016-9-13
 * @version	v1.0.0
 */
public class SignUtil {
	
	public static String separator ="[tfedu]";

	/**
	 * 根据参数集合生成校验码
	 * @param map
	 * @param app_key
	 * @return
	 */
	public static String createSign(HttpServletRequest request,String app_key){
		Map<String, String> map = SignUtil.getParameterMap(request);
		if(map==null||map.isEmpty()){
			return null;
		}
		//使用 Map按key进行排序
		map=HashMapUtil.sortMapByKey(map);
		StringBuffer signSB=new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			signSB.append(entry.getValue()).append(separator);
		}
		signSB.append(app_key);
		return MD5.getMD5Str(signSB.toString());
	}

	/**
	 * 根据参数集合生成校验码
	 * @param keys
	 * @param values
	 * @param app_key
	 * @return
	 */
	public static String createSignMap(String[] keys,Object[] values,String app_key){
		if(keys==null||keys.length<=0||values==null||values.length<=0){
			return null;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		int i=0;
		for (String key : keys) {
			map.put(key, values[i++]);
		}
		//使用 Map按key进行排序
		map=HashMapUtil.sortMapByKeyValueObj(map);
		StringBuffer signSB=new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			signSB.append(entry.getValue()).append(separator);
		}
		signSB.append(app_key);
		return MD5.getMD5Str(signSB.toString());
	}

	/**
	 * 根据参数集合生成校验码
	 * @param keys
	 * @param values
	 * @param app_key
	 * @return
	 */
	public static String createSignMap(String[] keys,String[] values,String app_key){
		if(keys==null||keys.length<=0||values==null||values.length<=0){
			return null;
		}
		Map<String, String> map=new HashMap<String, String>();
		int i=0;
		for (String key : keys) {
			map.put(key, values[i++]);
		}
		//使用 Map按key进行排序
		map=HashMapUtil.sortMapByKey(map);
		StringBuffer signSB=new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			signSB.append(entry.getValue()).append(separator);
		}
		signSB.append(app_key);
		return MD5.getMD5Str(signSB.toString());
	}
	
	/**
	 * 根据参数集合生成校验码
	 * @param map
	 * @param app_key
	 * @return
	 */
	public static boolean signParams(String sign,String signLocal){
		return StringUtils.isNotEmpty(sign)&&StringUtils.isNotEmpty(signLocal)
				&&StringUtils.equalsIgnoreCase(sign,signLocal);
	}
	
	
	/**
	 * 得到所有参数map集合
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String> map = null;
		Enumeration<String> paramNames = request.getParameterNames();
		if (paramNames != null) {
			map = new HashMap<String, String>();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				// 如果参数为sign 退出不加入Map
				if (StringUtils.equalsIgnoreCase(paramName, "sign")) {
					continue;
				}
				String paramValue = request.getParameter(paramName);
				map.put(paramName, paramValue);
			}
		}
		return map;
	}
	
	
	
	public static void main(String[] args) {
		
		String appId = "642584";
		String appKey = "c5f06463d14e";
		
		String result =  SignUtil.createSignMap(
				new String[]{"appId"},
				new String[]{appId}, appKey);
		
		
		System.out.println(result);
		System.out.println("&sign="+result);
		
	}
	
	
	
}
