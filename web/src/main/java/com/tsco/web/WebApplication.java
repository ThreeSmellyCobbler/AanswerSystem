package com.tsco.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author  chenjia
 * web 启动类
 */
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebApplication.class).run(args);
    }
}
