package com.tsco.web.domain;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiResponses({
        @ApiResponse(code = 1000, message = "success"),
        @ApiResponse(code = 400, message = "参数异常"),
        @ApiResponse(code = 10001, message = "系统异常"),
        @ApiResponse(code = 1002, message = "未登录")}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {

    private static final String CODE_SUCCESS = "1000";

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
