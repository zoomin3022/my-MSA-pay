package com.example.mymsapay.banking.application.port.out;


import com.example.mymsapay.banking.adapter.out.persistence.registeredaccount.RegisteredBankAccountEntity;
import com.example.mymsapay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {

    RegisteredBankAccountEntity createBankAccount(
              RegisteredBankAccount.MembershipId membershipId
            , RegisteredBankAccount.BankName bankName
            , RegisteredBankAccount.BankAccountNumber bankAccountNumber
            , RegisteredBankAccount.BankAccountLinkedIsValid bankAccountLinkedIsValid
    );
}
