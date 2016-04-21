package net.tfedu.zhl.helper;

import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.ParamsException;

/**
 * Controller助手类
 * @author bruce
 *
 */
public class ControllerHelper {
    
    /**
     * 获取单个参数值
     * @param request
     * @param paramName
     * @return 字符串
     * @throws ParamsException
     */
    public static String getParameter(HttpServletRequest request, String paramName) throws ParamsException{
        String param;
        if(StringUtils.isNotEmpty(request.getParameter(paramName))){
            param = request.getParameter(paramName).toString().trim();
        } else {
            throw new ParamsException();
        }
        return param;
    }
    
    /**
     * 获取单个参数值
     * @param request
     * @param paramName
     * @return 整数
     * @throws ParamsException
     */
    public static int getIntParameter(HttpServletRequest request, String paramName) throws ParamsException{
        int param; 
        if(StringUtils.isNotEmpty(request.getParameter(paramName))){
            param = Integer.parseInt(request.getParameter(paramName).toString().trim());
        } else {
            throw new ParamsException();
        }
        return param;
    }
    
    /**
     * 获取单个参数值
     * @param request
     * @param paramName
     * @return 长整形
     * @throws ParamsException
     */
    public static long getLongParameter(HttpServletRequest request, String paramName) throws ParamsException{
        long param; 
        if(StringUtils.isNotEmpty(request.getParameter(paramName))){
            param = Long.parseLong(request.getParameter(paramName).toString().trim());
        } else {
            throw new ParamsException();
        }
        return param;
    }
}
