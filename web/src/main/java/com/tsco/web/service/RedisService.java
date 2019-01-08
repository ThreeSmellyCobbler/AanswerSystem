package com.tsco.web.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public interface RedisService<T> {
    /**
     * 功能描述：redis是否存在key
     *
     * @param key key 不能为空
     * @return true key存在 false key不存在
     */
    Boolean hasKey(String key);

    /**
     * 功能描述：获取redis中key的value
     *
     * @param key key 不能为空
     * @return 获取redis中value的值 如果值不存在 返回null
     */
    T get(String key);

    /**
     * 功能描述：向redis里存入数据
     *
     * @param key   key 不能为空
     * @param value value value
     */
    void set(String key, T value);

    /**
     * 功能描述：删除redis中的key
     *
     * @param key key 不能为空
     */
    void delete(String key);

    /**
     * 功能描述：向redis里存入数据和设置缓存到期时间
     *
     * @param key   key 不能为空
     * @param value value value
     * @param date  缓存到期时间
     */
    void setWithDate(String key, T value, Date date);

    /**
     * 功能描述：向redis里存入数据和设置缓存时间（缓存时间多少秒）
     *
     * @param key     key 不能为空
     * @param value   value value
     * @param timeout 多少秒
     */
    void setWithSeconds(String key, T value, long timeout);

    /**
     * 功能描述：向redis里存入数据和设置缓存时间（缓存时间多少分钟）
     *
     * @param key     key 不能为空
     * @param value   value value
     * @param timeout 多少分钟
     */
    void setWithMinutes(String key, T value, long timeout);

    /**
     * 功能描述：向redis里存入数据和设置缓存时间（缓存时间多少小时）
     *
     * @param key     key 不能为空
     * @param value   value value
     * @param timeout 多少小时
     */
    void setWithHours(String key, T value, long timeout);

    /**
     * 功能描述：向redis里存入数据(和设置缓存时间（缓存时间多少天）
     *
     * @param key     key 不能为空
     * @param value   value value
     * @param timeout 多少天
     */
    void setWithDays(String key, T value, long timeout);

    /**
     * 功能描述：向redis里存入数据和设置缓存时间
     *
     * @param key     key 不能为空
     * @param value   value value
     * @param timeout 多少
     * @param unit    时间单位 不能为空
     */
    void setWithTimeout(String key, T value, long timeout, TimeUnit unit);
}
