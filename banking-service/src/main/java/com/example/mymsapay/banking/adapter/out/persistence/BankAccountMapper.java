package com.example.mymsapay.banking.adapter.out.persistence;

import com.example.mymsapay.banking.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public RegisteredBankAccount entityToDomain(
            BankAccountEntity membership) {
        System.out.println(membership.toString());
        return RegisteredBankAccount.generateBankAccount(
                new RegisteredBankAccount.BankAccountId(membership.getBankAccountId()),
                new RegisteredBankAccount.MembershipId(membership.getMembershipId()),
                new RegisteredBankAccount.BankName(membership.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(membership.getBankAccountNumber()),
                new RegisteredBankAccount.BankAccountLinkedIsValid(membership.isLinkedStatusIsValid()));
    }
}
