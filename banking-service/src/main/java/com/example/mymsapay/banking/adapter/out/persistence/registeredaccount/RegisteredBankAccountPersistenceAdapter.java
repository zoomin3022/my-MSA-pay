package com.example.mymsapay.banking.adapter.out.persistence.registeredaccount;

import com.example.mymsapay.common.PersistenceAdapter;
import com.example.mymsapay.banking.application.port.out.FindBankAccountPort;
import com.example.mymsapay.banking.application.port.out.RegisterBankAccountPort;
import com.example.mymsapay.banking.domain.RegisteredBankAccount;
import com.example.mymsapay.banking.exception.BankAccountException;
import com.example.mymsapay.banking.exception.BankAccountExceptionType;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements FindBankAccountPort, RegisterBankAccountPort {
    private final SpringDataRegisteredBankAccountRepository bankAccountRepository;

    @Override
    public RegisteredBankAccountEntity findById(Long bankAccountId) {
        return bankAccountRepository.findById(bankAccountId).orElseThrow
                (() -> new BankAccountException(BankAccountExceptionType.BANK_ACCOUNT_NOT_EXISTS));
    }

    @Override
    public RegisteredBankAccountEntity createBankAccount(RegisteredBankAccount.MembershipId membershipId, RegisteredBankAccount.BankName bankName, RegisteredBankAccount.BankAccountNumber bankAccountNumber, RegisteredBankAccount.BankAccountLinkedIsValid bankAccountLinkedIsValid) {
        return bankAccountRepository.save(
                RegisteredBankAccountEntity.builder()
                        .membershipId(membershipId.getMembershipId())
                        .bankName(bankName.getNameValue())
                        .bankAccountNumber(bankAccountNumber.getBankAccountNumberValue())
                        .linkedStatusIsValid(bankAccountLinkedIsValid.isValidValue())
                        .build());
    }
}
