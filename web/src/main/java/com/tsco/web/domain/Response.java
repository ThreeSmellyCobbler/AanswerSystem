package com.tsco.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {

    private static final String CODE_SUCCESS = "0000";

    String code;
    String message;
    T data;

    public static <T> Response<T> SUCCESS(T data) {
        return new Response<>(CODE_SUCCESS, "success", data);
    }

    public static <T> Response<T> SUCCESS() {
        return new Response<>(CODE_SUCCESS, "success", null);
    }

    public static <T> Response<T> FAIL(String code, String message) {
        return new Response<>(code, message, null);

    }

}
