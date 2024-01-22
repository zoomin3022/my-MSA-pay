package com.example.mymsapay.membership.exception;

import com.example.mymsapay.exception.CustomExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MembershipExceptionType implements CustomExceptionType {
    MEMBERSHIP_EXCEPTION_NOT_EXISTS(HttpStatus.BAD_REQUEST, "M005", "존재하지 않는 계정입니다");

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
