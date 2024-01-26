package com.example.mymsapay.banking.application.port.out;

import com.example.mymsapay.banking.adapter.out.persistence.BankAccountEntity;

public interface FindBankAccountPort {
    BankAccountEntity findById(Long bankAccountId);
}
