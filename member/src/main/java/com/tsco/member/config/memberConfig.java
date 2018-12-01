package com.tsco.member.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * member 项目的配置类
 *
 * @author  chen jia
 */
@Configuration
public class memberConfig {

    /**
     * dozer 配置类映射工具
     */
    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        return dozerBeanMapper;
    }
}
