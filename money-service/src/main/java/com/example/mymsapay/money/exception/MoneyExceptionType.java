package com.example.mymsapay.money.exception;

import com.example.mymsapay.exception.CustomExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MoneyExceptionType implements CustomExceptionType {
    BANK_ACCOUNT_NOT_EXISTS(HttpStatus.BAD_REQUEST, "B001", "존재하지 않는 계좌입니다"),
    BANK_ACCOUNT_NOT_VALID(HttpStatus.BAD_REQUEST, "B002", "계좌가 유효하지 않습니다"),
    NULL_FAIL(HttpStatus.BAD_REQUEST, "B003", "계좌가 null 입니다");

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
