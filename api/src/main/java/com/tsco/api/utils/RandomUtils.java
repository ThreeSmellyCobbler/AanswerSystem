package com.tsco.api.utils;

import java.util.Random;

public class RandomUtils {

    private static int VERIFICATION_CODE_BIT = 6;

    public static String generateVerificationCode() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < VERIFICATION_CODE_BIT; i++) {
            stringBuffer.append(random.nextInt(10));
        }
        return stringBuffer.toString();
    }
}
