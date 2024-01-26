package com.example.mymsapay.banking.application.port.out;

import com.example.mymsapay.banking.adapter.out.external.bank.BankAccount;
import com.example.mymsapay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.example.mymsapay.banking.domain.RegisteredBankAccount;

public interface RequestBankAccountInfoPort {
    BankAccount getAccountInfo(GetBankAccountRequest request);
}
