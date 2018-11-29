package com.tsco.member.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


@Configuration
@PropertySource(value = "classpath:datasource.properties")
public class DataSourceConfig {

    @Value("${spring.mysql.datasource.url}")
    private String url;

    @Value("${spring.mysql.datasource.username}")
    private String userName;

    @Value("${spring.mysql.datasource.password}")
    private String password;

    @Value("${spring.mysql.datasource.driver-class-name}")
    private String driverName;

    @Bean
    @ConfigurationProperties(prefix = "spring.mysql.datasource")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            //设置mybatis sql的xml文件扫描路径
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mappers/*-mapper.xml"));
            //设置po路径
            sqlSessionFactoryBean.setTypeAliasesPackage("com.tsco.member.domain.po");
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean("sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
