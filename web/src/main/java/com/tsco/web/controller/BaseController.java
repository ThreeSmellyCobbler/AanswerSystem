package com.tsco.web.controller;

import com.tsco.web.exception.ExceptionCode;
import com.tsco.web.exception.WebException;
import com.tsco.web.utils.Constans;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chen jia
 * 需要登录的controller继承此类
 */
public abstract class BaseController {

    private HttpServletRequest request;

    private HttpServletResponse response;

    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    protected Integer getCurrentUserId() {
        if (request.getSession().getAttribute(Constans.USER_ID) == null) {
            throw new WebException(ExceptionCode.UN_AUTHORITY, "需要登录");
        }
        return (Integer) request.getSession().getAttribute(Constans.USER_ID);
    }


}
