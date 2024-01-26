package com.example.mymsapay.banking.application.port.in;

import com.example.mymsapay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}
