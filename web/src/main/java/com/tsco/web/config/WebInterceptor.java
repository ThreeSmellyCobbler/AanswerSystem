package com.tsco.web.config;

import com.tsco.web.config.annotations.Interceptor;
import com.tsco.web.utils.Constans;
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

@Component
public class WebInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Interceptor interceptor = ((HandlerMethod) handler).getMethodAnnotation(Interceptor.class);
        if (interceptor == null) {
            //如果没有使用注解,不拦截
            return true;
        }
        if (isNeedLoginIntercept(request, interceptor)) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean isNeedLoginIntercept(HttpServletRequest request, Interceptor interceptor) {
        //接口需要登录,但是用户没有登录,进行拦截
        if (interceptor.needLogin() && request.getSession().getAttribute(Constans.USER_ID) == null) {
            return true;
        }
        return false;
    }
}
