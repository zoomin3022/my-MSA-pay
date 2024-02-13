package com.example.mymsapay.banking.adapter.out.persistence.registeredaccount;

import com.example.mymsapay.banking.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBankAccountMapper {
    public RegisteredBankAccount entityToDomain(
            RegisteredBankAccountEntity entity) {
        return RegisteredBankAccount.generateBankAccount(
                new RegisteredBankAccount.BankAccountId(entity.getBankAccountId()),
                new RegisteredBankAccount.MembershipId(entity.getMembershipId()),
                new RegisteredBankAccount.BankName(entity.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(entity.getBankAccountNumber()),
                new RegisteredBankAccount.BankAccountLinkedIsValid(entity.isLinkedStatusIsValid()));
    }
}
