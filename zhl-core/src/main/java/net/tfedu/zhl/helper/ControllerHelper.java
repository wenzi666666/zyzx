package net.tfedu.zhl.helper;

import javax.servlet.http.HttpServletRequest;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.ParamsException;

/**
 * Controller助手类
 * 
 * @author bruce
 *
 */
public class ControllerHelper {
	
	 
	/**
	 * 检测空
	 * @param str
	 * @return
	 * @throws Exception
	 */
    public static String checkEmpty(String str) throws Exception{
    	if(StringUtils.isEmpty(str)){
    		throw new  ParamsException();
    	}
    	return str.trim();
    }

    /**
     * 获取单个参数值
     * 
     * @param request
     * @param paramName
     * @return 字符串
     * @throws ParamsException
     */
    public static String getParameter(HttpServletRequest request, String paramName) throws ParamsException {
        String param;
        if (StringUtils.isNotEmpty(request.getParameter(paramName))) {
            param = request.getParameter(paramName).toString().trim();
        } else {
            throw new ParamsException();
        }
        return param;
    }
    
    /**
     * 获取单个参数值
     * 
     * @param request
     * @param paramName
     * @return 字符串
     * @throws ParamsException
     */
    public static String getHeaderParameter(HttpServletRequest request, String paramName) throws ParamsException {
        String param;
        if (StringUtils.isNotEmpty(request.getHeader(paramName))) {
            param = request.getHeader(paramName).toString().trim();
        } else {
            throw new ParamsException();
        }
        return param;
    }

    /**
     * 获取单个参数值
     * 
     * @param request
     * @param paramName
     * @return 整数
     * @throws ParamsException
     */
    public static int getIntParameter(HttpServletRequest request, String paramName) throws ParamsException {
        int param;
        if (StringUtils.isNotEmpty(request.getParameter(paramName))) {
            param = Integer.parseInt(request.getParameter(paramName).toString().trim());
        } else {
            throw new ParamsException();
        }
        return param;
    }

    /**
     * 获取单个参数值，如果获取失败，则赋值0
     * 
     * @param request
     * @param paramName
     * @return 整数
     * @throws ParamsException
     */
    public static int getIntWithDefault(HttpServletRequest request, String paramName) throws ParamsException {
        int param;
        if (StringUtils.isNotEmpty(request.getParameter(paramName))) {
            param = Integer.parseInt(request.getParameter(paramName).toString().trim());
        } else {
            param = 0;
        }
        return param;
    }

    /**
     * 获取单个参数值
     * 
     * @param request
     * @param paramName
     * @return 长整形
     * @throws ParamsException
     */
    public static long getLongParameter(HttpServletRequest request, String paramName) throws ParamsException {
        long param;
        if (StringUtils.isNotEmpty(request.getParameter(paramName))) {
            param = Long.parseLong(request.getParameter(paramName).toString().trim());
        } else {
            throw new ParamsException();
        }
        return param;
    }

    /**
     * 获取页码
     * 
     * @param request
     * @return
     * @throws ParamsException
     */
    public static int getPage(HttpServletRequest request) throws ParamsException {
        String strPageNum = request.getParameter("page");
        return StringUtils.isNotEmpty(strPageNum) ? Integer.parseInt(strPageNum) : 1;
    }

    /**
     * 获取页大小
     * 
     * @param request
     * @return
     * @throws ParamsException
     */
    public static int getPageSize(HttpServletRequest request) throws ParamsException {
        String strPageSize = request.getParameter("perPage");
        return StringUtils.isNotEmpty(strPageSize) ? Integer.parseInt(strPageSize) : 10;
    }
}
