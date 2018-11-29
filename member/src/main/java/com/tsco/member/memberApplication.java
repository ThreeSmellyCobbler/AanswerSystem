package com.tsco.member;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class memberApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(memberApplication.class).run(args);
    }

}
