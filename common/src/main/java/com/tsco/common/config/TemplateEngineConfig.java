package com.tsco.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@PropertySource(value = "classpath:templateEngine.properties")
public class TemplateEngineConfig {

    @Value("${spring.thymeleaf.prefix}")
    private String prefix;

    @Value("${spring.thymeleaf.suffix}")
    private String suffix;

    @Value("${spring.thymeleaf.mode}")
    private String mode;

    @Value("${spring.thymeleaf.encoding}")
    private String encoding;

    /*配置模板解析器
    Thymeleaf3使用ITemplateResolver接口，SpringResourceTemplateResolver实现类
    Thymeleaf3之前使用TemplateResolver接口，ServletContextTemplateResolver实现类*/
    @Qualifier
    @Bean("templateResolver")
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix(prefix);
        templateResolver.setSuffix(suffix);
        // 设置templateMode属性为HTML5
        templateResolver.setTemplateMode(mode);
        // 设置编码格式为UTF-8
        templateResolver.setCharacterEncoding(encoding);
        templateResolver.setCacheable(false);
        // templateResolver.setOrder(1);
        return templateResolver;
    }

    @Primary
    @Bean("myTemplateEngine")
    public TemplateEngine templateEngine(@Qualifier("templateResolver") ITemplateResolver templateResolver) {
        TemplateEngine templateEngine = new SpringTemplateEngine();
        // 注入模板解析器
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }


}
