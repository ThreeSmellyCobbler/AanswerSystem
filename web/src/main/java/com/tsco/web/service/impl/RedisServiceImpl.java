package com.tsco.web.service.impl;

import com.tsco.web.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl<T> implements RedisService<T> {

    @Autowired
    private RedisTemplate<String, T> redisTemplate;

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setWithSeconds(String key, T value, long timeout) {
        setWithTimeout(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void setWithMinutes(String key, T value, long timeout) {
        setWithTimeout(key, value, timeout, TimeUnit.MINUTES);
    }

    @Override
    public void setWithHours(String key, T value, long timeout) {
        setWithTimeout(key, value, timeout, TimeUnit.HOURS);
    }

    @Override
    public void setWithDays(String key, T value, long timeout) {
        setWithTimeout(key, value, timeout, TimeUnit.DAYS);
    }

    @Override
    public void setWithTimeout(String key, T value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public void setWithDate(String key, T value, Date date) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expireAt(key, date);
    }

}
