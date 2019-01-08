package com.tsco.api.utils;

public class StringUtils {

    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }
    
    
}
