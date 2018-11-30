package com.tsco.web.controller;

import com.tsco.web.utils.Constans;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

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

    private Optional<Long> currentUserId = Optional.empty();

    protected void setCurrentUserId(Long userId) {
        currentUserId = Optional.ofNullable(userId);
    }

    protected Optional<Long> getCurrentUserId() {
        if (request.getSession().getAttribute(Constans.USER_ID) != null) {
            Long userId = (Long) request.getSession().getAttribute(Constans.USER_ID);
            return Optional.of(userId);
        }
        return Optional.empty();
    }


}
