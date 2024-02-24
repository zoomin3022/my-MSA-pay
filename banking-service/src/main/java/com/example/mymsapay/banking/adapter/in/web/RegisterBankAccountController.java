package com.example.mymsapay.banking.adapter.in.web;

import com.example.mymsapay.common.WebAdapter;
import com.example.mymsapay.banking.adapter.in.web.dto.RegisterBankAccountRequest;
import com.example.mymsapay.banking.application.port.in.RegisterBankAccountCommand;
import com.example.mymsapay.banking.application.port.in.RegisterBankAccountUseCase;
import com.example.mymsapay.banking.domain.RegisteredBankAccount;
import com.example.mymsapay.banking.exception.BankAccountException;
import com.example.mymsapay.banking.exception.BankAccountExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/banking")
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerMembershipUseCase;

    @PostMapping(path = "/bank-accounts")
    ResponseEntity<RegisteredBankAccount> registerBankAccount(@RequestBody RegisterBankAccountRequest request) {
        // 먼저 Request가 들어오고

        // request 를 command로 바꿔준다 (중간을 추상화) request가 수정됐을때 뒤에 인터페이스가 영향받지 않기 위해
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .isValid(request.isLinkedSatusIsValid())
                .build();

        RegisteredBankAccount registeredBankAccount = registerMembershipUseCase.registerBankAccount(command);
        if(registeredBankAccount == null) {
            throw new BankAccountException(BankAccountExceptionType.NULL_FAIL);
        }
        return ResponseEntity.ok(registeredBankAccount);
    }
}
