package net.tfedu.zhl.helper;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;

/**
 * 
 * copyRight@知好乐教育技术北京有限公司
 * @author 	jiys
 * @date   	2016-9-9
 * @version	v1.0.0
 */
public class ParamsUtil {

	/**
	 * 参数非空校验
	 * @param user_uuid
	 * @return 
	 * @throws NullParamsException
	 */
	public static void verifyNullParams(Object... params) throws NullPointerException {
		if(params==null||params.length<=0){
			throw new NullPointerException();
		}
		for (Object obj : params) {
			if(obj==null||StringUtils.isEmpty(obj.toString())){
				throw new NullPointerException();
			}
		}
	}
}
