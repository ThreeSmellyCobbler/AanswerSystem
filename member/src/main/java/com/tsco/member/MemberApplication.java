package com.tsco.member;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 * @author chenjia
 * member 项目启动类
 */
@SpringBootApplication
public class MemberApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MemberApplication.class).run(args);
    }

}
