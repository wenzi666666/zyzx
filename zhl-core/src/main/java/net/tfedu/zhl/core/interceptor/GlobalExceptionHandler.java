package net.tfedu.zhl.core.interceptor;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.tfedu.zhl.core.exception.NoLoginException;
import net.tfedu.zhl.core.exception.WithoutUserException;
import net.tfedu.zhl.helper.ResultJSON;

@ControllerAdvice
public class GlobalExceptionHandler {

    ResultJSON result;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public ResultJSON handleSQLException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        result = new ResultJSON("sqlError", e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    @ResponseBody
    public ResultJSON handleRuntimeException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        result = new ResultJSON("unKnown", e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoLoginException.class)
    @ResponseBody
    public ResultJSON handleNoLoginException(HttpServletRequest request, HttpServletResponse response,
            NoLoginException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }
    
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WithoutUserException.class)
    @ResponseBody
    public ResultJSON handleWithoutUserException(HttpServletRequest request, HttpServletResponse response,
            WithoutUserException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }
}
