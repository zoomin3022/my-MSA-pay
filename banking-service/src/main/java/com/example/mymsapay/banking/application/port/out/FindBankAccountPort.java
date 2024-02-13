package com.example.mymsapay.banking.application.port.out;

import com.example.mymsapay.banking.adapter.out.persistence.registeredaccount.RegisteredBankAccountEntity;

public interface FindBankAccountPort {
    RegisteredBankAccountEntity findById(Long bankAccountId);
}
