package net.tfedu.zhl.core.interceptor;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.tfedu.zhl.core.exception.DataAccessException;
import net.tfedu.zhl.core.exception.InvalidAccessTokenException;
import net.tfedu.zhl.core.exception.InvalidPasswordException;
import net.tfedu.zhl.core.exception.NoAuthorizationException;
import net.tfedu.zhl.core.exception.NoLoginException;
import net.tfedu.zhl.core.exception.NoTokenException;
import net.tfedu.zhl.core.exception.OutOfDateException;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.core.exception.PrepareContentExistException;
import net.tfedu.zhl.core.exception.UnCustomException;
import net.tfedu.zhl.core.exception.UnusualErrorException;
import net.tfedu.zhl.core.exception.WithoutUserException;
import net.tfedu.zhl.core.exception.WrongPassWordException;
import net.tfedu.zhl.helper.ResultJSON;

/**
 * 全局错误处理
 * 
 * @author bruce
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    ResultJSON result;

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = { SQLException.class, DataAccessException.class })
    @ResponseBody
    public ResultJSON handleSQLException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        result = new ResultJSON("DataAccessError", e.getCause().getClass() + "," + e.getCause().getMessage(), "", "");
        e.printStackTrace();
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = { Exception.class, RuntimeException.class, UnCustomException.class })
    @ResponseBody
    public ResultJSON handleRuntimeException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        result = new ResultJSON("ServerError", e.getCause().getClass() + "," + e.getCause().getMessage(), "", "");
        e.printStackTrace();
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(value = { IOException.class })
    @ResponseBody
    public ResultJSON handleRuntimeIOException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        result = new ResultJSON("IOError", e.getCause().getClass() + "," + e.getCause().getMessage(), "", "");
        e.printStackTrace();
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(NoLoginException.class)
    @ResponseBody
    public ResultJSON handleNoLoginException(HttpServletRequest request, HttpServletResponse response,
            NoLoginException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(WithoutUserException.class)
    @ResponseBody
    public ResultJSON handleWithoutUserException(HttpServletRequest request, HttpServletResponse response,
            WithoutUserException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(NoAuthorizationException.class)
    @ResponseBody
    public ResultJSON handleNoAuthorizationException(HttpServletRequest request, HttpServletResponse response,
            NoAuthorizationException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(WrongPassWordException.class)
    @ResponseBody
    public ResultJSON handleWrongPassWordException(HttpServletRequest request, HttpServletResponse response,
            WrongPassWordException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(OutOfDateException.class)
    @ResponseBody
    public ResultJSON handleOutOfDateException(HttpServletRequest request, HttpServletResponse response,
            OutOfDateException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(UnusualErrorException.class)
    @ResponseBody
    public ResultJSON handleUnusualErrorException(HttpServletRequest request, HttpServletResponse response,
            UnusualErrorException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(ParamsException.class)
    @ResponseBody
    public ResultJSON handleParamsException(HttpServletRequest request, HttpServletResponse response,
            ParamsException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(InvalidAccessTokenException.class)
    @ResponseBody
    public ResultJSON handleInvalidAccessTokenException(HttpServletRequest request, HttpServletResponse response,
            InvalidAccessTokenException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseBody
    public ResultJSON handleInvalidPasswordException(HttpServletRequest request, HttpServletResponse response,
            InvalidPasswordException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(NoTokenException.class)
    @ResponseBody
    public ResultJSON handleNoTokenException(HttpServletRequest request, HttpServletResponse response,
            NoTokenException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @ExceptionHandler(PrepareContentExistException.class)
    @ResponseBody
    public ResultJSON handlePrepareContentExistException(HttpServletRequest request, HttpServletResponse response,
            PrepareContentExistException e) {
        result = new ResultJSON(e.getCode(), e.getMessage(), "", "");
        return result;
    }

}
