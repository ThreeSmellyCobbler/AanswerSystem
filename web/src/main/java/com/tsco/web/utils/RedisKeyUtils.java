package com.tsco.web.utils;

public class RedisKeyUtils {

    public static String buildKey(String prefix, String keyWord) {
        return prefix + keyWord;
    }
}
