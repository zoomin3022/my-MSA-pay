package com.example.mymsapay.money.exception;

import com.example.mymsapay.exception.CustomException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MoneyException extends CustomException {

    private final MoneyExceptionType moneyExceptionType;

    @Override
    public MoneyExceptionType getCustomExceptionType() {
        return moneyExceptionType;
    }
}
