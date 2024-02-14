package com.example.mymsapay.banking.adapter.out.external.bank;

import com.example.mymsapay.ExternalSystemAdapter;
import com.example.mymsapay.banking.application.port.out.RequestBankAccountInfoPort;
import com.example.mymsapay.banking.application.port.out.RequestExternalFirmbankingPort;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmbankingPort {
    @Override
    public BankAccount getAccountInfo(GetBankAccountRequest request) {
        // 실제로 외부 은행에 http 을 통해서
        // 실제 은행 계좌 정보를 가져오고

        // 실제 은행 계좌 -> BankAccount 로 변환
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }

    @Override
    public FirmBankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request) {
        // request 활용 외부 은행이랑 통신을 해서 펌뱅킹 요청


        // 그 결과를 패캠 패이의 FirmBankingResult에 파싱
        return new FirmBankingResult("성공");
    }
}
