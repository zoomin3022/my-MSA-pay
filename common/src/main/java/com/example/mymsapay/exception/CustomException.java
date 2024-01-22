package com.example.mymsapay.exception;

public abstract class CustomException extends RuntimeException {

    public abstract CustomExceptionType getCustomExceptionType();

}
