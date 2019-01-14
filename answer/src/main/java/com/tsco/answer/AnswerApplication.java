package com.tsco.answer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AnswerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AnswerApplication.class).run(args);
    }
}
