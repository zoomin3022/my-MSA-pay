package com.example.mymsapay.banking.exception;

import com.example.mymsapay.exception.CustomException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BankAccountException extends CustomException {

    private final BankAccountExceptionType bankAccountExceptionType;

    @Override
    public BankAccountExceptionType getCustomExceptionType() {
        return bankAccountExceptionType;
    }
}
