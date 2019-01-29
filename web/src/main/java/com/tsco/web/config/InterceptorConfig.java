package com.tsco.web.config;

import com.tsco.web.config.Interceptor.CrossDomainInterceptor;
import com.tsco.web.config.Interceptor.WebInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private WebInterceptor webInterceptor;

    @Autowired
    private CrossDomainInterceptor crossDomainInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这里可以添加多个拦截器,组成一个拦截器链
        /*registry.addInterceptor(crossDomainInterceptor)
                .addPathPatterns("/**");*/
        registry.addInterceptor(webInterceptor)
                //拦截所有请求
                .addPathPatterns("/**");
    }

    /**
     * 静态资源处理
     *
     * @param registry
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
