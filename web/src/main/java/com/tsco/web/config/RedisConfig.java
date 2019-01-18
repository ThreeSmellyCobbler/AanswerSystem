package com.tsco.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
@PropertySource(value = "classpath:redis.properties")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.port}")
    private String port = "6379";

    @Value("${spring.redis.database}")
    private String dataBaseIndex;

    @Value("${spring.redis.password}")
    private String password;


    /**
     * 配置jedisConnectionFactory
     *
     * @return
     */
    @Bean("myJedisConnectionFactory")
    public JedisConnectionFactory getJedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(hostName);
        configuration.setDatabase(Integer.valueOf(dataBaseIndex));
        configuration.setPassword(RedisPassword.none());
        configuration.setPort(Integer.valueOf(port));
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration);
        return jedisConnectionFactory;
    }

    @Primary
    @Bean(name = "myRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("myJedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory) {
        //StringRedisTemplate的构造方法中默认设置了stringSerializer
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //set key serializer
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(objectJackson2JsonRedisSerializer);
        template.setHashValueSerializer(objectJackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setConnectionFactory(jedisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }

}

