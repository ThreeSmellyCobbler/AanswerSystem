package com.tsco.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chenjia
 * web 启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tsco.answer"})
@MapperScan(basePackages = {"com.tsco.answer"})
public class WebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebApplication.class).run(args);
    }
}
