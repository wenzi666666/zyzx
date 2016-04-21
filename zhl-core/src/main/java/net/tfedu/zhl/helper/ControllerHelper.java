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
     * @return
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
}
