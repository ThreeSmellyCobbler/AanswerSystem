package com.tsco.web.config;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.tsco.api.dubboService.UserDubboService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * dubbo 配置
 *
 * @author chen jia
 */
@Configuration
public class DubboConfig {
    private static final String VERSION = "1.0";

    @Bean
    public ReferenceBean<UserDubboService> userMicroService() {
        ReferenceBean<UserDubboService> referenceBean = new ReferenceBean<>();
        referenceBean.setInterface(UserDubboService.class);
        referenceBean.setVersion(VERSION);
        return referenceBean;
    }
}

