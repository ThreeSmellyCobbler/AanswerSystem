package com.tsco.web.config.Interceptor;

import com.tsco.web.config.annotations.Interceptor;
import com.tsco.web.utils.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author chen jia
 */

@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Interceptor interceptor = ((HandlerMethod) handler).getMethodAnnotation(Interceptor.class);
        if (isNeedLoginIntercept(request, interceptor)) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    private boolean isNeedLoginIntercept(HttpServletRequest request, Interceptor interceptor) {
        //没有注解 不需要登录拦截
        if (interceptor == null) {
            return false;
        }
        //接口需要登录,但是用户没有登录,进行拦截
        if (interceptor.needLogin() && request.getSession().getAttribute(Constans.USER_ID) == null) {
            log.info("user not login");
            return true;
        }
        return false;
    }
}
