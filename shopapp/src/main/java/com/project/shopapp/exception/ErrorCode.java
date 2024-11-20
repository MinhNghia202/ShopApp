package com.project.shopapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND_EXCEPTION(1001,"User not found!",HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND_EXCEPTION(1002,"Order not found!", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND_EXCEPTION(1003,"Product not found!", HttpStatus.NOT_FOUND),
    ORDER_DETAIL_NOT_FOUND_EXCEPTION(1004,"Order detail not found!", HttpStatus.NOT_FOUND),
    INVALID_PHONE_PASSWORD_EXCEPTION(1005,"Invalid phone number / password" , HttpStatus.NOT_FOUND),
    WRONG_PHONE_NUMBER_PASSWORD(1006, "Wrong phone number / password", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007,"Unauthenticated", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1008,"Role not found", HttpStatus.NOT_FOUND)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
