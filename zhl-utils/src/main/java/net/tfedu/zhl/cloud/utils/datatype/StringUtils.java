package net.tfedu.zhl.cloud.utils.datatype;

public class StringUtils {

    /**
     * 判断是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || str.equals("") || str.equals("null");
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
   
    
}
