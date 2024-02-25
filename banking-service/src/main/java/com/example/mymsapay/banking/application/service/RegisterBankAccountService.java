package com.example.mymsapay.banking.application.service;

import com.example.mymsapay.banking.application.port.out.GetMembershipPort;
import com.example.mymsapay.banking.application.port.out.MembershipStatus;
import com.example.mymsapay.common.UseCase;
import com.example.mymsapay.banking.adapter.out.external.bank.BankAccount;
import com.example.mymsapay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.example.mymsapay.banking.adapter.out.persistence.registeredaccount.RegisteredBankAccountEntity;
import com.example.mymsapay.banking.adapter.out.persistence.registeredaccount.RegisteredBankAccountMapper;
import com.example.mymsapay.banking.application.port.in.RegisterBankAccountCommand;
import com.example.mymsapay.banking.application.port.in.RegisterBankAccountUseCase;
import com.example.mymsapay.banking.application.port.out.RegisterBankAccountPort;
import com.example.mymsapay.banking.application.port.out.RequestBankAccountInfoPort;
import com.example.mymsapay.banking.domain.RegisteredBankAccount;
import com.example.mymsapay.banking.exception.BankAccountException;
import com.example.mymsapay.banking.exception.BankAccountExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerMembershipPort;
    private final RegisteredBankAccountMapper membershipMapper;

    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

    private final GetMembershipPort getMembershipPort;

    @Transactional
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        // 은행 계좌를 등록해야하는 서비스 (비즈니스 로직)

        // 멤버 서비스도 확인?
        // 멤버십 서비스와 통신 통해 정상인 회원인지 확인
        MembershipStatus membership = getMembershipPort.getMembership(command.getMembershipId());
        if (!membership.isValid()) {
            throw new BankAccountException(BankAccountExceptionType.MEMBERSHIP_NOT_VALID);
        }

        // 1. 외부 실제 은행에 등록된 계좌인지 (정상인지) 확인하다.
        // 외부의 은행에 통신해서 확인절차 필요
        // Biz Logic -> External System
        // Port -> Adapter
        // 실제 외부의 은행계좌 정보를 GET
        BankAccount accountInfo = requestBankAccountInfoPort.getAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean isValid = accountInfo.isValid();
        // 2. 등록 가능하면 등록하고 정보 리턴
        // 2-1. 등록 불가능하면 예외 발생
        if (isValid) {
            RegisteredBankAccountEntity membershipEntity =
                    registerMembershipPort.createBankAccount(
                            new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                            new RegisteredBankAccount.BankName(command.getBankName()),
                            new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                            new RegisteredBankAccount.BankAccountLinkedIsValid(command.isValid())
                    );
            return membershipMapper.entityToDomain(membershipEntity);
        } else {
            throw new BankAccountException(BankAccountExceptionType.BANK_ACCOUNT_NOT_VALID);
        }
    }
}
