package com.tsco.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:mail.properties")
public class MailSenderConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String userName;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.encoding}")
    private String encoding;

    @Value("${spring.mail.port}")
    private String port;

    @Bean
    public JavaMailSender javaMailSender() {
        // 默认配置相关
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(Integer.valueOf(port));
        javaMailSender.setUsername(userName);
        javaMailSender.setPassword(password);
        javaMailSender.setDefaultEncoding(encoding);

        // 认证相关
        Properties properties = new Properties();
        properties.setProperty("mail.host", host);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.socketFactory.port", port);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }
}
