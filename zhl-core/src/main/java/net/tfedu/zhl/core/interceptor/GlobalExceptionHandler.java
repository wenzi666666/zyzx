package net.tfedu.zhl.core.interceptor;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    ResultJSON result;
    CustomException exception;
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public ResultJSON handleSQLException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        exception = CustomException.UNCUSTOM;
        result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    @ResponseBody
    public ResultJSON handleRuntimeException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        exception = CustomException.UNCUSTOM;
        result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
        return result;
    }
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public ResultJSON handleDataAccessException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        exception = CustomException.DATAACCESS;
        result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
        return result;
    }
}
