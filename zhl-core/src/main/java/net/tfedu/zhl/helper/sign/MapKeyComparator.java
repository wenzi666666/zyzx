package net.tfedu.zhl.helper.sign;


import java.util.Comparator;
/**
 * Map排序对象
 * copyRight@知好乐教育技术北京有限公司
 * @author 	jiys
 * @date   	2016-9-13
 * @version	v1.0.0
 */
public class MapKeyComparator implements Comparator<String>{

	@Override
	public int compare(String str1, String str2) {
		//升序排序
		return str1.compareTo(str2);
	}
}
