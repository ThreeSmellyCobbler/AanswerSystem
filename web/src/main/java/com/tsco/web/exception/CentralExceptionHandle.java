package com.tsco.web.exception;

import com.tsco.web.domain.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 *
 * @auto chen jia
 */
@Component
@ControllerAdvice
public class CentralExceptionHandle {

    @ExceptionHandler(WebException.class)
    @ResponseBody
    public Response handleActivityException(HttpServletRequest request, WebException we) {
        return Response.FAIL(we.getCode(), we.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response handleException(HttpServletRequest request, Exception e) {
        return Response.FAIL(ExceptionCode.INNER_ERROR, e.getMessage());
    }
}
