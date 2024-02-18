package com.example.mymsapay.money.exception;

import com.example.mymsapay.exception.CustomExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MoneyExceptionType implements CustomExceptionType {
    MONEY_ACCOUNT_NOT_EXISTS(HttpStatus.BAD_REQUEST, "B001", "존재하지 않는 머니 계좌입니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
