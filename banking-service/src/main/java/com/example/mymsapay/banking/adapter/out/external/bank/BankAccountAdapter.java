package com.example.mymsapay.banking.adapter.out.external.bank;

import com.example.mymsapay.ExternalSystemAdapter;
import com.example.mymsapay.banking.application.port.out.RequestBankAccountInfoPort;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort {
    @Override
    public BankAccount getAccountInfo(GetBankAccountRequest request) {
        // 실제로 외부 은행에 http 을 통해서
        // 실제 은행 계좌 정보를 가져오고

        // 실제 은행 계좌 -> BankAccount 로 변환
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }
}
