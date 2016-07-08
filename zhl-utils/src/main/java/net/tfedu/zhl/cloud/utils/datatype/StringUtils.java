package net.tfedu.zhl.cloud.utils.datatype;

import javax.servlet.http.HttpServletRequest;

public class StringUtils {

    /**
     * 判断是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || str.trim().equals("") || str.trim().equals("null");
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    /**
     * 返回request中名称为name的参数
     * @param request
     * @param name
     * @return
     */
    public static String getRequestParam(HttpServletRequest request, String name){
    	String value = request.getParameter(name);
    	return  value == null ?  "" : value.trim() ;
    }
    
}
