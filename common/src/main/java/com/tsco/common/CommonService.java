package com.tsco.common;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableDubbo
@EnableAsync
@SpringBootApplication
public class CommonService {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CommonService.class).run(args);
    }
}
