package com.example.mymsapay.banking.application.port.out;


import com.example.mymsapay.banking.adapter.out.persistence.BankAccountEntity;
import com.example.mymsapay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {

    BankAccountEntity createBankAccount(
              RegisteredBankAccount.MembershipId membershipId
            , RegisteredBankAccount.BankName bankName
            , RegisteredBankAccount.BankAccountNumber bankAccountNumber
            , RegisteredBankAccount.BankAccountLinkedIsValid bankAccountLinkedIsValid
    );
}
