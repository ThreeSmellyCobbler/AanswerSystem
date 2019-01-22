package com.tsco.web.config;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.tsco.api.dubboService.MailSenderDubboService;
import com.tsco.api.dubboService.UserDubboService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * dubbo 配置
 *
 * @author chen jia
 */
@Configuration
public class DubboReferenceBeanConfig {
    private static final String VERSION = "1.0.0";

    @Bean
    public ReferenceBean<UserDubboService> userDubboService() {
        ReferenceBean<UserDubboService> referenceBean = new ReferenceBean<>();
        referenceBean.setInterface(UserDubboService.class);
        referenceBean.setVersion(VERSION);
        return referenceBean;
    }

    @Bean
    public ReferenceBean<MailSenderDubboService> mailSenderDubboService() {
        ReferenceBean<MailSenderDubboService> referenceBean = new ReferenceBean<>();
        referenceBean.setInterface(MailSenderDubboService.class);
        referenceBean.setVersion(VERSION);
        //设置超时时间3秒
        referenceBean.setTimeout(3000);
        //不重试
        referenceBean.setRetries(0);
        return referenceBean;
    }


}

