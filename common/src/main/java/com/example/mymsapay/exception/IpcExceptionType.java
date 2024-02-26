package com.example.mymsapay.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum IpcExceptionType implements CustomExceptionType{
    MEMBERSHIP_SERVICE_CONNECTION_FAIL(HttpStatus.BAD_REQUEST, "IPC001", "멤버십 서비스와 통신 실패"),;

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
