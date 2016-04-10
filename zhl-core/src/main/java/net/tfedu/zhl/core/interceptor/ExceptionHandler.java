package net.tfedu.zhl.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.core.exception.DaoException;
import net.tfedu.zhl.core.exception.ServiceException;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

public class ExceptionHandler {
    
    ResultJSON result;
    CustomException exception;
    
    public ResultJSON resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
          
        // 根据不同错误转向不同页面  
        if(ex instanceof ServiceException) {  
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), ex.getMessage(), "", "");
        }else if(ex instanceof DaoException) {  
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), ex.getMessage(), "", ""); 
        }  
        return result;
    }  
}
