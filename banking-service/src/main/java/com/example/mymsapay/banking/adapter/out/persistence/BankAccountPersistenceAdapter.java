package com.example.mymsapay.banking.adapter.out.persistence;

import com.example.mymsapay.PersistenceAdapter;
import com.example.mymsapay.banking.application.port.out.FindBankAccountPort;
import com.example.mymsapay.banking.application.port.out.RegisterBankAccountPort;
import com.example.mymsapay.banking.domain.RegisteredBankAccount;
import com.example.mymsapay.banking.exception.BankAccountException;
import com.example.mymsapay.banking.exception.BankAccountExceptionType;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class BankAccountPersistenceAdapter implements FindBankAccountPort, RegisterBankAccountPort {
    private final SpringDataBankAccountRepository bankAccountRepository;

    @Override
    public BankAccountEntity findById(Long bankAccountId) {
        return bankAccountRepository.findById(bankAccountId).orElseThrow
                (() -> new BankAccountException(BankAccountExceptionType.BANK_ACCOUNT_NOT_EXISTS));
    }

    @Override
    public BankAccountEntity createBankAccount(RegisteredBankAccount.MembershipId membershipId, RegisteredBankAccount.BankName bankName, RegisteredBankAccount.BankAccountNumber bankAccountNumber, RegisteredBankAccount.BankAccountLinkedIsValid bankAccountLinkedIsValid) {
        return bankAccountRepository.save(
                BankAccountEntity.builder()
                        .membershipId(membershipId.getMembershipId())
                        .bankName(bankName.getNameValue())
                        .bankAccountNumber(bankAccountNumber.getBankAccountNumberValue())
                        .linkedStatusIsValid(bankAccountLinkedIsValid.isValidValue())
                        .build());
    }
}
