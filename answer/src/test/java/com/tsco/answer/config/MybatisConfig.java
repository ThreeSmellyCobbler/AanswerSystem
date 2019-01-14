package com.tsco.answer.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(value = "com.tsco.answer")
@PropertySource(value = "classpath:dataSource.properties")
public class MybatisConfig {

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
            sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
            //开启mybatis的驼峰功能
            sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            System.out.println("===============出现异常了================");
            return null;
        }
    }

    @Bean("sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager replicaTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
