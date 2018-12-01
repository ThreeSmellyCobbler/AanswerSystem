package com.tsco.web.exception;

import lombok.Data;

/**
 * @author  chen jia
 *
 */

@Data
public class WebException extends RuntimeException {

    private String code;

    private String message;

    public WebException(String message) {
        this.code = ExceptionCode.INNER_ERROR;
        this.message = message;
    }

    public WebException(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
